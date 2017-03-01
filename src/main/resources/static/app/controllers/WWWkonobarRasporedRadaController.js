(function () {
     'use strict';

     angular
         .module('app')
         .controller('WWWkonobarRasporedRadaController', WWWkonobarRasporedRadaController);

     WWWkonobarRasporedRadaController.$inject = ['$cookies','$http','$scope','$location'];
     function WWWkonobarRasporedRadaController($cookies,$http,$scope,$location) {

        if($cookies.get('uloga') != 'Steward')
                $location.url('/');
        var date = new Date();

        $scope.radi=false;
        $http.get('/stewards/findOne/'+$cookies.get('id'))
                        .success(function(response){
                            $scope.loggedUser = response;
                            $scope.steward=response;
                            $http.post('/schedules/forEmployee',$scope.loggedUser).success(function(response){
                                                                    $scope.dailySchedules=response;
                                                                    console.log($scope);
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


        $scope.vidiDodeljenRegion = function(region){
            $http.post('/restaurantregions/forRestaurant',$scope.loggedUser.restaurant)
                .success(function(response){
                    $scope.regions = response;
                    console.log($scope);
                    $http.post('/restauranttables/forRestaurant',$scope.loggedUser.restaurant)
                                    .success(function(response){
                                        $scope.tables = response;
                                        console.log($scope);
                                        $scope.restaurantRegion=region;
                                    });
                });
        }

     }
 })();