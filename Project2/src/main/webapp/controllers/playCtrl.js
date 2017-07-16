var myApp = angular.module('myApp');

/*
 * CONTROLLERS METHOD
 */
myApp.controller('PlayController', ['$http', '$rootScope', '$scope', '$timeout', function($http, $rootScope, $scope, $timeout) {
	
	$scope.rolePredictions = ['Employee', 'Hacker', 'HR', 'Trainer'];
	
	
	getPlayers = function() {
		console.log("Looking for ALL user objects in DB");
		
		$http({
			url: '/Project2/getAllUsers.do',
			method: 'GET',
		})
		.then(function successCallBack(response) {  // goes in DB and returns list with usernames if successful 
			
			$scope.allPlayers = response.data;
			$scope.choice = $scope.allPlayers[0];
			console.log("SUCCESS - got all players");
			/*console.log("scope.allPLayers: " + $scope.allPlayers);*/
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
	
	$scope.msg = 'hi again';
	
	/*$scope.modalFunction = function() {
		console.log('opening pop up');
		var modalInstance = $modal.open({
			templateUrl: 'modal.html',
			controller: 'popCtrl'
		});
	}*/
	
	/*$scope.openModal = function(data) {
		console.log('Modal is openned!');
		$scope.modal = 'openned';
		var modalInstance = $modal.open({
			templateUrl: 'modal.html',
			resolve: {
				data: function() {
					return data === null ? {} : data;				
				}
			}
		});
 	};

	$scope.openModal();*/
	/*$scope.voteButton = false;*/
	$scope.voteButton = true;
	
	$scope.gameStart = function(){
		console.log('game starting');
		$scope.phase = 'day';
		$scope.action = 'Welcome to Revature Town... please look around and get acquainted with everything';
		
		/*$timeout($scope.goToNight, 10000);*/
	}
	
	$scope.goToNight = function() {
		console.log('Going to night');
		
		$scope.phase = 'night';
		$scope.action = 'Anyone with roles, perform your actions now. Employees go to sleep!';
		
		$scope.voteButton = true;
		
		$timeout($scope.goToDay, 5000);
	}
	
	$scope.goToDay = function() {
		$http({
			url: '/Project2/nightEnd.do',
			method: 'POST'
		})
		.then(function successCallBack(response) {  // goes in DB and returns list with usernames if successful 
			$scope.messages = response.data;
		}, function errorCallBack(response){
			console.log("Failed in nightActions")
		});
		console.log('Going to day');
		
		$scope.phase = 'day';
		$scope.action = 'Discuss along with you peers about what happened last night. Who did it?';
		
		$scope.voteButton = false;
		$timeout($scope.goToVoting, 5000);
	}
	
	$scope.goToVoting = function() {
		console.log('Starting voting');
		
		$scope.action = 'Vote for who you think should be fired';
		
		$scope.voteButton = true;
		
		$timeout($scope.goToTrial, 5000);
	}
	
	$scope.goToTrial = function() {
		$scope.voteButton = true;
		$http({
			url: '/Project2/getMostVoted.do',
			method: 'POST'
		})
		.then(function successCallBack(response) {  // goes in DB and returns list with usernames if successful 
			$scope.allPlayerstemp = response.data;
			$scope.onTrial = response.data.slice(allPlayerstemp.length-1, allPlayerstemp.length);
			$scope.allPlayers = allPlayerstemp.slice(0, allPlayerstemp.length-1);
		}, function errorCallBack(response){
			console.log("Failed in getMostVoted")
		});
		console.log('Starting trial');
		
		$scope.action = 'This person is being put on trial. Do you think this person is guilty or innocent?';
		
		$timeout($scope.goToClosing, 5000);
	}
	
	$scope.goToClosing = function() {
		$http({
			url: '/Project2/trial.do',
			method: 'POST',
			data: $scope.onTrial
		})
		.then(function successCallBack(response) {  // goes in DB and returns list with usernames if successful 
			$scope.allPlayerstemp = response.data;
			$scope.onTrial = response.data.slice(allPlayerstemp.length-1, allPlayerstemp.length);
			if($scope.onTrial.status.id == 1)
				$scope.message = $scope.onTrial.username + " was found innocent!";
			else
				$scope.message = $scope.onTrial.username + " was found guilty and was fired!";
			$scope.allPlayers = allPlayerstemp.slice(0, allPlayerstemp.length-1);
		}, function errorCallBack(response){
			console.log("Failed in Trial")
		});
		console.log('Starting closing');
		
		$scope.action = 'Trial results are in';
		
		$scope.voteButton = false;
		
		$timeout($scope.goToNight, 5000);
	}
	var test = this;
	setTarget = function(username) {
		$rootScope.user.targetUser = username;
		console.log(username);
		console.log(user.targetUser);
	};
	   
}]);



function openRole(evt, roleName) { //role tab being displayed
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
    document.getElementById(roleName).style.display = "block";
    evt.currentTarget.className += " active";
}

