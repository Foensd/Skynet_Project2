var myApp = angular.module('myApp');

/*
 * CONTROLLERS METHOD
 */

myApp.controller('PlayController', ['$http', '$rootScope', '$scope', function($http, $rootScope, $scope) {
	
	document.getElementById("chat").src="http://widget.mibbit.com/?settings=a4cc8e334a558b93d1cb07eb4ac82f51" + 
	"&server=irc.mibbit.net%3A%2B6697" +
	"&channel=%23Skynet_Game_Chat" +
	"&autoConnect=true" + 
	"&nick=" + $rootScope.user.username;

	$scope.rolePredictions = ['Employee', 'Hacker', 'HR', 'Trainer'];
	/*$scope.allPlayers = [
		{name: 'Player 1', status: 'Active'},
		{name: 'I have a really long name', status: 'Fired'},
		{name: 'Player 3', status: 'Active'},
		{name: 'Player 4', status: 'Fired'},
		{name: 'Player 5', status: 'Active'},
		{name: 'Player 4', status: 'Fired'},
		{name: 'Player 4', status: 'Fired'},
		{name: 'Player 4', status: 'Fired'},
		{name: 'Player 4', status: 'Fired'},
		{name: 'Player 4', status: 'Fired'}
	];*/
	
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
			$scope.numberOfPlayers = $scope.allPlayers.length;
			$scope.loadingRequest = false;  // hide the 'loader'
			
		}, function errorCallBack(response){
			console.log("did not get players")
			
			$scope.loadingRequest = false;
			$scope.message = "There are no other players online."
		});
	}
	
	getPlayers(); // run getPlayers() function to retrieve players upon page load
   
}]);

