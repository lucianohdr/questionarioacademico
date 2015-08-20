App.controller("PessoaControllerEdit", ['$scope', '$location', 'PessoaResource', 'PerfilResource','$window', '$stateParams',
                                             
     function($scope, $location, PessoaResource, PerfilResource, $window, $stateParams){

		var root = '/pessoa/';
		var emptyObj = {pessoa: {
			"id":0,
			"identificacao":"",
			"email":"",
			"nome":"",
			"login":"",
			"senha":"",
		}};
		 PerfilResource.query(function (res) { $scope.perfis = res; });	
		 
		 masterUpdate($scope, $stateParams, $window, $location, PessoaResource, root);
	 		
		 masterDelete($scope,$stateParams,$window, $location, PessoaResource, root);
 		
	 }
]).controller("PessoaControllerNew", ['$scope', '$location', 'PessoaResource', 'PerfilResource',
     function($scope, $location, PessoaResource, PerfilResource){

		var root = '/pessoa/';
		var emptyObj = {pessoa: {
			"id":0,
			"identificacao":"",
			"email":"",
			"nome":"",
			"login":"",
			"senha":"",
		}};
		PerfilResource.query(function (res) { $scope.perfis = res; });
	 	masterCreate($scope, $location, PessoaResource, root, emptyObj);
 	}

]).controller("PessoaControllerList", ['$scope', '$location', 'PessoaResource',
    function($scope, $location, PessoaResource){
		masterRead($scope, $location, PessoaResource);
	}
]);