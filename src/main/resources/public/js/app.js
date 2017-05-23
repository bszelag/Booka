(function() {
    angular
        .module('booka', ['ui.router',
                            'ngResource',
                            'ngCookies',
                            'booka.books',
                            'booka.login',
                            'booka.navbarMenu',
                            'booka.gdrive',
                            'booka.institutions'
        ]);
})();