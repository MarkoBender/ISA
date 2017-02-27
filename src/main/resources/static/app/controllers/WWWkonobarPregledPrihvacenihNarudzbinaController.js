(function () {
     'use strict';

     angular
         .module('app')
         .controller('WWWkonobarPregledPrihvacenihNarudzbinaController', WWWkonobarPregledPrihvacenihNarudzbinaController);

     WWWkonobarPregledPrihvacenihNarudzbinaController.$inject = ['$cookies','$http','$scope','$location'];
     function WWWkonobarPregledPrihvacenihNarudzbinaController($cookies,$http,$scope,$location) {

        /*if($cookies.get('uloga') != 'Steward')
                    $location.url('/#');*/

        $http.get('/stewards/findOne/'+$cookies.get('id'))
                                .success(function(response){
                                    $scope.loggedUser = response;
                                    $scope.steward = response;
                                });

        $scope.logout = function (){
                     $cookies.put('name', null);
                     $cookies.put('id', null);
                     $cookies.put('uloga',null);
                }

     }
 })();