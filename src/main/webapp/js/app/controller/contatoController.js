App.controller("ContatoController", ['$scope', '$location', 'ContatoResource' ,'$window', '$routeParams',
                                     
	    function($scope, $location, ContatoResource, $window, $routeParams){
	
			var root = '/contato/';
			var emptyObj = {contato: {"id":0, "nome":'', "endereco":''}};
			
			$scope.read = masterRead($scope, $location, ContatoResource);
			
			$scope.create = masterCreate($scope, $location, ContatoResource, root, emptyObj);
			
			$scope.update = masterUpdate($scope, $routeParams, $window, $location, ContatoResource, root);
			
			$scope.destroy =  masterDelete($scope,$routeParams,$window, $location, ContatoResource, root);
		}

]);