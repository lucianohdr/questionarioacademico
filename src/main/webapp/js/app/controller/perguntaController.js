App.controller("PerguntaController", ['PerguntaResource', 'TipoPerguntaResource', 'QuestionarioResource', 'AlternativaResource','$scope', '$location', '$window', '$stateParams',
                                             
     function(PerguntaResource, TipoPerguntaResource, QuestionarioResource, AlternativaResource,$scope, $location, $window, $stateParams){

		var root = '/pergunta/';
		var Pergunta = function(){
			return { pergunta: {
				"id":0,
				"nome" : "",
				"descricao" : "",
				"alternativas": []
			}};
		}
 		
		$scope.model = new PerguntaResource(new Pergunta);
		
		TipoPerguntaResource.query(function(res){
			$scope.tipoperguntas = res;
		});
		
		$scope.newAlternativa = function(){
			$scope.model.pergunta.alternativas.push(new AlternativaResource());
		}
		
		$scope.destroyAlternativa = function(index){
			$scope.model.pergunta.alternativas.splice(index, 1);
		}
		
		$scope.save  = function(){
			if(!this.model.pergunta.id){
				$scope.model.$save(function(res){
					var questionario = '';
					var pergunta = res;
					
					QuestionarioResource.get({param1: $stateParams.id}, function(response){
						
						questionario = response.questionario;
						
						QuestionarioResource.addPergunta({}, {questionario: questionario, pergunta: pergunta}, function(questionario){
							$scope.$emit("QuestionarioControllerEdit.getPerguntas");
							$scope.limparForm();
						});
					});
				});
			} else {
				$scope.model.$update({param1: $scope.model.pergunta.id}, function(res) {
					var questionario = '';
					var pergunta = res;
					
					$scope.$emit("QuestionarioControllerEdit.getPerguntas");
					$scope.limparForm();
					
				});
			}
		}
		
		$scope.destroy = function(){
			var removeFunction = function(){
				
				QuestionarioResource.rmPergunta({}, {questionario: {id: $stateParams.id}, pergunta: $scope.model.pergunta}, function(){
					$scope.$emit("QuestionarioControllerEdit.getPerguntas");
					
					$scope.model.$delete({param1: $scope.model.pergunta.id}, function(res) {
						$scope.limparForm();
						
						$scope.$emit("QuestionarioControllerEdit.getPerguntas");
					});
				});
			}
			confirm("Tem certeza que deseja deletar esta Pergunta?", removeFunction);
		}
		
		$scope.limparForm = function(){
			$scope.mainForm.$setPristine();
			$scope.model = new PerguntaResource(new Pergunta);
		}
		
		$scope.$on("PerguntaController.editPergunta", function(event, pergunta){
			$scope.model.pergunta = {};
			$scope.model.pergunta = angular.copy(pergunta);
		});
	 }
]);

