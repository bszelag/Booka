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

        public JsonWrapper(T inner, int field) {
            this.inner = inner;
            this.amount = field;
        }

        public T getInner() {
            return inner;
        }

        public int getAmount() {
            return amount;
        }
    }
}
