package com.boot.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1//search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = {"{query}-{department}-{language}-{format}"}, method = RequestMethod.GET)
    public String searchQuery(@PathVariable String query, @PathVariable Integer department, @PathVariable String language,@PathVariable String format){
        return searchService.searchQuery(query, department, language, format);
    }
}
