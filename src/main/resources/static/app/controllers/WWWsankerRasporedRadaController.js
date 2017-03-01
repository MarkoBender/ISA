(function () {
     'use strict';

     angular
         .module('app')
         .controller('WWWsankerRasporedRadaController', WWWsankerRasporedRadaController);

     WWWsankerRasporedRadaController.$inject = ['$cookies','$http','$scope','$location'];
     function WWWsankerRasporedRadaController($cookies,$http,$scope,$location) {

        /*if($cookies.get('uloga') != 'Barmen')
                    $location.url('/#');*/

        /*$http.get('/barmen/findOne/'+$cookies.get('id'))
                                .success(function(response){
                                    $scope.loggedUser = response;
                                    $scope.barman = response;
                                    $http.post('/schedules/forEmployee',$scope.loggedUser).success(function(response){
                                                                            $scope.dailySchedules=response;
                                                                        });
                                });

        $scope.logout = function (){
                     $cookies.put('name', null);
                     $cookies.put('id', null);
                     $cookies.put('uloga',null);
                }*/

        if($cookies.get('uloga') != 'Barman')
                    $location.url('/');

        $scope.logout = function (){
                     $cookies.put('name', null);
                     $cookies.put('id', null);
                     $cookies.put('uloga',null);
                     $location.url('/');
                }

        var date = new Date();
        $scope.radi=false;

        $http.get('/barmen/findOne/'+$cookies.get('id'))
                            .success(function(response){
                                $scope.loggedUser = response;
                                $scope.barman=response;
                                $http.post('/schedules/forEmployee',$scope.loggedUser).success(function(response){
                                    $scope.dailySchedules=response;
                                });
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

     }
})();