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
            friendsService.getFriends().then((response) => {
                var friends = response.data;
                friends.forEach(function (f) {
                    var friend;
                    var isFriendAuthorized, isUserAuthorized;
                    if (f.friendId.friend2.id === userId) {
                        friend = f.friendId.friend1;
                        isFriendAuthorized = f.friend2Allow;
                        isUserAuthorized = f.friend1Allow;
                    } else {
                        friend = f.friendId.friend2;
                        isFriendAuthorized = f.friend1Allow;
                        isUserAuthorized = f.friend2Allow;
                    }
                    vm.friends[friend.id] = {"login" : friend.login,
                        "name" : friend.name, "surname" : friend.surname,
                        "isUserAuthorized" : isUserAuthorized, "isFriendAuthorized" : isFriendAuthorized};
                });
                }).catch((error) => {
                    console.log(error);
                });
        }

        function addAccess(friendId) {
            friendsService.addAccess(friendId).then((response) => {
                $state.reload();
            });
        }

        function addFriend() {
            $state.go('add-friend');
        }

        function chat(friendId) {
            $state.go('chat', {friendId: friendId});
        }
    }
})();