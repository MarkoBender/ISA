(function () {
         'use strict';

         angular
             .module('app')
             .controller('inforadController', inforadController);

         inforadController.$inject = ['$cookies','$http','$scope','$location'];
         function inforadController($cookies,$http,$scope,$location) {

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

            $scope.logout = function (){
                         $cookies.put('name', null);
                         $cookies.put('id', null);
                         $cookies.put('uloga',null);
                    }

         }
     })();