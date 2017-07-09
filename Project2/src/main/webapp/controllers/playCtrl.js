var myApp = angular.module('myApp');

/*
 * CONTROLLERS METHOD
 */

myApp.controller('PlayController', function($scope) {
	
	$scope.message = "Let's play!";
	$scope.message2 = "Please enter your name";
	$scope.user = {
	     name: ''/*,
	     email: ''*/
    };
    $scope.register = function() {
       console.log('User clicked register', this.user);
    };
   
});
