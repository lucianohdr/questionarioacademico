App.controller("AlunoControllerEdit", ['$scope', '$location', 'AlunoResource', 'TipoUsuarioResource','$window', '$routeParams',
                                             
     function($scope, $location, AlunoResource, TipoUsuarioResource, $window, $routeParams){

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
		 TipoUsuarioResource.query(function (res) { $scope.tipousuarios = res; });	
		 masterUpdate($scope, $routeParams, $window, $location, AlunoResource, root);
	 		
		 masterDelete($scope,$routeParams,$window, $location, AlunoResource, root);
 		
	 }
]).controller("AlunoControllerNew", ['$scope', '$location', 'AlunoResource', 'TipoUsuarioResource',
     function($scope, $location, AlunoResource, TipoUsuarioResource){

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
		TipoUsuarioResource.query(function (res) { $scope.tipousuarios = res; });
	 	 masterCreate($scope, $location, AlunoResource, root, emptyObj);
 	}

]).controller("AlunoControllerList", ['$scope', '$location', 'AlunoResource',
    function($scope, $location, AlunoResource){

		masterRead($scope, $location, AlunoResource);
	}
]);