(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$cookies','$scope','$http','$location'];
    //AccountController.$inject = ['$scope'];

    function LoginController($cookies,$scope,$http,$location) {

        if($cookies.get('uloga') == 'Guest')
            $location.url('/restorani');
        else if($cookies.get('uloga') == 'SystemManager')
            $location.url('/kuka');
        else if($cookies.get('uloga') == 'RestaurantManager')
            $location.url('/konkursi');
        else if($cookies.get('uloga') == 'Salesman')
            $location.url('/salesmanorders');
        else if($cookies.get('uloga') == 'Steward')
            $location.url('/WWWkonobar');
        else if($cookies.get('uloga') == 'Cook')
            $location.url('/WWWkuvar');
        else if($cookies.get('uloga') == 'Barman')
            $location.url('/WWWsanker');


        $scope.register = function(){
            $http.post('/users/existsEmail',$scope.newUser.email)
                .success(function(response){
                    if(response == false){
                        $http.put('/guests/create', $scope.newUser)
                            .success(function(response){
                                console.log("Dodao sam usera");
                            });
                    }
                    else
                        alert("Email zauzet! Stavite drugi");
                });
        };

        //redirect deo
        $scope.exists = function(){
            $http.put('/users/exists', $scope.loggingUser).success(function(response){
                if(response.email != null){
                    console.log(response);
                    $cookies.put('name', response.name);
                    $cookies.put('id', response.user_id);
                    $cookies.put('uloga',response.uloga);
                    if(response.uloga == 'Guest')
                        $location.url('/restorani');
                    else if(response.uloga == 'SystemManager')
                        $location.url('/kuka');
                    else if(response.uloga == 'RestaurantManager')
                        $location.url('/konkursi');
                     else if(response.uloga == 'Salesman')
                        $location.url('/salesmanorders');
                     else if($cookies.get('uloga') == 'Steward')
                         $location.url('/WWWkonobar');
                     else if($cookies.get('uloga') == 'Cook')
                         $location.url('/WWWkuvar');
                     else if($cookies.get('uloga') == 'Barman')
                         $location.url('/WWWsanker');
                }
                else{
                    alert('Neuspesno logovanje');
                }
            });


        };

    }

  function passwordVerify() {
    return {
      restrict: 'A', // only activate on element attribute
      require: '?ngModel', // get a hold of NgModelController
      link: function(scope, elem, attrs, ngModel) {
        if (!ngModel) return; // do nothing if no ng-model

        // watch own value and re-validate on change
        scope.$watch(attrs.ngModel, function() {
          validate();
        });

        // observe the other value and re-validate on change
        attrs.$observe('passwordVerify', function(val) {
          validate();
        });

        var validate = function() {
          // values
          var val1 = ngModel.$viewValue;
          var val2 = attrs.passwordVerify;

          // set validity
          ngModel.$setValidity('passwordVerify', val1 === val2);
        };
      }
    }
  }


})();
