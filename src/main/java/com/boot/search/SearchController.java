package com.boot.search;

import com.boot.book.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/v1/search")
public class SearchController {

    @Autowired
    private SearchService searchService;


    @RequestMapping(method = RequestMethod.POST)
    public String searchQuery(@RequestBody LibraryQuery query){
        return searchService.searchQuery(query);
    }

    @RequestMapping(value = "books", method = RequestMethod.POST)
    public Collection<Book> searchBook(@RequestBody LibraryQuery query){
        return searchService.searchBook(searchService.searchQuery(query));
    }
}
