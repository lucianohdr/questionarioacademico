App.controller("AlunoControllerEdit", ['$scope', '$location', 'AlunoResource', 
                                       'PerfilResource', 'CursoResource', 'TurmaResource', 'UsuarioResource', 
                                       '$window', '$stateParams', 'edit', 
                                       '$state', 
                                             
     function($scope, $location, AlunoResource, 
    		 PerfilResource, CursoResource, TurmaResource, UsuarioResource, 
    		 $window, $stateParams, edit, $state){

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
		
		$scope.edit = edit;
		
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
		
		$scope.edit = edit;
		
		CursoResource.query(function (res) { $scope.cursos = res; });
		
		TurmaResource.query(function (res) { $scope.turmas = res; });

		$scope.model = new AlunoResource(emptyObj);
		
		$scope.save = function() {
			
			$scope.model.$save(function(res) {
				$state.go('login')
			});
			
			/*//Salvando usuario
			UsuarioResource.save({}, {usuario: $scope.model.aluno.pessoa.usuario}, function(usuarioResponse){
				$scope.model.aluno.pessoa.usuario = usuarioResponse;
				//Salvando pessoa
				PessoaResource.save({}, {pessoa: $scope.model.pessoa}, function(pessoaResponse){
					$scope.model.aluno.pessoa = pessoaResponse;
					
					//salvando Aluno
				});
			});*/
		}
 	}

]).controller("AlunoControllerList", ['$scope', '$location', 'AlunoResource',
    function($scope, $location, AlunoResource){

		masterRead($scope, $location, AlunoResource);
	}
]);