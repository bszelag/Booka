(function() {
    'use strict';

    angular
        .module('booka.friends', ['ngTable',
            'booka.friends.showFriends',
            'booka.friends.chat'
        ]);
})();