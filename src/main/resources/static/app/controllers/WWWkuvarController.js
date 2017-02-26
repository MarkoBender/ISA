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

            $http.get('/cooks/findOne/'+$cookies.get('id'))
                    .success(function(response){
                        $scope.loggedUser = response;
                    });

            $scope.changepassword = function (){
                if($scope.newpassword == $scope.confirmpassword){
                $http.put('/cooks/changepassword/'+$scope.loggedUser.user_id,$scope.newpassword)
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