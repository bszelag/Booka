(function() {
    'use strict';

    angular
        .module('booka.friends')
        .service('friendsService', friendsService);

    friendsService.$inject = ['$http', '$q'];

    function friendsService($http, $q) {
        var service = {
            getFriends : getFriends,
            addAccess : addAccess,
            addNewFriend : addNewFriend,
            getUsers : getUsers
        };

        return service;
        ////////////////

        function getFriends() {
            return $http.get('/api/v1/friends/')
                .then(handleResponse())
                .catch(handleError())
        }
        
        function addAccess(friendId) {
            var path = '/api/v1/friends/changeAuthorizedState/' + friendId.toString();
            return $http.post(path)
                .then(handleResponse())
                .catch(handleError())
        }

        function addNewFriend(friendId) {
            var path = '/api/v1/friends/' + friendId;
            return $http.post(path)
                .then(handleResponse())
                .catch(handleError())
        }

        function getUsers() {
            return $http.get('/api/v1/users')
                .then(handleResponse())
                .catch(handleError())
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