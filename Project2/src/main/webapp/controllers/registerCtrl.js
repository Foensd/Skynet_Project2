var myApp = angular.module('myApp');

/*
 * CONTROLLERS METHOD
 */

myApp.controller('RegisterController', [
		'$scope',
		'$http',
		'$location',
		function($scope, $http,$location) {

			var REQUEST_SERVICE_URI = '/Project2/register.do';
			var playerObject = {} // create playerObject

			$scope.message = "Let's play!";
			$scope.message2 = "Please enter your name:";
			$scope.user = {
				username : ''
			};

			$scope.register = function() {
			
				playerObject = $scope.user; // adding user to a playerObject
				console.log('playerObject.username: ' + playerObject.username);
			
				console.log("REGISTER BUTTON WAS CLICKED");
				$http.post(REQUEST_SERVICE_URI, playerObject).then(
						function successsCallBack(response) {
							//var path = "/Project2/#/lobby";
							 var path = null;
						$location.path("/lobby");
						}, function() {
							alert("Username is either taken or blank");
						});

			}
		} ])
