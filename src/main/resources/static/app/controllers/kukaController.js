(function () {
         'use strict';

         angular
             .module('app')
             .controller('KukaController', KukaController);

         KukaController.$inject = ['$cookies','$http','$scope'];
         function KukaController($cookies,$http,$scope) {

         }
     })();