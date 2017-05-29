(function() {
    'use strict';

    angular
        .module('booka.friends.chat', [])
        .controller('ChatController', ChatController);

    ChatController.$inject = ['$q', '$scope', 'authorizationService', '$stateParams', 'Pubnub'];

    function ChatController($q, $scope, authorizationService, $stateParams, Pubnub) {
        var vm = this;

        var friendId = $stateParams.friendId;


        vm.isAuthorized = authorizationService.isAuthorized;
        vm.userData = authorizationService.getUserData;

        vm.friendId = friendId;
        vm.threadName = 'general';
        vm.messageContent = '';
        vm.messages = [];

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
                content : vm.messageContent,
                sender_uuid: vm.userData().id,
                date: new Date()
            };
            //vm.thread.$publish(message);

            Pubnub.publish({
                channel: vm.thread,
                message: message,
                callback: function (m) {
                    console.log(m)
                }
            });

            vm.messageContent = '';
        }

        function pubnubInit(userId) {
            Pubnub.init({
                publish_key: 'pub-c-ea5297f9-b2a3-43a3-ada8-4716635f7687',
                subscribe_key: 'sub-c-1e313a40-402b-11e7-b6a4-02ee2ddab7fe',
                uuid: userId
            });

            if (userId < friendId) {
                vm.threadName = userId.toString() + '_' + friendId.toString();
            } else {
                vm.threadName = friendId.toString() + '_' + userId.toString();
            }

            vm.thread = vm.threadName;

            Pubnub.subscribe({
                channel: vm.thread,
                triggerEvents: ['callback']
            });

            var deffered  = $q.defer();

            Pubnub.history({
                channel: vm.thread,
                count: 50,
                reverse: false,
                callback: function (m) {
                    $scope.$apply(function () {
                        console.log(m);
                        vm.messages = m[0] ;
                    });
                    deffered.resolve(m);
                    $scope.scrollDown(0);
                }
            });

            return deffered.promise;
        }

        // Make it possible to scrollDown to the bottom of the messages container
        $scope.scrollDown = function(time) {
            var $elem = $('.collection');
            $('#chat').animate({
                scrollTop: $elem.height()
            }, time);
        };

        // Listenning to messages sent.
        $scope.$on(Pubnub.getMessageEventNameFor(vm.thread), function(ngEvent, m) {
            $scope.$apply(function() {
                console.log('event - ', m);
                vm.messages.push(m)
            });
            $scope.scrollDown(400);
        });
    }
})();