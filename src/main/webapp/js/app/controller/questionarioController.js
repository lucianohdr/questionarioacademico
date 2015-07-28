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
		}};
		
		CategoriaQuestionarioResource.query(function (res) { $scope.categorias = res; });
		
		$scope.model = new QuestionarioResource(emptyObj);
		$scope.save = function() {
			$scope.model.$save(function(res) {
				var questionario = QuestionarioResource.getLastQuestionario(function(){
					$scope.model.questionario =  questionario;
					$location.path(root + "edit/" + $scope.model.questionario.id);
				});
			});
		}
 	}

]).controller("QuestionarioControllerList", ['$scope', '$location', 'QuestionarioResource',
    function($scope, $location, QuestionarioResource){
		masterRead($scope, $location, QuestionarioResource);
	}
]);