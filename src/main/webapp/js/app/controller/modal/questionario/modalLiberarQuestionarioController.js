App.controller('modalLiberarQuestionarioController', function($scope, $modalInstance, DisciplinaResource, 
				CursoResource, QuestionarioResource, questionario){
	
	$scope.questionario = new QuestionarioResource(questionario);
	
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
	
	$scope.addDisciplina = function(disciplina){
		if(!$scope.questionario.disciplinas){
			$scope.questionario.disciplinas = [];
		}
		
		if(disciplina){
			if(!$scope.disciplinaJaExiste(disciplina)){
				$scope.questionario.disciplinas.push(disciplina);
			}
		}
	}
	
	$scope.rmDisciplina = function(index){
		$scope.questionario.disciplinas.splice(index, 1);
	}
	
	$scope.disciplinaJaExiste = function(disciplina){
		
		if($scope.questionario.disciplinas.length != 0){
			var existe = false;
			for(var i = 0; i < $scope.questionario.disciplinas.length; i++){
				if(disciplina.id ===  $scope.questionario.disciplinas[i].id) {
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