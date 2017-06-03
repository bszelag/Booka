(function() {
    'use strict';

    angular
        .module('booka.friends.chat', [])
        .controller('ChatController', ChatController);

    ChatController.$inject = ['$scope', 'authorizationService', '$stateParams', 'Pubnub'];

    function ChatController($scope, authorizationService, $stateParams, Pubnub) {
        var vm = this;

        var friendId = $stateParams.friendId;


        vm.isAuthorized = authorizationService.isAuthorized;
        vm.userData = authorizationService.getUserData;

        vm.friendId = friendId;
        vm.threadName = 'general';
        vm.messageContent = '';
        $scope.messages = [];

        vm.sendMessage = sendMessage;

        init();
        //////////////

        function init() {
            if(authorizationService.isAuthorized()) {
                authorizationService.getSessionUser().then((response) => {
                    authorizationService.setUserData(response.data);
                    pubnubInit(response.data.id);
                });
            }

        }

        function sendMessage() {
            if (!vm.messageContent || vm.messageContent === '')
                return;
            var message = {
                uuid: Date.now() + vm.userData().id,
                content : vm.messageContent,
                sender_uuid: vm.userData().id,
                date: new Date()
            };

            Pubnub.publish({
                channel: $scope.thread,
                message: message,
                callback: function (m) {
                }
            });

            vm.messageContent = '';
        }

        function pubnubInit(userId) {
            Pubnub.init({
                publish_key: 'pub-c-ea5297f9-b2a3-43a3-ada8-4716635f7687',
                subscribe_key: 'sub-c-1e313a40-402b-11e7-b6a4-02ee2ddab7fe',
                uuid: userId,
                ssl: true
            });

            if (userId < friendId) {
                vm.threadName = userId.toString() + '_' + friendId.toString();
            } else {
                vm.threadName = friendId.toString() + '_' + userId.toString();
            }

            $scope.thread = vm.threadName;

            Pubnub.subscribe({
                channel: $scope.thread,
                triggerEvents: ['callback']
            });

            Pubnub.history({
                channel: $scope.thread,
                count: 20,
                reverse: false,
                callback: function (m) {
                    $scope.$apply(function () {
                        $scope.messages = m[0] ;
                    });
                    $scope.$digest();
                    $scope.scrollDown(0);
                }
            });

            subscribeNewMessage(function (ngEvent, m) {
                $scope.messages.push(m);
                $scope.$digest();
                $scope.scrollDown(100);
            })
        }

        var subscribeNewMessage = function (callback) {
            $scope.$on(Pubnub.getMessageEventNameFor($scope.thread), callback);
            $scope.scrollDown(100);
        };

        $scope.scrollDown = function (time) {
            var $elem = $('#messages');
            $('#messages').animate({
                scrollTop: $elem.height()
            }, time);
        };
    }
})();
