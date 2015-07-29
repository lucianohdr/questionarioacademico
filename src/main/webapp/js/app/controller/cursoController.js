App.controller("CursoControllerEdit", ['$scope', '$location', 'CursoResource', 'ProfessorResource', 
                                       'DepartamentoResource', '$window', '$stateParams',
                                             
     function($scope, $location, CursoResource, ProfessorResource, DepartamentoResource, $window, $stateParams){

		var root = '/curso/';
		/*var emptyObj = {curso: {
			"id":0,
			"nome":"",
			"descricao":"",
			professor: {
				"id": 0
			},
			departamento: {
				"id": 0
			}
		}};*/
		
		ProfessorResource.query(function (res) { $scope.professores = res; });	
		DepartamentoResource.query(function (res) { $scope.departamentos = res; });
		
		masterUpdate($scope, $stateParams, $window, $location, CursoResource, root);
		masterDelete($scope,$stateParams,$window, $location, CursoResource, root);
 		
	 }
]).controller("CursoControllerNew", ['$scope', '$location', 'CursoResource', 'ProfessorResource', 'DepartamentoResource',
     function($scope, $location, CursoResource, ProfessorResource, DepartamentoResource){

		var root = '/curso/';
		var emptyObj = {curso: {
			"id":0,
			"nome":"",
			"descricao":""
		}};
		
		ProfessorResource.query(function (res) { $scope.professores = res; });	
		DepartamentoResource.query(function (res) { $scope.departamentos = res; });
		
	 	masterCreate($scope, $location, CursoResource, root, emptyObj);
 	}

]).controller("CursoControllerList", ['$scope', '$location', 'CursoResource',
    function($scope, $location, CursoResource){
		masterRead($scope, $location, CursoResource);
	}
]);