App.controller("AlunoControllerEdit", ['$scope', '$location', 'AlunoResource', 
                                       'PerfilResource', 'CursoResource', 'TurmaResource', 'UsuarioResource', 
                                       '$window', '$stateParams', '$state', 
                                             
     function($scope, $location, AlunoResource, 
    		 PerfilResource, CursoResource, TurmaResource, UsuarioResource, 
    		 $window, $stateParams, isAluno, $state){

		var root = '/aluno/';
		var emptyObj = {aluno: {
			"id":0,
			"ra":"",
			"pessoa.usuario.tipousuario": { id: 5},
			"pessoa.email":"",
			"pessoa.nome":"",
			"pessoa.usuario.login":"",
			"pessoa.usuario.senha":"",
		}};
		
		CursoResource.query(function (res) { $scope.cursos = res; });
		
		TurmaResource.query(function (res) { $scope.turmas = res; });	
		
		masterUpdate($scope, $stateParams, $window, $location, AlunoResource, root);
	 		
		masterDelete($scope,$stateParams,$window, $location, AlunoResource, root);
 		
	 }
]).controller("AlunoControllerNew", ['$scope', '$location', 'AlunoResource', 
                                     'PerfilResource','CursoResource', 'UsuarioResource',  
                                     'PessoaResource', 'TurmaResource', 'edit', 
                                     '$state',
     function($scope, $location, AlunoResource, 
    		 PerfilResource, CursoResource, UsuarioResource, 
    		 PessoaResource, TurmaResource, edit, 
    		 $state){

		var root = '/aluno/';
		var emptyObj = {aluno: {
			"id":0,
			"ra":"",
			pessoa : {
				"nome" : "",
				"email": "",
				usuario: {
					"login" : "",
					"senha" : "",
					"perfis": []
				}
			}
		}};
		
		$scope.alunoNew = true;
		
		CursoResource.query(function (res) { $scope.cursos = res; });
		
		TurmaResource.query(function (res) { $scope.turmas = res; });

		$scope.model = new AlunoResource(emptyObj);
		
		$scope.save = function() {
			
			$scope.model.$save(function(res) {
				$state.go('login');
			});
		};
 	}

]).controller("AlunoControllerList", ['$scope', '$location', 'AlunoResource',
    function($scope, $location, AlunoResource){

		masterRead($scope, $location, AlunoResource);
	}

]).controller("AlunoControllerCadastro", ['$scope', '$location', 'AlunoResource', 'CursoResource', 'TurmaResource', 'authService', 
                                      function($scope, $location, AlunoResource, CursoResource, TurmaResource, authService, identity){
	
		$scope.model = {};
		
		var usuario = authService.identity().$$state.value.usuario;
		
		$scope.getAluno = function(){
			AlunoResource.alunoPorUsuario({}, {usuario: usuario}, function(res){
				$scope.model.aluno = new AlunoResource(res);
			});
		}
		
		$scope.getAluno();
		
		$scope.isAluno = authService.isInRole("ALUNO");
		console.log($scope.isAluno);
		CursoResource.query(function (res) { $scope.cursos = res; });
		
		TurmaResource.query(function (res) { $scope.turmas = res; });
		
		$scope.save = function() {
			$scope.model.aluno.$update({param1: $scope.model.aluno.id}, function(res) {
				$scope.getAluno();
			});
		}
	}
]);