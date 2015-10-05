App.controller("VisualizarResultadoController", ['$scope', '$location', '$window', '$stateParams',
                                             
     function($scope, $location, $window, $stateParams){
		
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