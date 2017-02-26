(function () {
    'use strict';

    angular
        .module('app')
        .controller('WWWpregledMenadzeraSistemaController', WWWpregledMenadzeraSistemaController);

    WWWpregledMenadzeraSistemaController.$inject = ['$cookies','$http','$scope','$location'];
    function WWWpregledMenadzeraSistemaController($cookies,$http,$scope, $location) {

        if($cookies.get('uloga') != 'SystemManager')
                    $location.url('/#');

        $scope.logout = function (){
                     $cookies.put('name', null);
                     $cookies.put('id', null);
                     $cookies.put('uloga',null);
                }

        $http.get('/systemManagers/all').success(function(response){
            console.log("I got the data I requested!");
            $scope.systemManagers = response;
            console.log($scope)
        });

        $scope.zapocniKreiranjeMenadzeraSistema=function(){
            $scope.systemManager=null;
        }

        $scope.zavrsiKreiranjeMenadzeraSistema = function(){
            console.log("usao");

            $http.post('/users/existsEmail',$scope.systemManager.email)
                .success(function (response){
                    if(response == false){
                        $http.put('/systemManagers/create', $scope.systemManager)
                            .success(function(response){
                                console.log("Poslao sam nest!");
                            });
                    }
                    else
                        alert("Email zauzet! Stavite drugi");
                });

            /*$http.put('/restaurantManagers/create', $scope.restaurantManager).success(function(response){
                console.log("Poslao sam nest!");
            });*/
        };

         $scope.zapocniIzmenuMenadzeraSistema = function(id){
             console.log("DOBIJENI ID JE "+id);
             $scope.systemManager = null;
             $http.get('/systemManagers/findOne/' + id).success(function(response){
                 $scope.systemManager = response;
             });
        };

        $scope.zavrsiIzmenuMenadzeraSistema = function(){
            $http.put('/systemManagers/update', $scope.systemManager).success(function(response){
                console.log("Poslao sam nest!");
            });
        };

    }
})();