App.controller("UsuarioControllerEdit", ['$scope', '$location', 'UsuarioResource', 'TipoUsuarioResource','$window', '$routeParams',
                                             
     function($scope, $location, UsuarioResource, TipoUsuarioResource, $window, $routeParams){

		var root = '/usuario/';
		var emptyObj = {usuario: {
			"id":0,
			"identificacao":"",
			"email":"",
			"nome":"",
			"login":"",
			"senha":"",
		}};
		 TipoUsuarioResource.query(function (res) { $scope.tipousuarios = res; });	
		 masterUpdate($scope, $routeParams, $window, $location, UsuarioResource, root);
	 		
		 masterDelete($scope,$routeParams,$window, $location, UsuarioResource, root);
 		
	 }
]).controller("UsuarioControllerNew", ['$scope', '$location', 'UsuarioResource', 'TipoUsuarioResource',
     function($scope, $location, UsuarioResource, TipoUsuarioResource){

		var root = '/usuario/';
		var emptyObj = {usuario: {
			"id":0,
			"identificacao":"",
			"email":"",
			"nome":"",
			"login":"",
			"senha":"",
		}};
		TipoUsuarioResource.query(function (res) { $scope.tipousuarios = res; });
	 	masterCreate($scope, $location, UsuarioResource, root, emptyObj);
 	}

]).controller("UsuarioControllerList", ['$scope', '$location', 'UsuarioResource',
    function($scope, $location, UsuarioResource){
		masterRead($scope, $location, UsuarioResource);
	}
]);