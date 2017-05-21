(function() {
    'use strict';

    angular
        .module('booka.books.bookDetails')
        .controller('BookDetailsController', BookDetailsController);

    BookDetailsController.$inject = ['$location', '$scope', 'authorizationService', 'booksService', '$stateParams'];

    function BookDetailsController($location, $scope, authorizationService, booksService, $stateParams) {
        var vm = this;

        var bookId = $stateParams.bookId;

        vm.isAuthorized = authorizationService.isAuthorized;
        vm.userData = authorizationService.getUserData;
        vm.book = {};

        vm.openPdf = openPdf;

        init();
        //////////////

        function init() {
            if(authorizationService.isAuthorized()) {
                authorizationService.getSessionUser().then((response) => {
                    authorizationService.setUserData(response.data);
                    getBook(bookId);
                });
            }
        }

        function getBook(bookId) {
            booksService.getBook(bookId).then((response) => {
                vm.book = response.data;
                console.log(response.data);
            }).catch((error) => {
                console.log(error);
            });
        }

        function openPdf() {
            window.location.href = vm.book.path;
        }
    }
})();