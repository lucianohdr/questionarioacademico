var App = angular.module('QuestionarioAcademico', ['rest.service','ui.bootstrap','ui.date', 'ui.router', 'ngMessages']);

App.config(['$stateProvider', '$urlRouterProvider', '$httpProvider',function($stateProvider, $urlRouterProvider, $httpProvider) {
	$stateProvider
		.state("home", { url: "/", templateUrl:'view/index.html', controller: homeController})
		.state("categoriaquestionario" ,{ url: "/categoriaquestionario/", templateUrl:'view/categoriaquestionario/list.html', controller: 'CategoriaQuestionarioControllerList'})
		.state("categoriaquestionarioNew" ,{ url: "/categoriaquestionario/new", templateUrl:'view/categoriaquestionario/form.html', controller: 'CategoriaQuestionarioControllerNew'})
		.state("categoriaquestionarioEdit" ,{ url: "/categoriaquestionario/edit/:id", templateUrl:'view/categoriaquestionario/form.html', controller: 'CategoriaQuestionarioControllerEdit' })
		
		.state("alunoEdit" ,{ url: '/aluno/edit/:id', controller: 'AlunoControllerEdit', templateUrl:'view/aluno/form.html'})
		.state("alunoNew" ,{ url: '/aluno/new', controller: 'AlunoControllerNew', templateUrl:'view/aluno/form.html'})
		.state("aluno" ,{ url: '/aluno/', controller: 'AlunoControllerList', templateUrl:'view/aluno/list.html'})
		
		.state("professorEdit" ,{ url: '/professor/edit/:id', controller: 'ProfessorControllerEdit', templateUrl:'view/professor/form.html'})
		.state("professorNew" ,{ url: '/professor/new', controller: 'ProfessorControllerNew', templateUrl:'view/professor/form.html'})
		.state("professor" ,{ url: '/professor/', controller: 'ProfessorControllerList', templateUrl:'view/professor/list.html'})
		
		.state("pessoaEdit" ,{ url: '/pessoa/edit/:id', controller: 'PessoaControllerEdit', templateUrl:'view/pessoa/form.html'})
		.state("pessoaNew" ,{ url: '/pessoa/new', controller: 'PessoaControllerNew', templateUrl:'view/pessoa/form.html'})
		.state("pessoa" ,{ url: '/pessoa/', controller: 'PessoaControllerList', templateUrl:'view/pessoa/list.html'})
		
		.state("cursoEdit" ,{ url: '/curso/edit/:id', controller: 'CursoControllerEdit', templateUrl:'view/curso/form.html'})
		.state("cursoNew" ,{ url: '/curso/new', controller: 'CursoControllerNew', templateUrl:'view/curso/form.html'})
		.state("curso" ,{ url: '/curso/', controller: 'CursoControllerList', templateUrl:'view/curso/list.html'})
		
		.state("departamentoEdit" ,{ url: '/departamento/edit/:id', controller: 'DepartamentoControllerEdit', templateUrl:'view/departamento/form.html'})
		.state("departamentoNew" ,{ url: '/departamento/new', controller: 'DepartamentoControllerNew', templateUrl:'view/departamento/form.html'})
		.state("departamento" ,{ url: '/departamento/', controller: 'DepartamentoControllerList', templateUrl:'view/departamento/list.html'})
		
		.state("turmaEdit" ,{ url: '/turma/edit/:id', controller: 'TurmaControllerEdit', templateUrl:'view/turma/form.html'})
		.state("turmaNew" ,{ url: '/turma/new', controller: 'TurmaControllerNew', templateUrl:'view/turma/form.html'})
		.state("turma" ,{ url: '/turma/', controller: 'TurmaControllerList', templateUrl:'view/turma/list.html'})
		
		.state("disciplinaEdit" ,{ url: '/disciplina/edit/:id', controller: 'DisciplinaControllerEdit', templateUrl:'view/disciplina/form.html'})
		.state("disciplinaNew" ,{ url: '/disciplina/new', controller: 'DisciplinaControllerNew', templateUrl:'view/disciplina/form.html'})
		.state("disciplina" ,{ url: '/disciplina/', controller: 'DisciplinaControllerList', templateUrl:'view/disciplina/list.html'})
		
		.state("questionarioEdit", { url: '/questionario/edit/:id',
				views: {
					'' : {controller : 'QuestionarioControllerEdit', templateUrl: 'view/questionario/form.html'},
					'pergunta@questionarioEdit' : 	  { controller: 'PerguntaController', templateUrl:'view/questionario/pergunta/form.html'},
					'perguntaList@questionarioEdit' : { controller: 'PerguntaController', templateUrl:'view/questionario/pergunta/list.html'}					
				}
		})
		.state("questionarioNew" ,{ url: '/questionario/new', controller: 'QuestionarioControllerNew', templateUrl:'view/questionario/form.html'})
		.state("questionario" ,{ url: '/questionario/', controller: 'QuestionarioControllerList', templateUrl:'view/questionario/list.html'})
		
		.state("perfilEdit" ,{ url: '/perfil/edit/:id', controller: 'PerfilControllerEdit', templateUrl:'view/perfil/form.html'})
		.state("perfilNew" ,{ url: '/perfil/new', controller: 'PerfilControllerNew', templateUrl:'view/perfil/form.html'})
		.state("perfil" ,{ url: '/perfil/', controller: 'PerfilControllerList', templateUrl:'view/perfil/list.html'});
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