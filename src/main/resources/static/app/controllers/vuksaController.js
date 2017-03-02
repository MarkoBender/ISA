(function () {
    'use strict';

    angular
        .module('app')
        .controller('VuksaController', VuksaController);

    VuksaController.$inject = ['$cookies','$http','$scope','$window','$location'];
    function VuksaController($cookies,$http,$scope,$window,$location) {

        if($cookies.get('uloga') != 'RestaurantManager')
            $location.url('/');

        $scope.logout = function (){
                     $cookies.put('name', null);
                     $cookies.put('id', null);
                     $cookies.put('uloga',null);
                };

        $scope.register = function(){
            $scope.error = "";
            $http.post('/users/existsEmail',$scope.newUser.email)
                .success(function(response){
                    if(response == false){
                        $http.put('/salesmen/create', $scope.newUser)
                            .success(function(response){
                                console.log("Dodao sam ponudjaca");
                                $window.location.reload();
                            });
                    }
                    else
                        $scope.error = "Email vec zauzet, izaberite novi!";
                });
        };
    }
})();