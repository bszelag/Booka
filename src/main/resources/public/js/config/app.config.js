(function(){
    'use strict';

    angular
        .module('booka')
        .config(config);

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
