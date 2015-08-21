App.controller("AlunoControllerEdit", ['$scope', '$location', 'AlunoResource', 
                                       'PerfilResource', 'CursoResource', 'TurmaResource', '$window', '$stateParams', 'edit',
                                             
     function($scope, $location, AlunoResource, PerfilResource, CursoResource, TurmaResource, $window, $stateParams, edit){

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
]).controller("AlunoControllerNew", ['$scope', '$location', 'AlunoResource', 'PerfilResource','CursoResource', 'TurmaResource', 'UsuarioResource', 'edit',
     function($scope, $location, AlunoResource, PerfilResource, CursoResource, TurmaResource, UsuarioResource, edit){

		var root = '/aluno/';
		var emptyObj = {aluno: {
			"id":0,
			"ra":"",
			pessoa : {
				"nome" : "",
				"email": "",
				usuario: {
					"login" : "",
					"senha" : ""
				}
			}
		}};
		
		$scope.edit = edit;
		
		CursoResource.query(function (res) { $scope.cursos = res; });
		
		TurmaResource.query(function (res) { $scope.turmas = res; });

		$scope.model = new AlunoResource(emptyObj);
		
		$scope.save = function() {
			$scope.model.$save(function(res) {
				$location.path('login');
			});
		}
	 	
 	}

]).controller("AlunoControllerList", ['$scope', '$location', 'AlunoResource',
    function($scope, $location, AlunoResource){

		masterRead($scope, $location, AlunoResource);
	}
]);