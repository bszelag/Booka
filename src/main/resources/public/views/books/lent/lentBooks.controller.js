(function() {
    'use strict';

    angular
        .module('booka.books.lentBooks')
        .controller('LentBooksController', LentBooksController);

    LentBooksController.$inject = ['$location', '$scope', 'authorizationService', 'booksService', 'NgTableParams'];

    function LentBooksController($location, $scope, authorizationService, booksService, NgTableParams) {
        var vm = this;

        vm.isAuthorized = authorizationService.isAuthorized;
        vm.userData = authorizationService.getUserData;
        vm.activeTab = 0;
        vm.lentBooksTable = [];
        vm.borrowedBooksTable = [];
        vm.lentActive = 0;
        vm.lentBookStructure = {};

        vm.lentBook = lentBook;

        init();
        //////////////

        function init() {
            if(authorizationService.isAuthorized()) {
                authorizationService.getSessionUser().then((response) => {
                    authorizationService.setUserData(response.data);
                    getLentBooks(response.data.id);
                    getBorrowedBooks(response.data.id);
                });
            }
        }

        function initBorrowedBooksTable(books) {
            var initialParameters = {
                count: 10
            };
            var settings = {
                data: books,
                counts: []
            };
            vm.borrowedBooksTable = new NgTableParams(initialParameters, settings);
        }

        function initLentBooksTable(books) {
            var initialParameters = {
                count: 10
            };
            var settings = {
                data: books,
                counts: []
            };
            vm.lentBooksTable = new NgTableParams(initialParameters, settings);
        }

        function getLentBooks(userId) {
            booksService.getLentBooks(userId).then((response) => {
                initLentBooksTable(response.data);
            }).catch((error) => {
                console.log(error);
            });
        }

        function getBorrowedBooks(userId) {
            booksService.getBorrowedBooks(userId).then((response) => {
                initBorrowedBooksTable(response.data);
            }).catch((error) => {
                console.log(error);
            });
        }

        function lentBook() {
            booksService.lentBook(vm.lentBookStructure.book.id, vm.lentBookStructure.friend.id).then((response) => {
                console.log(response.status);
                vm.lentActive = 0;
            })
        }
    }
})();