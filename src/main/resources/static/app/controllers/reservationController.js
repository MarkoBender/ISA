(function () {
    'use strict';

    angular
        .module('app')
        .controller('ReservationController', ReservationController);

    ReservationController.$inject = ['$cookies','$http','$scope'];
    function ReservationController($cookies,$http,$scope) {

        $http.get('/guests/findOne/'+$cookies.get('id'))
            .success(function(response){
                $scope.loggedUser = response;
            });
        //console.log($scope.loggedUser);

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


        $scope.naruci = function(orderItem){
            /*console.log(orderItem);
            console.log($scope.loggedUser);
            console.log($scope.trenutnaRezervacija);*/

            var datum=new Date();
            console.log(datum.getTime());
            console.log($scope.trenutnaRezervacija.dateTime);
            if(datum.getTime()<$scope.trenutnaRezervacija.dateTime)
                datum=$scope.trenutnaRezervacija.dateTime;

            var rezervacija = {
                                date : datum,
                                foodDrinkItem : orderItem,
                                status : "gostPorucio",
                                reservation: $scope.trenutnaRezervacija,
                                guest : $scope.loggedUser
                            };

            $http.put('/orderItems/create',rezervacija).success(function(response){
                console.log("PROSLO USPESNO");
                console.log(rezervacija);
                $scope.trenutnaRezervacija=null;
                console.log($scope.trenutnaRezervacija);
            });


        }

        $scope.zapocni_narucivanje = function(reservation){
            $scope.trenutnaRezervacija=reservation;
            $http.get('/dishes/menu/' + reservation.restaurant.restaurant_id).success(function(response){
                $scope.jelovnik = response;
            });

            $http.get('/drinks/menu/' + reservation.restaurant.restaurant_id).success(function(response){
                $scope.kartaPica = response;
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
