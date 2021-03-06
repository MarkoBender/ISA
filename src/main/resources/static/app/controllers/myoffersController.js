(function () {
    'use strict';

    angular
        .module('app')
        .controller('myoffersController', myoffersController);

    myoffersController.$inject = ['$cookies','$http','$scope','$location'];
    function myoffersController($cookies,$http,$scope,$location) {

            if($cookies.get('uloga') != 'Salesman')
                $location.url('/');

            var d = new Date();
            $scope.currentdate = d.getTime();

            $scope.logout = function (){
                     $cookies.put('name', null);
                     $cookies.put('id', null);
                     $cookies.put('uloga',null);
                };

            $http.get('/salesmen/findOne/'+$cookies.get('id'))
                .success(function(response){
                    $scope.loggedUser = response;
                    $http.post('/offers/salesmanoffers',$scope.loggedUser)
                                .success(function(response){
                                    $scope.offers = response;
                                });
                });

            $scope.clear = function(template_offer){
                $scope.template_offer = template_offer;
                console.log($scope);
            };


            $scope.update = function (){
                $scope.template_offer.salesman = $scope.loggedUser;
                $http.put('/offers/update/'+$scope.template_offer.offer_id,$scope.template_offer)
                    .success(function(response){
                        console.log("Updated offer")
                    });
            };

            $scope.delete = function (){
                $http.delete('/offers/delete/'+$scope.template_offer.offer_id,{})
                    .success(function(response){
                        console.log("Deleted offer");
                        });
            };


    }
})();