package com.finzly.bookmanager.models;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BookRetrieveRequest {
    private final Long bookId;
    private final String bookName;
    @Builder.Default
    private final Integer page = 0;
    @Builder.Default
    private final Integer size = 10;
}
