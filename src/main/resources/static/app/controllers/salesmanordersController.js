(function () {
    'use strict';

    angular
        .module('app')
        .controller('SalesmanordersController', SalesmanordersController);

    SalesmanordersController.$inject = ['$cookies','$http','$scope'];
    function SalesmanordersController($cookies,$http,$scope) {

            var d = new Date();
            $scope.newoffer = {};
            $scope.activeorders = [];

            $http.get('/salesmen/findOne/'+$cookies.get('id'))
                .success(function(response){
                    $scope.loggedUser = response;
                    $http.post('/offers/salesmanoffers',$scope.loggedUser)
                                .success(function(response){
                                    $scope.offers = response;
                                });
                });

            //racunam da sam ovde skinuo samo moje offere
            console.log($scope);

            $http.get('/buyingorders/all')
                .success(function(response){
                    angular.forEach(response,function(value,index){
                        if(value.active_until > d.getTime() && value.status == 'Active')
                            $scope.activeorders.push(value);
                    })
                });




            //prvo sam stavio da na novi json trenutna vrednost konkursa buda ona sto sam klinkuo
            $scope.clear = function(id){
                $scope.mojflag = false;
                $scope.newoffer = {};
                angular.forEach($scope.activeorders,function(value,index){
                    if(value.buying_order_id == id)
                        $scope.newoffer.myorder = value;
                })
                //ovde sam se pitao da li posti vec ponuda od tog usera za taj konkurs ako da, hocu da ga menjam
                angular.forEach($scope.offers,function(value,index){
                    if(value.myorder.buying_order_id == $scope.newoffer.myorder.buying_order_id){
                        $scope.newoffer.offer_id = value.offer_id;
                        $scope.newoffer.price = value.price;
                        $scope.newoffer.description = value.description;
                        $scope.newoffer.delivery_deadline = value.delivery_deadline;
                        $scope.mojflag = true;
                    }
                })
                console.log($scope);
            };

            $scope.create = function (){
                $scope.newoffer.salesman = $scope.loggedUser
                $http.put('/offers/create',$scope.newoffer)
                    .success(function (response){
                        console.log("Offer created");
                    });
            };

            $scope.update = function (){
                $http.put('/offers/update/'+$scope.newoffer.offer_id,$scope.newoffer)
                    .success(function(response){
                        console.log("Updated offer");
                    });
            };

            $scope.delete = function (){
                $http.delete('/offers/delete/'+$scope.newoffer.offer_id,{})
                    .success(function(response){
                        console.log("Deleted offer");
                    });
            };


            $scope.changepassword = function (){
                if($scope.newpassword == $scope.confirmpassword){
                $http.put('/salesmen/changepassword/'+$scope.loggedUser.user_id,$scope.newpassword)
                    .success(function(response){
                        console.log("Password changed!");
                    });
                }
                else{
                    $scope.error = 'Oba polja moraju imati istu vrednost!';
                    document.getElementById('newpassword').style.borderColor = "red";
                    document.getElementById('confirmpassword').style.borderColor = "red";
                }
            };

            console.log($scope);


    }
})();