App.controller("VisualizarResultadoController", ['$scope', '$location', '$window', '$stateParams', 'QuestionariorespostaResource', 'QuestionariodisponivelResource',
                                             
     function($scope, $location, $window, $stateParams, QuestionariorespostaResource, QuestionariodisponivelResource){
		//Buscando
	
		
	/*QuestionariodisponivelResource.get({param1: $stateParams.id}, function(res){*/
		QuestionariorespostaResource.carregaResultado({idquestionariodisponivel: $stateParams.id}, function(questionariorespostas){
			$scope.questionariodisponivel = res.questionariodisponivel;
			$scope.questionariodisponivel.questionariorespostas = questionariorespostas;
		});
		/*});*/
	 }
]).controller("ResultadoControllerList", ['$scope', '$location', 'QuestionariodisponivelResource', 'authService', 'UsuarioResource',
    function($scope, $location, QuestionariodisponivelResource, authService, UsuarioResource){
	
		UsuarioResource.perfis(function(res){
			$scope.perfis = res;
		});
	
		$scope.questRespostaPorUsuarioEPorPerfil = function(){
			var perfil = $scope.perfil;
			
			if(perfil){
				QuestionariodisponivelResource.respostasPorUsuarioEPorPerfil({}, {perfil: perfil}, function(res){
					$scope.questionariodisponivels = res;
				});
			}
		}
	}
]);