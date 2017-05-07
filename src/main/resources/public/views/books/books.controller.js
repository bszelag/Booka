(function() {
    'use strict';

    angular
        .module('booka.books')
        .controller('BooksController', BooksController);

    BooksController.$inject = ['showBooksService', 'NgTableParams'];

    function BooksController(showBooksService, NgTableParams) {
        var vm = this;
        
        vm.booksTable = {};
        vm.userId = 1;
        
        init();
        //////////////
        
        function init() {
            getBooks(vm.userId);
        }

        function initBooksTable(books) {
            var initialParameters = {
                count: 10
            };
            var settings = {
                data: books,
                counts: []
            };
            vm.booksTable = new NgTableParams(initialParameters, settings);
        }

        function getBooks(userId) {

            // TODO uncomment this part after successful implementation of /books API
            // showBooksService.getBooks(userId).then((response) => {
            //     initBooksTable(response.data);
            //     }).catch((error) => {
            //             console.log(error);
            //     });

            initBooksTable([{
                "title" : "Zamek", "author" : "Kafka", "status" : "Borrowed", "format" : "book"
            }, {
                "title" : "Ameryka", "author" : "Kafka", "status" : "Viable", "format" : "ebook"
            }]);
        }
    }
})();