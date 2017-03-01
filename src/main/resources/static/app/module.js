/**
 * Created by dan.geabunea on 3/29/2016.
 */
(function(){
    'use strict';

    var App = angular.module('app', ['ngRoute','ngCookies']);

    App.config(['$routeProvider', function ($routeProvider) {

        $routeProvider.when('/conf/:id',{
            templateUrl:'html/confirmation.html',
            controller: 'ConfirmationController'
        })

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

        $routeProvider.when('/WWWkuvarRasporedRada', {
                                            templateUrl: 'html/WWWkuvarRasporedRada.html',
                                            controller: 'WWWkuvarRasporedRadaController'
                                        });

        $routeProvider.when('/WWWkuvarPregledJela', {
                                                    templateUrl: 'html/WWWkuvarPregledJela.html',
                                                    controller: 'WWWkuvarPregledJelaController'
                                                });

        $routeProvider.when('/WWWkonobar', {
                                    templateUrl: 'html/WWWkonobar.html',
                                    controller: 'WWWkonobarController'
                                });

        $routeProvider.when('/WWWkonobarNalog', {
                                            templateUrl: 'html/WWWkonobarNalog.html',
                                            controller: 'WWWkonobarNalogController'
                                        });

        $routeProvider.when('/WWWkonobarRasporedRada', {
                                    templateUrl: 'html/WWWkonobarRasporedRada.html',
                                    controller: 'WWWkonobarRasporedRadaController'
                                });

        $routeProvider.when('/WWWkonobarPregledNarudzbina', {
                                                    templateUrl: 'html/WWWkonobarPregledNarudzbina.html',
                                                    controller: 'WWWkonobarPregledNarudzbinaController'
                                                    });

        $routeProvider.when('/WWWkonobarPregledGotovihNarudzbina', {
                                                            templateUrl: 'html/WWWkonobarPregledGotovihNarudzbina.html',
                                                            controller: 'WWWkonobarPregledGotovihNarudzbinaController'
                                                            });

        $routeProvider.when('/WWWkonobarPregledPrihvacenihNarudzbina', {
                                                            templateUrl: 'html/WWWkonobarPregledPrihvacenihNarudzbina.html',
                                                            controller: 'WWWkonobarPregledPrihvacenihNarudzbinaController'
                                                });

        $routeProvider.when('/WWWsanker', {
                                            templateUrl: 'html/WWWsanker.html',
                                            controller: 'WWWsankerController'
                                        });

        $routeProvider.when('/WWWsankerNalog', {
                    templateUrl: 'html/WWWsankerNalog.html',
                    controller: 'WWWsankerNalogController'
                });
        $routeProvider.when('/WWWsankerRasporedRada', {
                            templateUrl: 'html/WWWsankerRasporedRada.html',
                            controller: 'WWWsankerRasporedRadaController'
                        });

        $routeProvider.when('/WWWsankerPregledGotovihPica', {
                                    templateUrl: 'html/WWWsankerPregledGotovihPica.html',
                                    controller: 'WWWsankerPregledGotovihPicaController'
                                });

        $routeProvider.when('/WWWsankerPregledNarucenihPica', {
                                            templateUrl: 'html/WWWsankerPregledNarucenihPica.html',
                                            controller: 'WWWsankerPregledNarucenihPicaController'
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

        $routeProvider.when('/kartapica', {
                            templateUrl: 'html/kartapica.html',
                            controller: 'kartapicaController'
                });

        $routeProvider.when('/salesmanaccount', {
                    templateUrl: 'html/salesmanaccount.html',
                    controller: 'salesmanaccountController'
        });

        $routeProvider.when('/konkursponude/:orderid', {
                    templateUrl: 'html/offersforOrder.html',
                    controller: 'offersfororderController'
        });

        $routeProvider.when('/noviradnik', {
                    templateUrl: 'html/noviradnik.html',
                    controller: 'noviradnikController'
        });

        $routeProvider.when('/rasporedstolova', {
                    templateUrl: 'html/rasporedSedenja.html',
                    controller: 'rasporedsedenjaController'
        });

        $routeProvider.when('/dodavanjesmena', {
                    templateUrl: 'html/dodavanjeSmena.html',
                    controller: 'dodavanjesmenaController'
        });

        $routeProvider.when('/inforad', {
                    templateUrl: 'html/infoRad.html',
                    controller: 'inforadController'
        });

        $routeProvider.otherwise({});
    }]);
})();
