(function() {
    'use strict';

    angular
        .module('booka.login')
        .factory('authorizationService', authorizationService);

    authorizationService.$inject = ['$cookies', '$http', '$q'];

    function authorizationService($cookies, $http, $q) {
        var COOKIE_KEY = "session";

        var userData = {};

        var service = {
            isAuthorized: isAuthorized,
            signIn: signIn,
            signOut: signOut,
            setUserData: setUserData,
            getUserData: getUserData,
            getSessionUser: getSessionUser,
            getUserById: getUserById
        };

        return service;
        ////////////////

        function isAuthorized() {
            return !!$cookies.get(COOKIE_KEY);
        }

        function signIn(credentials) {
            return $http.post('/api/v1/users/sign_in', credentials)
                .then(handleResponse())
                .catch(handleError("Wrong username or password"));
        }

        function signOut() {
            return $http.post('/api/v1/users/sign_out')
                .then(handleResponse("Successfully logged out"))
                .catch(handleError("An error occurred while logging out"));
        }

        function setUserData(data) {
            userData = data;
        }

        function getUserData() {
            return userData;
        }

        function getSessionUser() {
            return $http.get('/api/v1/users/session')
                .then(handleResponse())
                .catch(handleError("An error occurred while getting user data"));
        }

        function getUserById(id) {
            return $http.get('/api/v1/users/' + id)
                .then(handleResponse())
                .catch(handleError("An error occurred while getting user with id " + id));
        }

        function handleResponse(msg) {
            return function(response) {
                if(msg) console.log(msg, response);
                return response;
            };
        }

        function handleError(msg) {
            return function(error) {
                if(msg) console.error(msg, error);
                return $q.reject(error);
            }
        }

    }
})();