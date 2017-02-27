(function () {
     'use strict';

     angular
         .module('app')
         .controller('WWWsankerRasporedRadaController', WWWsankerRasporedRadaController);

     WWWsankerRasporedRadaController.$inject = ['$cookies','$http','$scope','$location'];
     function WWWsankerRasporedRadaController($cookies,$http,$scope,$location) {

        /*if($cookies.get('uloga') != 'Barmen')
                    $location.url('/#');*/

        $http.get('/barmen/findOne/'+$cookies.get('id'))
                                .success(function(response){
                                    $scope.loggedUser = response;
                                    $scope.barman = response;
                                });

        $scope.logout = function (){
                     $cookies.put('name', null);
                     $cookies.put('id', null);
                     $cookies.put('uloga',null);
                }

     }
 })();