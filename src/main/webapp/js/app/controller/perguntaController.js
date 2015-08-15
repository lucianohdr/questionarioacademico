App.controller("PerguntaController", ['PerguntaResource', 'TipoPerguntaResource', 'QuestionarioResource', 'AlternativaResource','$scope', '$location', '$window', '$stateParams',
                                             
     function(PerguntaResource, TipoPerguntaResource, QuestionarioResource, AlternativaResource,$scope, $location, $window, $stateParams){

		var root = '/pergunta/';
		var emptyObj = {pergunta: {
			"id":0,
			"nome" : "",
			"descricao" : ""
		}};
 		
		$scope.model = new PerguntaResource(emptyObj);
		
		TipoPerguntaResource.query(function(res){
			$scope.tipoperguntas = res;
		});
		$scope.model.pergunta.alternativas = [];

		/*AlternativaResource.query({}, {pergunta: $scope.model.pergunta},function(res){
			$scope.model.pergunta.alternativas = res;
		})*/
		
		$scope.newAlternativa = function(){
			$scope.model.pergunta.alternativas.push(new AlternativaResource());
		}
		
		$scope.destroyAlternativa = function(index){
			var alternativaDelete = $scope.model.pergunta.alternativas[index];
			if(alternativaDelete.id){
				AlternativaResource.remove({param1: alternativaDelete.id}, function(){
					$scope.model.pergunta.alternativas.splice(index, 1);
				})
			} else {
				$scope.model.pergunta.alternativas.splice(index, 1);
			}
		}
		
		/*$scope.saveAlternativa = function(alternativa){
			$scope.model.pergunta.alternativas.splice(index, 1);
		}*/
		
		$scope.save  = function(){
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
				
				QuestionarioResource.rmPergunta({}, {questionario: {id: $stateParams.id}, pergunta: $scope.model.pergunta}, function(){
					$scope.$emit("QuestionarioControllerEdit.getPerguntas");
					
					$scope.model.$delete({param1: $scope.model.pergunta.id}, function(res) {
						$scope.mainForm.$setPristine();
						$scope.model.pergunta = new PerguntaResource();
						$scope.model.pergunta.alternativas = [];
						$scope.$emit("QuestionarioControllerEdit.getPerguntas");
					});
				});
			}
			confirm("Tem certeza que deseja deletar os registros?", removeFunction);
		}
		
		$scope.$on("PerguntaController.editPergunta", function(event, pergunta){
			$scope.model.pergunta = {};
			$scope.model.pergunta = angular.copy(pergunta);
		});
	 }
]);

