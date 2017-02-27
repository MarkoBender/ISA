(function () {
    'use strict';

    angular
        .module('app')
        .controller('dodavanjesmenaController', dodavanjesmenaController);

    dodavanjesmenaController.$inject = ['$cookies','$http','$scope'];
    function dodavanjesmenaController($cookies,$http,$scope) {

        var d = new Date();
        $scope.currentdate = d.getTime();

        $scope.newsched = {};

        $http.get('/restaurantManagers/findOne/'+$cookies.get('id'))
            .success(function(response){
                $scope.loggedUser = response;

                $http.post('/employees/forRestaurant',$scope.loggedUser.restaurant)
                .success(function(response){
                    $scope.employees = response;
                });

                $http.post('/restaurantregions/forRestaurant',$scope.loggedUser.restaurant)
                    .success(function(response){
                        $scope.regions = response;
                    });
             });

        $scope.newSchedule = function(){
            if($scope.newsched.employee.uloga != 'Steward')
                $scope.newsched.restaurantRegion = null;
            $scope.newsched.restaurant = $scope.loggedUser.restaurant;
            $http.put('/schedules/create',$scope.newsched)
                .success(function(response){
                    console.log('added schedule!');
                });
            console.log($scope);
        };
    }
})();