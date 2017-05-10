package com.boot.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping(value = "/api/v1//search")
public class SearchController {

    @Autowired
    private SearchService searchService;


    @RequestMapping(method = RequestMethod.POST)
    public String searchQuery(@RequestBody Query query){
        return searchService.searchQuery(query.getTitle(), query.getAuthor(), query.getDepartment());
    }
}
