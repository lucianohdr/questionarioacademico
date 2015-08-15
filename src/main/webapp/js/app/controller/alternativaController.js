App.controller("AlternativaController", ['$scope', '$location', 'AlternativaResource', 'PerguntaResource','$window', '$stateParams',
                                             
     function($scope, $location, AlternativaResource, PerguntaResource, $window, $stateParams){

		var root = '/alternativa/';
		var emptyObj = {alternativa: {
			"id":0,
			"descricao":""
		}};
		
		$scope.save = function(alternativa){
			
		}
		
		/*$scope.destroy = function(alternativa){
			AlternativaResource.$delete(function(res){
				
			});
		}
		
		$scope.list = function(idpergunta){
			AlternativaResource.getAlternativas(function(res){
				
			});
		}*/
		
		//masterUpdate($scope, $stateParams, $window, $location, AlternativaResource, root);
	 		
		//masterDelete($scope,$stateParams,$window, $location, AlternativaResource, root);
 		
	 }
])/*.controller("AlternativaControllerNew", ['$scope', '$location', 'AlternativaResource', 'TipoUsuarioResource',
     function($scope, $location, AlternativaResource, TipoUsuarioResource){

		var root = '/alternativa/';
		var emptyObj = {alternativa: {
			"id":0,
			"ra":"",
			pessoa : {
				"nome" : "",
				"email": "",
				usuario: {
					"login" : "",
					"senha" : "",
					tipousuario: {
						"id": 5
					}
				}
			}
		}};
		TipoUsuarioResource.query(function (res) { $scope.tipousuarios = res; });
	 	 masterCreate($scope, $location, AlternativaResource, root, emptyObj);
 	}

]).controller("AlternativaControllerList", ['$scope', '$location', 'AlternativaResource',
    function($scope, $location, AlternativaResource){

		masterRead($scope, $location, AlternativaResource);
	}
])*/;