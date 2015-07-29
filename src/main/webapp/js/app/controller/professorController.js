App.controller("ProfessorControllerEdit", ['$scope', '$location', 'ProfessorResource', 'TipoUsuarioResource','$window', '$stateParams',
                                             
     function($scope, $location, ProfessorResource, TipoUsuarioResource, $window, $stateParams){

		var root = '/professor/';
		var emptyObj = {professor: {
			"id":0,
			"matricula":"",
			"pessoa.usuario.tipousuario": { id: 4},
			"pessoa.email":"",
			"pessoa.nome":"",
			"pessoa.usuario.login":"",
			"pessoa.usuario.senha":"",
		}};
		 TipoUsuarioResource.query(function (res) { $scope.tipousuarios = res; });	
		 masterUpdate($scope, $stateParams, $window, $location, ProfessorResource, root);
	 		
		 masterDelete($scope,$stateParams,$window, $location, ProfessorResource, root);
 		
	 }
]).controller("ProfessorControllerNew", ['$scope', '$location', 'ProfessorResource', 'TipoUsuarioResource',
     function($scope, $location, ProfessorResource, TipoUsuarioResource){

		var root = '/professor/';
		var emptyObj = {professor: {
			"id":0,
			"matricula":"",
			pessoa : {
				"nome" : "",
				"email": "",
				usuario: {
					"login" : "",
					"senha" : "",
					tipousuario: {
						"id": 4
					}
				}
			}
		}};
		TipoUsuarioResource.query(function (res) { $scope.tipousuarios = res; });
	 	 masterCreate($scope, $location, ProfessorResource, root, emptyObj);
 	}

]).controller("ProfessorControllerList", ['$scope', '$location', 'ProfessorResource',
    function($scope, $location, ProfessorResource){

		masterRead($scope, $location, ProfessorResource);
	}
]);