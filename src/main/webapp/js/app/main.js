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
		
		//TODO: usar o resolve nas rotas
		
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
					'' : {templateUrl: 'view/questionario/form.html', controller : 'QuestionarioControllerEdit'},
					'pergunta@questionarioEdit' : 	  { controller: 'PerguntaController', templateUrl:'view/questionario/pergunta/form.html'},
					'perguntaList@questionarioEdit' : { controller: 'PerguntaController', templateUrl:'view/questionario/pergunta/list.html'},
				}
		})
		.state("questionarioNew" ,{ url: '/questionario/new', controller: 'QuestionarioControllerNew', templateUrl:'view/questionario/form.html'})
		.state("questionario" ,{ url: '/questionario/', controller: 'QuestionarioControllerList', templateUrl:'view/questionario/list.html'})
		
		.state("perfilEdit" ,{ url: '/perfil/edit/:id', controller: 'PerfilControllerEdit', templateUrl:'view/perfil/form.html'})
		.state("perfilNew" ,{ url: '/perfil/new', controller: 'PerfilControllerNew', templateUrl:'view/perfil/form.html'})
		.state("perfil" ,{ url: '/perfil/', controller: 'PerfilControllerList', templateUrl:'view/perfil/list.html'});
	/*$routeProvider
		
		.when('/', {controller: homeController, templateUrl:'view/index.html'})
		
		.when('/contato/edit/:id', {controller: 'ContatoControllerEdit', templateUrl:'view/contato/form.html'})
		.when('/contato/new', {controller: 'ContatoControllerNew', templateUrl:'view/contato/form.html'})
		.when('/contato/', {controller: 'ContatoControllerList', templateUrl:'view/contato/list.html'})
		
		.when('/perfil/', {controller: perfilController.read, templateUrl:'view/perfil/list.html'})
		.when('/perfil/new', {controller: perfilController.create, templateUrl:'view/perfil/form.html'})
		.when('/perfil/edit/:id', {controller: perfilController.update, templateUrl:'view/perfil/form.html'})

		.when('/tarefa/', {controller: tarefaController.read, templateUrl:'view/tarefa/list.html'})
		.when('/tarefa/new', {controller: tarefaController.create, templateUrl:'view/tarefa/form.html'})
		.when('/tarefa/edit/:id', {controller: tarefaController.update, templateUrl:'view/tarefa/form.html'})
		
		.when('/tipousuario/edit/:id', {controller: 'TipoUsuarioControllerEdit', templateUrl:'view/tipousuario/form.html'})
		.when('/tipousuario/new', {controller: 'TipoUsuarioControllerNew', templateUrl:'view/tipousuario/form.html'})
		.when('/tipousuario/', {controller: 'TipoUsuarioControllerList', templateUrl:'view/tipousuario/list.html'})
		
		.when('/categoriaquestionario/edit/:id', {controller: 'CategoriaQuestionarioControllerEdit', templateUrl:'view/categoriaquestionario/form.html'})
		.when('/categoriaquestionario/new', {controller: 'CategoriaQuestionarioControllerNew', templateUrl:'view/categoriaquestionario/form.html'})
		.when('/categoriaquestionario/', {controller: 'CategoriaQuestionarioControllerList', templateUrl:'view/categoriaquestionario/list.html'})
		
		.when('/usuario/edit/:id', {controller: 'UsuarioControllerEdit', templateUrl:'view/usuario/form.html'})
		.when('/usuario/new', {controller: 'UsuarioControllerNew', templateUrl:'view/usuario/form.html'})
		.when('/usuario/', {controller: 'UsuarioControllerList', templateUrl:'view/usuario/list.html'})
		
		.when('/aluno/edit/:id', {controller: 'AlunoControllerEdit', templateUrl:'view/aluno/form.html'})
		.when('/aluno/new', {controller: 'AlunoControllerNew', templateUrl:'view/aluno/form.html'})
		.when('/aluno/', {controller: 'AlunoControllerList', templateUrl:'view/aluno/list.html'})
		
		.when('/professor/edit/:id', {controller: 'ProfessorControllerEdit', templateUrl:'view/professor/form.html'})
		.when('/professor/new', {controller: 'ProfessorControllerNew', templateUrl:'view/professor/form.html'})
		.when('/professor/', {controller: 'ProfessorControllerList', templateUrl:'view/professor/list.html'})
		
		//TODO: usar o resolve nas rotas
		
		.when('/curso/edit/:id', {controller: 'CursoControllerEdit', templateUrl:'view/curso/form.html'})
		.when('/curso/new', {controller: 'CursoControllerNew', templateUrl:'view/curso/form.html'})
		.when('/curso/', {controller: 'CursoControllerList', templateUrl:'view/curso/list.html'})
		
		.when('/departamento/edit/:id', {controller: 'DepartamentoControllerEdit', templateUrl:'view/departamento/form.html'})
		.when('/departamento/new', {controller: 'DepartamentoControllerNew', templateUrl:'view/departamento/form.html'})
		.when('/departamento/', {controller: 'DepartamentoControllerList', templateUrl:'view/departamento/list.html'})
		
		.when('/turma/edit/:id', {controller: 'TurmaControllerEdit', templateUrl:'view/turma/form.html'})
		.when('/turma/new', {controller: 'TurmaControllerNew', templateUrl:'view/turma/form.html'})
		.when('/turma/', {controller: 'TurmaControllerList', templateUrl:'view/turma/list.html'})
		
		.when('/disciplina/edit/:id', {controller: 'DisciplinaControllerEdit', templateUrl:'view/disciplina/form.html'})
		.when('/disciplina/new', {controller: 'DisciplinaControllerNew', templateUrl:'view/disciplina/form.html'})
		.when('/disciplina/', {controller: 'DisciplinaControllerList', templateUrl:'view/disciplina/list.html'})
		
		.when('/questionario/edit/:id', {controller: 'QuestionarioControllerEdit', templateUrl:'view/questionario/form.html'})
		.when('/questionario/new', {controller: 'QuestionarioControllerNew', templateUrl:'view/questionario/form.html'})
		.when('/questionario/', {controller: 'QuestionarioControllerList', templateUrl:'view/questionario/list.html'})
		
		.when('/questionario/:id/perguntas', {controller: 'PerguntaController', templateUrl:'view/questionario/pergunta/form.html'})
													   
		.otherwise({redirectTo:'/'});*/
	
		//$httpProvider.interceptors.push('httpInterceptor');
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