(function() {
    'use strict';

    angular
        .module('booka.books')
        .controller('BooksController', BooksController);

    BooksController.$inject = ['$state', 'authorizationService'];

    function BooksController($state, authorizationService) {
        var vm = this;

        vm.isAuthorized = authorizationService.isAuthorized;
        vm.userData = authorizationService.getUserData;

        init();
        //////////////

        function init() {
            if(authorizationService.isAuthorized()) {
                authorizationService.getSessionUser().then((response) => {
                    authorizationService.setUserData(response.data);
                });
            }
        }
    }
})();