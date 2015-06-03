App.controller("ContatoControllerEdit", ['$scope', '$location', 'ContatoResource' ,'$window', '$routeParams',
                                     
	    function($scope, $location, ContatoResource, $window, $routeParams){
	
			var root = '/contato/';
			var emptyObj = {contato: {"id":0, "nome":'', "endereco":''}};
			
			masterUpdate($scope, $routeParams, $window, $location, ContatoResource, root);
			
			masterDelete($scope,$routeParams,$window, $location, ContatoResource, root);
		}

]).controller("ContatoControllerNew", ['$scope', '$location', 'ContatoResource',
        
       function($scope, $location, ContatoResource){
	
			var root = '/contato/';
			var emptyObj = {contato: {"id":0, "nome":'', "endereco":''}};
			
			masterCreate($scope, $location, ContatoResource, root, emptyObj);
	   }
]).controller("ContatoControllerList", ['$scope', '$location', 'ContatoResource',
                                        
        function($scope, $location, ContatoResource){
			masterRead($scope, $location, ContatoResource);
		}
]);