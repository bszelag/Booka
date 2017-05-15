(function() {
    'use strict';

    angular
        .module('booka.login')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['authorizationService', '$location'];

    function LoginController(authorizationService, $location) {
        var vm = this;

        vm.signInForm = {};

        vm.signIn = signIn;
        vm.signInWithFacebook = signInWithFacebook;

        init();
        ///////////////

        function init() {
            vm.signInForm.login = "";
            vm.signInForm.password = "";
        }

        function signIn(isValid) {
            if(isValid) {
                var credentials = {};
                credentials.login = vm.signInForm.login;
                credentials.password = vm.signInForm.password;

                authorizationService.signIn(credentials).then(() => {
                    authorizationService.getSessionUser().then((response) => {
                        authorizationService.setUserData(response.data);
                        $location.path('/');
                    });
                });
            }
        }

        function signInWithFacebook(isValid) {
            if (isValid) {
                console.log("Facebook: Not implemented yet");
            }
        }
    }
})();