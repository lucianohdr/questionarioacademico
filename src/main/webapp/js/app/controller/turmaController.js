App.controller("TurmaControllerEdit", ['$scope', '$location', 'TurmaResource', 'CursoResource','$window', '$stateParams',
                                             
     function($scope, $location, TurmaResource, CursoResource, $window, $stateParams){

		var root = '/turma/';
		/*var emptyObj = {turma: {
			"id":0,
			"nome":"",
			"descricao":"",
			curso : {
				"id" : 0
			}
		}};*/
		CursoResource.query(function (res) { $scope.cursos = res; });	
		 masterUpdate($scope, $stateParams, $window, $location, TurmaResource, root);
	 		
		 masterDelete($scope,$stateParams,$window, $location, TurmaResource, root);
 		
	 }
]).controller("TurmaControllerNew", ['$scope', '$location', 'TurmaResource', 'CursoResource',
     function($scope, $location, TurmaResource, CursoResource){

		var root = '/turma/';
		var emptyObj = {turma: {
			"id":0,
			"nome":"",
			"descricao":"",
		}};
		
		CursoResource.query(function (res) { $scope.cursos = res; });
	 	masterCreate($scope, $location, TurmaResource, root, emptyObj);
 	}

]).controller("TurmaControllerList", ['$scope', '$location', 'TurmaResource',
    function($scope, $location, TurmaResource){

		masterRead($scope, $location, TurmaResource);
	}
]);