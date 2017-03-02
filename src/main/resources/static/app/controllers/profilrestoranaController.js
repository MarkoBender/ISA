(function () {
    'use strict';

    angular
        .module('app')
        .controller('profilrestoranaController', profilrestoranaController);

    profilrestoranaController.$inject = ['$cookies','$http','$scope','$location'];
    function profilrestoranaController($cookies,$http,$scope,$location) {

        if($cookies.get('uloga') != 'RestaurantManager')
            $location.url('/');

        $scope.logout = function (){
                     $cookies.put('name', null);
                     $cookies.put('id', null);
                     $cookies.put('uloga',null);
                };

        $http.get('/restaurantManagers/findOne/'+$cookies.get('id'))
            .success(function(response){
                $scope.loggedUser = response;
                $scope.restaurant = response.restaurant;
            });
        $scope.update = function(){
            $http.put('/restaurants/update/'+$scope.restaurant.restaurant_id,$scope.restaurant)
                .success(function(response){
                    console.log("Restaurant updated!");
                });
        };
    }
})();