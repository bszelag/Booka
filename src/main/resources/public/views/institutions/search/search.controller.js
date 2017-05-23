(function() {
    'use strict';

    angular
        .module('booka.institutions.search')
        .controller('InstitutionsSearchController', InstitutionsSearchController);

    InstitutionsSearchController.$inject = ['$location', '$scope', 'authorizationService', 'institutionsService', 'NgTableParams'];

    function InstitutionsSearchController($location, $scope, authorizationService, institutionsService, NgTableParams) {
        var vm = this;

        vm.isAuthorized = authorizationService.isAuthorized;
        vm.userData = authorizationService.getUserData;
        vm.searchQuery= {};
        vm.searchResults= [];
        vm.departments = [];
        vm.responseAvailable = false;
        vm.resultsTable = {};
        vm.filterDepartments = {};

        vm.institutionsSearch = institutionsSearch;

        init();
        //////////////

        function init() {
            if(authorizationService.isAuthorized()) {
                authorizationService.getSessionUser().then((response) => {
                    authorizationService.setUserData(response.data);
                    vm.searchQuery = {"title" : null, "author" : null, "department" : null};
                    getDepartments();
                });
            }
        }

        function institutionsSearch() {
            institutionsService.searchInLibrary(vm.searchQuery).then((response) => {
                initResultsTable(response.data);
            }).catch((error) => {
                console.log(error);
            });
        }

        function initResultsTable(results) {
            var initialParameters = {
                count: 10
            };
            var settings = {
                data: results,
                counts: []
            };
            vm.resultsTable = new NgTableParams(initialParameters, settings);
            vm.responseAvailable = true;
        }

        function getDepartments() {
            institutionsService.getDepartments().then((response) => {
                vm.departments = response.data;
                console.log(response.data);
            }).catch((error) => {
                console.log(error);
            });
        }
    }
})();