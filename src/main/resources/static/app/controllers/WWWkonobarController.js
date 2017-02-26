(function () {
         'use strict';

         angular
             .module('app')
             .controller('WWWkonobarController', WWWkonobarController);

         WWWkonobarController.$inject = ['$cookies','$http','$scope','$location'];
         function WWWkonobarController($cookies,$http,$scope,$location) {

            /*if($cookies.get('uloga') != 'Steward')
                        $location.url('/#');*/

            $scope.logout = function (){
                         $cookies.put('name', null);
                         $cookies.put('id', null);
                         $cookies.put('uloga',null);
                    }

         }
     })();