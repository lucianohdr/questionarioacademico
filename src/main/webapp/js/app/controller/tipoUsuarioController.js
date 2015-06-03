App.controller("TipoUsuarioControllerEdit", ['$scope', '$location', 'TipoUsuarioResource' ,'$window', '$routeParams',
                                             
    function($scope, $location, TipoUsuarioResource, $window, $routeParams){

		var root = '/tipousuario/';
		var emptyObj = { tipousuario: {"id":0, "nome":'', "descricao": '', "observacao" : ''}};
		
		masterUpdate($scope, $routeParams, $window, $location, TipoUsuarioResource, root);
		
		masterDelete($scope,$routeParams,$window, $location, TipoUsuarioResource, root);
		
	}
]).controller("TipoUsuarioControllerNew", ['$scope', '$location', 'TipoUsuarioResource',
    function($scope, $location, TipoUsuarioResource){
		var root = '/tipousuario/';
		var emptyObj = { tipousuario: {"id":0, "nome":'', "descricao": '', "observacao" : ''}};
		
		masterCreate($scope, $location, TipoUsuarioResource, root, emptyObj);
	}
]).controller("TipoUsuarioControllerList", ['$scope', '$location', 'TipoUsuarioResource',
    function($scope, $location, TipoUsuarioResource){
	
		masterRead($scope, $location, TipoUsuarioResource);
		
	}
]);