(function () {
    'use strict';

    angular
        .module('app')
        .controller('jelovnikController', jelovnikController);

    jelovnikController.$inject = ['$cookies','$http','$scope'];
    function jelovnikController($cookies,$http,$scope) {

        $scope.newdish = {};
         $http.get('/restaurantManagers/findOne/'+$cookies.get('id'))
                     .success(function(response){
                         $scope.loggedUser = response;
                         $http.get('/dishes/menu/'+$scope.loggedUser.restaurant.restaurant_id)
                            .success(function(response){
                                 $scope.menu = response;
                            });
                     });

        $http.get('/dishtypes/all')
            .success(function(response){
                $scope.dishtypes = response;
            });

        $scope.clear = function (){
            $scope.newdish = {};
            $scope.newdish.restaurant = $scope.loggedUser.restaurant;

        };

        $scope.create = function (){
            $http.put('/dishes/create',$scope.newdish)
                .success(function(response){
                    console.log("Dodao dish na meni!");
                });
        }

        $scope.readyforupdate = function(template_dish){
            $scope.updateddish = template_dish;
        }

        $scope.update = function(){
            $http.put('/dishes/update/'+$scope.updateddish.dish_id,$scope.updateddish)
                .success(function(response){
                    console.log("Dish updated");
                });
        }

        $scope.delete = function(id){
            $http.delete('/dishes/delete/'+id,{})
                .success(function(response){
                    console.log("Obrisao dish");
                });
        }

        console.log($scope);
    }
})();