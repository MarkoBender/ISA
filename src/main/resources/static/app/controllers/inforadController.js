(function () {
         'use strict';

         angular
             .module('app')
             .controller('inforadController', inforadController);

         inforadController.$inject = ['$cookies','$http','$scope','$location','$compile'];
         function inforadController($cookies,$http,$scope,$location,$compile) {

            if($cookies.get('uloga') != 'RestaurantManager')
                $location.url('/');

            $scope.logout = function (){
                     $cookies.put('name', null);
                     $cookies.put('id', null);
                     $cookies.put('uloga',null);
                     console.log('loggedout');
                };

            function parseSVG(s) {
                var div= document.createElementNS('http://www.w3.org/1999/xhtml', 'div');
                div.innerHTML= '<svg xmlns="http://www.w3.org/2000/svg">'+s+'</svg>';
                var frag= document.createDocumentFragment();
                while (div.firstChild.firstChild)
                    frag.appendChild(div.firstChild.firstChild);
                return frag;
            }

            $http.get('/restaurantManagers/findOne/'+$cookies.get('id'))
                .success(function(response){
                    $scope.loggedUser = response;
                    $http.post('/buyingorders/forRestaurant',$scope.loggedUser.restaurant)
                        .success(function(response){
                             $scope.buyingorders = response;
                        });
                    $http.post('/ocene/gradeforRestaurant',$scope.loggedUser.restaurant)
                        .success(function(response){
                            $scope.restaurantGrade = response;
                        });
                });

            $scope.logout = function (){
                         $cookies.put('name', null);
                         $cookies.put('id', null);
                         $cookies.put('uloga',null);
                    }
            $scope.click = function(){
                var xarray=[];
                var yarray=[];
                var q=0;
                for(var i = 0; i < 7; i++) {
                    (function(i){
                        console.log($scope.dateStart);
                        $http.post('/invitations/visitsForDay/'+$scope.loggedUser.restaurant.restaurant_id+'/'+i,$scope.dateStart)
                            .success(function(response){
                                var y = 410 - (4*response);
                                var x = (i+2) * 100;
                                xarray[i] = x;
                                yarray[i] = y;
                                var html = '<circle cx="'+ x +'" cy="'+ y +'" r="4" stroke="black" stroke-width="1" fill="blue" />'
                                var compiledeHTML = $compile(parseSVG(html))($scope);
                                angular.element(document.getElementById('svgcontainer')).append(compiledeHTML);
                                q++;

                                if(q==7){
                                    for(var w=0;w<7;w++){
                                        if(w!=6){
                                            var html2 = '<line x1="'+xarray[w]+'" x2="'+xarray[w+1]+'" y1="'+yarray[w]+'" y2="'+yarray[w+1]+'" style="stroke:rgb(0,0,255);stroke-width:1"></line>'
                                            var compiledeHTML = $compile(parseSVG(html2))($scope);
                                            angular.element(document.getElementById('svgcontainer')).append(compiledeHTML);
                                        }
                                    }
                                }

                                console.log(q);
                        });
                    })(i);
                }
            };

            $scope.productGrade = function (){
                var flag=false;
                $http.post('/foodndrinks/itemFromName/'+$scope.productName,$scope.loggedUser.restaurant)
                    .success(function(response){
                        $scope.menuitem = response;
                        for(var prop in $scope.menuitem) {
                                if($scope.menuitem.hasOwnProperty(prop))
                                    flag=true;
                            }
                            if(flag){
                                $scope.error="";
                                $http.post('/ocene/gradeForProduct',$scope.menuitem)
                                    .success(function(response){
                                       $scope.averageProductGrade = response;
                                    });
                            }
                            else
                                $scope.error = "Jelo ili pice ne postoji na meniju restorana!";
                    });
            };

            $scope.konobarGrade = function(){
                var flag=false;
                $http.post('/stewards/getfromName/'+$scope.konobarName+'/'+$scope.konobarSurname,$scope.loggedUser.restaurant)
                    .success(function(response){
                        $scope.steward = response;
                        console.log($scope);
                        for(var prop in $scope.steward) {
                            if($scope.steward.hasOwnProperty(prop))
                                flag=true;
                        }
                        if(flag){
                            $scope.errorSteward="";
                            $http.post('/ocene/gradeForSteward',$scope.steward)
                                .success(function(response){
                                    $scope.averageStewardGrade = response;
                                    console.log($scope);
                                });
                            $http.post('/bills/cashForSteward',$scope.steward)
                                .success(function(response){
                                    $scope.cashMoneySteward = response;
                                });
                        }
                        else{
                            $scope.errorSteward="Konobar ne postoji!";
                        }
                    });
            };

            $scope.cash = function (){
                if($scope.cashFrom != null && $scope.cashTo != null){
                    $http.post('/bills/cashForRestaurant/'+$scope.cashFrom.getTime()+'/'+$scope.cashTo.getTime(),$scope.loggedUser.restaurant)
                        .success(function(response){
                            $scope.cashMoney = response;
                        });
                }
            };

         }
})();