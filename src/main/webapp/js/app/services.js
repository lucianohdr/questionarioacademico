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

questionarioAcademicoServices.factory('CategoriaQuestionarioResource', ['$resource',function($resource) {
	var api = $resource(configUrl('categoriaquestionarios'), params(), values());
	return api;
}]);

questionarioAcademicoServices.factory('UsuarioResource', ['$resource',function($resource) {
	var loginDisponivel = {
			'url': baseUrl + 'usuarios/loginDisponivel',
			'method': 'GET', 
			isArray: false
	};
	var api = $resource(configUrl('usuarios'), params(), {
		'update': {'method': 'PUT'},
		loginDisponivel : loginDisponivel
	});
	return api;
}]);

questionarioAcademicoServices.factory('AlunoResource', ['$resource',function($resource) {
	var alunoPorUsuario = {
			'url': baseUrl + 'alunos/alunoPorUsuario',
			'method': 'POST', 
			isArray: false
	};
	var api = $resource(configUrl('alunos'), params(), {
		'update': {'method': 'PUT'},
		alunoPorUsuario : alunoPorUsuario	
	});
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
	var disciplinaPorCurso = {
			'url': baseUrl + 'disciplinas/disciplinaPorCurso',
			'method': 'POST', 
			isArray: true
	};
	
	var api = $resource(configUrl('disciplinas'), params(), {
		'update': {'method': 'PUT'},
		'disciplinaPorCurso': disciplinaPorCurso
	  });
	
	return api;
}]);

questionarioAcademicoServices.factory('QuestionarioResource', ['$resource',function($resource) {
	
	var addPergunta = {
			'url': baseUrl + 'questionarios/addPergunta',
			'method': 'POST', 
			isArray: false
	};
	
	var rmPergunta = {
			'url': baseUrl + 'questionarios/rmPergunta',
			'method': 'POST', 
			isArray: false
	};
	
	var perguntas = {
			'url': baseUrl + 'questionarios/perguntas',
			'method': 'POST', 
			isArray: true
	};
	
	var api = $resource(configUrl('questionarios'), params(), {
		'update': {'method': 'PUT'},
		'addPergunta': addPergunta,
		'rmPergunta': rmPergunta,
		'perguntas' : perguntas
	  });
	return api;
}]);

questionarioAcademicoServices.factory('PerguntaResource', ['$resource',function($resource) {
	var api = $resource(configUrl('perguntas'), params(), values());
	return api;
}]);

questionarioAcademicoServices.factory('TipoPerguntaResource', ['$resource',function($resource) {
	var api = $resource(configUrl('tipoperguntas'), params(), values());
	return api;
}]);

questionarioAcademicoServices.factory('AlternativaResource', ['$resource',function($resource) {
	var api = $resource(configUrl('alternativas'), params(), values());
	return api;
}]);

questionarioAcademicoServices.factory('PessoaResource', ['$resource',function($resource) {
	var api = $resource(configUrl('pessoas'), params(), values());
	return api;
}]);

questionarioAcademicoServices.factory('PeriodoResource', ['$resource',function($resource) {
	var api = $resource(configUrl('periodos'), params(), values());
	return api;
}]);