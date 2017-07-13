var myApp = angular.module('myApp');

/*
 * CONTROLLERS METHOD
 */

myApp.controller('PlayController', [
		'$scope',
		'$http',
		function($scope, $http) {

			var REQUEST_SERVICE_URI = '/Project2/play.do';
			var playerObject = {} // create playerObject

			$scope.message = "Let's play!";
			$scope.message2 = "Please enter your name:";
			$scope.user = {
				username : ''
			};

			$scope.register = function() {
				// var path = "/Project2/#/lobby";

				playerObject = $scope.user; // adding user to a playerObject
				console.log('playerObject.username: ' + playerObject.username);
				// console.log('playerObject: ' + $scope.user.name);

				console.log("REGISTER BUTTON WAS CLICKED");
				$http.post(REQUEST_SERVICE_URI, playerObject).then(
						function successsCallBack(response) {
							var path = "/Project2/#/lobby";
							location.href = path;
						}, function() {
							alert("Username is either taken or blank");
						});

			}
		} ])
