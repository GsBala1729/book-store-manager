package com.finzly.bookmanager.controller;

import com.finzly.bookmanager.models.BookDetails;
import com.finzly.bookmanager.models.BookRetrieveRequest;
import com.finzly.bookmanager.service.IBookManagerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "book/")
@RequiredArgsConstructor
public class BookManagerController {
    private final IBookManagerService bookManagerService;

    @GetMapping
    public ResponseEntity<Page<BookDetails>> getBooks(@RequestParam(value = "book_id", required = false) final Long bookId,
                                                      @RequestParam(value = "book_name", required = false) final String bookName) {
        return ResponseEntity.ok().body(bookManagerService.queryBooks(BookRetrieveRequest.builder()
                        .bookId(bookId).bookName(bookName).build()));
    }

    @PostMapping
    public ResponseEntity createBook(@RequestBody @NonNull final BookDetails bookDetails) {
        bookManagerService.createBook(bookDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body("Book details Created in Db");
    }

    @PutMapping
    public ResponseEntity updatedBook(@RequestBody @NonNull final BookDetails bookDetails) {
        bookManagerService.updateBook(bookDetails);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Book details Updated in Db");
    }
}
