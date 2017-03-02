(function () {
    'use strict';

    angular
        .module('app')
        .controller('noviradnikController', noviradnikController);

    noviradnikController.$inject = ['$cookies','$http','$scope','$window','$location'];
    function noviradnikController($cookies,$http,$scope,$window,$location) {

        if($cookies.get('uloga') != 'RestaurantManager')
            $location.url('/');

        $scope.logout = function (){
                     $cookies.put('name', null);
                     $cookies.put('id', null);
                     $cookies.put('uloga',null);
                };

        $http.get('/dishtypes/all')
            .success(function(response){
                $scope.dishtypes = response;
            });

        $http.get('/restaurantManagers/findOne/'+$cookies.get('id'))
            .success(function(response){
                $scope.loggedUser = response;
            });

        $scope.registerCook = function (){
            $scope.newCook.restaurant = $scope.loggedUser.restaurant;
            $http.post('/users/existsEmail',$scope.newCook.email)
                .success(function(response){
                    if(response == false){
                          $http.put('/cooks/create',$scope.newCook)
                            .success(function(response){
                                console.log("Created Cook!");
                                $window.location.reload();
                          });
                    }
                    else
                        $scope.error = "Email vec u koristi postavite drugi!" ;
            });
        };

        $scope.registerBarman = function (){
            $scope.newBarman.restaurant = $scope.loggedUser.restaurant;
            $http.post('/users/existsEmail',$scope.newBarman.email)
                .success(function(response){
                    if(response == false){
                          $http.put('/barmen/create',$scope.newBarman)
                            .success(function(response){
                                console.log("Created Barman!");
                                $window.location.reload();
                          });
                    }
                    else
                        $scope.error = "Email vec u koristi postavite drugi!" ;
            });
        };

        $scope.registerSteward = function (){
            $scope.newSteward.restaurant = $scope.loggedUser.restaurant;
            $http.post('/users/existsEmail',$scope.newSteward.email)
                .success(function(response){
                    if(response == false){
                          $http.put('/stewards/create',$scope.newSteward)
                            .success(function(response){
                                console.log("Created Steward!");
                                $window.location.reload();
                          });
                    }
                    else
                        $scope.error = "Email vec u koristi postavite drugi!" ;
            });
        };

    }
})();