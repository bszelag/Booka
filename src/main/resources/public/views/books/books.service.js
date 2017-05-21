(function() {
    'use strict';

    angular
        .module('booka.books')
        .service('booksService', booksService);

    booksService.$inject = ['$http', '$q'];

    function booksService($http, $q) {
        var service = {
            getBooks : getBooks,
            getBook : getBook
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