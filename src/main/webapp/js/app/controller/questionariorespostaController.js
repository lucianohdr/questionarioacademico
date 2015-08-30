App.controller("QuestionariorespostaControllerEdit", ['$scope', '$location', 'QuestionariorespostaResource', 
                                                      'QuestionarioResource',  'authService', '$window', '$stateParams',
                                             
     function($scope, $location, QuestionariorespostaResource, QuestionarioResource, authService, $window, $stateParams){

		var usuario = authService.getUsuario();
		
		var emptyObj = {questionarioresposta: {
			"id":0,
			"cryptid":"",
			"questionario": {}
		}};
		
		$scope.model = new QuestionariorespostaResource(emptyObj);
		
		QuestionarioResource.get({param1: $stateParams.id}, function(res){
			$scope.model.questionarioresposta.questionario = new QuestionarioResource(res.questionario);
			console.log($scope.model.questionarioresposta);
		});
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

]).controller("QuestionariorespostaControllerList", ['$scope', '$location', 'QuestionariorespostaResource', 'QuestionarioResource', 'authService',
    function($scope, $location, QuestionariorespostaResource, QuestionarioResource, authService){
	
		var usuario = authService.getUsuario();
		
		QuestionarioResource.porUsuarioEporStatus({}, {usuario: usuario}, function(res){
			$scope.questionarios = res;
		});
		
		masterRead($scope, $location, QuestionariorespostaResource);
	}
]);