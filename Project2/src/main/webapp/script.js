/*
 * creating module, naming it 'myApp'
 * module is a container for the different parts of application (controllers, service, etc)
 * 
 * ngRoute will make it possible to not refresh the page (its a module)
 */
									
var myApp = angular.module('myApp', ['ngRoute']); 

myApp.controller('mainController', function($scope) {
	
	$scope.message = 'Hello from home controller';
	
});

myApp.config(function($routeProvider) {
  $routeProvider

  .when('/', {
    templateUrl : 'pages/home.html',
    controller  : 'mainController'
  })

  .when('/about', {
    templateUrl : 'pages/about.html',
    controller  : 'aboutController'
  })

  .when('/contact', {
    templateUrl : 'pages/contact.html',
    controller  : 'contactController'
  })

  .otherwise({redirectTo: '/'});
});

//this will remove the URL prefix
myApp.config(['$locationProvider', function($locationProvider) {
	  $locationProvider.hashPrefix('');
	}]);

//create the controller and inject Angular's $scope
myApp.controller('mainController', function($scope) {

    // create a message to display in our view
    $scope.message = 'This is the home page';
});

myApp.controller('aboutController', function($scope) {
	
	$scope.message = "Now I'm in the about page";
});

myApp.controller('contactController', function($scope) {
	$scope.message = "here is the contact page";
});



