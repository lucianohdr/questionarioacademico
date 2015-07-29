App.controller("DisciplinaControllerEdit", ['$scope', '$location', 'DisciplinaResource', 'CursoResource', 
                                            'ProfessorResource', '$window', '$stateParams',
                                             
     function($scope, $location, DisciplinaResource, CursoResource, ProfessorResource, $window, $stateParams){

		var root = '/disciplina/';
		
		CursoResource.query(function (res) { $scope.cursos = res; });	
		ProfessorResource.query(function (res) { $scope.professores = res; });
		
		masterUpdate($scope, $stateParams, $window, $location, DisciplinaResource, root);
	 		
		masterDelete($scope,$stateParams,$window, $location, DisciplinaResource, root);
 		
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