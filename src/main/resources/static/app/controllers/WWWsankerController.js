(function () {
         'use strict';

         angular
             .module('app')
             .controller('WWWsankerController', WWWsankerController);

         WWWsankerController.$inject = ['$cookies','$http','$scope','$location'];
         function WWWsankerController($cookies,$http,$scope,$location) {

            /*if($cookies.get('uloga') != 'Barman')
                        $location.url('/#');*/

            $scope.logout = function (){
                         $cookies.put('name', null);
                         $cookies.put('id', null);
                         $cookies.put('uloga',null);
                    }

         }
     })();