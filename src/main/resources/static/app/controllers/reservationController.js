(function () {
    'use strict';

    angular
        .module('app')
        .controller('ReservationController', ReservationController);

    ReservationController.$inject = ['$cookies','$http','$scope'];
    function ReservationController($cookies,$http,$scope) {

        var refresh = function(){
            $scope.reservationsWithInvitations = new Array();
            $http.get('/reservations/allActive/' + $cookies.get('id')).success(function(response){
                console.log("I got the data I requested!");
                $scope.reservations = response;
                var currRes = response;
                var currInvs = new Array();
                for(var i = 0; i < response.length; i++) {
                    (function(i){
                        $http.put('/invitations/getInvited', currRes[i]).success(function(response){
                            $scope.reservationsWithInvitations.push({
                                reservation : currRes[i],
                                invitations : response
                            });
                        });
                    })(i);
                }
            });
console.log($scope.reservationsWithInvitations);
        }


        $scope.naruci = function(jelo){
            console.log(jelo);
        }

        $scope.zapocni_narucivanje = function(restoran){
            $http.get('/dishes/menu/' + restoran.restaurant_id).success(function(response){
                $scope.jelovnik = response;
            });
        }


        var currentReservation;
        $scope.getInvitable = function(res){
            currentReservation = res;
            $http.put('/invitations/invitableFriends',res).success(function(response){
                console.log("I got the data I requested!");
                $scope.invitable = response;
            });
        }


        //"/add/{id}"
        $scope.invite = function(invited_id){
            $http.put('/invitations/add/'+ invited_id,currentReservation).success(function(response){
                console.log("I got the data I requested!");
                $scope.getInvitable(currentReservation);
                refresh();
            });
        }




        refresh();

    }
})();
