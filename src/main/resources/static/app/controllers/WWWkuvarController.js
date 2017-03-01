(function () {
         'use strict';

         angular
             .module('app')
             .controller('WWWkuvarController', WWWkuvarController);

         WWWkuvarController.$inject = ['$cookies','$http','$scope','$location','$window'];
         function WWWkuvarController($cookies,$http,$scope,$location,$window) {

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
                                            $scope.radi=true;
                                            console.log($scope.radi);
                                        }
                                    }
                                });
                            console.log($scope);
                        });
                    });

            $scope.changepassword = function (){
                if($scope.newpassword == $scope.confirmpassword){
                $http.put('/cooks/changepassword/'+$scope.loggedUser.user_id,$scope.newpassword)
                    .success(function(response){
                        $window.location.reload();
                        console.log("Password changed!");
                    });
                }
                else{
                    $scope.error = 'Oba polja moraju imati istu vrednost!';
                    document.getElementById('newpassword').style.borderColor = "red";
                    document.getElementById('confirmpassword').style.borderColor = "red";
                }
            };

         }
     })();