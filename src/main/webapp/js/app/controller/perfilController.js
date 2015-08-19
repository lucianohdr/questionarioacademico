App.controller("PerfilControllerEdit", ['$scope', '$location', 'PerfilResource' ,'$window', '$stateParams',
                                             
    function($scope, $location, PerfilResource, $window, $stateParams){

		var root = '/perfil/';
		var emptyObj = { perfil: {"id":0, "nome":'', "descricao": '', "observacao" : ''}};
		
		masterUpdate($scope, $stateParams, $window, $location, PerfilResource, root);
		
		masterDelete($scope, $stateParams, $window, $location, PerfilResource, root);
		
	}
]).controller("PerfilControllerNew", ['$scope', '$location', 'PerfilResource',
    function($scope, $location, PerfilResource){
	
		var root = '/perfil/';
		var emptyObj = { perfil: {"id":0, "nome":'', "descricao": '', "observacao" : ''}};
		
		masterCreate($scope, $location, PerfilResource, root, emptyObj);
	}
]).controller("PerfilControllerList", ['$scope', '$location', 'PerfilResource',
    function($scope, $location, PerfilResource){
		masterRead($scope, $location, PerfilResource);
	}
]);