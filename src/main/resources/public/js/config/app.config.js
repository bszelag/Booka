(function(){
    'use strict';

    angular
        .module('booka')
        .config(config);

    config.$inject = ['$stateProvider', '$urlRouterProvider', '$locationProvider'];

    function config($stateProvider, $urlRouterProvider, $locationProvider) {

        $locationProvider.html5Mode(true);
        $locationProvider.hashPrefix('');

        var bookaPrefix = 'views/';

        $urlRouterProvider.otherwise("/");

        $stateProvider
            .state('home', {
                url: '/',
                templateUrl: bookaPrefix + "main/main.html"
            })

            .state('login', {
                url: "/login",
                templateUrl: bookaPrefix + "login/login.view.html",
                controller: 'LoginController',
                controllerAs: 'vm'
            })

            .state('books', {
                url: '/books',
                templateUrl: bookaPrefix + "books/books.view.html",
                controller: 'BooksController',
                controllerAs: 'vm',
                params: {
                    userId : {
                        value: null
                    },
                    hiddenParam: 'YES'
                }
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

            .state('error', {
                url: '/friends',
                templateUrl: bookaPrefix + "friends/friends.html"
            });

        function getCurrentUser($state, authorizationService) {
            var userId = $state.params.user;
            if(userId) {
                return authorizationService.getUserById(userId).then((response) => {
                    return response.data;
                }).catch((error) => {
                    $state.go('error');
                });
            }
            else {
                return authorizationService.getUserData();
            }
        }
    }
})();
