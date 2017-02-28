(function () {
     'use strict';

     angular
         .module('app')
         .controller('WWWkuvarPregledJelaController', WWWkuvarPregledJelaController);

     WWWkuvarPregledJelaController.$inject = ['$cookies','$http','$scope','$location', '$window'];
     function WWWkuvarPregledJelaController($cookies,$http,$scope,$location,$window) {

        /*if($cookies.get('uloga') != 'Cook')
                    $location.url('/#');*/

        /*$http.get('/cooks/findOne/'+$cookies.get('id'))
                                .success(function(response){
                                    $scope.loggedUser = response;
                                    $scope.cook = response;
                                });*/
        $http.get('/cooks/findOne/'+$cookies.get('id'))
                                        .success(function(response){
                                            $scope.loggedUser = response;
                                            $scope.cook = response;
                                            $http.post('/orderItems/findOrderItemForCook/',$scope.loggedUser)
                                                .success(function(response){
                                                    $scope.myavalibleorders = response;
                                                    console.log($scope);
                                                });
                                        });

        $scope.logout = function (){
                     $cookies.put('name', null);
                     $cookies.put('id', null);
                     $cookies.put('uloga',null);
                }

        $scope.kuvarPrihvati = function(order){
                    $http.put('/orderItems/kuvarPrihvati/'+$scope.loggedUser.user_id,order).success(function(response){
                        $window.location.reload();
                    });
                }

        $scope.kuvarSpremi = function(order){
            $http.put('/orderItems/kuvarSpremi',order).success(function(response){
                $window.location.reload();
            });
        }
     }
 })();