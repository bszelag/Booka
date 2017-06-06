(function() {
    'use strict';

    angular
        .module('booka.books.addBook')
        .controller('AddBookController', AddBookController);

    AddBookController.$inject = ['$state', 'authorizationService', 'booksService'];

    function AddBookController($state, authorizationService, booksService) {
        var vm = this;

        vm.isAuthorized = authorizationService.isAuthorized;
        vm.userData = authorizationService.getUserData;
        vm.newBook = {};

        vm.addNewBook = addBook;

        init();
        //////////////

        function init() {
            if(authorizationService.isAuthorized()) {
                authorizationService.getSessionUser().then((response) => {
                    authorizationService.setUserData(response.data);
                    vm.newBook = {
                        "title" : "",
                        "author" : "",
                        "format" : "",
                        "path" : ""
                    };
                    vm.formats = [{
                        "id" : 'e',
                        "text" : 'ebook'
                    }, {
                        "id" : 'b',
                        "text" : "book"
                    }];
                });
            }
        }

        function addBook() {
            console.log(vm.newBook);
            booksService.addBook(vm.newBook).then((response) => {
            }).catch((error) => {
                console.log(error);
            }).finally(function () {
                $state.go('books');
            });
        }

    }
})();