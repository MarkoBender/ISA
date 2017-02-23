(function () {
    'use strict';

    angular
        .module('app')
        .controller('BookingsController', BookingsController);

    BookingsController.$inject = ['$http'];

    function BookingsController($http) {
        var vm = this;
        vm.getAll = getAll;
        vm.getAffordable = getAffordable;
        vm.deleteBooking = deleteBooking;


        $scope.addContact = function(){
            console.log($scope.contact);
            $http.post('/users/new', $scope.user).success(function(response){
                console.log(response);
                refresh();
            });
        };

    }
})();
