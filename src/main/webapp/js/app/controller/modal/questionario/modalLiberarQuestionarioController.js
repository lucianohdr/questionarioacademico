App.controller('modalLiberarQuestionarioController', function($scope, $modalInstance, DisciplinaResource, 
				CursoResource, QuestionarioResource, questionario, QuestionariodisponivelResource){
	
	$scope.questionario = new QuestionarioResource(questionario);
	
	$scope.questionariodisponivel = {};
	$scope.questionariodisponivel.questionariodisponivel = new QuestionarioResource(questionario);
	
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
	
	$scope.addQuestionariodisponivel = function(questionariodisponivel){
		if(!$scope.questionario.questionariodisponivels){
			$scope.questionario.questionariodisponivels = [];
		}
		
		if(questionariodisponivel){
			if(!$scope.disciplinaJaExiste(questionariodisponivel)){
				questionariodisponivel.questionario = {};
				questionariodisponivel.questionario.id = $scope.questionario.id;
				$scope.questionario.questionariodisponivels.push(questionariodisponivel);
			}
		}
	}
	
	$scope.rmQuestionariodisponivel = function(index){
		$scope.questionario.questionariodisponivels.splice(index, 1);
	}
	
	$scope.disciplinaJaExiste = function(questionariodisponivel){
		
		if($scope.questionario.questionariodisponivels.length != 0){
			var existe = false;
			for(var i = 0; i < $scope.questionario.questionariodisponivels.length; i++){
				
				if(questionariodisponivel.disciplina.id ===  $scope.questionario.questionariodisponivels[i].disciplina.id) {
					existe = true;
					break;
				} 
			}
		} else {
			return existe;
		}
		return existe;
	}
	$scope.ok = function(){
		$scope.questionario.$update({param1: $scope.questionario.id}, function(){
			$modalInstance.close();
		})
	}
	
	$scope.cancel = function () {
		$modalInstance.dismiss('cancel');
	};
	
	$scope.listaDisciplinas();
	
	$scope.listaCursos();
	
});