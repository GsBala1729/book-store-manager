package com.finzly.bookmanager.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookDetails {
    private Long id;
    private String bookCode;
    private String bookName;
    private String bookDesc;
}
