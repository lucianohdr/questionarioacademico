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
	          },
	          identity: function(authService){
	        	  return authService.identity();
	          }
			}
		})
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
			data: {
				roles: []
			}
		})
		
		.state("acessonegado", { 
			url: "/acessonegado",
			views: {
				'content@': {
					templateUrl:'view/acessonegado.html', 
					controller: "MainController"
				}
			},
			data: {
				roles: ["ALUNO", "ADMINISTRADOR", "COORDENADOR", "CHEFE", "PROFESSOR"]
			} 
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
				roles: ["ADMINISTRADOR"]
			} 
		})
		.state("cadastro-aluno" , {
			parent: 'app',
			url: '/cadastro/aluno',
			views:{
				'content@':{
					templateUrl:'view/aluno/form.html', 
					controller: 'AlunoControllerCadastro'
				}
			},
			data: {
				roles: ["ALUNO"]
			}
		})
		.state("cadastro-professor" , {
			parent: 'app',
			url: '/cadastro/professor',
			views:{
				'content@':{
					templateUrl:'view/professor/formCadastro.html', 
					controller: 'ProfessorControllerCadastro'
				}
			},
			data: {
				roles: ["PROFESSOR", "COORDENADOR"]
			}
		})
		.state("categoriaquestionarioNew" , {
			parent: 'app',
			url: "/categoriaquestionario/new",
			views: {
				'content@':{
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
			views: {
				'content@': {
					controller: 'AlunoControllerNew', 
					templateUrl:'view/aluno/form.html',  
				}
			},
			resolve :{ 
				edit: function(){
					return true;
				}
			},
			data: {
				roles: []
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
			},
			data: {
				roles: ["ADMINISTRADOR", "COORDENADOR", "PROFESSOR", "CHEFE"]
			} 
		})
		.state("professorNew" ,{ 
			url: '/professor/new',
			views: {
				'content@': {
					controller: 'ProfessorControllerNew', 
					templateUrl:'view/professor/form.html',
				}
			},
			data :{
				roles: []
			}
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
			data :{
				roles: ["ADMINISTRADOR", "COORDENADOR"]
			}
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
			data :{
				roles: ["ADMINISTRADOR"]
			}
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
			data :{
				roles: ["ADMINISTRADOR"]
			}
		})
		.state("pessoa" ,{ 
			parent: 'app',
			url: '/pessoa/',
			views: {
				'content@': {
					controller: 'PessoaControllerList', 
					templateUrl:'view/pessoa/list.html',
				}
			},
			data :{
				roles: ["ADMINISTRADOR"]
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
			},
			data :{
				roles: ["ADMINISTRADOR"]
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
			},
			data :{
				roles: ["ADMINISTRADOR"]
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
			},
			data :{
				roles: ["ADMINISTRADOR"]
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
			},
			data :{
				roles: ["ADMINISTRADOR"]
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
			},
			data :{
				roles: ["ADMINISTRADOR"]
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
			},
			data :{
				roles: ["ADMINISTRADOR"]
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
			},
			data :{
				roles: ["ADMINISTRADOR", "COORDENADOR"]
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
			,data :{
				roles: ["ADMINISTRADOR", "COORDENADOR"]
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
			},
			data :{
				roles: ["ADMINISTRADOR", "COORDENADOR"]
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
			},
			data :{
				roles: ["ADMINISTRADOR"]
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
			},
			data :{
				roles: ["ADMINISTRADOR"]
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
			},
			data :{
				roles: ["ADMINISTRADOR"]
			}
		})
		//revisar
		.state("questionarioEdit", { 
			parent: 'app',
			url: '/questionario/edit/:id',
			views: {
					'content@' : 							  { 
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
			data :{
				roles: ["ADMINISTRADOR", "COORDENADOR"]
			}
		})
		.state("questionarioNew" ,{ 
			parent: 'app',
			url: '/questionario/new', 
			views: {
				'content@':{
					controller: 'QuestionarioControllerNew', 
					templateUrl:'view/questionario/form.html',
				}
			},
			data :{
				roles: ["ADMINISTRADOR", "COORDENADOR"]
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
			},
			data :{
				roles: ["ADMINISTRADOR", "COORDENADOR"]
			}
		})
		.state("questionarioresposta" ,{ 
			parent: 'app',
			url: '/responder/lista',
			views: {
				'content@': {
					controller: 'QuestionariorespostaControllerList', 
					templateUrl:'view/questionarioresposta/list.html',
				}
			},
			data :{
				roles: ["ADMINISTRADOR", "COORDENADOR", "ALUNO", "PROFESSOR"]
			}
		})
		.state("questionariorespostaEdit" ,{ 
			parent: 'app',
			url: '/responder/:id',
			views: {
				'content@': {
					controller: 'QuestionariorespostaControllerEdit', 
					templateUrl:'view/questionarioresposta/form.html',
				}
			},
			data :{
				roles: ["ADMINISTRADOR", "COORDENADOR", "ALUNO", "PROFESSOR"]
			}
		})
		.state("questionariorespostaVisualizar" ,{ 
			parent: 'app',
			url: '/visualizar/:id',
			views: {
				'content@': {
					controller: 'QuestionariorespostaControllerVisualizar', 
					templateUrl:'view/questionarioresposta/visualizar.html',
				}
			},
			data :{
				roles: ["ADMINISTRADOR", "COORDENADOR", "ALUNO", "PROFESSOR"]
			}
		})
		.state("resultado" ,{ 
			parent: 'app',
			url: '/resultado/',
			views: {
				'content@': {
					controller: 'ResultadoControllerList',
					templateUrl:'view/resultado/list.html',
				}
			},
			data :{
				roles: ["ADMINISTRADOR", "COORDENADOR", "PROFESSOR"]
			}
		})
		.state("visualizarResultado" ,{ 
			parent: 'app',
			url: '/resultado/visualizar/:id',
			views: {
				'content@': {
					controller: 'VisualizarResultadoController', 
					templateUrl:'view/resultado/form.html',
				}
			},
			data :{
				roles: ["ADMINISTRADOR", "COORDENADOR", "PROFESSOR"]
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
			},
			data :{
				roles: ["ADMINISTRADOR"]
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
			},
			data :{
				roles: ["ADMINISTRADOR"]
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
			},
			data :{
				roles: ["ADMINISTRADOR"]
			}
		});
	
}]).run(['$rootScope', '$state', '$stateParams', 'authorization', 'authService',
          function($rootScope, $state, $stateParams, authorization, authService) {
      $rootScope.$on('$stateChangeStart', function(event, toState, toStateParams) {
	      $rootScope.toState = toState;
	      $rootScope.toStateParams = toStateParams;
	      
	      //console.log('$stateChangeStart to '+toState.to+'- fired when the transition begins. toState,toParams : \n',toState, toStateParams);
	      
	      if (authService.isIdentityResolved()) authorization.authorize();
    });
      
      /*$rootScope.$on('$stateChangeError',function(event, toState, toParams, fromState, fromParams){
    	  console.log('$stateChangeError - fired when an error occurs during transition.');
    	  console.log(arguments);
    	});

    	$rootScope.$on('$stateChangeSuccess',function(event, toState, toParams, fromState, fromParams){
    	  console.log('$stateChangeSuccess to '+toState.name+'- fired once the state transition is complete.');
    	});

    	$rootScope.$on('$viewContentLoaded',function(event){
    	  console.log('$viewContentLoaded - fired after dom rendered',event);
    	});

    	$rootScope.$on('$stateNotFound',function(event, unfoundState, fromState, fromParams){
    	  console.log('$stateNotFound '+unfoundState.to+'  - fired when a state cannot be found by its name.');
    	  console.log(unfoundState, fromState, fromParams);
    	});*/
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