App.controller("PerguntaController", ['$scope', '$location', 'PerguntaResource', 'TipoUsuarioResource','$window', '$routeParams',
                                             
     function($scope, $location, PerguntaResource, TipoUsuarioResource, $window, $routeParams){

		var root = '/pergunta/';
		var emptyObj = {pergunta: {
			"id":0,
			"matricula":"",
			"pessoa.usuario.tipousuario": { id: 4},
			"pessoa.email":"",
			"pessoa.nome":"",
			"pessoa.usuario.login":"",
			"pessoa.usuario.senha":"",
		}};
 		
	 }
])/*.controller("PerguntaControllerNew", ['$scope', '$location', 'PerguntaResource', 'TipoUsuarioResource',
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
])*/;