App.controller("VisualizarResultadoController", ['$scope', '$location', '$window', '$stateParams', 'QuestionariorespostaResource', 'QuestionariodisponivelResource', '$modal',
                                             
     function($scope, $location, $window, $stateParams, QuestionariorespostaResource, QuestionariodisponivelResource, $modal){
		//Buscando
	
	/*QuestionariodisponivelResource.get({param1: $stateParams.id}, function(res){*/
		QuestionariorespostaResource.carregaResultado({idquestionariodisponivel: $stateParams.id}, function(resultado){
			$scope.resultado = resultado;
			//$scope.questionariodisponivel.questionariorespostas = questionariorespostas;
		});
		/*});*/
		
		 $scope.exibirRespostas = function(resultadoResposta){
			 var modalInstance = $modal.open({
				size: 'lg',
		 		animation: true,
		 		templateUrl: 'view/modal/resultado/respostas.html',
		 		controller: 'modalRespostasController',
		 		resolve : {
		 			resultadoResposta : function(){
			 			return resultadoResposta;
			 		}
		 		}
		 	 });
		 } 
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