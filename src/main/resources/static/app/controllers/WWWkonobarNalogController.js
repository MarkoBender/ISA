(function () {
     'use strict';

     angular
         .module('app')
         .controller('WWWkonobarNalogController', WWWkonobarNalogController);

     WWWkonobarNalogController.$inject = ['$cookies','$http','$scope','$location','$window'];
     function WWWkonobarNalogController($cookies,$http,$scope,$location,$window) {

        if($cookies.get('uloga') != 'Steward')
                        $location.url('/');
        var date = new Date();

        $scope.radi=false;
        $http.get('/stewards/findOne/'+$cookies.get('id'))
                        .success(function(response){
                            $scope.loggedUser = response;
                            $scope.steward=response;
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
                                                $cookies.put('region',value.restaurantRegion.restaurant_region_id)
                                                console.log($scope.radi);
                                            }
                                        }
                                    });
                                console.log($scope);
                            });
                        });

        $scope.logout = function (){
                     $cookies.put('name', null);
                     $cookies.put('id', null);
                     $cookies.put('uloga',null);
                     $location.url('/');
                }


        $scope.update = function (){
                    if($scope.loggedUser.email != $scope.steward.email){
                        $http.post('/users/existsEmail',$scope.steward.email)
                            .success(function(response){
                                if(response == false){
                                    $http.put('/stewards/update/'+$scope.steward.user_id,$scope.steward)
                                        .success(function(response){
                                            console.log("Password changed!");
                                            $window.location.reload();
                                        });
                                }
                                else
                                    $scope.error = 'Email se vec koristi , izaberite drugi!';
                            });
                    }
                    else{
                        $http.put('/stewards/update/'+$scope.steward.user_id,$scope.steward)
                            .success(function(response){
                                $window.location.reload();
                                console.log("Updated!");
                            });
                    }
            };

        $scope.changepassword = function (){
            if($scope.newpassword == $scope.confirmpassword){
                $scope.loggedUser.password = $scope.newpassword;
                console.log($scope);
                $http.put('/stewards/update/'+$scope.loggedUser.user_id,$scope.loggedUser)
                    .success(function(response){
                        console.log("Password changed!");
                        $window.location.reload();
                    });
                }
                else{
                    $scope.error = 'Oba polja moraju imati istu vrednost!';
                    document.getElementById('newpassword').style.borderColor = "red";
                    document.getElementById('confirmpassword').style.borderColor = "red";
                }
        }
     }
 })();