App.controller("AlunoControllerEdit", ['$scope', '$location', 'AlunoResource', 
                                       'PerfilResource', 'CursoResource', 'TurmaResource', '$window', '$stateParams',
                                             
     function($scope, $location, AlunoResource, PerfilResource, CursoResource, TurmaResource, $window, $stateParams){

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
		PerfilResource.query(function (res) { $scope.tipousuarios = res; });
		
		masterUpdate($scope, $stateParams, $window, $location, AlunoResource, root);
	 		
		masterDelete($scope,$stateParams,$window, $location, AlunoResource, root);
 		
	 }
]).controller("AlunoControllerNew", ['$scope', '$location', 'AlunoResource', 'PerfilResource','CursoResource', 'TurmaResource',
     function($scope, $location, AlunoResource, PerfilResource, CursoResource, TurmaResource){

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
					tipousuario: {
						"id": 5
					}
				}
			}
		}};
		CursoResource.query(function (res) { $scope.cursos = res; });	
		TurmaResource.query(function (res) { $scope.turmas = res; });	
		PerfilResource.query(function (res) { $scope.tipousuarios = res; });
		
	 	masterCreate($scope, $location, AlunoResource, root, emptyObj);
 	}

]).controller("AlunoControllerList", ['$scope', '$location', 'AlunoResource',
    function($scope, $location, AlunoResource){

		masterRead($scope, $location, AlunoResource);
	}
]);