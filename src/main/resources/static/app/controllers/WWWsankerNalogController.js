(function () {
     'use strict';

     angular
         .module('app')
         .controller('WWWsankerNalogController', WWWsankerNalogController);

     WWWsankerNalogController.$inject = ['$cookies','$http','$scope','$location'];
     function WWWsankerNalogController($cookies,$http,$scope,$location) {

        /*if($cookies.get('uloga') != 'Steward')
                    $location.url('/#');*/

        $http.get('/barmen/findOne/'+$cookies.get('id'))
                        .success(function(response){
                            $scope.loggedUser = response;
                            $scope.barman = response;
                        });

        $scope.logout = function (){
                     $cookies.put('name', null);
                     $cookies.put('id', null);
                     $cookies.put('uloga',null);
                }


        $scope.update = function (){
                    if($scope.loggedUser.email != $scope.barman.email){
                        $http.post('/users/existsEmail',$scope.barman.email)
                            .success(function(response){
                                if(response == false){
                                    $http.put('/barmen/update/'+$scope.barman.user_id,$scope.barman)
                                        .success(function(response){
                                            console.log("Password changed!");
                                        });
                                }
                                else
                                    $scope.error = 'Email se vec koristi , izaberite drugi!';
                            });
                    }
                    else{
                        $http.put('/barmen/update/'+$scope.barman.user_id,$scope.barman)
                            .success(function(response){
                                console.log("Updated!");
                            });
                    }
            };

        $scope.changepassword = function (){
            if($scope.newpassword == $scope.confirmpassword){
                $scope.loggedUser.password = $scope.newpassword;
                console.log($scope);
                $http.put('/barmen/update/'+$scope.loggedUser.user_id,$scope.loggedUser)
                    .success(function(response){
                        console.log("Password changed!");
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