var myApp = angular.module('myApp');

/*
 * CONTROLLERS METHOD
 */
myApp.controller('PlayController', ['$rootScope', '$scope', function($rootScope, $scope) {
	
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
   
}]);

