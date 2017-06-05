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

        function addNewFriend() {
            friendsService.addNewFriend(vm.newFriend.login).then((response) => {
            }).finally(function () {
                $state.go('show-friends', {}, { reload: 'show-friends'});
            })

        }
    }
})();