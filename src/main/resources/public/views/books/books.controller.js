(function() {
    'use strict';

    angular
        .module('booka.books')
        .controller('BooksController', BooksController);

    BooksController.$inject = ['$scope', '$state', '$stateParams'];

    function BooksController($scope, $state, $stateParams) {
        var vm = this;

        vm.user = $stateParams.userId;
        console.log($stateParams.userId);

        $scope.state = $state.current;
        $scope.params = $stateParams;

        console.log($scope.params);
        //////////////
    }
})();