var myApp = angular.module('myApp');

/*
 * CONTROLLERS METHOD
 */

myApp.controller('RulesController', function($scope, $interval) {
	
	$scope.message = "here is the rules page, from script.js";
	$scope.message2 = "Message 2 information here... from homeCtrl.js";
	
	$scope.counter = 0;
	$interval(function(){
		console.log("Iteration: " + $scope.counter);
		$scope.counter = $scope.counter + 1;
		}, 500, 15);
});

