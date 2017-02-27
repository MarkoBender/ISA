(function () {
    'use strict';

    angular
        .module('app')
        .controller('kartapicaController', kartapicaController);

    kartapicaController.$inject = ['$cookies','$http','$scope'];
    function kartapicaController($cookies,$http,$scope) {

        $scope.newdish = {};
         $http.get('/restaurantManagers/findOne/'+$cookies.get('id'))
                     .success(function(response){
                         $scope.loggedUser = response;
                         $http.get('/drinks/menu/'+$scope.loggedUser.restaurant.restaurant_id)
                            .success(function(response){
                                 $scope.menu = response;
                            });
                     });

        $http.get('/drinktypes/all')
            .success(function(response){
                $scope.dishtypes = response;
            });

        $scope.clear = function (){
            $scope.newdish = {};
            $scope.newdish.restaurant = $scope.loggedUser.restaurant;

        };

        $scope.create = function (){
            $http.put('/drinks/create',$scope.newdish)
                .success(function(response){
                    console.log("Dodao dish na meni!");
                });
        }

        $scope.readyforupdate = function(template_dish){
            $scope.updateddish = template_dish;
        }

        $scope.update = function(){
            console.log($scope);
            $http.put('/drinks/update/'+$scope.updateddish.foodDrinkItem_id,$scope.updateddish)
                .success(function(response){
                    console.log("Drink updated");
                });
        }

       /* $scope.delete = function(id){
            $http.delete('/dishes/delete/'+id,{})
                .success(function(response){
                    console.log("Obrisao dish");
                });
        }*/

        console.log($scope);
    }
})();