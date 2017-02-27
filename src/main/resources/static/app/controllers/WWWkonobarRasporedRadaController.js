(function () {
     'use strict';

     angular
         .module('app')
         .controller('WWWkonobarRasporedRadaController', WWWkonobarRasporedRadaController);

     WWWkonobarRasporedRadaController.$inject = ['$cookies','$http','$scope','$location'];
     function WWWkonobarRasporedRadaController($cookies,$http,$scope,$location) {

        /*if($cookies.get('uloga') != 'Steward')
                    $location.url('/#');*/

        $http.get('/stewards/findOne/'+$cookies.get('id'))
                                .success(function(response){
                                    $scope.loggedUser = response;
                                    $scope.steward = response;
                                    $http.post('/dailySchedules/forEmployee',$scope.loggedUser).success(function(response)){
                                        $scope.dailySchedules=response;
                                    });
                                });

        $scope.vidiDodeljenRegion = function(region){
            $http.post('/restaurantregions/forRestaurant',$scope.loggedUser.restaurant)
                .success(function(response){
                    $scope.regions = response;
                    console.log($scope);
                });
            $http.post('/restauranttables/forRestaurant',$scope.loggedUser.restaurant)
                .success(function(response){
                    $scope.tables = response;
                    console.log($scope);
                });


        }

        $scope.logout = function (){
                     $cookies.put('name', null);
                     $cookies.put('id', null);
                     $cookies.put('uloga',null);
                }
     }
 })();