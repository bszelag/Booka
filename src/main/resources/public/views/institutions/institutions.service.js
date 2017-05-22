(function() {
    'use strict';

    angular
        .module('booka.institutions')
        .service('institutionsService', institutionsService);

    institutionsService.$inject = ['$http', '$q'];

    function institutionsService($http, $q) {
        var service = {
            searchInLibrary : searchInLibrary,
            getDepartments : getDepartments
        };

        return service;
        ////////////////

        function searchInLibrary(query) {
            return $http.post('/api/v1/search/library/', query)
                .then(handleResponse())
                .catch(handleError())
        }

        function getDepartments() {
            return $http.get('/api/v1/departments/')
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