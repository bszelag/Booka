(function() {
    'use strict';

    angular
        .module('booka.navbarMenu')
        .controller('NavbarMenuController', NavbarMenuController);

    NavbarMenuController.$inject = ['authorizationService', '$state'];

    function NavbarMenuController(authorizationService, $state) {
        var vm = this;

        vm.isAuthorized = authorizationService.isAuthorized;
        vm.userData = {"id" : 11}; //authorizationService.getUserData;
        vm.goToLoginPage = goToLoginPage;
        vm.signOut = signOut;

        init();
        //////////////

        function init() {
            /*if(authorizationService.isAuthorized()) {
                authorizationService.getSessionUser().then((response) => {
                    authorizationService.setUserData(response.data);
                });
            }*/
            console.log(vm.isAuthorized);
        }

        function goToLoginPage() {
            $state.go('/login');
        }

        function signOut() {
            authorizationService.signOut().then(() => {
                authorizationService.setUserData({});
            });
        }
    }
})();