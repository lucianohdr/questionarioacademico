/*TipoUsuarioController = (function() {

	"use strict";
	
	var root = '/tipousuario/';
	var emptyObj = { tipousuario: {"id":0, "nome":'', "descricao": '', "observacao" : ''}};
	
	function TipoUsuarioController() {}

	TipoUsuarioController.prototype.read = function($scope, $location, TipoUsuarioResource) {
		masterRead($scope, $location, TipoUsuarioResource);
	};
	
	TipoUsuarioController.prototype.read.$inject = ['$scope', '$location', 'TipoUsuarioResource'];

	TipoUsuarioController.prototype.create = function($scope, $location, TipoUsuarioResource) {
		masterCreate($scope, $location, TipoUsuarioResource, root, emptyObj);
	};
	
	TipoUsuarioController.prototype.create.$inject = ['$scope', '$location', 'TipoUsuarioResource'];

	TipoUsuarioController.prototype.update = function($scope, $routeParams, $window, $location, TipoUsuarioResource) {
		masterUpdate($scope, $routeParams, $window, $location, TipoUsuarioResource, root);
		
		masterDelete($scope, $routeParams, $window, $location, TipoUsuarioResource, root)
	};
	
	TipoUsuarioController.prototype.update.$inject = ['$scope', '$routeParams', '$window', '$location', 'TipoUsuarioResource'];

	TipoUsuarioController.prototype.destroy = function($scope, $routeParams, $window, $location, ContatoResource){
		masterDelete($scope,$routeParams,$window,$location,ContatoResource, root, true);
	};
	
	TipoUsuarioController.prototype.destroy.$inject = ['$scope', '$routeParams', '$window', '$location', 'ContatoResource'];
	
	return TipoUsuarioController;
  
})();

var tipoUsuarioController = new TipoUsuarioController();
*/

App.controller("TipoUsuarioControllerEdit", ['$scope', '$location', 'TipoUsuarioResource' ,'$window', '$routeParams',
                                             
    function($scope, $location, TipoUsuarioResource, $window, $routeParams){

		var root = '/tipousuario/';
		var emptyObj = { tipousuario: {"id":0, "nome":'', "descricao": '', "observacao" : ''}};
		
		$scope.update = masterUpdate($scope, $routeParams, $window, $location, TipoUsuarioResource, root);
		
		$scope.destroy =  masterDelete($scope,$routeParams,$window, $location, TipoUsuarioResource, root);
	}
]).controller("TipoUsuarioControllerNew", ['$scope', '$location', 'TipoUsuarioResource',
    function($scope, $location, TipoUsuarioResource){
		var root = '/tipousuario/';
		var emptyObj = { tipousuario: {"id":0, "nome":'', "descricao": '', "observacao" : ''}};
		
		$scope.create = masterCreate($scope, $location, TipoUsuarioResource, root, emptyObj);
	}
]).controller("TipoUsuarioControllerList", ['$scope', '$location', 'TipoUsuarioResource',
    function($scope, $location, TipoUsuarioResource){
	
		$scope.read = masterRead($scope, $location, TipoUsuarioResource);
		
	}
]);