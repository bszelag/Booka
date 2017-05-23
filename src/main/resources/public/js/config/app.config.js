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
                templateUrl: bookaPrefix + "books/showBooks/showBooks.view.html",
                controller: 'ShowBooksController',
                controllerAs: 'vm',
            })

            .state('book', {
                url: '/book',
                templateUrl: bookaPrefix + "books/bookDetails/bookDetails.view.html",
                controller: 'BookDetailsController',
                controllerAs: 'vm',
                params: {
                    bookId : null
                }
            })

            .state('lent', {
                url: '/lent',
                templateUrl: bookaPrefix + "books/lent/lentBooks.view.html",
                controller: 'LentBooksController',
                controllerAs: 'vm',
            })

            .state('drive', {
                url: '/drive',
                templateUrl: bookaPrefix + "gdrive/gdrive.view.html",
                controller: 'GoogleDriveController',
                controllerAs: 'vm'
            })

            .state('show-friends', {
                url: '/friends',
                templateUrl: bookaPrefix + "friends/showFriends/showFriends.view.html",
                controller: 'ShowFriendsController',
                controllerAs: 'vm'
            })

            .state('add-friend', {
                url: '/friends/add',
                templateUrl: bookaPrefix + "friends/addFriend/addFriend.view.html",
                controller: 'AddFriendController',
                controllerAs: 'vm'
            })

            .state('chat', {
                url: '/friends/chat',
                templateUrl: bookaPrefix + "friends/chat/chat.view.html",
                controller: 'ChatController',
                controllerAs: 'vm'
            })

            .state('account', {
                url: '/friends',
                templateUrl: bookaPrefix + "friends/showFriends/showFriends.view.html",
            })

            .state('institutions', {
                url: '/friends',
                templateUrl: bookaPrefix + "friends/showFriends/showFriends.view.html",
            })

            .state('search', {
                url: '/friends',
                templateUrl: bookaPrefix + "friends/showFriends/showFriends.view.html",
            })

            .state('error', {
                url: '/friends',
                templateUrl: bookaPrefix + "friends/showFriends/showFriends.view.html",
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
