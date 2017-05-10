(function() {
    'use strict';

    angular
        .module('booka.login')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['authorizationService', '$location'];

    function LoginController(authorizationService, $location) {
        var vm = this;

        vm.signin = {};

        vm.login = login;

        init();
        ///////////////

        function init() {
            vm.signin.email = "";
            vm.signin.password = "";
        }

        function login(isValid) {
            if(isValid) {
                var credentials = {};
                credentials.login = vm.signin.email;
                credentials.password = vm.signin.password;

                authorizationService.login(credentials).then(() => {
                    authorizationService.getSessionUser().then((response) => {
                        authorizationService.setUserData(response.data);
                        $location.path('/');
                    });
                });
            }
        }
    }
})();