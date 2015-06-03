App.controller("ContatoControllerEdit", ['$scope', '$location', 'ContatoResource' ,'$window', '$routeParams',
                                     
	    function($scope, $location, ContatoResource, $window, $routeParams){
	
			var root = '/contato/';
			var emptyObj = {contato: {"id":0, "nome":'', "endereco":''}};
			
			$scope.update = masterUpdate($scope, $routeParams, $window, $location, ContatoResource, root);
			
			$scope.destroy =  masterDelete($scope,$routeParams,$window, $location, ContatoResource, root);
		}

]).controller("ContatoControllerNew", ['$scope', '$location', 'ContatoResource',
        
       function($scope, $location, ContatoResource){
	
			var root = '/contato/';
			var emptyObj = {contato: {"id":0, "nome":'', "endereco":''}};
			
			$scope.create = masterCreate($scope, $location, ContatoResource, root, emptyObj);
	   }
]).controller("ContatoControllerList", ['$scope', '$location', 'ContatoResource',
                                        
        function($scope, $location, ContatoResource){
			$scope.read = masterRead($scope, $location, ContatoResource);
		}
]);