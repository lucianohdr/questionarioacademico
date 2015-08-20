App.controller("ProfessorControllerEdit", ['$scope', '$location', 'ProfessorResource', 'PerfilResource','$window', '$stateParams', 'edit',
                                             
     function($scope, $location, ProfessorResource, PerfilResource, $window, $stateParams, edit){

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
		
		$scope.edit = edit;
		
		masterUpdate($scope, $stateParams, $window, $location, ProfessorResource, root);
	 		
		masterDelete($scope,$stateParams,$window, $location, ProfessorResource, root);
 		
	 }
]).controller("ProfessorControllerNew", ['$scope', '$location', 'ProfessorResource', 'PerfilResource',
     function($scope, $location, ProfessorResource, PerfilResource){

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
	 	masterCreate($scope, $location, ProfessorResource, root, emptyObj);
 	}

]).controller("ProfessorControllerList", ['$scope', '$location', 'ProfessorResource',
    function($scope, $location, ProfessorResource){

		masterRead($scope, $location, ProfessorResource);
	}
]);