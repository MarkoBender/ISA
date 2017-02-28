(function () {
     'use strict';

     angular
         .module('app')
         .controller('WWWsankerPregledNarucenihPicaController', WWWsankerPregledNarucenihPicaController);

     WWWsankerPregledNarucenihPicaController.$inject = ['$cookies','$http','$scope','$location','$window'];
     function WWWsankerPregledNarucenihPicaController($cookies,$http,$scope,$location,$window) {

        /*if($cookies.get('uloga') != 'Barmen')
                    $location.url('/#');*/

        $http.get('/barmen/findOne/'+$cookies.get('id'))
                                .success(function(response){
                                    $scope.loggedUser = response;
                                    $scope.barman = response;
                                    $http.post('/orderItems/findOrderItemForBarman/',$scope.loggedUser)
                                            .success(function(response){
                                                $scope.myavalibleorders = response;
                                                console.log($scope);
                                            });
                                });

        $scope.logout = function (){
                     $cookies.put('name', null);
                     $cookies.put('id', null);
                     $cookies.put('uloga',null);
                }

        $scope.sankerPrihvati = function(order){
            $http.put('/orderItems/sankerPrihvati/'+$scope.loggedUser.user_id,order).success(function(response){
                $window.location.reload();
            });
        }

        $scope.sankerSpremi = function(order){
            $http.put('/orderItems/sankerSpremi',order).success(function(response){
                $window.location.reload();
            });
        }

     }
 })();