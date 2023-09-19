package com.finzly.bookmanager.service;

import com.finzly.bookmanager.database.entity.Book;
import com.finzly.bookmanager.database.repo.BookRepository;
import com.finzly.bookmanager.exceptions.NoBooksFoundException;
import com.finzly.bookmanager.models.BookDetails;
import com.finzly.bookmanager.models.BookRetrieveRequest;
import com.finzly.bookmanager.transformer.BookDataTransformer;
import jakarta.persistence.criteria.Predicate;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class BookManagerServiceImpl implements IBookManagerService {
    private final BookRepository bookRepository;
    private final BookDataTransformer bookDataTransformer;

    @Override
    public Page<BookDetails> queryBooks(BookRetrieveRequest bookRetrieveRequest) {
        Page<Book> books = bookRepository.findAll(createSpecification(bookRetrieveRequest),
                PageRequest.of(bookRetrieveRequest.getPage(), bookRetrieveRequest.getSize()));
        if(Objects.isNull(books)){
            throw new NoBooksFoundException("No Books are available");
        }
        return books.map(bookDataTransformer::toBookDetails);
    }

    @Override
    public void createBook(BookDetails bookDetails) {
        final Book bookEntity = bookDataTransformer.createBookEntity(bookDetails);
        if (Objects.isNull(bookEntity)) {
            log.info("Book Entity Cannot Be Null");
            throw new IllegalArgumentException("Book Entity Cannot Be Null");
        }
        bookRepository.save(bookEntity);
    }

    @Override
    public void updateBook(BookDetails bookDetails) {
        if (Objects.isNull(bookDetails) || Objects.isNull(bookDetails.getId())) {
            log.info("Book Entity Or Book Id Cannot Be Null");
            throw new IllegalArgumentException("Book Entity Or Book Id Cannot Be Null");
        }
        final Book bookFromDB = bookRepository.findById(bookDetails.getId())
                .orElseThrow(() -> new IllegalArgumentException("Matching Book Details Not found in DB"));
        bookDataTransformer.toBookEntity(bookFromDB, bookDetails);
        bookRepository.save(bookFromDB);
    }

    Specification<Book> createSpecification(@NonNull final BookRetrieveRequest bookRetrieveRequest) {
        return (root, query, criteriaBuilder) -> {
            Predicate bookPredicate = criteriaBuilder.conjunction();
            return bookPredicate;
        };
    }
}
