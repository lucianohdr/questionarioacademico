var App = angular.module('QuestionarioAcademico', ['rest.service','ui.bootstrap','ui.date', 'ui.router', 'ngMessages']);

App.config(['$stateProvider', '$urlRouterProvider', '$httpProvider', function($stateProvider, $urlRouterProvider, $httpProvider) {
	
	$stateProvider
		.state('app', {
			'abstract': true,
			views: {
				'menu@': {
					templateUrl:'view/menu.html', 
					controller: "MenuController",
				}
			},
			resolve: { 
	          authorize: function(authorization) {
                        return authorization.authorize();
            }
		}})
		.state("home", {
			parent: 'app',
			url: "/", 
			views:{
				"content@" : {
					templateUrl:'view/home.html', 
					controller: "HomeController",
				}
			},
			data: {
				roles: ["ALUNO", "ADMINISTRADOR", "COORDENADOR", "CHEFE", "PROFESSOR"]
			} 
		})
		.state("login", { 
			url: "/login",
			views: {
				'content@': {
					templateUrl:'view/login.html', 
					controller: "MainController"
				}
			},
		})
		
		.state("acessonegado", { 
			url: "/acessonegado",
			views: {
				'content@': {
					templateUrl:'view/acessonegado.html', 
					controller: "MainController"
				}
			},
		})
		
		.state("categoriaquestionario" , {
			parent: 'app',
			url: "/categoriaquestionario/", 
			views: {
				'content@': {
					templateUrl:'view/categoriaquestionario/list.html', 
					controller: 'CategoriaQuestionarioControllerList',
				}
			},
			data: {
				roles: ["ALUNO"]
			} 
		})
		.state("categoriaquestionarioNew" , {
			parent: 'app',
			url: "/categoriaquestionario/new",
			views: {
				'contend@':{
					templateUrl:'view/categoriaquestionario/form.html', 
					controller: 'CategoriaQuestionarioControllerNew',
				}
			},
			data: {
				roles: ["ADMINISTRADOR"]
			} 
		})
		.state("categoriaquestionarioEdit" , {
			parent: 'app',
			url: "/categoriaquestionario/edit/:id",
			views: {
				'content@': {
					templateUrl:'view/categoriaquestionario/form.html', 
					controller: 'CategoriaQuestionarioControllerEdit',
				}
			},
			data: {
				roles: ["ADMINISTRADOR"]
			}
		})
		
		.state("alunoEdit" ,{
			parent: 'app',
			url: '/aluno/edit/:id',
			views: {
				'content@': {
					controller: 'AlunoControllerEdit', 
					templateUrl:'view/aluno/form.html', 
				}
			},
			resolve :{ 
				edit: function(){
					return false;
				}
			},
			data: {
				roles: ["ADMINISTRADOR", "COORDENADOR"]
			}
		})
		.state("alunoNew" ,{
			url: '/aluno/new', 
			controller: 'AlunoControllerNew', 
			templateUrl:'view/aluno/form.html',  
			resolve :{ 
				edit: function(){
					return true;
				}
			}
		})
		.state("aluno" ,{ 
			parent: 'app',
			url: '/aluno/',
			views: {
				'content@': {
					controller: 'AlunoControllerList', 
					templateUrl:'view/aluno/list.html',
				}
			},
			data: {
				roles: ["ADMINISTRADOR", "COORDENADOR"]
			}
		})
		
		//TODO: alterar forma de filtrar edicao dos cadastros de aluno e professor
		.state("professorEdit" ,{ 
			parent: 'app',
			url: '/professor/edit/:id',
			views: {
				'content@': {
					controller: 'ProfessorControllerEdit', 
					templateUrl:'view/professor/form.html', 
				}
			},
			resolve :{ 
				edit: function(){
					return false;
				}
			}
		})
		.state("professorNew" ,{ 
			parent: 'app',
			url: '/professor/new',
			views: {
				'content@': {
					controller: 'ProfessorControllerNew', 
					templateUrl:'view/professor/form.html',
				}
			},
		})
		.state("professor" ,{ 
			parent: 'app',
			url: '/professor/',
			views: {
				'content@': {
					controller: 'ProfessorControllerList', 
					templateUrl:'view/professor/list.html',
				}
			},
		})
		
		.state("pessoaEdit" ,{ 
			parent: 'app',
			url: '/pessoa/edit/:id',
			views: {
				'content@':{
					controller: 'PessoaControllerEdit', 
					templateUrl:'view/pessoa/form.html',
				}
			},
		})
		.state("pessoaNew" ,{
			parent: 'app',
			url: '/pessoa/new',
			views: {
				'content@':{
					controller: 'PessoaControllerNew', 
					templateUrl:'view/pessoa/form.html',
				}
			},
		})
		.state("pessoa" ,{ 
			parent: 'app',
			url: '/pessoa/',
			views: {
				'content@': {
					controller: 'PessoaControllerList', 
					templateUrl:'view/pessoa/list.html',
				}
			}
		})
		
		.state("cursoEdit" ,{ 
			parent: 'app',
			url: '/curso/edit/:id',
			views: {
				'content@': {
					controller: 'CursoControllerEdit', 
					templateUrl:'view/curso/form.html',
				}
			}
		})
		.state("cursoNew" ,{ 
			parent: 'app',
			url: '/curso/new',
			views: {
				'content@': {
					controller: 'CursoControllerNew', 
					templateUrl:'view/curso/form.html',
				}
			}
		})
		.state("curso" ,{ 
			parent: 'app',
			url: '/curso/',
			views: {
				'content@': {
					controller: 'CursoControllerList', 
					templateUrl:'view/curso/list.html',
				}
			}
		})
		
		.state("departamentoEdit" ,{ 
			parent: 'app',
			url: '/departamento/edit/:id',
			views: {
				'content@': {
					controller: 'DepartamentoControllerEdit', 
					templateUrl:'view/departamento/form.html',
				}
			}
		})
		.state("departamentoNew" ,{ 
			parent: 'app',
			url: '/departamento/new',
			views: {
				'content@': {
					controller: 'DepartamentoControllerNew', 
					templateUrl:'view/departamento/form.html',
				}
			}
		})
		.state("departamento" ,{ 
			parent: 'app',
			url: '/departamento/',
			views: {
				'content@':{
					controller: 'DepartamentoControllerList', 
					templateUrl:'view/departamento/list.html',
				}
			}
		})
		
		.state("turmaEdit" ,{ 
			parent: 'app',
			url: '/turma/edit/:id',
			views: {
				'content@': {
					controller: 'TurmaControllerEdit', 
					templateUrl:'view/turma/form.html',
				}
			}
		})
		.state("turmaNew" ,{ 
			parent: 'app',
			url: '/turma/new',
			views: {
				'content@': {
					controller: 'TurmaControllerNew', 
					templateUrl:'view/turma/form.html',
				}
			}
		})
		.state("turma" ,{ 
			parent: 'app',
			url: '/turma/',
			views: {
				'content@':{
					controller: 'TurmaControllerList', 
					templateUrl:'view/turma/list.html',
				}
			}
		})
		
		.state("disciplinaEdit" ,{ 
			parent: 'app',
			url: '/disciplina/edit/:id',
			views: {
				'content@': {
					controller: 'DisciplinaControllerEdit', 
					templateUrl:'view/disciplina/form.html',
				}
			}
		})
		.state("disciplinaNew" ,{ 
			parent: 'app',
			url: '/disciplina/new',
			views: {
				'content@': {
					controller: 'DisciplinaControllerNew', 
					templateUrl:'view/disciplina/form.html',
				}
			}
		})
		.state("disciplina" ,{ 
			parent: 'app',
			url: '/disciplina/',
			views: {
				'content@':{
					controller: 'DisciplinaControllerList',
					templateUrl:'view/disciplina/list.html',
				}
			}
		})
		//revisar
		.state("questionarioEdit", { 
			parent: 'app',
			url: '/questionario/edit/:id',
			views: {
					'' : 							  { 
						controller: 'QuestionarioControllerEdit', 
						templateUrl: 'view/questionario/form.html'
					},
					'pergunta@questionarioEdit' : 	  { 
						controller: 'PerguntaController', 
						templateUrl:'view/questionario/pergunta/form.html'
					},
					'perguntaList@questionarioEdit' : { 
						controller: 'PerguntaController', 
						templateUrl:'view/questionario/pergunta/list.html'
						}					
			},
		})
		.state("questionarioNew" ,{ 
			parent: 'app',
			url: '/questionario/new', 
			views: {
				'content@':{
					controller: 'QuestionarioControllerNew', 
					templateUrl:'view/questionario/form.html',
				}
			}
		})
		.state("questionario" ,{ 
			parent: 'app',
			url: '/questionario/',
			views: {
				'content@': {
					controller: 'QuestionarioControllerList', 
					templateUrl:'view/questionario/list.html',
				}
			}
		})
		
		.state("perfilEdit" ,{
			parent: 'app',
			url: '/perfil/edit/:id',
			views: {
				'content@': {
					controller: 'PerfilControllerEdit', 
					templateUrl:'view/perfil/form.html',
				}
			}
		})
		.state("perfilNew" ,{ 
			parent: 'app',
			url: '/perfil/new',
			views: {
				'content@':{
					controller: 'PerfilControllerNew', 
					templateUrl:'view/perfil/form.html',
				}
			}
		})
		.state("perfil" ,{ 
			parent: 'app',
			url: '/perfil/',
			views: {
				'content@': {
					controller: 'PerfilControllerList',
					templateUrl:'view/perfil/list.html',
				}
			}
		});
	
}]).run(['$rootScope', '$state', '$stateParams', 'authorization', 'authService',
          function($rootScope, $state, $stateParams, authorization, authService) {
      $rootScope.$on('$stateChangeStart', function(event, toState, toStateParams) {
	      $rootScope.toState = toState;
	      $rootScope.toStateParams = toStateParams;
	
	      if (authService.isIdentityResolved()) authorization.authorize();
    });
  }
]);

/*App.factory('httpInterceptor', ['$q','$window', function ($q, $window) {
	return function (promise) {
		return promise.then(function (response) {
			try {
				console.log("authenticated");
				if (response.data.authenticated == false) {
					console.log("false");
					return $q.reject(response);
				} else {
					console.log("true");
					return response;
				}
			} catch (e) {
				alert(e.message);
			}
		}, function (response) {
			return $q.reject(response);
		});
	};
}]);*/