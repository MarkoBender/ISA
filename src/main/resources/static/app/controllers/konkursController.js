(function () {
    'use strict';

    angular
        .module('app')
        .controller('KonkursController', KonkursController);

    KonkursController.$inject = ['$cookies','$http','$scope'];
    function KonkursController($cookies,$http,$scope) {

        $scope.logout = function (){
                     $cookies.put('name', null);
                     $cookies.put('id', null);
                     $cookies.put('uloga',null);
                };

        $http.get('/restaurantManagers/findOne/'+$cookies.get('id'))
            .success(function(response){
                $scope.loggedUser = response;
                $http.post('/buyingorders/forRestaurant',$scope.loggedUser.restaurant)
                    .success(function(response){
                         $scope.buyingorders = response;
                    });
            });
        console.log($scope);
        $scope.create = function(){
            $scope.neworder.restaurant = $scope.loggedUser.restaurant;
            $http.put('/buyingorders/create',$scope.neworder)
                .success(function (response){
                    console.log("Dodao sam novi buyingorder!");
                });
            };

        $scope.resetfields = function (){
            $scope.neworder = null;
        };
    }
})();