App.controller("HomeController", ['$scope', '$location', '$window', '$stateParams', '$state', 'QuestionarioResource', 'QuestionariodisponivelResource', 'authService',
                                             
     function($scope, $location, $window, $stateParams, $state, QuestionarioResource, QuestionariodisponivelResource, authService){
			
			var usuario = authService.identity().$$state.value.usuario;
			
			QuestionariodisponivelResource.porUsuario({}, {usuario: usuario}, function(res){
				$scope.questionariodisponivels = res;
			});
	 }
]);
