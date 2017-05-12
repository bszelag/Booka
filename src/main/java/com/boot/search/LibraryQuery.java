package com.boot.search;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class LibraryQuery implements Serializable {
    private String title;
    private String author;
    private Integer department;
}
