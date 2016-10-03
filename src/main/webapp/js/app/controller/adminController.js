App.controller("AdminControllerNew", ['$scope', '$location', '$state', 'AdminResource', 'PerfilResource', 'UsuarioResource', '$window', '$stateParams',
                                             
     function($scope, $location, $state, AdminResource, PerfilResource, UsuarioResource, $window, $stateParams){

		var root = '/admin/';
		var emptyObj = {professor: {
			"id":0,
			"matricula":"",
			pessoa : {
				"nome" : "",
				"email": "",
				usuario: {
					"login" : "",
					"senha" : "",
					"perfis": []
				}
			}
		}};
		
		$scope.model = new AdminResource(emptyObj);
		$scope.save = function() {
			$scope.model.$save(function(res) {
				$state.go('login');
			});
		};
		
		$scope.checkAdmin = function(){
			UsuarioResource.hasAdmin(function(res){
				console.log(res);
				$scope.hasAdmin = res;
			});
		};
		
		$scope.checkAdmin();
	 }
]);