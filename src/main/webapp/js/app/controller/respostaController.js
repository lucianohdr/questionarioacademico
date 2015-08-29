App.controller("RespostaControllerEdit", ['$scope', '$location', 'RespostaResource', 
                                                      'QuestionarioResource', '$window', '$stateParams',
                                             
     function($scope, $location, RespostaResource, QuestionarioResource, $window, $stateParams){

		var root = '/resposta/';
		/*var emptyObj = {resposta: {
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
		
		masterUpdate($scope, $stateParams, $window, $location, RespostaResource, root);
		masterDelete($scope,$stateParams,$window, $location, RespostaResource, root);
 		
	 }
]).controller('RespostaControllerNew', ['$scope', '$location', 'RespostaResource', 
        		'QuestionarioResource', '$window', '$stateParams',
     function($scope, $location, RespostaResource, ProfessorResource, DepartamentoResource){

		var root = '/resposta/';
		var emptyObj = {resposta: {
			"id":0,
			"nome":"",
			"descricao":""
		}};
		
	 	masterCreate($scope, $location, RespostaResource, root, emptyObj);
 	}

]).controller("RespostaControllerList", ['$scope', '$location', 'RespostaResource',
    function($scope, $location, RespostaResource){
		masterRead($scope, $location, RespostaResource);
	}
]);