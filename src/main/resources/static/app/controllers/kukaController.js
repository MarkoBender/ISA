(function () {
         'use strict';

         angular
             .module('app')
             .controller('KukaController', KukaController);

         KukaController.$inject = ['$cookies','$http','$scope','$location','$window'];
         function KukaController($cookies,$http,$scope,$location,$window) {
            if($cookies.get('uloga') != 'SystemManager'){
                        $location.url('/');
            }

            $http.get('/systemManagers/findOne/'+$cookies.get('id'))
                                .success(function(response){
                                    $scope.loggedUser = response;
                                    });



            $scope.logout = function (){
                         $cookies.put('name', null);
                         $cookies.put('id', null);
                         $cookies.put('uloga',null);
                         $location.url('/');
                    }

         }
     })();