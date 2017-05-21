(function() {
    'use strict';

    angular
        .module('booka.books', ['ngTable',
                                'booka.books.showBooks',
                                'booka.books.bookDetails']);
})();