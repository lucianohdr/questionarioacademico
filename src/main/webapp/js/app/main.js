var App = angular.module('QuestionarioAcademico', ['rest.service','ui.bootstrap','ui.date', 'ngRoute']);

App.config(['$routeProvider','$httpProvider',function($routeProvider, $httpProvider) {
	
	$routeProvider
		
		.when('/', {controller: homeController, templateUrl:'view/index.html'})
		
		.when('/usuario/', {controller: usuarioController.read, templateUrl:'view/usuario/list.html'})
		.when('/usuario/new', {controller: usuarioController.create, templateUrl:'view/usuario/form.html'})
		.when('/usuario/edit/:id', {controller: usuarioController.update, templateUrl:'view/usuario/form.html'})
		
		//TODO: criar controllers separados para edit e list
		//para resolver problem: 'Error in resource configuration for action `get`. Expected response to contain an object but got an array'
		
		.when('/contato/', {controller: 'ContatoController', templateUrl:'view/contato/list.html'})
		.when('/contato/new', {controller: 'ContatoController', templateUrl:'view/contato/form.html'})
		.when('/contato/edit/:id', {controller: 'ContatoController', templateUrl:'view/contato/form.html'})
		
		.when('/perfil/', {controller: perfilController.read, templateUrl:'view/perfil/list.html'})
		.when('/perfil/new', {controller: perfilController.create, templateUrl:'view/perfil/form.html'})
		.when('/perfil/edit/:id', {controller: perfilController.update, templateUrl:'view/perfil/form.html'})

		.when('/tarefa/', {controller: tarefaController.read, templateUrl:'view/tarefa/list.html'})
		.when('/tarefa/new', {controller: tarefaController.create, templateUrl:'view/tarefa/form.html'})
		.when('/tarefa/edit/:id', {controller: tarefaController.update, templateUrl:'view/tarefa/form.html'})
		
		.when('/tipousuario/', {controller: tipoUsuarioController.read, templateUrl:'view/tipousuario/list.html'})
		.when('/tipousuario/new', {controller: tipoUsuarioController.create, templateUrl:'view/tipousuario/form.html'})
		.when('/tipousuario/edit/:id', {controller: tipoUsuarioController.update, templateUrl:'view/tipousuario/form.html'})

		.otherwise({redirectTo:'/'});
	
	$httpProvider.interceptors.push('httpInterceptor');
		
}]);

App.factory('httpInterceptor', ['$q','$window', function ($q, $window) {
	return function (promise) {
		return promise.then(function (response) {
			try {
				if (response.data.authenticated == false) {
					$('#loginModal').modal('show');
					return $q.reject(response);
				} else {
					return response;
				}
			} catch (e) {
				alert(e.message);
			}
		}, function (response) {
			return $q.reject(response);
		});
	};
}]);