App.controller("PerguntaController", ['PerguntaResource', 'TipoPerguntaResource', 'QuestionarioResource', '$scope', '$location', '$window', '$stateParams',
                                             
     function(PerguntaResource, TipoPerguntaResource, QuestionarioResource, $scope, $location, $window, $stateParams){

		var root = '/pergunta/';
		var emptyObj = {pergunta: {
			"id":0,
			"nome" : "",
			"descricao" : ""
		}};
 		
		TipoPerguntaResource.query(function(res){
			$scope.tipoperguntas = res;
		});
		
		$scope.model = new PerguntaResource(emptyObj);
		
		$scope.save  = function(){
			//console.log($scope.mainForm);
			if(!this.model.pergunta.id){
				$scope.model.$save(function(res){
					var questionario = '';
					var pergunta = res;
					
					QuestionarioResource.get({param1: $stateParams.id}, function(response){
						
						questionario = response.questionario;
						
						QuestionarioResource.addPergunta({}, {questionario: questionario, pergunta: pergunta}, function(questionario){
							$scope.$emit("QuestionarioControllerEdit.getPerguntas");
							$scope.mainForm.$setPristine();
						});
					});
				});
			} else {
				$scope.model.$update({param1: $scope.model.pergunta.id}, function(res) {
					var questionario = '';
					var pergunta = res;
					
					$scope.$emit("QuestionarioControllerEdit.getPerguntas");
					$scope.model.pergunta = {};
					$scope.mainForm.$setPristine();
					
				});
			}
		}
		
		$scope.destroy = function(){
			var removeFunction = function(){
				$scope.model.$delete({param1: $scope.model.pergunta.id}, function(res) {
					$scope.mainForm.$setPristine();
					$scope.model.pergunta = PerguntaResource();
				});
			}
			confirm("Tem certeza que deseja deletar os registros?", removeFunction);
		}
		
		$scope.$on("PerguntaController.editPergunta", function(event, pergunta){
			$scope.model.pergunta = angular.copy(pergunta);
		});
	 }
]);

