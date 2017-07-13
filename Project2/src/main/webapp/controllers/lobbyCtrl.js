var myApp = angular.module('myApp');

/*
 * CONTROLLERS METHOD
 */
myApp.controller('LobbyController', ['$scope', '$http', '$interval', function($scope, $http, $interval) {
	
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
		.then(function successCallBack(response) {  // goes in DB and returns list with usernames if succesfull 
			
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
	
	getPlayers(); // run getPlayers() function to retrieve players upon page load

	var promise = $interval(function() {getPlayers()}, 10000); // interval to recheck getPlayers every 10 secs
}]);




