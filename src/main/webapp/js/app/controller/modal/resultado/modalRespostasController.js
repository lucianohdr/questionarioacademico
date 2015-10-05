App.controller('modalRespostasController', function($scope, $modalInstance, resultadoResposta){
	
	$scope.resultadoResposta = resultadoResposta;
	
	$scope.ok = function(){
		$modalInstance.dismiss('cancel');
	}
});