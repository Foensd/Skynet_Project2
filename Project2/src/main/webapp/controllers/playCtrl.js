var myApp = angular.module('myApp');

/*
 * CONTROLLERS METHOD
 */
myApp.controller('PlayController', ['$scope', function($scope) {

   $scope.username = 'username'; // create a message to display in our view
   $scope.role = 'some role';
   
}]);

