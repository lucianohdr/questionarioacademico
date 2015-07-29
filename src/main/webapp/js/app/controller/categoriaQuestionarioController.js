App.controller("CategoriaQuestionarioControllerEdit", ['$scope', '$location', 'CategoriaQuestionarioResource' ,'$window', '$stateParams',
                                             
    function($scope, $location, CategoriaQuestionarioResource, $window, $stateParams){

		var root = '/categoriaquestionario/';
		var emptyObj = { categoriaquestionario: {"id":0, "nome":'', "descricao": ''}};
		
		masterUpdate($scope, $stateParams, $window, $location, CategoriaQuestionarioResource, root);
		
		masterDelete($scope,$stateParams,$window, $location, CategoriaQuestionarioResource, root);
		
	}
]).controller("CategoriaQuestionarioControllerNew", ['$scope', '$location', 'CategoriaQuestionarioResource',
    function($scope, $location, CategoriaQuestionarioResource){
	
		var root = '/categoriaquestionario/';
		var emptyObj = { categoriaquestionario: {"id":0, "nome":'', "descricao": ''}};
		//$scope.error= "Desculpe aconteceu um erro inesperado";
		masterCreate($scope, $location, CategoriaQuestionarioResource, root, emptyObj);
	}
]).controller("CategoriaQuestionarioControllerList", ['$scope', '$location', 'CategoriaQuestionarioResource',
    function($scope, $location, CategoriaQuestionarioResource){
	
		masterRead($scope, $location, CategoriaQuestionarioResource);
		
	}
]);