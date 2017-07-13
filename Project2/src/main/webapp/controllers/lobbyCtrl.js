var myApp = angular.module('myApp');

/*
 * CONTROLLERS METHOD
 */
myApp.controller('LobbyController', ['$scope', '$http', function($scope, $http) {
	
	console.log("currently in lobbyCtrl.js");
	$scope.message = "This is where we'll display everyone's name (from lobbyCtrl.js)";
	$scope.response = ['firstPersonName', 'secondPersonName']; // creating an array to be displayed
	
	//$scope.allPlayers = ['test1', 'test2'];
			
	/*var myVar;

	$scope.hideFunction = function() {
		console.log("In hideFunction");
	    myVar = setTimeout(showPage, 3000);
	}

	function showPage() {
	  document.getElementById("loader").style.display = "none";
	  document.getElementById("myDiv").style.display = "block";
	}*/
	
	console.log("Trying to get users from DB");
	$scope.loadingRequest = true;
	
	$http({
		url: '/Project2/lobby.do',
		method: 'GET',
	}).then(function successCallBack(response) {
		$scope.allPlayers = response.data;
		console.log("successfully got players");
		console.log("scope.allPLayers: " + $scope.allPlayers)
		
		$scope.loadingRequest = false;
		
	}, function errorCallBack(response){
		console.log("did not get players")
		
		$scope.loadingRequest = false;
		$scope.message = "There are no other players online."
	});
		
	
}]);




