(function () {
    'use strict';

    angular
        .module('app')
        .controller('RestorauntsController', RestorauntsController);

    RestorauntsController.$inject = ['$cookies','$http','$scope'];
    function RestorauntsController($cookies,$http,$scope) {

        $http.get('/guests/findOne/'+$cookies.get('id'))
                                        .success(function(response){
                                            $scope.loggedUser = response;
                                            });

        $http.get('http://ip-api.com/json')
            .success(function(coordinates) {
                $scope.mylat = coordinates.lat;
                $scope.mylng = coordinates.lon;
            $http.get('/restaurants/all').success(function(response){
                console.log("I got the data I requested!");
                $scope.restaurants = response;
                var i;
                for (i = 0; i < $scope.restaurants.length; i++) {
                    $scope.restaurants[i].distance = getDist($scope.restaurants[i].lat,$scope.restaurants[i].lng);
                }
            });
        });



        $http.get('/reservations/allInactive/' + $cookies.get('id')).success(function(response){
            $scope.visited = response;
            console.log("Brrrrrrrrrrrrt");
            $http.get('/invitations/pastVisits/' + $cookies.get('id')).success(function(response){
                $scope.visited = $scope.visited.concat(response);
                console.log($scope.visited);
            });
        });

        $scope.proba = function(){
            console.log("EO GA!");
        }

        function getDist(lat,lon) {
          var R = 6371; // Radius of the earth in km
          var dLat = deg2rad(lat-$scope.mylat);  // deg2rad below
          var dLon = deg2rad(lon-$scope.mylng);
          var a =
            Math.sin(dLat/2) * Math.sin(dLat/2) +
            Math.cos(deg2rad($scope.mylat)) * Math.cos(deg2rad(lat)) *
            Math.sin(dLon/2) * Math.sin(dLon/2)
            ;
          var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
          var d = R * c; // Distance in km
          return d;
        }

        $scope.getDistanceFromLatLonInKm = function(lat,lon) {
          var R = 6371; // Radius of the earth in km
          var dLat = deg2rad(lat-$scope.mylat);  // deg2rad below
          var dLon = deg2rad(lon-$scope.mylng);
          var a =
            Math.sin(dLat/2) * Math.sin(dLat/2) +
            Math.cos(deg2rad($scope.mylat)) * Math.cos(deg2rad(lat)) *
            Math.sin(dLon/2) * Math.sin(dLon/2)
            ;
          var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
          var d = R * c; // Distance in km
          return d;
        }

        function deg2rad(deg) {
          return deg * (Math.PI/180)
        }



        $scope.getFriends = function(){
            console.log('/guests/friends/' + $cookies.get('id'));
            $http.get('/guests/friends/' + $cookies.get('id')).success(function(response){
                console.log("I got the data I requested!");
                $scope.friends = response;
            });
        }

        $scope.getFriends();

        $scope.zapocniOcenjivanje = function(reservation){
            $scope.ocena={};
            console.log(reservation);
            $scope.reservation=reservation;
            $http.post('/ocene/findByGuestAndReservation/'+$scope.reservation.reservation_id,$scope.loggedUser).success(function(response){
                if(response.ocena_id==-1){
                    $scope.ocena={};
                    $scope.ocena.ocenaRestorana=0;
                    $scope.ocena.ocenaUsluge=0;
                    $scope.ocena.guest=$scope.loggedUser;
                    $scope.ocena.reservation=$scope.reservation;
                }else{
                    $scope.ocena=response;
                }

            });

        }

        $scope.zavrsiOcenjivanje = function(){
            $http.put('/ocene/create',$scope.ocena).success(function(response){
                console.log("dodavanje odradio");
            });
            //console.log($scope.ocena);
        }

        $scope.logout = function (){
             $cookies.put('name', null);
             $cookies.put('id', null);
             $cookies.put('uloga',null);
        }

        $scope.zapocniRezervaciju = function(res){
            $scope.restoran = res;
            $scope.trajanje = null;
            $scope.datum = null;
            $scope.broj_stola = null;

            $scope.selectedElement = 0;


            console.log(res);
            $http.post('/restaurantregions/forRestaurant',$scope.restoran)
                            .success(function(response){
                                $scope.regions = response;
                                console.log($scope);
                                $http.post('/restauranttables/forRestaurant',$scope.restoran)
                                                .success(function(response){
                                                    $scope.tables = response;
                                                    console.log($scope);
                                                });
                            });
        };

        $scope.dict={};
        $scope.brojStolova=Object.keys($scope.dict).length;
        var RRID=-1;

        $scope.selectElement = function(evt) {
            var oldX = evt.offsetX;
            var oldY = evt.offsetY;

            $scope.selectedElement = evt.target;

            //console.log(oldX);
            //console.log(oldY);
            //console.log($scope.selectedElement);

            angular.forEach($scope.tables,function(value,index){
                if(oldX >= value.xvalue && oldX <= value.xvalue + value.width){
                    if(oldY >= value.yvalue && oldY <= value.yvalue + value.height){
                        $scope.id = value.restaurant_table_id;
                        $scope.X = value.xvalue;
                        $scope.Y = value.yvalue;

                        var resregID=value.restaurantRegion.restaurant_region_id;

                        if(Object.keys($scope.dict).length==0){
                            RRID=-1;
                        }else{
                            for(var key in $scope.dict){
                                //RRID=$scope.dict[key];
                                RRID=$scope.dict[key].restaurantRegion.restaurant_region_id;
                                break;
                            }
                        }

                        //$scope.regionID=value.restaurantRegion.restaurant_region_id;

                        if(!$scope.dict[$scope.id]){
                            if(RRID==-1 || RRID==resregID){
                                $scope.selectedElement.style.fill = 'green';
                                //$scope.dict[$scope.id] = resregID;
                                $scope.dict[$scope.id] = value;
                            }else
                                alert("NEMOZE DRUGI REGION");
                        }
                        else{
                            $scope.selectedElement.style.fill = 'red';
                            delete $scope.dict[$scope.id];
                        }

                        if(Object.keys($scope.dict).length==0){
                            RRID=-1;
                        }else{
                            for(var key in $scope.dict){
                                //RRID=$scope.dict[key];
                                RRID=$scope.dict[key].restaurantRegion.restaurant_region_id;
                                break;
                            }
                        }

                        $scope.brojStolova=Object.keys($scope.dict).length;

                        console.log(RRID);

                        console.log($scope.id);
                        console.log($scope.dict);
                    }
                }
            });
        }

        $scope.odabraniStolovi = new Array();
        $scope.ucitajStolove= function(){
            for(var key in $scope.dict)
                $scope.odabraniStolovi.push($scope.dict[key]);
            console.log($scope.odabraniStolovi);
        }


        var pozvaniPrijatelji = new Array();
        $scope.pozoviPrijatelja = function(id){
            pozvaniPrijatelji.push(id);
            for (var i = 0; i < $scope.friends.length; i++) {
                if($scope.friends[i].user_id == id){
                    $scope.friends.splice(i, 1);
                }
            }
        }

        $scope.korak1enabled = function(){
            return true;
        }



        $scope.rezervisi = function(id){
            console.log('/users/getOne/' + $cookies.get('id'));
            $http.get('/users/getOne/' + $cookies.get('id')).success(function(response){
                $scope.host = response;
                var rezervacija = {
                    restaurant : $scope.restoran,
                    host : $scope.host,
                    dateTime : $scope.datum,
                    duration : $scope.trajanje,
                    tables : $scope.odabraniStolovi
                };
                console.log(pozvaniPrijatelji);
                console.log("POKUSAVAM DA POSTOVO SAM!");
                $http.put('/reservations/add',rezervacija).success(function(response){
                    for (var i = 0; i < pozvaniPrijatelji.length; i++) {
                        $http.put('/invitations/add/' + pozvaniPrijatelji[i], response).success(function(response){
                            console.log("POSTOVO SAM!");
                        });
                    }
                    $scope.getFriends();
                });
            });



        };

    }
})();
