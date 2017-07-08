/*
 * creating module, naming it 'myApp'
 * module is a container for the different parts of application (controllers, service, etc)
 * 
 * ngRoute will make it possible to not refresh the page (its a module)
 */									
var myApp = angular.module('myApp', ['ngRoute']); 

/*
 * DEFINING CONTROLLERS
 */
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

  .when('/rules', {
    templateUrl : 'pages/rules.html',
    controller  : 'rulesController'
  })

  .otherwise({redirectTo: '/'});
});

/*
 * CONTROLLERS METHODS
 */
//this will remove the URL prefix
myApp.config(['$locationProvider', function($locationProvider) {
	  $locationProvider.hashPrefix('');
	}]);

//create the controller and inject Angular's $scope
myApp.controller('mainController', function($scope) {

    $scope.message = 'This is the home page, from script.js'; // create a message to display in our view
});


myApp.controller('aboutController', function($scope) {
	
	$scope.message = "Now I'm in the about page, from script.js";
});


myApp.controller('rulesController', function($scope) {
	
	$scope.message = "here is the rules page, from script.js";
	$scope.message2 = "Message 2 information here..., from scrip.js";
});


//this will make the navbar active for the nav being clicked
myApp.controller('NavController', function NavController($scope, $location) {   
	
	$scope.isActive = function (viewLocation)
	{
		return viewLocation === $location.path();
	};
})


