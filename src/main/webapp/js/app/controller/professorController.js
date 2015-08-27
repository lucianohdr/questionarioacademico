App.controller("ProfessorControllerEdit", ['$scope', '$location', 'ProfessorResource', 'PerfilResource', '$window', '$stateParams',
                                             
     function($scope, $location, ProfessorResource, PerfilResource, $window, $stateParams){

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
])
.controller("ProfessorControllerCadastro", ['$scope', '$location', 'ProfessorResource', 'UsuarioResource', 'authService',
                                          function($scope, $location, ProfessorResource, UsuarioResource, authService){
	
		var usuario = authService.identity().$$state.value.usuario;
		
		$scope.model = {};
		
		$scope.getProfessor = function(){
			ProfessorResource.professorPorUsuario({}, {usuario: usuario}, function(res){
				$scope.model.professor = new ProfessorResource(res);
			});
		}
		
		$scope.save = function() {
			$scope.model.professor.$update({param1: $scope.model.professor.id}, function(res) {
				$scope.getProfessor();
			});
		}
		
		$scope.getProfessor();
	}
]);