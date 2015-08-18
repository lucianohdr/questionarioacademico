App.controller('modalLiberarQuestionarioController', function($scope, $modalInstance, DisciplinaResource, CursoResource){
	
	console.log($scope);
	$scope.listaDisciplinas = function(){
		DisciplinaResource.query(function(res){
			$scope.disciplinas = res;
		});
	}
	
	$scope.listaCursos = function(){
		CursoResource.query(function(res){
			$scope.cursos = res;
		});
	}
	
	$scope.disciplinaPorCurso = function(){
		
		var idcurso = $scope.model.curso ? $scope.model.curso.id : undefined;
		if(idcurso){
			DisciplinaResource.disciplinaPorCurso({}, {curso: {id: idcurso}}, function(res){
				$scope.disciplinas = res;
			});
		} else {
			$scope.listaDisciplinas();
		}
	}
	
	$scope.ok = function(){
		//faz algo
		$modalInstance.close();
	}
	
	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};
	
	$scope.listaDisciplinas();
	
	$scope.listaCursos();
	
	
});