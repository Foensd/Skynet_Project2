/*
 * creating module, naming it 'myApp'
 * module is a container for the different parts of application (controllers, service, etc)
 * 
 * ngRoute will make it possible to not refresh the page (its a module)
 */									
var myApp = angular.module('myApp', ['ngRoute']); 

//this will remove the URL prefix
myApp.config(['$locationProvider', function($locationProvider) {
	  $locationProvider.hashPrefix('');
	}]);

//this will make the navbar active for the nav being clicked
myApp.controller('NavController', function NavController($scope, $location) {   
	
	$scope.isActive = function (viewLocation)
	{
		return viewLocation === $location.path();
	};
})


/*
 * DEFINING CONTROLLERS AND ROUTES
 * 
 * routes will choose the file that should be displayed (templateUrl)
 * then it will say which controller to control whatever is being displayed
 * 
 * controllers are all imported in the index.html file
 */
myApp.config(function($routeProvider) {
  
	$routeProvider

  .when('/', {
    templateUrl : 'pages/home.html',
    controller  : 'HomeController'
  })

  .when('/about', {
    templateUrl : 'pages/about.html',
    controller  : 'AboutController'
  })

  .when('/rules', {
    templateUrl : 'pages/rules.html',
    controller  : 'RulesController'
  })
  
  .when('/register', {
    templateUrl : 'pages/register.html',
    controller  : 'RegisterController'
  })
  
  .when('/lobby', {
    templateUrl : 'pages/lobby.html',
    controller  : 'LobbyController'
  })
  
  .when('/play', {
    templateUrl : 'pages/play.html',
    controller  : 'PlayController'
  })

  .otherwise({redirectTo: '/'});
});



