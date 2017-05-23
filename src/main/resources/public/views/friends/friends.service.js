(function() {
    'use strict';

    angular
        .module('booka.friends')
        .service('friendsService', friendsService);

    friendsService.$inject = ['$http', '$q'];

    function friendsService($http, $q) {
        var service = {
            getFriends : getFriends,
            getAuthorizedViewers : getAuthorizedViewers
        };

        return service;
        ////////////////

        function getFriends(userId) {
            return $http.get('/api/v1/friends' + userId )
                .then(handleResponse())
                .catch(handleError())
        }

        function getAuthorizedViewers(userId) {
            return $http.get('/api/v1/friends/authorized-viewers/' + userId )
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