package com.boot.search;

import org.springframework.stereotype.Service;

@Service
public interface SearchService {
    String searchQuery(String query, String department, String language, String format);
}
