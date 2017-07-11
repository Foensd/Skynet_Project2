
var myApp = angular.module('myApp');

/*
 * CONTROLLERS METHOD
 */

myApp.controller('PlayController', function($scope, PlayerService) {
	
	$scope.message = "Let's play!";
	$scope.message2 = "Please enter your name";
	$scope.user = {
	     name: ''/*,
	     email: ''*/
    };
    $scope.register = function() {
      PlayerService.AddPlayerToDB($scope.user);
    }
})
    .factory("PlayerService", ['$http', function($http){ //Creating factory named "PlayerService 
    	debugger;
    	var REQUEST_SERVICE_URI = 'http://13.59.197.145:8085/Project2/#/play'
    	var playerObject = {} // create playerObject
    	
    	playerObject.AddPlayerToDB = function(user)
    	{
    		$http.post(REQUEST_SERVICE_URI, user).then(function (response) {
    			alert(response.status);
    		})
    	}
    	
    	return playerObject;
    	
    }])
   



