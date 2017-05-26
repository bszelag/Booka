(function() {
    'use strict';

    angular
        .module('chat')
        .constant('config', {
            rltm: {
                service: "pubnub",
                config: {
                    publishKey: "pub-c-ea5297f9-b2a3-43a3-ada8-4716635f7687",
                    subscribeKey: "sub-c-1e313a40-402b-11e7-b6a4-02ee2ddab7fe"
                }
            }
        });

    angular
        .module('booka.friends.chat', [])
        .controller('chat', ChatController);

    ChatController.$inject = ['$scope', 'authorizationService', '$stateParams', 'Messages'];

    function ChatController($scope, authorizationService, $stateParams, Messages) {
        var vm = this;

        var friendId = $stateParams.friendId;

        vm.isAuthorized = authorizationService.isAuthorized;
        vm.userData = authorizationService.getUserData;

        vm.friendId = friendId;

        $scope.messages = [];

        // Receive Messages
        Messages.receive(function(message) {
            $scope.messages.push(message);
        });

        // Send Messages
        $scope.send = function() {
            Messages.send({
                to: friendId,
                data: $scope.textbox,
                user: Messages.user()
            });
        };

        init();
        //////////////

        function init() {
            if(authorizationService.isAuthorized()) {
                authorizationService.getSessionUser().then((response) => {
                    authorizationService.setUserData(response.data);
                    Messages.user({id: response.data.id, name : response.data.login});
                });
            }
        }
    }
})();