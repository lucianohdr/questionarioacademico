App.controller("QuestionarioControllerEdit", ['$scope', '$rootScope', '$location', 'QuestionarioResource', 
                                              'CategoriaQuestionarioResource' ,'$window', '$stateParams',
                                             
     function($scope, $rootScope, $location, QuestionarioResource, CategoriaQuestionarioResource, $window, $stateParams){

		var root = '/questionario/';
		
	 	CategoriaQuestionarioResource.query(function (res) { $scope.categorias = res; });
	 	
	 	$scope.dateOptions = {
	 			dateFormat : "dd/mm/yy"
	 	}
	 	
		$scope.save = function() {
			$scope.model.$update({param1: $stateParams.id}, function(res) {
				if (actionUpdate) actionUpdate('save', $stateParams.id);
				$location.path(root);
			});
		}
		
		$scope.rootScope = $rootScope;
		
		$scope.$on("QuestionarioControllerEdit.getPerguntas", function(event){
			QuestionarioResource.perguntas({}, {questionario: {id:$stateParams.id}}, function(res){
				$scope.model.questionario.perguntas = res;
			});
		});
		
		 masterUpdate($scope, $stateParams, $window, $location, QuestionarioResource, root);
	 		
		 masterDelete($scope,$stateParams,$window, $location, QuestionarioResource, root);
		 
	 }
]).controller("QuestionarioControllerNew", ['$scope', '$location', 'QuestionarioResource', 'CategoriaQuestionarioResource',
     function($scope, $location, QuestionarioResource, CategoriaQuestionarioResource){

		var root = '/questionario/';
		var emptyObj = {questionario: {
			"id":0,
			"descricao": "",
			"observacao": ""
		}};
		
		CategoriaQuestionarioResource.query(function (res) { $scope.categorias = res; });
		
		$scope.model = new QuestionarioResource(emptyObj);
		$scope.save = function() {
			$scope.model.$save(function(res) {
					$location.path(root + "edit/" + res.id);
			});
		}
 	}

]).controller("QuestionarioControllerList", ['$scope', '$location', 'QuestionarioResource',
    function($scope, $location, QuestionarioResource){
		masterRead($scope, $location, QuestionarioResource);
	}
]);