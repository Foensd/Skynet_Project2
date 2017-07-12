
var myApp = angular.module('myApp');

/*
 * CONTROLLERS METHOD
 */

myApp.controller('PlayController', ['$scope', '$http', function($scope, $http) {
	
	var REQUEST_SERVICE_URI = '/Project2/play.do';
	//var REQUEST_SERVICE_URI = 'http://13.59.197.145:8085/Project2/#/play';
    var playerObject = {} // create playerObject
	
	$scope.message = "Let's play!";
	$scope.message2 = "Please enter your name";
	$scope.user = {
	     username: ''/*,
	     email: ''*/
    };
	
	
    $scope.register = function() {
    	
    	playerObject = $scope.user;  // adding user to a playerObject
    	console.log('playerObject.username: ' + playerObject.username);
    	//console.log('playerObject: ' + $scope.user.name);
    	
    	console.log("REGISTER BUTTON WAS CLICKED");
    	$http.post(REQUEST_SERVICE_URI, playerObject)
    		.then(function(playerObject) {
	            console.log("SUCCESSFULLY ENTERED A USER");
	        	//$scope.user = data;
	        });
    }
}])
    /*.factory("PlayerService", ['$http', function($http){ //Creating factory named "PlayerService 
    	debugger;
    	
    	
    	playerObject.AddPlayerToDB = function(user)
    	{
    		$http.post(REQUEST_SERVICE_URI, user).then(function (response) {
    			alert(response.status);
    		})
    	}
    	//rest request
    	return playerObject;
    	
    }])*/
   



