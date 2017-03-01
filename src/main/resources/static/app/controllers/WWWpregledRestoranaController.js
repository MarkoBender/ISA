(function () {
    'use strict';

    angular
        .module('app')
        .controller('WWWpregledRestoranaController', WWWpregledRestoranaController);

    WWWpregledRestoranaController.$inject = ['$cookies','$http','$scope','$location','$window'];
    function WWWpregledRestoranaController($cookies,$http,$scope,$location,$window) {

        if($cookies.get('uloga') != 'SystemManager')
                    $location.url('/');

        $http.get('/systemManagers/findOne/'+$cookies.get('id'))
                                        .success(function(response){
                                            $scope.loggedUser = response;
                                            });

        $scope.logout = function (){
                     $cookies.put('name', null);
                     $cookies.put('id', null);
                     $cookies.put('uloga',null);
                     $location.url('/');
                }


        $http.get('/restaurants/all').success(function(response){
                    console.log("I got the data I requested!");
                    $scope.restaurants = response;
                });

        $scope.zapocniKreiranjeRestorana = function(){
                    $scope.restoran = {};

                    $('#modal-1').on('shown.bs.modal', function () {
                                mapa1();

                            });
                };

        $scope.zavrsiKreiranjeRestorana = function(){
                    console.log("usao");
                    $http.put('/restaurants/create', $scope.restoran).success(function(response){
                        console.log("Poslao sam nest!");
                        $window.location.reload();
                    });
                };

        $scope.zapocniIzmenuRestorana = function(id){
                    console.log("DOBIJENI ID JE "+id);
                    $scope.restoran = null;
                    $http.get('/restaurants/findOne/' + id).success(function(response){
                        $scope.restoran = response;
                        $('#modal-2').on('shown.bs.modal', function () {
                                    mapa2();
                                });
                    });
               };

        $scope.zavrsiIzmenuRestorana = function(){
                    console.log("usao");
                    $http.put('/restaurants/update/'+$scope.restoran.restaurant_id, $scope.restoran).success(function(response){
                        console.log("Poslao sam nest!");
                        $window.location.reload();
                    });
                };

        function mapa1(){
            var latlng = new google.maps.LatLng(4.3695030, 101.1224120);
            var myOptions = {
              zoom: 4,
              center: latlng,
              mapTypeId: google.maps.MapTypeId.ROADMAP
            };

            var map = new google.maps.Map(document.getElementById("map_container1"),myOptions);

            google.maps.event.addListener(map, 'click', function (evt) {
                    placeMarker(evt.latLng);
                    console.log(marker.getPosition().lat());
                    console.log(marker.getPosition().lng());
                    $scope.restoran.lat=marker.getPosition().lat();
                    $scope.restoran.lng=marker.getPosition().lng();
                    console.log("VRSIM IZMENU");
                    console.log($scope.restoran.lat);
                    console.log($scope.restoran.lng);
                });

            var marker;

            function placeMarker(location) {
                if (marker) {
                    //if marker already was created change positon
                    marker.setPosition(location);
                } else {
                    //create a marker
                    marker = new google.maps.Marker({
                        position: location,
                        map: map,
                        draggable: true
                    });
                }
            }
        }

        function mapa2(){
                    var latlng = new google.maps.LatLng($scope.restoran.lat, $scope.restoran.lng);
                    var myOptions = {
                      zoom: 4,
                      center: latlng,
                      mapTypeId: google.maps.MapTypeId.ROADMAP
                    };

                    var map = new google.maps.Map(document.getElementById("map_container2"),myOptions);

                    google.maps.event.addListener(map, 'click', function (evt) {
                            placeMarker(evt.latLng);
                            $scope.restoran.lat=marker.getPosition().lat();
                            $scope.restoran.lng=marker.getPosition().lng();
                        });

                    var marker = marker = new google.maps.Marker({
                         position: latlng,
                         map: map,
                         draggable: true
                     });

                    function placeMarker(location) {
                        if (marker) {
                            //if marker already was created change positon
                            marker.setPosition(location);
                        } else {
                            //create a marker
                            marker = new google.maps.Marker({
                                position: location,
                                map: map,
                                draggable: true
                            });
                        }
                    }
                }


    }
})();