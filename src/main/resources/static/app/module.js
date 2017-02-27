/**
 * Created by dan.geabunea on 3/29/2016.
 */
(function(){
    'use strict';

    var App = angular.module('app', ['ngRoute','ngCookies']);

    App.config(['$routeProvider', function ($routeProvider) {

        $routeProvider.when('/cars', {
            templateUrl: 'brt.html'

        });

        $routeProvider.when('/restorani', {
            templateUrl: 'html/restorani.html',
            controller: 'RestorauntsController'
        });

        $routeProvider.when('/rezervacije', {
            templateUrl: 'html/rezervacije.html',
            controller: 'ReservationController'

        });
        $routeProvider.when('/pozivnice', {
            templateUrl: 'html/pozivnice.html',
            controller: 'InvitationsController'
        });




        $routeProvider.when('/nalog', {
            templateUrl: 'html/nalog.html',
            controller: 'AccountController'
        });


        $routeProvider.when('/', {
            templateUrl: 'html/login.html',
            controller: 'LoginController'
        });

        $routeProvider.when('/register', {
            templateUrl: 'html/register.html',
            controller: 'LoginController'
        });









        $routeProvider.when('/kuka', {
                    templateUrl: 'html/kuka.html',
                    controller: 'KukaController'
                });

        $routeProvider.when('/WWWpregledRestorana', {
                            templateUrl: 'html/WWWpregledRestorana.html',
                            controller: 'WWWpregledRestoranaController'
                        });

        $routeProvider.when('/WWWpregledMenadzeraRestorana', {
                                    templateUrl: 'html/WWWpregledMenadzeraRestorana.html',
                                    controller: 'WWWpregledMenadzeraRestoranaController'
                                });

        $routeProvider.when('/WWWpregledMenadzeraSistema', {
                                            templateUrl: 'html/WWWpregledMenadzeraSistema.html',
                                            controller: 'WWWpregledMenadzeraSistemaController'
                                        });

        $routeProvider.when('/WWWkuvar', {
                            templateUrl: 'html/WWWkuvar.html',
                            controller: 'WWWkuvarController'
                        });

        $routeProvider.when('/WWWkuvarNalog', {
                                                    templateUrl: 'html/WWWkuvarNalog.html',
                                                    controller: 'WWWkuvarNalogController'
                                                });

        $routeProvider.when('/WWWkonobar', {
                                    templateUrl: 'html/WWWkonobar.html',
                                    controller: 'WWWkonobarController'
                                });

        $routeProvider.when('/WWWkonobarNalog', {
                                            templateUrl: 'html/WWWkonobarNalog.html',
                                            controller: 'WWWkonobarNalogController'
                                        });

        $routeProvider.when('/WWWsanker', {
                                            templateUrl: 'html/WWWsanker.html',
                                            controller: 'WWWsankerController'
                                        });

        $routeProvider.when('/WWWsankerNalog', {
                    templateUrl: 'html/WWWsankerNalog.html',
                    controller: 'WWWsankerNalogController'
                });

        $routeProvider.when('/noviponudjac', {
            templateUrl: 'html/vuksa.html',
            controller: 'VuksaController'
        });

        $routeProvider.when('/konkursi', {
            templateUrl: 'html/konkurs.html',
            controller: 'KonkursController'
        });

        $routeProvider.when('/salesmanorders', {
                    templateUrl: 'html/salesmanOrders.html',
                    controller: 'SalesmanordersController'
        });

        $routeProvider.when('/myoffers', {
                    templateUrl: 'html/myoffers.html',
                    controller: 'myoffersController'
        });

        $routeProvider.when('/profilrestorana', {
                    templateUrl: 'html/profilrestorana.html',
                    controller: 'profilrestoranaController'
        });

        $routeProvider.when('/jelovnik', {
                    templateUrl: 'html/jelovnik.html',
                    controller: 'jelovnikController'
        });

        $routeProvider.when('/salesmanaccount', {
                    templateUrl: 'html/salesmanaccount.html',
                    controller: 'salesmanaccountController'
        });

        $routeProvider.when('/konkursponude/:orderid', {
                    templateUrl: 'html/offersforOrder.html',
                    controller: 'offersfororderController'
        });

        $routeProvider.when('/noviradnik/', {
                    templateUrl: 'html/noviradnik.html',
                    controller: 'noviradnikController'
        });

        $routeProvider.when('/rasporedstolova/', {
                    templateUrl: 'html/rasporedSedenja.html',
                    controller: 'rasporedsedenjaController'
        });
        $routeProvider.otherwise({});
    }]);
})();
