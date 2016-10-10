App.controller("AdminControllerNew", ['$scope', '$http', '$location', '$state', 'AdminResource', 'PerfilResource', 'UsuarioResource', '$window', '$stateParams',
                                             
     function($scope, $http, $location, $state, AdminResource, PerfilResource, UsuarioResource, $window, $stateParams){

		var root = '/admin/';
		
		var topMessageHasAdmin = "O sistema já possui um Administrador. Não há a necessidade de se cadastrar outro!";
		var topMessageDontHaveAdmin = "O sistema ainda não possui um Administrador cadastrado. Antes de iniciar o uso é necessário o cadastro de um Administrador para que as atividades possam ser executadas.";
		
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
			$http.get('./usuarios/hasAdmin').then(function(res){
				console.log(res.data);
				$scope.hasAdmin = res.data;
				$scope.setTopMessage();
			});
		};
		
		$scope.checkAdmin();
		
		$scope.setTopMessage= function(){
			$scope.topMessage = topMessageDontHaveAdmin;
			if($scope.hasAdmin){
				$scope.topMessage = topMessageHasAdmin;
			}
		};
	 }
]);