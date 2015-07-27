App.controller("QuestionarioControllerEdit", ['$scope', '$location', 'QuestionarioResource', 
                                              'CategoriaQuestionarioResource' ,'$window', '$routeParams',
                                             
     function($scope, $location, QuestionarioResource, CategoriaQuestionarioResource, $window, $routeParams){

		var root = '/questionario/';
		
		 CategoriaQuestionarioResource.query(function (res) { $scope.categorias = res; });	
		 masterUpdate($scope, $routeParams, $window, $location, QuestionarioResource, root);
	 		
		 masterDelete($scope,$routeParams,$window, $location, QuestionarioResource, root);
 		
	 }
]).controller("QuestionarioControllerNew", ['$scope', '$location', 'QuestionarioResource', 'CategoriaQuestionarioResource',
     function($scope, $location, QuestionarioResource, CategoriaQuestionarioResource){

		var root = '/questionario/';
		var emptyObj = {questionario: {
			"id":0,
			"descricao": "",
			"observacao": "",
			"dataliberacao": "",
			"datavalidade": ""
		}};
		
		CategoriaQuestionarioResource.query(function (res) { $scope.categorias = res; });
	 	masterCreate($scope, $location, QuestionarioResource, root, emptyObj);
 	}

]).controller("QuestionarioControllerList", ['$scope', '$location', 'QuestionarioResource',
    function($scope, $location, QuestionarioResource){
		masterRead($scope, $location, QuestionarioResource);
	}
]);