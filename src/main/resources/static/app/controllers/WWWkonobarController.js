(function () {
         'use strict';

         angular
             .module('app')
             .controller('WWWkonobarController', WWWkonobarController);

         WWWkonobarController.$inject = ['$cookies','$http','$scope','$location'];
         function WWWkonobarController($cookies,$http,$scope,$location) {

            /*if($cookies.get('uloga') != 'Steward')
                        $location.url('/#');*/
            var date = new Date();

            console.log(date.getTime());

            console.log("Hours: "+date.getHours());
            console.log("Minutes: "+date.getMinutes());
            console.log("Month: "+(date.getMonth()+1));
            console.log("Day: "+date.getDate());
            console.log("Year: "+date.getFullYear());

            //console.log(date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2));

            //date.toString("MMM dd"); // "Dec 20"

            $scope.radi=false;
            $http.get('/stewards/findOne/'+$cookies.get('id'))
                            .success(function(response){
                                $scope.loggedUser = response;
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