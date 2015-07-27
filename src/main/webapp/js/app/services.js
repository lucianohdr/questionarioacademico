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

questionarioAcademicoServices.factory('AlunoResource', ['$resource',function($resource) {
	var api = $resource(configUrl('alunos'), params(), values());
	return api;
}]);

questionarioAcademicoServices.factory('ProfessorResource', ['$resource',function($resource) {
	var api = $resource(configUrl('professors'), params(), values());
	return api;
}]);

questionarioAcademicoServices.factory('TurmaResource', ['$resource',function($resource) {
	var api = $resource(configUrl('turmas'), params(), values());
	return api;
}]);

questionarioAcademicoServices.factory('DepartamentoResource', ['$resource',function($resource) {
	var api = $resource(configUrl('departamentos'), params(), values());
	return api;
}]);

questionarioAcademicoServices.factory('CursoResource', ['$resource',function($resource) {
	var api = $resource(configUrl('cursos'), params(), values());
	return api;
}]);

questionarioAcademicoServices.factory('DisciplinaResource', ['$resource',function($resource) {
	var api = $resource(configUrl('disciplinas'), params(), values());
	return api;
}]);

questionarioAcademicoServices.factory('QuestionarioResource', ['$resource',function($resource) {
	var api = $resource(configUrl('questionarios'), params(), values());
	return api;
}]);

questionarioAcademicoServices.factory('PerguntaResource', ['$resource',function($resource) {
	var api = $resource(configUrl('perguntas'), params(), values());
	return api;
}]);