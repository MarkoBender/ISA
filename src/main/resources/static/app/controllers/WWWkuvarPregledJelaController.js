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

        if($cookies.get('uloga') != 'Cook')
                    $location.url('/');

        var date = new Date();
        $scope.radi=false;
        $scope.logout = function (){
                     $cookies.put('name', null);
                     $cookies.put('id', null);
                     $cookies.put('uloga',null);
                     $location.url('/');
                }

        $http.get('/cooks/findOne/'+$cookies.get('id'))
                .success(function(response){
                    $scope.loggedUser = response;
                    $scope.cook=response;
                    $http.post('/schedules/forEmployee',$scope.loggedUser).success(function(response){
                        angular.forEach(response,function(value,index){
                                var endHours=value.endHours;
                                if(endHours==0)
                                    endHours=24;

                                if(value.startday<=date.getTime() && value.endday>=date.getTime() && value.startHours<=date.getHours() && endHours>=date.getHours()){
                                    var potencijalno_dobro=true;
                                    if(value.startHours==date.getHours()){
                                        if(value.startMinutes>date.getMinutes()){
                                            potencijalno_dobro=false;
                                        }
                                    }

                                    if(potencijalno_dobro){
                                        if(value.endHours==date.getHours()){
                                            if(value.endMinutes<date.getMinutes()){
                                                potencijalno_dobro=false;
                                            }
                                        }
                                    }

                                    if(potencijalno_dobro){
                                        console.log("JEBENOOOOOOOOOOOOOOOOOOOO SAM U DOBROM VREMENU");
                                        $http.post('/orderItems/findOrderItemForCook/',$scope.loggedUser)
                                                .success(function(response){
                                                    $scope.myavalibleorders = response;
                                                    console.log($scope);
                                                });
                                        $scope.radi=true;
                                        console.log($scope.radi);
                                    }
                                }
                            });
                        console.log($scope);
                    });
                });


        /*$http.get('/cooks/findOne/'+$cookies.get('id'))
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
                }*/

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