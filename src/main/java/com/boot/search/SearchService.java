package com.boot.search;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface SearchService {

    String searchQuery(LibraryQuery query);
    Collection<Object> searchBook(String URL);

    class JsonWrapper<T> {

        @JsonUnwrapped
        private T inner;
        private int amount;
        private String mapURL;

        public JsonWrapper(T inner, int field, String mapURL) {
            this.inner = inner;
            this.amount = field;
            this.mapURL = mapURL;
        }

        public T getInner() {
            return inner;
        }

        public int getAmount() {
            return amount;
        }

        public String getMapURL() {
            return mapURL;
        }
    }
}
