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

            $http.get('/barmen/findOne/'+$cookies.get('id'))
                                .success(function(response){
                                    $scope.loggedUser = response;
                                });

            $scope.changepassword = function (){
                if($scope.newpassword == $scope.confirmpassword){
                $http.put('/barmen/changepassword/'+$scope.loggedUser.user_id,$scope.newpassword)
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