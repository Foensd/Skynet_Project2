var myApp = angular.module('myApp');

/*
 * CONTROLLERS METHOD
 */
myApp.controller('PlayController', ['$http', '$rootScope', '$scope', '$timeout', '$q', function($http, $rootScope, $scope, $timeout, $q) {
	
	$scope.rolePredictions = ['Employee', 'Hacker', 'HR', 'Trainer'];
	$scope.choice;
	$scope.voteButton = false;
	$scope.responseMessages = false;
	$scope.gameStatusMsg = 'no message yet';

	$scope.voteAction = function(){
		$rootScope.user.targetUser = $scope.choice;
		console.log("targetUser" + $rootScope.user.targetUser);
		$http({
			url: '/Project2/action.do',
			method: 'POST',
			data: $rootScope.user
		})
		.then(function successCallBack(response) {  // goes in DB and returns list with usernames if successful 
			console.log("SUCCESS - updated target");
		}, function errorCallBack(response){
			console.log("Failed in voteAction's request to updateTarget")
		});

	}
	
	
	getPlayers = function() {
		console.log("Looking for ALL user objects in DB");
		
		$http({
			url: '/Project2/getAllUsers.do',
			method: 'GET',
		})
		.then(function successCallBack(response) {  // goes in DB and returns list with usernames if successful 
			// returns an array list with all users
			// username(String), role(Object), status(Object), targetUser(String)
			$scope.allPlayers = response.data;
			
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
	getPlayers(); // run getPlayers() function to retrieve players upon page load
	
	// chat
	$timeout(function() {document.getElementById("chat").src="http://widget.mibbit.com/?settings=a4cc8e334a558b93d1cb07eb4ac82f51" + 
	"&server=irc.mibbit.net%3A%2B6697" +
	"&channel=%23Skynet_Game_Chat" +
	"&autoConnect=true" + 
	"&nick=" + $rootScope.user.username;},500);

	
	
	
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
 	
	//time function with 5 second delay
	countDown = function(timerAmount) {   // passing in timerAmount value from function call
		$scope.clock = timerAmount;
		return $q(function(resolve, reject) {
			var time = $timeout(function () {
		        var timer = setInterval(function () {
		            if ($scope.clock > 0) {
		                $scope.clock--;
		            } else {
		                clearInterval(timer);
		                resolve('GOING TO NEXT PHASE');
		            }
		            $scope.$apply();
		        }, 1000);
		    }, 5000);	// 5 second delay before counter begins
		});
	}
	
	// checking if person can vote at night
	voteCredAtNight = function(){
		
		console.log('Role decription: ' + $rootScope.user.role);
		console.log('Status : ' + $rootScope.user.status);
		if($rootScope.user.role != 'Employee' && $rootScope.user.status == 'Active')
		{
			$scope.voteButton = true;
			console.log('This person can vote at night...');
		}
		else {
			$scope.voteButton = false;
			console.log('This person CANNOT vote at night...');
			alert('YOU CANNOT VOTE!');
		}
	}
	
	voteCredAtDay = function() {
		if($rootScope.user.status == 'Active')
		{
			$scope.voteButton = true;
			console.log('This person can vote during day...');
		}
		else {
			$scope.voteButton = false;
			console.log('This person IS DEAD - CANNOT vote');
			alert('YOU ARE DEAD! DEAD PEOPLE CANNNOT VOTE');
		}
	}
	
	endGame = function(finalMsg){
		console.log('--in endGame function');
		
		if (finalMsg == 'EmployeesWin') {
			console.log('EmployeesWin');
			$scope.gameStatusMsg = 'There are no more hackers, the Employees have won!';
		}
		else if (finalMsg == 'HackersWin') {
			console.log('HackersWin');
			$scope.gameStatusMsg = 'The Hackers have outnumbered the Employees, the Hackers have won!';
		}
		alert($scope.gameStatusMsg);
	}
	
	$scope.gameStart = function(){
		console.log('game starting');
		$scope.phase = 'day';
		$scope.action = 'Welcome to Revature Town... please look around and get acquainted with everything';
		
		
		var promise = countDown(10); // passing x amount of seconds to perform the timer in countDown()
		//when function above resolves, it returns a promise, which lets us perform the following actions:
		promise.then(function(promiseResolve){  
			console.log('PROMISE: ' + promiseResolve);
			goToNight();
		});

	}
	
	
	goToNight = function() {
		console.log('Going to night');
		
		$scope.phase = 'night';
		$scope.action = 'Anyone with roles, perform your actions now. Employees go to sleep!';
		
		voteCredAtNight();
	
		var promise = countDown(30); // passing x amount of seconds to perform the timer in countDown()
		//when function above resolves, it returns a promise, which lets us perform the following actions:
		promise.then(function(promiseResolve){  
			console.log('PROMISE: ' + promiseResolve);
			goToDay();
		});
	}
	
	goToDay = function() {
		$scope.responseMessages = true;
		$http({
			url: '/Project2/nightEnd.do',
			method: 'POST'
		})
		.then(function successCallBack(response) {  // goes in DB and returns list with usernames if successful 
			$scope.messages = response.data;
			console.log('$SCOPE.MESSAGES' + $scope.messages);
			if ($scope.messages[2] == 'HackersWin' || $scope.messages[2] == 'EmployeesWin')
			{
				console.log('GAME IS OVER - going to endGame function');
				endGame($scope.messages[3]);
			}
			else if ($scope.messages[3] == 'NoWin') {
				console.log('NoWin');
				$scope.gameStatusMsg = 'There are still Hackers out there, but the Employees still have a chance!';
			}
			
		}, function errorCallBack(response){
			console.log("Failed in nightActions")
		});
		console.log('Going to day');
		
		getPlayers();
		
		$scope.phase = 'day';
		$scope.action = 'Discuss along with you peers about what happened last night. Who did it?';
		
		$scope.voteButton = false;
		
		var promise = countDown(40); // passing x amount of seconds to perform the timer in countDown()
		//when function above resolves, it returns a promise, which lets us perform the following actions:
		promise.then(function(promiseResolve){  
			console.log('PROMISE: ' + promiseResolve);
			goToVoting();
		});
	}
	
	goToVoting = function() {
		
		console.log('Starting voting');
		
		$scope.action = 'Vote for who you think should be fired';
		
		voteCredAtDay();
		
		var promise = countDown(40); // passing x amount of seconds to perform the timer in countDown()
		//when function above resolves, it returns a promise, which lets us perform the following actions:
		promise.then(function(promiseResolve){  
			console.log('PROMISE: ' + promiseResolve);
			goToTrial();
		});
	}
	
	goToTrial = function() {
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
		
		var promise = countDown(40); // passing x amount of seconds to perform the timer in countDown()
		//when function above resolves, it returns a promise, which lets us perform the following actions:
		promise.then(function(promiseResolve){  
			console.log('PROMISE: ' + promiseResolve);
			goToClosing();
		});
	}
	
	goToClosing = function() {
		
		$http({
			url: '/Project2/trial.do',
			method: 'POST',
			data: $scope.onTrial
		})
		.then(function successCallBack(response) {  // goes in DB and returns list with usernames if successful 
			$scope.allPlayerstemp = response.data;
			$scope.onTrial = response.data.slice(allPlayerstemp.length-1, allPlayerstemp.length);
			if($scope.onTrial.status.id == 1)
				$scope.message1 = $scope.onTrial.username + " was found innocent!";
			else {
				$scope.message1 = $scope.onTrial.username + " was found guilty and was fired!";
				$http({
					url: '/Project2/afterGuiltyTrial.do',
					method: 'POST'
				})
				.then(function successCallBack(response) {
					$scope.message2 = response.data;
				}, function errorCallBack(response) {
					console.log("Failed in afterGuiltyTrial");
				});
			}
			$scope.allPlayers = allPlayerstemp.slice(0, allPlayerstemp.length-1);
		}, function errorCallBack(response){
			console.log("Failed in Trial")
		});
		
		console.log('Starting closing');
		
		$scope.action = 'Trial results are in';
		
		$scope.voteButton = false;
		
		var promise = countDown(40); // passing x amount of seconds to perform the timer in countDown()
		//when function above resolves, it returns a promise, which lets us perform the following actions:
		promise.then(function(promiseResolve){  
			console.log('PROMISE: ' + promiseResolve);
			goToNight();
		});
	}
	   
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

