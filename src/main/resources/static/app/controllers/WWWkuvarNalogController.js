(function () {
     'use strict';

     angular
         .module('app')
         .controller('WWWkuvarNalogController', WWWkuvarNalogController);

     WWWkuvarNalogController.$inject = ['$cookies','$http','$scope','$location'];
     function WWWkuvarNalogController($cookies,$http,$scope,$location) {

        /*if($cookies.get('uloga') != 'Steward')
                    $location.url('/#');*/
        $http.get('/dishtypes/all')
                    .success(function(response){
                        $scope.dishtypes = response;
                    });

        $http.get('/cooks/findOne/'+$cookies.get('id'))
                        .success(function(response){
                            $scope.loggedUser = response;
                            $scope.cook = response;
                        });

        $scope.logout = function (){
                     $cookies.put('name', null);
                     $cookies.put('id', null);
                     $cookies.put('uloga',null);
                }


        $scope.update = function (){
                    if($scope.loggedUser.email != $scope.cook.email){
                        $http.post('/users/existsEmail',$scope.cook.email)
                            .success(function(response){
                                if(response == false){
                                    $http.put('/cooks/update/'+$scope.cook.user_id,$scope.cook)
                                        .success(function(response){
                                            console.log("Password changed!");
                                        });
                                }
                                else
                                    $scope.error = 'Email se vec koristi , izaberite drugi!';
                            });
                    }
                    else{
                        $http.put('/cooks/update/'+$scope.cook.user_id,$scope.cook)
                            .success(function(response){
                                console.log("Updated!");
                            });
                    }
            };

        $scope.changepassword = function (){
            if($scope.newpassword == $scope.confirmpassword){
                $scope.loggedUser.password = $scope.newpassword;
                console.log($scope);
                $http.put('/cooks/update/'+$scope.loggedUser.user_id,$scope.loggedUser)
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