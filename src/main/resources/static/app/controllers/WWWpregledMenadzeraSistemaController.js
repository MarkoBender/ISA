(function () {
    'use strict';

    angular
        .module('app')
        .controller('WWWpregledMenadzeraSistemaController', WWWpregledMenadzeraSistemaController);

    WWWpregledMenadzeraSistemaController.$inject = ['$cookies','$http','$scope','$location','$window'];
    function WWWpregledMenadzeraSistemaController($cookies,$http,$scope, $location,$window) {

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
                                $window.location.reload();
                            });
                    }
                    else
                        alert("Email zauzet! Stavite drugi");
                });
        };

         $scope.zapocniIzmenuMenadzeraSistema = function(id){
             console.log("DOBIJENI ID JE "+id);
             $scope.systemManager = null;
             $http.get('/systemManagers/findOne/' + id).success(function(response){
                 $scope.systemManager = response;
             });
        };

        $scope.zavrsiIzmenuMenadzeraSistema = function(){
            $http.post('/users/existsEmail',$scope.systemManager.email)
                            .success(function (response){
                                if(response == false){
                                    $http.put('/systemManagers/update', $scope.systemManager).success(function(response){
                                                    console.log("Poslao sam nest!");
                                                    $window.location.reload();
                                                });
                                }
                                else
                                    alert("Email zauzet! Stavite drugi");
                            });

        };

    }
})();