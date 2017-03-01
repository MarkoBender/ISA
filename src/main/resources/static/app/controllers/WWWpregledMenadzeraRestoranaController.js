(function () {
    'use strict';

    angular
        .module('app')
        .controller('WWWpregledMenadzeraRestoranaController', WWWpregledMenadzeraRestoranaController);

    WWWpregledMenadzeraRestoranaController.$inject = ['$cookies','$http','$scope','$location','$window'];
    function WWWpregledMenadzeraRestoranaController($cookies,$http,$scope,$location,$window) {

        if($cookies.get('uloga') != 'SystemManager')
                    $location.url('/');

        $http.get('/systemManagers/findOne/'+$cookies.get('id'))
                    .success(function(response){
                        $scope.loggedUser = response;
                        });

        $scope.logout = function (){
                     $cookies.put('name', null);
                     $cookies.put('id', null);
                     $cookies.put('uloga',null);
                     $location.url('/');
                }

        $http.get('/restaurantManagers/all').success(function(response){
            console.log("I got the data I requested!");
            $scope.restaurantManagers = response;
            console.log($scope)
        });

        $scope.zapocniKreiranjeMenadzeraRestorana=function(){
            $http.get('/restaurantManagers/availableRestaurants').success(function(response){
                $scope.restaurantManager=null;
                $scope.availableRestaurants=response;
                console.log(response.length);
            });
        }

        $scope.zavrsiKreiranjeMenadzeraRestorana = function(){
            $http.post('/users/existsEmail',$scope.restaurantManager.email)
                .success(function (response){
                    if(response == false){
                        $http.put('/restaurantManagers/create', $scope.restaurantManager)
                            .success(function(response){
                                console.log("Poslao sam nest!");
                                $window.location.reload();
                            });
                    }
                    else
                        alert("Email zauzet! Stavite drugi");
                });
        };

         $scope.zapocniIzmenuMenadzeraRestorana = function(id){
             console.log("DOBIJENI ID JE "+id);
             $scope.restaurantManager = null;
             $scope.availableRestaurants=null;
             $http.get('/restaurantManagers/findOne/' + id).success(function(response){
                 response.restaurant=null;
                 $scope.restaurantManager = response;
                 $http.get('/restaurantManagers/availableRestaurantsForUpdate/'+id).success(function(response){

                     $scope.availableRestaurants=response;
                     console.log(response.length);
                 });
             });
        };

        $scope.zavrsiIzmenuMenadzeraRestorana = function(){
            $http.post('/users/existsEmail',$scope.restaurantManager.email)
                            .success(function (response){
                                if(response == false){
                                $http.put('/restaurantManagers/update', $scope.restaurantManager).success(function(response){
                                                console.log("Poslao sam nest!");
                                                $window.location.reload();
                                            });
                                    /*$http.put('/restaurantManagers/create', $scope.restaurantManager)
                                        .success(function(response){
                                            console.log("Poslao sam nest!");
                                            $window.location.reload();
                                        });*/
                                }
                                else
                                    alert("Email zauzet! Stavite drugi");
                            });
            /*$http.put('/restaurantManagers/update', $scope.restaurantManager).success(function(response){
                console.log("Poslao sam nest!");
            });*/
        };

    }
})();