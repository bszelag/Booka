(function() {
    angular
        .module('booka', ['ui.router',
                            'ngResource',
                            'ngCookies',
                            'chat',
                            'booka.books',
                            'booka.login',
                            'booka.navbarMenu',
                            'booka.gdrive',
                            'booka.institutions',
                            'booka.friends'
        ]);
})();