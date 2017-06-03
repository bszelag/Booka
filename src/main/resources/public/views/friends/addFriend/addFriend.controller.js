(function() {
    'use strict';

    angular
        .module('booka.friends.addFriend')
        .controller('AddFriendController', AddFriendController);

    AddFriendController.$inject = ['$state', 'authorizationService',
        'friendsService'];

    function AddFriendController($state, authorizationService, friendsService) {
        var vm = this;

        vm.isAuthorized = authorizationService.isAuthorized;
        vm.userData = authorizationService.getUserData;
        vm.newFriend = {};

        vm.addNewFriend = addNewFriend;

        init();
        //////////////

        function init() {
            if(authorizationService.isAuthorized()) {
                authorizationService.getSessionUser().then((response) => {
                    authorizationService.setUserData(response.data);
                });
            }
        }

        function addNewFriend(friendId) {
            var contact = {};
            contact = {
                "friend" : {
                    "friend1" : {
                        "id" : vm.userData.id
                    },
                    "friend2" : {
                        "id" : friendId
                    }
                }
            };

            friendsService.addNewFriend(contact).then((response) => {
               console.log(response.status);
            });
        }

        function addFriend() {
            var friends = {};
            friendsService.getUsers().then((response) => {
                friends = response.data;
                friends.forEach(function (f) {
                    if (f.login == vm.newFriend.login) {
                        addNewFriend(f.id);
                    }
                });
            })
        }
    }
})();