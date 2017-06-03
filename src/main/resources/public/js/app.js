(function() {
    angular
        .module('booka', ['ui.router',
                            'ngResource',
                            'ngCookies',
                            'pubnub.angular.service',
                            'ngNotify',
                            'booka.books',
                            'booka.login',
                            'booka.navbarMenu',
                            'booka.gdrive',
                            'booka.institutions',
                            'booka.friends'
        ]);
})();