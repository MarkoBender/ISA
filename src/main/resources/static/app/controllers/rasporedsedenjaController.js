(function () {
    'use strict';

    angular
        .module('app')
        .controller('rasporedsedenjaController', rasporedsedenjaController)
        .directive('tableSetup',tableSetup);

    function tableSetup (){
        return{
            template:'<h3>dasomldasom</h3>'
        }
    }

    rasporedsedenjaController.$inject = ['$cookies','$http','$scope','$compile','$injector','$window'];
    function rasporedsedenjaController($cookies,$http,$scope,$compile,$injector,$window) {


            $http.get('/restaurantManagers/findOne/'+$cookies.get('id'))
                        .success(function(response){
                            $scope.loggedUser = response;
                            $http.post('/restaurantregions/forRestaurant',$scope.loggedUser.restaurant)
                                .success(function(response){
                                    $scope.regions = response;
                                    console.log($scope);
                                });
                            $http.post('/restauranttables/forRestaurant',$scope.loggedUser.restaurant)
                                .success(function(response){
                                    $scope.tables = response;
                                    console.log($scope);
                                });

                        });


            $scope.tableaction={};

             function parseSVG(s) {
                    var div= document.createElementNS('http://www.w3.org/1999/xhtml', 'div');
                    div.innerHTML= '<svg xmlns="http://www.w3.org/2000/svg">'+s+'</svg>';
                    var frag= document.createDocumentFragment();
                    while (div.firstChild.firstChild)
                        frag.appendChild(div.firstChild.firstChild);
                    return frag;
             }

             $scope.doClick = function(event){
                if($scope.tableaction=='add'){

                    $scope.newtable = {};


                    var offsetX = event.offsetX;
                    var offsetY = event.offsetY;
                    $scope.newtable.xvalue = offsetX;
                    $scope.newtable.yvalue = offsetY;
                    $scope.newtable.width = 35;
                    $scope.newtable.height = 35;
                    $scope.newtable.restaurant = $scope.loggedUser.restaurant;
                    $http.post('/restaurantregions/forRestaurant',$scope.loggedUser.restaurant)
                        .success(function(response){
                            $scope.regions = response;
                            angular.forEach($scope.regions,function(value,index){
                                if(offsetX > value.xvalue && offsetX < value.xvalue + value.width){
                                    if(offsetY > value.yvalue && offsetY < value.yvalue + value.height){
                                        $scope.newtable.restaurantRegion = value;
                                    }
                                }
                            })
                    });
                    var html = '<rect x="'+ offsetX +'" y="'+ offsetY +'" rx="5" ry="5" width="35" height="35"' +
                         ' style="fill:red;stroke:black;stroke-width:5;opacity:0.8" transform="translate(0,0)"'+
                         'ng-mousedown = "selectElement($event)" ng-mousemove="moveElement($event)" ng-mouseup="dropElement()"/>';

                    var compiledeHTML = $compile(parseSVG(html))($scope);
                    angular.element(document.getElementById('svgcontainer')).append(compiledeHTML);

                    console.log($scope);
                    $http.put('/restauranttables/create',$scope.newtable)
                        .success(function(response){
                            console.log('created table!');
                            $window.location.reload();
                        });

                }
             };

            $scope.tempX = 0;
            $scope.tempY = 0;
            $scope.finalX = 0;
            $scope.finalY = 0;
            $scope.oldX = 0;
            $scope.oldY = 0;
            $scope.translatedX =0;
            $scope.translatedY =0;

             $scope.selectedElement = 0;
             $scope.currentX = 0;
             $scope.currentY = 0;
             $scope.currentMatrix = 0;
             $scope.flag = false;

             $scope.id=0;
             $scope.selectElement = function(evt) {
                if($scope.tableaction=='edit'){
                    var oldX = evt.offsetX;
                    var oldY = evt.offsetY;

                    $scope.selectedElement = evt.target;
                    $scope.currentX = evt.clientX;
                    $scope.currentY = evt.clientY;
                    $scope.currentMatrix = $scope.selectedElement.getAttributeNS(null, "transform").slice(10,-1).split(',');
                    for(var i=0; i<$scope.currentMatrix.length; i++) {
                        $scope.currentMatrix[i] = parseFloat($scope.currentMatrix[i]);
                    }
                    $scope.flag = true;

                    angular.forEach($scope.tables,function(value,index){
                        if(oldX >= value.xvalue && oldX <= value.xvalue + value.width){
                            if(oldY >= value.yvalue && oldY <= value.yvalue + value.height){
                                $scope.id = value.restaurant_table_id;
                                $scope.oldX = value.xvalue;
                                $scope.oldY = value.yvalue;
                            }
                        }
                    })
                }
                if($scope.tableaction=='delete'){
                    var offsetX = evt.offsetX;
                    var offsetY = evt.offsetY;
                    $http.post('/restauranttables/forRestaurant',$scope.loggedUser.restaurant)
                        .success(function(response){
                            $scope.tables = response;
                            angular.forEach($scope.tables,function(value,index){
                                if(offsetX >= value.xvalue && offsetX <= value.xvalue + value.width){
                                    if(offsetY >= value.yvalue && offsetY <= value.yvalue + value.height){
                                        console.log(value.restaurant_table_id);
                                        $http.delete('/restauranttables/delete/'+value.restaurant_table_id)
                                            .success(function(response){
                                                console.log('deleted table!');
                                                $window.location.reload();
                                            });
                                    }
                                }
                            })
                        });
                }
             };


             $scope.moveElement = function(evt){
                if($scope.flag){
                    var dx = evt.clientX - $scope.currentX;
                    var dy = evt.clientY - $scope.currentY;
                    $scope.currentMatrix[0] += dx;
                    $scope.currentMatrix[1] += dy;
                    $scope.translatedX += dx;
                    $scope.translatedY += dy;
                    var newMatrix = "translate(" + $scope.currentMatrix.join(',') + ")";
                    $scope.selectedElement.setAttributeNS(null, "transform", newMatrix);
                    $scope.currentX = evt.clientX;
                    $scope.currentY = evt.clientY;
                }
             };
            $scope.changedtable ={};

            $scope.dropElement = function(){
                if($scope.tableaction=='edit'){
                    console.log($scope.translatedX);
                    console.log($scope.translatedY);
                    var newX = $scope.oldX + $scope.translatedX;
                    var newY = $scope.oldY + $scope.translatedY;
                    $http.post('/restaurantregions/forRestaurant',$scope.loggedUser.restaurant)
                            .success(function(response){
                                $scope.regions = response;
                                angular.forEach($scope.regions,function(value,index){
                                    if(newX > value.xvalue && newX < value.xvalue + value.width){
                                        if(newY > value.yvalue && newY < value.yvalue + value.height){
                                            $scope.changedtable.xvalue = newX;
                                            $scope.changedtable.yvalue = newY;
                                            $scope.changedtable.width = 35;
                                            $scope.changedtable.height = 35;
                                            $scope.changedtable.restaurantRegion = value;
                                            $scope.changedtable.restaurant = $scope.loggedUser.restaurant;
                                            console.log($scope);
                                            $http.put('/restauranttables/update/'+$scope.id,$scope.changedtable)
                                                .success(function(response){
                                                    console.log('updated table!');
                                                });
                                        }
                                    }
                                })
                        });
                     $scope.selectedElement = 0;
                     $scope.currentX = 0;
                     $scope.currentY = 0;
                     $scope.currentMatrix = 0;
                     $scope.flag = false;
                     $scope.oldX = 0;
                     $scope.oldY = 0;
                     $scope.translatedX =0;
                     $scope.translatedY =0;
                     $scope.id_toChange=0;
                     $window.location.reload();
                }
            };

            $scope.startDrag = function (evt){
                if($scope.tableaction=='test'){
                    $scope.tempX = event.offsetX;
                    $scope.tempY = event.offsetY;
                }
            };

            $scope.finishDrag = function (evt){
                if($scope.tableaction=='test'){
                    $scope.finalX = event.offsetX;
                    $scope.finalY = event.offsetY;

                    console.log($scope.tempX);
                    console.log($scope.tempY);
                    console.log($scope.finalX);
                    console.log($scope.finalY);
                    var beginX = 0;
                    if($scope.tempX < $scope.finalX)
                        beginX = $scope.tempX;
                    else
                        beginX = $scope.finalX;

                    var beginY = 0;
                    if($scope.tempY < $scope.finalY)
                        beginY = $scope.tempY;
                    else
                        beginY = $scope.finalY;

                    var html = '<rect x="'+ beginX +'" y="'+ beginY +'" rx="5" ry="5" width="'+ Math.abs($scope.tempX - $scope.finalX) +'" height="'+ Math.abs($scope.tempY - $scope.finalY) +'"' +
                             ' style="fill:'+$scope.newregion.color+';stroke:black;stroke-width:5;opacity:0.2" transform="translate(0,0)"'+
                             'ng-click="doClick($event)" />';

                    var compiledeHTML = $compile(parseSVG(html))($scope);
                    angular.element(document.getElementById('svgcontainer')).append(compiledeHTML);

                    $scope.newregion.xvalue = beginX;
                    $scope.newregion.yvalue = beginY;
                    $scope.newregion.width = Math.abs($scope.tempX - $scope.finalX);
                    $scope.newregion.height = Math.abs($scope.tempY - $scope.finalY);
                    $scope.newregion.restaurant = $scope.loggedUser.restaurant;
                    console.log($scope);
                    $http.put('/restaurantregions/create',$scope.newregion)
                        .success(function(response){
                            console.log('region created!');
                            $window.location.reload();
                        });

                    $scope.tempX = 0;
                    $scope.tempY = 0;
                    $scope.finalX = 0;
                    $scope.finalY = 0;

                }

            }
    }
})();