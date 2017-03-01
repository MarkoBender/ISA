(function () {
    'use strict';

    angular
        .module('app')
        .controller('InvitationsController', InvitationsController);

    InvitationsController.$inject = ['$scope','$http','$cookies'];

    function InvitationsController($scope,$http,$cookies) {

        $http.get('/guests/findOne/'+$cookies.get('id'))
                    .success(function(response){
                        $scope.loggedUser = response;
                    });

        var refresh = function(){
            $http.get('/invitations/activeNotConfirmed/' + $cookies.get('id')).success(function(response){
                console.log("I got the data I requested!" + response);
                $scope.notConfirmed = response;
                console.log(response);
            });

            $http.get('/invitations/activeConfirmed/' + $cookies.get('id')).success(function(response){
                console.log("I got the data I requested!");
                $scope.confirmed = response;
            });

        }

        refresh();


        $scope.naruci = function(orderItem){
            //console.log(jelo);
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

            $http.post('/orderItems/findByResIdAndGuest/'+reservation.reservation_id,$scope.loggedUser).success(function(response){
                            $scope.myavalibleorders=response;
                        });
            $http.get('/dishes/menu/' + reservation.restaurant.restaurant_id).success(function(response){
                console.log("Dobijo sam jelovnik!");
                $scope.jelovnik = response;
            });

            $http.get('/drinks/menu/' + reservation.restaurant.restaurant_id).success(function(response){
                $scope.kartaPica = response;
            });
        }

        $scope.potvrdi = function(pozivnica){
            $http.put('/invitations/confirmInvitation', pozivnica).success(function(response){
                console.log("Postovo sam brt moj!");
                refresh();
            });

        };

        $scope.otkaziPorudzbinu= function(orderItem){
            alert("USAO SAM")
            var trenutniDatum=new Date();
            trenutniDatum.setMinutes(trenutniDatum.getMinutes()+30);
            if(trenutniDatum.getTime()>=orderItem.reservation.dateTime){
                alert("NEMOZES OBRISATI");
            }
            else{
                alert("MOZES OBRISATI!");
                $http.delete('/orderItems/izbrisi/'+orderItem.orderItem_id).success(function(response){
                                    console.log("uspesno proslo");
                                });
            }
        }


    }
})();
