(function(){
    'use strict';

    angular
        .module('booka')
        .config(config);

    config.$inject = ['$routeProvider'];

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'resources/templates/main/main.html'
            })

            .when('/books', {
                templateUrl: 'resources/templates/books/ShowBooks.html'
            })

            .when('/friends', {
                templateUrl: 'resources/templates/friends/friends.html'
            })

            .otherwise({
                redirectTo: '/'
            });
    }
})();
