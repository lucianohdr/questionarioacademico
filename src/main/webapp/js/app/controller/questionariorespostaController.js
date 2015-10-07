App.controller("QuestionariorespostaControllerEdit", ['$scope', 'QuestionariorespostaResource', 
                                                      'QuestionarioResource',  'QuestionariodisponivelResource', 'authService', '$stateParams', '$state', 
                                             
     function($scope, QuestionariorespostaResource, QuestionarioResource, QuestionariodisponivelResource, authService, $stateParams, $state){
		$scope.title = "Responder Questionário";
		
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
			QuestionariorespostaResource.finalizaAvaliacao({}, {questionarioresposta: $scope.model.questionarioresposta}, function(res){
				$state.go('questionarioresposta');
			});
		}
		
	 }
]).controller("QuestionariorespostaControllerVisualizar", ['$scope', 'QuestionariorespostaResource', '$stateParams',
        function($scope, QuestionariorespostaResource, $stateParams){
			$scope.model = {};
		
			QuestionariorespostaResource.repostaPorUsuarioEidquestionarioresposta({idquestionariodisponivel: $stateParams.id}, function(questionarioresposta){
				$scope.model.questionarioresposta;
				$scope.model.questionarioresposta = questionarioresposta;
			});
			
        }
]).controller("QuestionariorespostaControllerList", ['$scope', '$location', 'QuestionariorespostaResource', 'QuestionarioResource', 'QuestionariodisponivelResource', 'authService',
    function($scope, $location, QuestionariorespostaResource, QuestionarioResource, QuestionariodisponivelResource, authService){
	
		var usuario = authService.getUsuario();
		$scope.usuario = usuario;
		QuestionariodisponivelResource.porUsuario({}, {usuario: usuario}, function(res){
			$scope.questionariodisponivels = res;
		});
		
		QuestionariodisponivelResource.respondidos({}, {usuario: usuario}, function(res){
			$scope.questionariosrespondidos = res
		});
	}
]);