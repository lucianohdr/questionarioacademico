App.controller("DisciplinaControllerEdit", ['$scope', '$location', 'DisciplinaResource', 'CursoResource', 
                                            'ProfessorResource', 'PeriodoResource', '$window', '$stateParams',
                                             
     function($scope, $location, DisciplinaResource, CursoResource, ProfessorResource, PeriodoResource, $window, $stateParams){

		var root = '/disciplina/';
		
		CursoResource.query(function (res) { $scope.cursos = res; });
		
		PeriodoResource.query(function (res) { $scope.periodos = res; });
		
		ProfessorResource.query(function (res) { $scope.professores = res; });
		
		masterUpdate($scope, $stateParams, $window, $location, DisciplinaResource, root);
	 		
		masterDelete($scope,$stateParams,$window, $location, DisciplinaResource, root);
 		
	 }
]).controller("DisciplinaControllerNew", ['$scope', '$location', 'DisciplinaResource', 'CursoResource', 'ProfessorResource', 'PeriodoResource',
     function($scope, $location, DisciplinaResource, CursoResource, ProfessorResource, PeriodoResource){

		var root = '/disciplina/';
		var emptyObj = {disciplina: {
			"id":0,
			"nome":"",
			"descricao":""
		}};
		CursoResource.query(function (res) { $scope.cursos = res; });
		
		PeriodoResource.query(function (res) { $scope.periodos = res; });
		
		ProfessorResource.query(function (res) { $scope.professores = res; });
		
	 	masterCreate($scope, $location, DisciplinaResource, root, emptyObj);
 	}

]).controller("DisciplinaControllerList", ['$scope', '$location', 'DisciplinaResource',
    function($scope, $location, DisciplinaResource){

		masterRead($scope, $location, DisciplinaResource);
	}
]);