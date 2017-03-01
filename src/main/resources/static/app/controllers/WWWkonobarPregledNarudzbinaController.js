(function () {
     'use strict';

     angular
         .module('app')
         .controller('WWWkonobarPregledNarudzbinaController', WWWkonobarPregledNarudzbinaController);

     WWWkonobarPregledNarudzbinaController.$inject = ['$cookies','$http','$scope','$location','$window'];
     function WWWkonobarPregledNarudzbinaController($cookies,$http,$scope,$location,$window) {

        /*if($cookies.get('uloga') != 'Steward')
                    $location.url('/#');*/

        $http.get('/stewards/findOne/'+$cookies.get('id'))
                                .success(function(response){
                                    $scope.loggedUser = response;
                                    $scope.steward = response;
                                    $http.post('/orderItems/findOrderItemForSteward/'+$cookies.get('region'),$scope.loggedUser)
                                        .success(function(response){
                                            $scope.myavalibleorders = response;
                                            console.log($scope);
                                        });
                                    $http.get('/bills/all').success(function(response){
                                        $scope.bills=response;
                                    });

                                });

        $scope.logout = function (){
                     $cookies.put('name', null);
                     $cookies.put('id', null);
                     $cookies.put('uloga',null);
                };

        $scope.konobarPrihvati = function(order){
            $http.put('/orderItems/konobarPrihvati/'+$scope.loggedUser.user_id,order).success(function(response){
                $window.location.reload();
            });
        }

        $scope.konobarDostavi = function(order){
            $http.put('/orderItems/konobarDostavi',order).success(function(response){
                $window.location.reload();
            });
        }

        $scope.pregledRezervisanihStolova=function(order){
            alert("PREGLED");
            $http.post('/restaurantregions/forRestaurant',$scope.loggedUser.restaurant)
                            .success(function(response){
                                $scope.regions = response;
                                console.log($scope);
                                $http.post('/restauranttables/forRestaurant',$scope.loggedUser.restaurant)
                                                .success(function(response){
                                                    $scope.tables = response;
                                                    console.log($scope);
                                                    $scope.reservedTables=order.reservation.tables;
                                                    //$scope.restaurantRegion=region;
                                                });
                            });
        }



     }
 })();