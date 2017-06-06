(function() {
    'use strict';

    angular
        .module('booka.books.bookDetails')
        .controller('BookDetailsController', BookDetailsController);

    BookDetailsController.$inject = ['$state', 'authorizationService', 'booksService', '$stateParams'];

    function BookDetailsController($state, authorizationService, booksService, $stateParams) {
        var vm = this;

        var bookId = $stateParams.bookId;

        vm.isAuthorized = authorizationService.isAuthorized;
        vm.userData = authorizationService.getUserData;
        vm.book = {};
        vm.tags = [];
        vm.newTag = null;

        vm.addTags = addTags;

        init();
        //////////////

        function init() {
            if(authorizationService.isAuthorized()) {
                authorizationService.getSessionUser().then((response) => {
                    authorizationService.setUserData(response.data);
                    getBook(bookId);
                    getTags();
                });
            }
        }

        function getTags() {
            booksService.getTags().then((response) => {
                vm.tags = response.data;
                console.log(response.data);
            }).catch((error) => {
                console.log(error);
            });
        }

        function getBook(bookId) {
            booksService.getBook(bookId).then((response) => {
                vm.book = response.data;
                console.log(response.data);
            }).catch((error) => {
                console.log(error);
            });
        }

        function addTags() {
            console.log(vm.newTag);
            var tag = {
                tag : vm.newTag,
                book : bookId
            };
            booksService.addTag(tag).then((response) => {
                console.log(response.data);
                $state.reload();
            }).catch((error) => {
                console.log(error);
            });
        }
    }
})();