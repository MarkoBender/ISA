(function () {
    'use strict';

    angular
        .module('app')
        .controller('ConfirmationController', ConfirmationController);



    ConfirmationController.$inject = ['$routeParams','$http','$cookies'];

    function ConfirmationController($routeParams,$http,$cookies) {


        console.log("brt");
        console.log($routeParams.id);



    }
})();
