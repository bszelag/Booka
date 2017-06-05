(function() {
    'use strict';

    angular
        .module('booka.login')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$state', 'authorizationService', '$location'];

    function LoginController($state, authorizationService, $location) {
        var vm = this;

        vm.signInForm = {};
        vm.signUpForm = {};

        vm.signIn = signIn;
        vm.signUp = signUp;
        vm.signInWithFacebook = signInWithFacebook;

        init();
        ///////////////

        function init() {
            vm.signInForm.login = "";
            vm.signInForm.password = "";

            vm.signUpForm.login = "";
            vm.signUpForm.password = "";
            vm.signUpForm.email = "";
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

        function signUp(isValid) {
            if(isValid) {
                var credentials = {};
                credentials.login = vm.signUpForm.login;
                credentials.password = vm.signUpForm.password;
                credentials.email = vm.signUpForm.email;

                authorizationService.signUp(credentials).then(() => {
                    $state.go('home');
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