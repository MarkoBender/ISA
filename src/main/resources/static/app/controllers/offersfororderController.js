(function () {
    'use strict';

    angular
        .module('app')
        .controller('offersfororderController', offersfororderController);

    offersfororderController.$inject = ['$cookies','$http','$scope','$routeParams','$location'];
    function offersfororderController($cookies,$http,$scope,$routeParams,$location) {

        if($cookies.get('uloga') != 'RestaurantManager')
            $location.url('/');

        $scope.orderID = $routeParams.orderid;

        $scope.logout = function (){
                     $cookies.put('name', null);
                     $cookies.put('id', null);
                     $cookies.put('uloga',null);
                };

        $http.get('/buyingorders/findOne/'+$scope.orderID)
            .success(function(response){
                $scope.buyingOrder = response;
                $http.post('/offers/offersfororder',$scope.buyingOrder)
                    .success(function(response){
                        $scope.offers = response;
                    });
            });

        $scope.info = function (offer){
            $scope.template_offer = offer;
        };

        $scope.accept = function (){
            $scope.template_offer.accepted = 'true';
            $http.put('/offers/update/'+$scope.template_offer.offer_id,$scope.template_offer)
                .success(function(response){
                    console.log("Offer accepted!");
                });
            $scope.template_offer.myorder.status= 'Closed';
            $http.put('/buyingorders/update/'+$scope.template_offer.myorder.buying_order_id,$scope.template_offer.myorder)
                .success(function(response){
                    console.log("Order closed!");
                });
        };
    }
})();