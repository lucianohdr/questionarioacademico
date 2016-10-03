var questionarioAcademicoServices = angular.module('rest.service', ['ngResource']);

var configUrl = function(model) {
	return baseUrl + model + '/:param1/:param2';
};

var params = function() {
	return {'param1': '', 'param2': ''};
};

var values = function() {
	return {'update': {'method': 'PUT'}};
};

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

questionarioAcademicoServices.factory('AdminResource', ['$resource',function($resource) {
	var api = $resource(configUrl('admin'), params(), values());
	return api;
}]);

questionarioAcademicoServices.factory('UsuarioResource', ['$resource',function($resource) {
	var loginDisponivel = {
			'url': baseUrl + 'usuarios/loginDisponivel',
			'method': 'GET', 
			isArray: false
	};
	
	var hasAdmin = {
			'url': baseUrl + 'usuarios/hasAdmin',
			'method': 'GET', 
			isArray: false
	};
	
	var perfis = {
			'url': baseUrl + 'usuarios/perfis',
			'method': 'GET', 
			isArray: true
	};
	
	var api = $resource(configUrl('usuarios'), params(), {
		'update': {'method': 'PUT'},
		loginDisponivel : loginDisponivel,
		hasAdmin : hasAdmin,
		'perfis' : perfis
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
	var professorPorUsuario = {
			'url': baseUrl + 'professors/professorPorUsuario',
			'method': 'POST', 
			isArray: false
	};
	var api = $resource(configUrl('professors'), params(), {
		'update': {'method': 'PUT'},
		professorPorUsuario : professorPorUsuario	
	});
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
	
	var liberarQuestionario = {
			'url': baseUrl + 'questionarios/liberarQuestionario',
			'method': 'POST', 
			isArray: false
	};
	
	var responder = {
			'url': baseUrl + 'questionarios/responder',
			'method': 'POST', 
			isArray: false
	};
	
	var api = $resource(configUrl('questionarios'), params(), {
		'update': {'method': 'PUT'},
		'update': {'method': 'POST'},
		'addPergunta': addPergunta,
		'rmPergunta': rmPergunta,
		'perguntas' : perguntas,
		'liberarQuestionario' : liberarQuestionario,
		'responder' : responder
	  });
	return api;
}]);

questionarioAcademicoServices.factory('PerguntaResource', ['$resource',function($resource) {
	var perguntas = {
			'url': baseUrl + 'perguntas/perguntasPorQuestionario',
			'method': 'POST', 
			isArray: true
	};
	
	var api = $resource(configUrl('perguntas'), params(), {
		'update': {'method': 'PUT'},
		'perguntas': perguntas
	});
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

questionarioAcademicoServices.factory('QuestionariorespostaResource', ['$resource',function($resource) {
	var finalizaAvaliacao = {
			'url': baseUrl + 'questionariorespostas/finalizaAvaliacao',
			'method': 'POST', 
			isArray: false
	};
	
	var carregaResultado = {
			'url': baseUrl + 'questionariorespostas/carregaResultado',
			'method': 'POST', 
			isArray: false
	}
	
	var repostaPorUsuarioEidquestionarioresposta = {
			'url': baseUrl + 'questionariorespostas/repostaPorUsuarioEidquestionarioresposta',
			'method': 'POST', 
			isArray: false
	}
	
	var api = $resource(configUrl('questionariorespostas'), params(), {
		'update': {'method': 'PUT'},
		'finalizaAvaliacao': finalizaAvaliacao,
		'carregaResultado' : carregaResultado,
		'repostaPorUsuarioEidquestionarioresposta': repostaPorUsuarioEidquestionarioresposta
	});
	
	return api;
}]);

questionarioAcademicoServices.factory('RespostaResource', ['$resource',function($resource) {
	var api = $resource(configUrl('respostas'), params(), values());
	return api;
}]);

questionarioAcademicoServices.factory('QuestionariodisponivelResource', ['$resource',function($resource) {
	var porUsuario= {
			'url': baseUrl + 'questionariodisponivels/porUsuario',
			'method': 'POST', 
			isArray: true
	};
	
	var porIdquestionario = {
			'url': baseUrl + 'questionariodisponivels/porIdquestionario/:idquestionario',
			'method': 'GET', 
			isArray: true
	};
	
	var respondidos = {
			'url': baseUrl + 'questionariodisponivels/respondidos',
			'method': 'POST', 
			isArray: true
	};
	
	var respostasPorUsuarioEPorPerfil = {
			'url': baseUrl + 'questionariodisponivels/respostasPorUsuarioEPorPerfil',
			'method': 'POST', 
			isArray: true
	};
	
	var api = $resource(configUrl('questionariodisponivels'), params(), {
		'update': {'method': 'PUT'},
		'porIdquestionario' : porIdquestionario,
		'porUsuario' : porUsuario,
		'respondidos': respondidos,
		'respostasPorUsuarioEPorPerfil' : respostasPorUsuarioEPorPerfil
	  });
	return api;
}]);