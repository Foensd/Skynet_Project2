
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
    	/*debugger;*/
    	var REQUEST_SERVICE_URI = 'http://localhost:8085/Project2/index.html#/play'
    	var playerObject = {} // create playerObject
    	
    	playerObject.AddPlayerToDB = function(user)
    	{
    		$http.post(REQUEST_SERVICE_URI, user).then(function (response) {
    			alert(response.status);
    		})
    	}
    	
    	return playerObject;
    	
    }])
   



