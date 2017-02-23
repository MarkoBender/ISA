(function () {
    'use strict';

    angular
        .module('app')
        .controller('VuksaController', VuksaController);

    VuksaController.$inject = ['$cookies','$http','$scope'];
    function VuksaController($cookies,$http,$scope) {
        $scope.register = function(){
            $http.post('/users/existsEmail',$scope.newUser.email)
                .success(function(response){
                    if(response == false){
                        $http.put('/salesmen/create', $scope.newUser)
                            .success(function(response){
                                console.log("Dodao sam ponudjaca");
                            });
                    }
                    else
                        alert("Email zauzet! Stavite drugi");
                });
        };
    }
})();