angular.module('app', ['ngRoute', 'ngAnimate'])
	.config(function($routeProvider, $locationProvider, $httpProvider) {

		//$locationProvider.html5Mode(true);

		//HOME
		$routeProvider.when('/home', {
			templateUrl: 'home.html',
			controller: 'HomeController'
		});

		$routeProvider.otherwise({redirectTo: '/'});		

		$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
	});