(function () {
         'use strict';

         angular
             .module('app')
             .controller('KukaController', KukaController);

         KukaController.$inject = ['$cookies','$http','$scope'];
         function KukaController($cookies,$http,$scope) {

            $scope.logout = function (){
                         $cookies.put('name', null);
                         $cookies.put('id', null);
                         $cookies.put('uloga',null);
                    }

         }
     })();