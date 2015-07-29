App.controller("PerguntaController", ['$scope', '$location', 'PerguntaResource', '$window', '$routeParams',
                                             
     function($scope, $location, PerguntaResource, $window, $routeParams){

		var root = '/pergunta/';
		var emptyObj = {pergunta: {
			"id":0,
			"nome" : "",
			"descricao" : ""
		}};
 		
	/*	PerguntaResource.get({param1: $routeParams.id}, function(res) {
			$scope.model = res;
			if (actionUpdate) actionUpdate('get');
		});*/
		
		
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