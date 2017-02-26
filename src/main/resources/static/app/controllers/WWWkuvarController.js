(function () {
         'use strict';

         angular
             .module('app')
             .controller('WWWkuvarController', WWWkuvarController);

         WWWkuvarController.$inject = ['$cookies','$http','$scope','$location'];
         function WWWkuvarController($cookies,$http,$scope,$location) {

            /*if($cookies.get('uloga') != 'Steward')
                        $location.url('/#');*/

            $scope.logout = function (){
                         $cookies.put('name', null);
                         $cookies.put('id', null);
                         $cookies.put('uloga',null);
                    }

         }
     })();