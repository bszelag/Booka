(function() {
    'use strict';

    angular
        .module('booka.books')
        .service('booksService', booksService);

    booksService.$inject = ['$http', '$q'];

    function booksService($http, $q) {
        var service = {
            getBooks : getBooks,
            getBook : getBook,
            getLentBooks : getLentBooks,
            getBorrowedBooks : getBorrowedBooks,
            lentBook : lentBook,
            unlent : unlent,
            addBook : addBook,
            deleteBook : deleteBook
        };

        return service;
        ////////////////

        function getBooks(userId) {
            return $http.get('/api/v1/books/user/' + userId )
                .then(handleResponse())
                .catch(handleError())
        }

        function getBook(bookId) {
            return $http.get('/api/v1/books/' + bookId )
                .then(handleResponse())
                .catch(handleError())
        }

        function getLentBooks() {
            return $http.get('/api/v1/books/lend/user'  )
                .then(handleResponse())
                .catch(handleError())
        }

        function getBorrowedBooks() {
            return $http.get('/api/v1/books/borrowed/user' )
                .then(handleResponse())
                .catch(handleError())
        }

        function lentBook(borrowed) {
            return $http.post('/api/v1/books/lend', borrowed)
                .then(handleResponse())
                .catch(handleError())
        }

        function unlent(lentId) {
            var path = '/api/v1/books/lend/' + lentId;
            return $http.delete(path)
                .then(handleResponse())
                .catch(handleError())
        }

        function addBook(book) {
            return $http.post('/api/v1/books', book)
                .then(handleResponse())
                .catch(handleError())
        }

        function deleteBook(bookId) {
            var path = '/api/v1/books/' + bookId;
            return $http.delete(path)
                .then(handleResponse())
                .catch(handleError())
        }

        function handleResponse(msg) {
            return function(response) {
                if(msg) console.log(msg, response);
                return response;
            };
        }

        function handleError(msg) {
            return function(error) {
                if(msg) console.error(msg, error);
                return $q.reject(error);
            }
        }
    }
})();