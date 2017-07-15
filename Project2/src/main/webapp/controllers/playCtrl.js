var myApp = angular.module('myApp');

/*
 * CONTROLLERS METHOD
 */
myApp.controller('PlayController', ['$rootScope', '$scope', '$timeout', function($rootScope, $scope, $timeout) {
	
	$scope.rolePredictions = ['Employee', 'Hacker', 'HR', 'Trainer'];
	$scope.allPlayers = [
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
	];
	
	$scope.status = ['Employee', 'Fired'];
	
	$timeout(function() {document.getElementById("chat").src="http://widget.mibbit.com/?settings=a4cc8e334a558b93d1cb07eb4ac82f51" + 
	"&server=irc.mibbit.net%3A%2B6697" +
	"&channel=%23Skynet_Game_Chat" +
	"&autoConnect=true" + 
	"&nick=" + $rootScope.user.username;},500);
}]);

