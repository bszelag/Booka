(function() {
    'use strict';

    angular
        .module('booka.books', ['ngTable',
                                'booka.books.showBooks',
                                'booka.books.lentBooks',
                                'booka.books.bookDetails',
                                'booka.books.addBook']);
})();