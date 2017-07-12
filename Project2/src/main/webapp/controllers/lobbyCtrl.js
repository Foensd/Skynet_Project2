var myApp = angular.module('myApp');

/*
 * CONTROLLERS METHOD
 */
myApp.controller('LobbyController', ['$scope', '$http', function($scope, $http) {
	
	$scope.message = "This is where we'll display everyone's name (from lobbyCtrl.js)";
	$scope.response = ['firstPersonName', 'secondPersonName']; // creating an array to be displayed
	
	$scope.allPlayers = [];
	
	$scope.getAllPlayersFromDB = function() {
		
		console.log("Trying to get users from DB");
		$http({
			url: '/Project2/lobby',
			method: 'GET',
		}).then(function successCallBack(response) {
			console.log("successfully got players");
			$scope.allPlayers = response.data;
		}, function errorCallBack(response){
			console.log("did not get players")
		});
		
	}
	
}]);

/*promise.then(function(response) {
allRoomsData.allRooms = response.data;
ForumService.allPlayers = response.data;
}, function(response) {

});*/
/*myApp.service("GetPlayersService", function($http, allPlayers){
	
	var playerData = this;
	
	playerData.allPlayers = [];
	
	playerData.getAllPlayers = function(){
		return $http({
			method: 'GET',
			url: 'Project2/lobby'
		});
	}
});
*/



