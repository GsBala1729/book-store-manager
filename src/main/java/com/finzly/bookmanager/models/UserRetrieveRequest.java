package com.finzly.bookmanager.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRetrieveRequest {
    private Long id;
    private String userName;
    @Builder.Default
    private final Integer page = 0;
    @Builder.Default
    private final Integer size = 10;
}


