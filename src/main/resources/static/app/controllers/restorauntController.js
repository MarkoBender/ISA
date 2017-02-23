(function () {
    'use strict';

    angular
        .module('app')
        .controller('RestorauntsController', RestorauntsController);

    RestorauntsController.$inject = ['$cookies','$http','$scope'];
    function RestorauntsController($cookies,$http,$scope) {

        $http.get('/restaurants/all').success(function(response){
            console.log("I got the data I requested!");
            $scope.restaurants = response;
        });

        $http.get('/reservations/allInactive/' + $cookies.get('id')).success(function(response){
            $scope.visited = response;
            console.log("Brrrrrrrrrrrrt");
            $http.get('/invitations/pastVisits/' + $cookies.get('id')).success(function(response){
                $scope.visited = $scope.visited.concat(response);
                console.log($scope.visited);
            });
        });



        console.log('/guests/friends/' + $cookies.get('id'));
        $http.get('/guests/friends/' + $cookies.get('id')).success(function(response){
            console.log("I got the data I requested!");
            $scope.friends = response;
        });

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
            console.log(res);
        };


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
                    duration : $scope.trajanje
                };
                console.log(pozvaniPrijatelji);
                console.log("POKUSAVAM DA POSTOVO SAM!");
                $http.put('/reservations/add',rezervacija).success(function(response){
                    for (var i = 0; i < pozvaniPrijatelji.length; i++) {
                        $http.put('/invitations/add/' + pozvaniPrijatelji[i], response).success(function(response){
                            console.log("POSTOVO SAM!");
                        });
                    }
                });
            });



        };

    }
})();
