package com.finzly.bookmanager.service;

import com.finzly.bookmanager.models.BookDetails;
import com.finzly.bookmanager.models.BookRetrieveRequest;
import org.springframework.data.domain.Page;

public interface IBookManagerService {
    Page<BookDetails> queryBooks(BookRetrieveRequest bookRetrieveRequest);

    void createBook(BookDetails bookDetails);
    void updateBook(BookDetails bookDetails);
}
