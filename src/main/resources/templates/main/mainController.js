angular.module('mainController', []).controller('mainController', [$scope, function($scope) {
       $scope.signed = false;
       $scope.singIn = function() {
           console.log("sign in");
           $scope.signed = true;
       }
}]);