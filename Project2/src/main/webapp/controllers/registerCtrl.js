var myApp = angular.module('myApp');

/*
 * CONTROLLERS METHOD
 */

myApp.controller('RegisterController', [
		'$scope',
		'$http',
		function($scope, $http) {

			var REQUEST_SERVICE_URI = '/Project2/register.do';
			var playerObject = {} // create playerObject

			$scope.message = "Let's play!";
			$scope.message2 = "Please enter your name:";
			$scope.user = {
				username : ''
			};

			$scope.loadingRequest = true; // makes the loadingRequest true, so it will display 'loader' upon page load
			
			$scope.register = function() {
				$scope.loadingRequest = false; // set loadingRequest to false so it hides the input box
				$scope.userMessage = "One second... ";
				
				playerObject = $scope.user; // adding user to a playerObject
				console.log("REGISTER BUTTON WAS CLICKED");
					
				$http.post(REQUEST_SERVICE_URI, playerObject)
					.then(function successsCallBack(response) {
						$scope.userMessage = "Done! See, that wasn't so bad ;)";
						
						console.log("Creating new user(playerObject) with username: " + playerObject.username)
						
						
						var path = "/Project2/#/lobby";
						location.href = path;
					}, function errorCallBack(response) {
						console.log("Error. Sending them back to /register");
						$scope.loadingRequest = true; // set loadingRequest back to true so it stops spinning, and lets user try again
						
						alert("Username is either taken or blank");
					});

			}
		} ])
