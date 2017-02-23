(function () {
    'use strict';

    angular
        .module('app')
        .controller('InvitationsController', InvitationsController);

    InvitationsController.$inject = ['$scope','$http','$cookies'];

    function InvitationsController($scope,$http,$cookies) {

        var refresh = function(){
            $http.get('/invitations/activeNotConfirmed/' + $cookies.get('id')).success(function(response){
                console.log("I got the data I requested!" + response);
                $scope.notConfirmed = response;
                console.log(response);
            });

            $http.get('/invitations/activeConfirmed/' + $cookies.get('id')).success(function(response){
                console.log("I got the data I requested!");
                $scope.confirmed = response;
            });

        }

        refresh();

        $scope.potvrdi = function(pozivnica){
            $http.put('/invitations/confirmInvitation', pozivnica).success(function(response){
                console.log("Postovo sam brt moj!");
                refresh();
            });

        };


    }
})();
