App.controller("QuestionariorespostaControllerEdit", ['$scope', '$location', 'QuestionariorespostaResource', 
                                                      'QuestionarioResource',  'authService', '$window', '$stateParams',
                                             
     function($scope, $location, QuestionariorespostaResource, QuestionarioResource, authService, $window, $stateParams){

		var usuario = authService.getUsuario();

		$scope.respostaFlag = false;
		
		var emptyObj = {questionarioresposta: {
			"id":0,
			"cryptid":"",
			"questionario": {},
			"respostas": []
		}};
		
		$scope.model = new QuestionariorespostaResource(emptyObj);
		
		//TODO: Mudar para pegar um objeto customizado no servidor, e trazer somente a disciplina que convem mostrar
		QuestionarioResource.get({param1: $stateParams.id}, function(res){
			$scope.model.questionarioresposta.questionario = new QuestionarioResource(res.questionario);
			
			$.each($scope.model.questionarioresposta.questionario.perguntas, function(index, pergunta){
				var resposta = {
						"id": 0,
						"pergunta": pergunta,
						respostaFlag : false
				}
				$scope.model.questionarioresposta.respostas.push(resposta);
			})
		});
		
		
		$scope.abrirResposta = function(resposta){
			console.log(resposta);
			resposta.respostaFlag = !resposta.respostaFlag
			return resposta.respostaFlag;
		}
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