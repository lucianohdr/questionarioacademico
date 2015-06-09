var questionarioAcademicoServices = angular.module('rest.service', ['ngResource']);

var configUrl = function(model) {
	return baseUrl + model + '/:param1/:param2';
}

var params = function() {
	return {'param1': '', 'param2': ''};
}

var values = function() {
	return {'update': {'method': 'PUT'}};
}

questionarioAcademicoServices.factory('UsuarioResource', ['$resource',function($resource) {
	var api = $resource(configUrl('usuarios'), params(), values());
    return api;
}]);

questionarioAcademicoServices.factory('PerfilResource', ['$resource',function($resource) {
	var api = $resource(configUrl('perfis'), params(), values());
    return api;
}]);

questionarioAcademicoServices.factory('ContatoResource', ['$resource',function($resource) {
	var api = $resource(configUrl('contatos'), params(), values());
    return api;
}]);

questionarioAcademicoServices.factory('TarefaResource', ['$resource',function($resource) {
	var api = $resource(configUrl('tarefas'), params(), values());
    return api;
}]);

questionarioAcademicoServices.factory('TipoUsuarioResource', ['$resource',function($resource) {
	var api = $resource(configUrl('tipousuarios'), params(), values());
	return api;
}]);

questionarioAcademicoServices.factory('CategoriaQuestionarioResource', ['$resource',function($resource) {
	var api = $resource(configUrl('categoriaquestionarios'), params(), values());
	return api;
}]);

questionarioAcademicoServices.factory('UsuarioResource', ['$resource',function($resource) {
	var api = $resource(configUrl('usuarios'), params(), values());
	return api;
}]);