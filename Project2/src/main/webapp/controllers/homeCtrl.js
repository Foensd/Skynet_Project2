var myApp = angular.module('myApp');

/*
 * CONTROLLERS METHOD
 */

//create the controller and inject Angular's $scope
myApp.controller('HomeController', ['$scope', '$location', function($scope, $location) {

   $scope.message = 'home page test mesasge from homeCtrl.js'; // create a message to display in our view
   $scope.go = function(path) {  // custom function that takes the 'path' from the button click
	   $location.path(path);
	}
   $scope.readRules = function() {
	   alert("Print rules");
   }
   
}]);

