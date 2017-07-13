var myApp = angular.module('myApp');

/*
 * CONTROLLERS METHOD
 */
myApp.controller('LobbyController', ['$scope', '$http', '$interval', function($scope, $http, $interval) {
	
	console.log("currently in lobbyCtrl.js");
	$scope.message = "This is where we'll display everyone's name (from lobbyCtrl.js)";
	$scope.response = ['firstPersonName', 'secondPersonName']; // creating an array to be displayed
	
	//$scope.allPlayers = ['test1', 'test2'];
			
	getPlayers = function() {
		console.log("Trying to get users from DB");
		$http({
			url: '/Project2/lobby.do',
			method: 'GET',
		}).then(function successCallBack(response) {
			$scope.allPlayers = response.data;
			console.log("successfully got players");
			console.log("vm.allPLayers: " + $scope.allPlayers)
		}, function errorCallBack(response){
			console.log("did not get players")
		});
	}
	
	var promise = $interval(function() {getPlayers()}, 10000);
	
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



