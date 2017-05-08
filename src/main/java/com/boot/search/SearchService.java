package com.boot.search;

import org.springframework.stereotype.Service;

@Service
public interface SearchService {
    String searchQuery(String title, String author, Integer department);
}
