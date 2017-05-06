(function(){
    'use strict';

    angular
        .module('booka')
        .config(config);

    // // function for dynamic load with requirejs of a javascript module for use with a view
    // // in the state definition call add property `resolve: req('/views/ui.js')`
    // // or `resolve: req(['/views/ui.js'])`
    // // or `resolve: req('views/ui')`
    // function req(deps) {
    //     if (typeof deps === 'string') deps = [deps];
    //     return {
    //         deps: function ($q, $rootScope) {
    //             var deferred = $q.defer();
    //             require(deps, function() {
    //                 $rootScope.$apply(function () {
    //                     deferred.resolve();
    //                 });
    //                 deferred.resolve();
    //             });
    //             return deferred.promise;
    //         }
    //     }
    // }

    config.$inject = ['$stateProvider', '$urlRouterProvider'];

    function config($stateProvider, $urlRouterProvider) {

        var bookaPrefix = 'views/';

        $urlRouterProvider.otherwise("/");

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: bookaPrefix + "main/main.html"
            })

            .state('books', {
                url: '/books',
                templateUrl: bookaPrefix + "books/books.view.html",
                controller: 'BooksController',
                controllerAs: 'vm'
            })

            .state('friends', {
                url: '/friends',
                templateUrl: bookaPrefix + "friends/friends.html"
            })

            .state('account', {
                url: '/friends',
                templateUrl: bookaPrefix + "friends/friends.html"
            })

            .state('institutions', {
                url: '/friends',
                templateUrl: bookaPrefix + "friends/friends.html"
            })

            .state('messages', {
                url: '/friends',
                templateUrl: bookaPrefix + "friends/friends.html"
            })

            .state('search', {
                url: '/friends',
                templateUrl: bookaPrefix + "friends/friends.html"
            })
    }
})();
