(function() {
    'use strict';

    angular
        .module('booka.books.lentBooks')
        .controller('LentBooksController', LentBooksController);

    LentBooksController.$inject = ['authorizationService', 'booksService', 'NgTableParams', 'friendsService'];

    function LentBooksController(authorizationService, booksService, NgTableParams, friendsService) {
        var vm = this;

        vm.isAuthorized = authorizationService.isAuthorized;
        vm.userData = authorizationService.getUserData;
        vm.activeTab = 0;
        vm.lentBooksTable = [];
        vm.borrowedBooksTable = [];
        vm.lentActive = 0;
        vm.lentBookStructure = {};
        vm.books = {};
        vm.friends = {};

        vm.lentBook = lentBook;

        init();
        //////////////

        function init() {
            if(authorizationService.isAuthorized()) {
                authorizationService.getSessionUser().then((response) => {
                    authorizationService.setUserData(response.data);
                    getLentBooks();
                    getBorrowedBooks();
                    getBooks(response.data.id);
                    getFriends(response.data.id);
                });
            }
        }

        function getBooks(userId) {
            booksService.getBooks(userId).then((response) => {
                vm.books = response.data;
            }).catch((error) => {
                console.log(error);
            });
        }

        function getFriends(userId) {
            friendsService.getFriends().then((resp) => {
                var friends = resp.data;
                friends.forEach(function (f) {
                    if(f.friendId.friend1.id === userId) {
                        vm.friends[f.friendId.friend2.id] = {"id" : f.friendId.friend2.id, "login" :  f.friendId.friend2.login};
                    } else {
                        vm.friends[f.friendId.friend1.id] = {"id" : f.friendId.friend1.id, "login" :  f.friendId.friend1.login};
                    }
                });
            }).catch((err) => {
                console.log(err);
            })
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

        function getLentBooks() {
            booksService.getLentBooks().then((response) => {
                initLentBooksTable(response.data);
            }).catch((error) => {
                console.log(error);
            });
        }

        function getBorrowedBooks() {
            booksService.getBorrowedBooks().then((response) => {
                initBorrowedBooksTable(response.data);
            }).catch((error) => {
                console.log(error);
            });
        }

        function lentBook() {
            var borrowed =  {
                "book" : {
                    "id" : vm.lentBookStructure.book
                },
                "borrower": {
                    "id" : vm.lentBookStructure.friend
                }
            };
            booksService.lentBook(borrowed).then((response) => {
                console.log(response.status);
                vm.lentActive = 0;
            })
        }
    }
})();