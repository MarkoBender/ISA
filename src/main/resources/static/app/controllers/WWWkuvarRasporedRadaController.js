(function () {
     'use strict';

     angular
         .module('app')
         .controller('WWWkuvarRasporedRadaController', WWWkuvarRasporedRadaController);

     WWWkuvarRasporedRadaController.$inject = ['$cookies','$http','$scope','$location'];
     function WWWkuvarRasporedRadaController($cookies,$http,$scope,$location) {

        /*if($cookies.get('uloga') != 'Cook')
                    $location.url('/#');*/

        $http.get('/cooks/findOne/'+$cookies.get('id'))
                                .success(function(response){
                                    $scope.loggedUser = response;
                                    $scope.cook = response;
                                });

        $scope.logout = function (){
                     $cookies.put('name', null);
                     $cookies.put('id', null);
                     $cookies.put('uloga',null);
                }

     }
 })();