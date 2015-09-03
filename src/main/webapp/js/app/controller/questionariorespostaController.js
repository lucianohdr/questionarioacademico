App.controller("QuestionariorespostaControllerEdit", ['$scope', '$location', 'QuestionariorespostaResource', 
                                                      'QuestionarioResource',  'QuestionariodisponivelResource', 'authService', '$window', '$stateParams',
                                             
     function($scope, $location, QuestionariorespostaResource, QuestionarioResource, QuestionariodisponivelResource, authService, $window, $stateParams){

		var usuario = authService.getUsuario();
		$scope.usuario = usuario;
		$scope.respostaFlag = false;
		
		var emptyObj = {questionarioresposta: {
			"id":0,
			"cryptid":"",
			"questionario": {},
			"respostas": []
		}};
		
		$scope.model = new QuestionariorespostaResource(emptyObj);
		
		QuestionariodisponivelResource.get({param1: $stateParams.id}, function(res){
			$scope.model.questionarioresposta.questionario = new QuestionarioResource(res.questionariodisponivel.questionario);
			
			$scope.model.questionarioresposta.questionariodisponivel = res.questionariodisponivel;
			
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
			resposta.respostaFlag = !resposta.respostaFlag
			return resposta.respostaFlag;
		}
		
		$scope.finalizarAvaliacao = function(){
			
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

]).controller("QuestionariorespostaControllerList", ['$scope', '$location', 'QuestionariorespostaResource', 'QuestionarioResource', 'QuestionariodisponivelResource', 'authService',
    function($scope, $location, QuestionariorespostaResource, QuestionarioResource, QuestionariodisponivelResource, authService){
	
		var usuario = authService.getUsuario();
		$scope.usuario = usuario;
		QuestionariodisponivelResource.porUsuario({}, {usuario: usuario}, function(res){
			$scope.questionariodisponivels = res;
		});
		
		masterRead($scope, $location, QuestionariorespostaResource);
	}
]);