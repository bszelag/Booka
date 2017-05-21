// Resource: https://github.com/sashee/drive-api-from-js/blob/master/gapi-auth-service.js

'use strict';

angular.module('booka.gdrive')
    .factory('gapiAuthService', function ($q) {
        var client_id="815651977839-9ceobh09h7bgucdej3o2jkgd4m2r0tv3.apps.googleusercontent.com";
        var scope=['https://www.googleapis.com/auth/drive.file'];

        function getConfig(immediate){
            return {
                'client_id': client_id,
                'scope': scope,
                immediate: immediate
            }
        }

        function isTokenNeedsRefresh(token){
            return !token || moment.duration(moment(token.expires_at*1000).valueOf()-moment().valueOf()).minutes()<10;
        }

        return {
            checkLogin:function(){
                var deferred = $q.defer();

                gapiCallbacks.push(function () {
                    if (isTokenNeedsRefresh(gapi.auth.getToken())) {
                        gapi.auth.authorize(getConfig(true), function (authResult) {
                            if (authResult && !authResult.error) {
                                deferred.resolve(gapi.auth.getToken().access_token);
                            } else {
                                deferred.reject(authResult);
                            }
                        })
                    } else {
                        deferred.resolve(gapi.auth.getToken().access_token);
                    }
                });

                return deferred.promise;
            },

            login:function() {
                var deferred = $q.defer();

                this.checkLogin().then(function (accessToken) {
                    deferred.resolve(accessToken);
                }, function () {
                    gapi.auth.authorize(getConfig(false), function (authResult) {
                        if (authResult && !authResult.error) {
                            deferred.resolve(gapi.auth.getToken().access_token);
                        } else {
                            deferred.reject(authResult);
                        }
                    })
                });

                return deferred.promise;
            }
        }


    });