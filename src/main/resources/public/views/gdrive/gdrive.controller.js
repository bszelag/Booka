// Resource: https://github.com/sashee/drive-api-from-js/

(function() {
    'use strict';

    angular
        .module('booka.gdrive')
        .controller('GoogleDriveController', GoogleDriveController);

    GoogleDriveController.$inject = ['$scope', 'GoogleDriveService', 'gapiAuthService'];

    function GoogleDriveController($scope, GoogleDriveService, gapiAuthService) {
        var vm = this;

        $scope.checkingLogin=true;
        gapiAuthService.checkLogin().then(function(){
            $scope.loggedIn=true;
        },function(){
            $scope.loggedIn=false;
        }).finally(function(){
            $scope.checkingLogin=false;
        });

        $scope.login=function(){
            gapiAuthService.login().then(function(){
                $scope.loggedIn=true;
            },function(authResult){
                $scope.loggedIn = false;
                console.err(authResult);
            })
        };

        $scope.files=[];

        $scope.clickFileUpload=function(){
            document.getElementById('uploadFile').click();
        };

        $scope.upload=function(){
            $scope.uploading=true;
            var allFiles=document.getElementById('uploadFile').files;
            var file=allFiles[0];

            console.log(file);

            GoogleDriveService.insertFile(file, file.name).then(function(link){
                $scope.files.push(link);
            }).finally(function(){
                $scope.uploading=false;
            });
        };

        vm.files = [];

        // TODO get list of files from google drive
        //vm.showFiles = showFiles;

        init();
        //////////////

        function init() {
        }

        // TODO get list of files from google drive
        // function showFiles() {
        //     GoogleDriveService.getFiles().then(function (response) {
        //         vm.files = response.result.files;
        //         console.log(response.result);
        //     })
        // }

    }
})();