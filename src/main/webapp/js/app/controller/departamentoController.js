App.controller("DepartamentoControllerEdit", ['$scope', '$location', 'DepartamentoResource', 'ProfessorResource','$window', '$routeParams',
                                             
     function($scope, $location, DepartamentoResource, ProfessorResource, $window, $routeParams){

		var root = '/departamento/';
		var emptyObj = {departamento: {
			"id":0,
			"nome":"",
			"descricao":"",
			professor: {
				"id":0
			}
		}};
		 ProfessorResource.query(function (res) { $scope.professores = res; });	
		 masterUpdate($scope, $routeParams, $window, $location, DepartamentoResource, root);
	 		
		 masterDelete($scope,$routeParams,$window, $location, DepartamentoResource, root);
 		
	 }
]).controller("DepartamentoControllerNew", ['$scope', '$location', 'DepartamentoResource', 'ProfessorResource',
     function($scope, $location, DepartamentoResource, ProfessorResource){

		var root = '/departamento/';
		var emptyObj = {departamento: {
			"id":0,
			"nome":"",
			"descricao":"",
			professor: {
				"id":0
			}
		}};
		ProfessorResource.query(function (res) { $scope.professores = res; });
	 	masterCreate($scope, $location, DepartamentoResource, root, emptyObj);
 	}

]).controller("DepartamentoControllerList", ['$scope', '$location', 'DepartamentoResource',
    function($scope, $location, DepartamentoResource){
		masterRead($scope, $location, DepartamentoResource);
	}
]);