(function () {
    'use strict';

    angular
        .module('app')
        .controller('salesmanaccountController', salesmanaccountController);

    salesmanaccountController.$inject = ['$cookies','$http','$scope'];
    function salesmanaccountController($cookies,$http,$scope) {

        $http.get('/salesmen/findOne/'+$cookies.get('id'))
                        .success(function(response){
                            $scope.loggedUser = response;
                            $scope.salesman = response;
                        });


        $scope.update = function (){
                    if($scope.loggedUser.email != $scope.salesman.email){
                        $http.post('/users/existsEmail',$scope.salesman.email)
                            .success(function(response){
                                if(response == false){
                                    $http.put('/salesmen/update/'+$scope.salesman.user_id,$scope.salesman)
                                        .success(function(response){
                                            console.log("Password changed!");
                                        });
                                }
                                else
                                    $scope.error = 'Email se vec koristi , izaberite drugi!';
                            });
                    }
                    else{
                        $http.put('/salesmen/update/'+$scope.salesman.user_id,$scope.salesman)
                            .success(function(response){
                                console.log("Updated!");
                            });
                    }
            };

        $scope.changepassword = function (){
            if($scope.newpassword == $scope.confirmpassword){
                $scope.loggedUser.password = $scope.newpassword;
                console.log($scope);
                $http.put('/salesmen/update/'+$scope.loggedUser.user_id,$scope.loggedUser)
                    .success(function(response){
                        console.log("Password changed!");
                    });
                }
                else{
                    $scope.error = 'Oba polja moraju imati istu vrednost!';
                    document.getElementById('newpassword').style.borderColor = "red";
                    document.getElementById('confirmpassword').style.borderColor = "red";
                }
        }
    }
})();