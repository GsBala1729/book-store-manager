package com.finzly.bookmanager.transformer;

import com.finzly.bookmanager.database.entity.Book;
import com.finzly.bookmanager.models.BookDetails;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface BookDataTransformer {
    Book createBookEntity(BookDetails bookDetails);

    BookDetails toBookDetails(Book book);

    Book toBookEntity(@MappingTarget Book bookFromDB, BookDetails bookDetails);
}
