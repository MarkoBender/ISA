(function () {
    'use strict';

    angular
        .module('app')
        .controller('myoffersController', myoffersController);

    myoffersController.$inject = ['$cookies','$http','$scope'];
    function myoffersController($cookies,$http,$scope) {

            var d = new Date();
            $scope.currentdate = d.getTime();


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