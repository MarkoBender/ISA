(function () {
         'use strict';

         angular
             .module('app')
             .controller('WWWkonobarController', WWWkonobarController);

         WWWkonobarController.$inject = ['$cookies','$http','$scope','$location'];
         function WWWkonobarController($cookies,$http,$scope,$location) {

            /*if($cookies.get('uloga') != 'Steward')
                        $location.url('/#');*/

            $http.get('/stewards/findOne/'+$cookies.get('id'))
                            .success(function(response){
                                $scope.loggedUser = response;
                            });

            $scope.logout = function (){
                         $cookies.put('name', null);
                         $cookies.put('id', null);
                         $cookies.put('uloga',null);
                    }


            /*$scope.changepassword = function (){
                        if($scope.newpassword == $scope.confirmpassword){
                            $scope.loggedUser.password = $scope.newpassword;
                            console.log($scope);
                            $http.put('/stewards/update/'+$scope.loggedUser.user_id,$scope.loggedUser)
                                .success(function(response){
                                    console.log("Password changed!");
                                });
                            }
                            else{
                                $scope.error = 'Oba polja moraju imati istu vrednost!';
                                document.getElementById('newpassword').style.borderColor = "red";
                                document.getElementById('confirmpassword').style.borderColor = "red";
                            }
                    }*/

            $scope.changepassword = function (){
                            if($scope.newpassword == $scope.confirmpassword){
                            $http.put('/stewards/changepassword/'+$scope.loggedUser.user_id,$scope.newpassword)
                                .success(function(response){
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