(function() {
    'use strict';

    angular
        .module('booka.books.bookDetails')
        .controller('BookDetailsController', BookDetailsController);

    BookDetailsController.$inject = ['authorizationService', 'booksService', '$stateParams'];

    function BookDetailsController(authorizationService, booksService, $stateParams) {
        var vm = this;

        var bookId = $stateParams.bookId;

        vm.isAuthorized = authorizationService.isAuthorized;
        vm.userData = authorizationService.getUserData;
        vm.book = {};
        vm.tags = [];
        vm.newTags = [];

        vm.addTags = addTags;

        init();
        //////////////

        function init() {
            if(authorizationService.isAuthorized()) {
                authorizationService.getSessionUser().then((response) => {
                    authorizationService.setUserData(response.data);
                    getBook(bookId);
                });
            }
            vm.tags = ["tag1", "tag2", "tag3", "tag453543543"];
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
            console.log(vm.newTags);
        }
    }
})();