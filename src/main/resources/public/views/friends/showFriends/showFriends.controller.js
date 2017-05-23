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
                        var isAuthorizedArray = authorized.filter(x => x.authorizedViewer.authorizedViewer.id === f.contact.contact2.id);
                        var isAuthorized;
                        isAuthorizedArray === [] ? isAuthorized=false : isAuthorized=true;
                        vm.friends[f.contact.contact2.id] = {"login" : f.contact.contact2.login,
                                                "name" : f.contact.contact2.name, "surname" : f.contact.contact2.surname,
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
    }
})();