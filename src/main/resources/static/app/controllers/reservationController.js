(function () {
    'use strict';

    angular
        .module('app')
        .controller('ReservationController', ReservationController);

    ReservationController.$inject = ['$cookies','$http','$scope','$window'];
    function ReservationController($cookies,$http,$scope,$window) {

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
                datum=new Date($scope.trenutnaRezervacija.dateTime);

            alert(datum.getHours());
            alert(datum.getMinutes());

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

        $scope.zatraziRacun = function(reservation){

            //alert(reservation.dateTime);
            //alert(reservation.duration);
            var datum=new Date(reservation.dateTime);
            alert(datum.getHours());
            alert(datum.getMinutes());

            datum.setHours(datum.getHours()+reservation.duration);
            alert(datum.getHours());
            alert(datum.getMinutes());
            //alert(datum.getTime());

            var trenutniDatum=new Date();
            if(trenutniDatum.getTime()<=datum.getTime() && trenutniDatum.getTime()>=reservation.dateTime){
                alert("MOZES PLATITI");

                $http.put('/bills/zahtevZaRacun',reservation).success(function(response){
                    alert("USPESNO!");
                    $window.location.reload();
                });
                /*$http.post('/orderItems/getByReservationPrice',reservation).success(function(response){
                   var price=response;
                   alert("cena ukupna za rezervaciju je: "+price);
                });*/

                /*$http.put('/reservations/setInactive/',reservation).success(function(response){
                    reservation=response;
                    var racun = {
                        reservation : reservation
                    }
                    bill

                });*/

            }
            else
                alert("NE MOZES PLATITI");
        }

        $scope.zapocni_narucivanje = function(reservation){
            $scope.trenutnaRezervacija=reservation;



            /*var datum=reservation.dateTime;
            var ocekivano_vreme=reservation.duration*60000;
            datum=datum-ocekivano_vreme;
            var trenutniDatum=new Date();
            console.log(datum);
            console.log(trenutniDatum.getTime());
            console.log(datum-trenutniDatum.getTime());
            if(datum-trenutniDatum.getTime()>=1800000)
                $scope.moguceObrisati=true;
            else
                $scope.moguceObrisati=false;*/
            //console.log('datum je - '+datum);
            //$scope.moguceObrisati=false;

            $http.post('/orderItems/findByResIdAndGuest/'+reservation.reservation_id,$scope.loggedUser).success(function(response){
                $scope.myavalibleorders=response;
            });

            $http.get('/dishes/menu/' + reservation.restaurant.restaurant_id).success(function(response){
                $scope.jelovnik = response;
            });

            $http.get('/drinks/menu/' + reservation.restaurant.restaurant_id).success(function(response){
                $scope.kartaPica = response;
            });
        }

        $scope.otkaziRezervaciju = function(reservation){
            //obrisi order item, invitations, reservation
            var trenutniDatum=new Date();
            trenutniDatum.setMinutes(trenutniDatum.getMinutes()+30);
            if(trenutniDatum.getTime()>=reservation.dateTime){
                alert("NEMOZES OBRISATI");

            }
            else{
                alert("MOZES OBRISATI!");
                $http.delete('/reservations/izbrisi/'+reservation.reservation_id).success(function(response){
                                    console.log("uspesno proslo");
                                });
            }
        }

        $scope.otkaziPorudzbinu= function(orderItem){
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
