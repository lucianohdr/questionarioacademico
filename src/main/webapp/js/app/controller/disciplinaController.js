App.controller("DisciplinaControllerEdit", ['$scope', '$location', 'DisciplinaResource', 'CursoResource', 
                                            'ProfessorResource', '$window', '$routeParams',
                                             
     function($scope, $location, DisciplinaResource, CursoResource, ProfessorResource, $window, $routeParams){

		var root = '/disciplina/';
		/*var emptyObj = {disciplina: {
			"id":0,
			"ra":"",
			"pessoa.usuario.tipousuario": { id: 5},
			"pessoa.email":"",
			"pessoa.nome":"",
			"pessoa.usuario.login":"",
			"pessoa.usuario.senha":"",
		}};*/
		CursoResource.query(function (res) { $scope.cursos = res; });	
		ProfessorResource.query(function (res) { $scope.professores = res; });
		
		masterUpdate($scope, $routeParams, $window, $location, DisciplinaResource, root);
	 		
		masterDelete($scope,$routeParams,$window, $location, DisciplinaResource, root);
 		
	 }
]).controller("DisciplinaControllerNew", ['$scope', '$location', 'DisciplinaResource', 'CursoResource', 'ProfessorResource',
     function($scope, $location, DisciplinaResource, CursoResource, ProfessorResource){

		var root = '/disciplina/';
		var emptyObj = {disciplina: {
			"id":0,
			"nome":"",
			"descricao":""
		}};
		CursoResource.query(function (res) { $scope.cursos = res; });
		ProfessorResource.query(function (res) { $scope.professores = res; });
		
	 	masterCreate($scope, $location, DisciplinaResource, root, emptyObj);
 	}

]).controller("DisciplinaControllerList", ['$scope', '$location', 'DisciplinaResource',
    function($scope, $location, DisciplinaResource){

		masterRead($scope, $location, DisciplinaResource);
	}
]);