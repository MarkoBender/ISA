(function () {
         'use strict';

         angular
             .module('app')
             .controller('KukaController', KukaController);

         KukaController.$inject = ['$cookies','$http','$scope','$location'];
         function KukaController($cookies,$http,$scope,$location) {

            if($cookies.get('uloga') != 'SystemManager')
                        $location.url('/#');

            $scope.logout = function (){
                         $cookies.put('name', null);
                         $cookies.put('id', null);
                         $cookies.put('uloga',null);
                    }

         }
     })();