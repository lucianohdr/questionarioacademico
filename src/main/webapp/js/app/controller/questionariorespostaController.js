App.controller("QuestionariorespostaControllerEdit", ['$scope', '$location', 'QuestionariorespostaResource', 
                                                      'QuestionarioResource', '$window', '$stateParams',
                                             
     function($scope, $location, QuestionariorespostaResource, QuestionarioResource, $window, $stateParams){

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
		
		masterUpdate($scope, $stateParams, $window, $location, QuestionariorespostaResource, root);
		masterDelete($scope,$stateParams,$window, $location, QuestionariorespostaResource, root);
 		
	 }
]).controller('QuestionariorespostaControllerNew', ['$scope', '$location', 'QuestionariorespostaResource', 
        		'QuestionarioResource', '$window', '$stateParams',
     function($scope, $location, QuestionariorespostaResource, ProfessorResource, DepartamentoResource){

		var root = '/curso/';
		var emptyObj = {curso: {
			"id":0,
			"nome":"",
			"descricao":""
		}};
		
		
	 	masterCreate($scope, $location, QuestionariorespostaResource, root, emptyObj);
 	}

]).controller("QuestionariorespostaControllerList", ['$scope', '$location', 'QuestionariorespostaResource',
    function($scope, $location, QuestionariorespostaResource){
		masterRead($scope, $location, QuestionariorespostaResource);
	}
]);