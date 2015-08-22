App.controller("MainController", ['$scope', '$http', '$state', function($scope, $http, $state){
	
	$scope.login = function () {
		
		$http.post(loginUrl, {usuario: $scope.model.usuario}).success(function(data) {
			if (data.authenticated) {
				$state.go('home');
			} else {
				$scope.title = "Usuário não encontrado";
				$scope.mensagem = "O login ou a senha podem estar errados!";
			}
		});
	}
	
	$scope.logout = function(){
		$http.get(logoutUrl).success(function(data) {
			if (data.authenticated == false) {
				$state.path(baseUrl);
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