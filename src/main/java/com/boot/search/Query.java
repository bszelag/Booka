package com.boot.search;

import java.io.Serializable;

class Query implements Serializable {
    private String title;
    private String author;
    private Integer department;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getDepartment() {
        return department;
    }
}
