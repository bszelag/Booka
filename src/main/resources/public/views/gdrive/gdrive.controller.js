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
            showFiles();
        };

        vm.files = [];

        vm.showFiles = showFiles;

        init();
        //////////////

        function init() {
        }

        function showFiles() {
            getFiles();
        }

        function getFiles() {
            GoogleDriveService.getFiles().then((response) => {
                listFiles(response.result.items);
            },function(){
                setTimeout(function(){
                    GoogleDriveService.getFiles().then((response) => {
                        listFiles(response.result.items)
                    })
                },5000);
            });
        }

        function listFiles(files) {
            vm.files = [];
            files.forEach(function (f) {
                vm.files.push(f);
            });
        }
    }
})();