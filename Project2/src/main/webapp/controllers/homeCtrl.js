var myApp = angular.module('myApp');

/*
 * CONTROLLERS METHOD
 */

//create the controller and inject Angular's $scope
myApp.controller('homeController', function($scope) {

   $scope.message = 'This is the home page, from script.js'; // create a message to display in our view
});
