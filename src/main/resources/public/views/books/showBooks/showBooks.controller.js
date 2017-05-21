(function() {
    'use strict';

    angular
        .module('booka.books.showBooks')
        .controller('ShowBooksController', ShowBooksController);

    ShowBooksController.$inject = ['$state', 'authorizationService',
        'booksService', 'NgTableParams'];

    function ShowBooksController($state, authorizationService, booksService,
                             NgTableParams) {
        var vm = this;

        vm.isAuthorized = authorizationService.isAuthorized;
        vm.userData = authorizationService.getUserData;
        vm.booksTable = {};

        vm.showBookDetails = showBookDetails;
        vm.showLentBooks = showLentBooks;

        init();
        //////////////

        function init() {
            if(authorizationService.isAuthorized()) {
                authorizationService.getSessionUser().then((response) => {
                    authorizationService.setUserData(response.data);
                    getBooks(response.data.id);
                });
            }
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
            booksService.getBooks(userId).then((response) => {
                initBooksTable(response.data);
            }).catch((error) => {
                        console.log(error);
            });
        }

        function showBookDetails(bookId) {
            $state.go('book', {bookId: bookId});
        }

        function showLentBooks() {
            $state.go('lent');
        }
    }
})();