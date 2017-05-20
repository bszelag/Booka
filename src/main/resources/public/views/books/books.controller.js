(function() {
    'use strict';

    angular
        .module('booka.books')
        .controller('BooksController', BooksController);

    BooksController.$inject = ['$state', 'authorizationService',
        'showBooksService', 'NgTableParams'];

    function BooksController($state, authorizationService, showBooksService,
                             NgTableParams) {
        var vm = this;

        vm.isAuthorized = authorizationService.isAuthorized;
        vm.userData = authorizationService.getUserData;
        vm.booksTable = {};

        init();
        //////////////

        function init() {
            if(authorizationService.isAuthorized()) {
                authorizationService.getSessionUser().then((response) => {
                    authorizationService.setUserData(response.data);
                    getBooks(vm.userData.id);
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
            showBooksService.getBooks(userId).then((response) => {
                initBooksTable(response.data);
            }).catch((error) => {
                        console.log(error);
            });
        }
    }
})();