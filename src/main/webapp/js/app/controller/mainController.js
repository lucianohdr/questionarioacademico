App.controller("MainController", ['$scope', '$http', '$state', 'authService', '$location' ,function($scope, $http, $state, authService, $location){
	
	$scope.login = function () {
		$http.post(loginUrl, {usuario: $scope.model.usuario}).success(function(data) {
			if (data.authenticated) {
				console.log(data);
				authService.authenticate({
					login : data.usuario.login,
					roles: data.roles
				});
				
				if ($scope.returnToState) $state.go($scope.returnToState.name, $scope.returnToStateParams);
		        else $state.go('home');
				
			} else {
				$scope.title = "Usuário não encontrado";
				$scope.mensagem = "O login ou a senha podem estar errados!";
			}
		});
	}
	
	$scope.logout = function(){
		$http.get(logoutUrl).success(function(data) {
			authService.authenticate(null);
			
			if (data.authenticated == false) {
				$state.go('login');
			} else {
				alert(data.message);
			}
		});
	}
	
	$scope.navClass = function(page) {
		var currentRoute = $state.current.url.substring(1).replace('/','') || 'home';
		return page === currentRoute ? 'active' : '';
	};
}]);