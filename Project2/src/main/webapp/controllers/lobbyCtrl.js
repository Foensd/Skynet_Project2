var myApp = angular.module('myApp');

/*
 * CONTROLLERS METHOD
 */
myApp.controller('LobbyController', ['$rootScope', '$scope', '$http', '$interval', '$location',
	function($rootScope, $scope, $http, $interval, $location) {
	
	//$rootScope.hideNavbar = true;
	
	console.log("currently in lobbyCtrl.js");
	$scope.message = "This is where we'll display everyone's name (from lobbyCtrl.js)";
	$scope.response = ['firstPersonName', 'secondPersonName']; // creating an array to be displayed
	
	$scope.loadingRequest = true; // makes the loadingRequest true, so it will display 'loader' upon page load
	
	getPlayers = function() {
		console.log("Trying to get users from DB");
		
		$http({
			url: '/Project2/lobby.do',
			method: 'GET',
		})
		.then(function successCallBack(response) {  // goes in DB and returns list with usernames if successful 
			
			$scope.allPlayers = response.data;
			console.log("successfully got players");
			console.log("scope.allPLayers: " + $scope.allPlayers)
			
			$scope.loadingRequest = false;  // hide the 'loader'
			
		}, function errorCallBack(response){
			console.log("did not get players")
			
			$scope.loadingRequest = false;
			$scope.message = "There are no other players online."
		});
	}
	
	allReady = function() {
		console.log("Checking if everybody is ready (Has a status)");
		
		$http({
			url: '/Project2/allReady.do',
			method: 'POST',
		})
		.then(function successCallBack(response) {  // goes in DB and returns list with usernames if successful 
			$interval.cancel(promise)
			$rootScope.user.role = response.data;
			var path = '/play';
			$location.path(path);
		}, function errorCallBack(response){
			console.log("Not all players are ready")
		});
	}
	
	$scope.ready = function() {
		var msg = 'Setting Employed status for: ' + $rootScope.user.username;
		$scope.userMessage = msg;
		playerObject = $rootScope.user; // adding user to a playerObject

		console.log("READY BUTTON WAS CLICKED FOR " + $rootScope.user.username + "!");
		
		$http({
			method: 'POST',
			url: '/Project2/readyButton.do',
			data: playerObject
			
		}).then(function successCallBack(response) {
				$scope.userMessage = "Set as Employed!";
				$rootScope.user.status = 'Employed';
				
				console.log("Setting " + playerObject.username + " as employed!");
				
				var path = '/lobby';
				$location.path(path);
			}, function  (response) {
				console.log("Error. Sending them back to /lobby");
			});

	}
	
	getPlayers(); // run getPlayers() function to retrieve players upon page load

	var promise = $interval(function() {getPlayers(), allReady()}, 10000); // interval to recheck getPlayers every 10 secs
}]);




