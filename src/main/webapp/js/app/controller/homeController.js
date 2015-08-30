App.controller("HomeController", ['$scope', '$location', '$window', '$stateParams', '$state', 'QuestionarioResource', 'authService',
                                             
     function($scope, $location, $window, $stateParams, $state, QuestionarioResource, authService){
			
			var usuario = authService.identity().$$state.value.usuario;
			
			QuestionarioResource.porUsuarioEporStatus({}, {usuario: usuario}, function(res){
				$scope.questionarios = res;
			});
	 }
]);
