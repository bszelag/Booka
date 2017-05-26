(function() {
    'use strict';

    angular
        .module('booka.friends')
        .service('friendsService', friendsService);

    friendsService.$inject = ['$http', '$q'];

    function friendsService($http, $q) {
        var service = {
            getFriends : getFriends,
            getAuthorizedViewers : getAuthorizedViewers,
            addAccess : addAccess,
            addNewFriend : addNewFriend,
            createQueue : createQueue,
            getMessages : getMessages,
            getUsers : getUsers
        };

        return service;
        ////////////////

        function getFriends(userId) {
            return $http.get('/api/v1/friends/' + userId )
                .then(handleResponse())
                .catch(handleError())
        }

        function getAuthorizedViewers(userId) {
            return $http.get('/api/v1/friends/authorized-viewers/' + userId )
                .then(handleResponse())
                .catch(handleError())
        }
        
        function addAccess(authorizedViewer) {
            return $http.post('/api/v1/friends/authorized-viewers', authorizedViewer)
                .then(handleResponse())
                .catch(handleError())
        }

        function addNewFriend(friend) {
            return $http.post('/api/v1/friends', friend)
                .then(handleResponse())
                .catch(handleError())
        }

        function getUsers() {
            return $http.get('/api/v1/users')
                .then(handleResponse())
                .catch(handleError())
        }
        
        function createQueue() {
            
        }
        
        function getMessages() {
            
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