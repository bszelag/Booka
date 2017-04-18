(function(){
    'use strict';

    angular
        .module('booka')
        .config(config);

    config.$inject = ['$routeProvider'];

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'resources/templates/main/main.html',
                controller: 'mainController',
                controllerAs: 'cnt'
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
