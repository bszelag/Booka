(function() {
    'use strict';

    angular
        .module('booka.friends.showFriends')
        .controller('ShowFriendsController', ShowFriendsController);

    ShowFriendsController.$inject = ['$state', 'authorizationService',
        'friendsService', 'NgTableParams'];

    function ShowFriendsController($state, authorizationService, friendsService) {
        var vm = this;

        vm.isAuthorized = authorizationService.isAuthorized;
        vm.userData = authorizationService.getUserData;
        vm.friends = {};

        vm.addFriend = addFriend;
        vm.addAccess = addAccess;
        vm.chat = chat;

        init();
        //////////////

        function init() {
            if(authorizationService.isAuthorized()) {
                authorizationService.getSessionUser().then((response) => {
                    authorizationService.setUserData(response.data);
                    getFriends(response.data.id);
                });
            }
        }

        function getFriends(userId) {
            friendsService.getFriends(userId).then((response) => {
                var friends = response.data;
                var authorized = [];
                friendsService.getAuthorizedViewers(userId).then((r) => {
                    authorized = r.data;
                    friends.forEach(function (f) {
                        var friend;
                        f.contact.contact2.id !== userId ? friend = f.contact.contact2 : friend = f.contact.contact1;
                        var isAuthorized = checkIfAuthorized(authorized, friend.id);
                        vm.friends[friend.id] = {"login" : friend.login,
                                                "name" : friend.name, "surname" : friend.surname,
                                                "authorized" : isAuthorized};
                    });
                }).catch((error) => {
                    console.log(error);
                });
                console.log(friends);
            }).catch((error) => {
                console.log(error);
            });
        }

        function addFriend() {
            $state.go('add-friend');
        }

        function addAccess(friendId) {
            var authorizedViewer = {
                "authorizedViewer" : {
                    "owner" : {
                        "id" : vm.userData().id
                    },
                    "authorizedViewer" : {
                        "id" : friendId
                    }
                }
            };
            friendsService.addAccess(authorizedViewer).then((response) => {
                console.log(response.data);
                $state.reload();
            });
        }

        function checkIfAuthorized(authorized, friend) {
            var hit = false;
            authorized.forEach(function (a) {
                if (a.authorizedViewer.authorizedViewer.id === friend) {
                    hit = true;
                }
            });
            return hit;
        }

        function chat(friendId) {
            $state.go('chat', {friendId: friendId});
        }
    }
})();