var myApp = angular.module('myApp');

/*
 * CONTROLLERS METHOD
 */
myApp.controller('PlayController', ['$http', '$rootScope', '$scope', '$timeout', function($http, $rootScope, $scope, $timeout) {
	
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
	
	$scope.status = ['Employee', 'Fired'];
	
	$timeout(function() {document.getElementById("chat").src="http://widget.mibbit.com/?settings=a4cc8e334a558b93d1cb07eb4ac82f51" + 
	"&server=irc.mibbit.net%3A%2B6697" +
	"&channel=%23Skynet_Game_Chat" +
	"&autoConnect=true" + 
	"&nick=" + $rootScope.user.username;},500);

	getPlayers(); // run getPlayers() function to retrieve players upon page load
   
}]);

function openCity(evt, cityName) {
    // Declare all variables
    var i, tabcontent, tablinks;

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
}

