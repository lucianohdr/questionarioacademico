App.controller("MainController", ['$scope', '$http', '$location', function($scope, $http, $location){
	
	$scope.login = function () {
		$http.post(loginUrl, {usuario: $scope.model.usuario}).success(function(data) {
			if (data.authenticated) {
				$location.path(baseUrl);
				$('#loginModal').modal('hide');
			} else {
				$scope.title = "Usuário não encontrado";
				$scope.mensagem = "O login ou a senha podem estar errados!";
			}
		});
	}
	
	$scope.logout = function(){
		$http.get(logoutUrl).success(function(data) {
			if (data.authenticated == false) {
				$location.path(baseUrl);
			} else {
				alert(data.message);
			}
		});
	}
	
	$scope.navClass = function(page) {
		var currentRoute = $location.path().substring(1).replace('/','') || 'home';
		return page === currentRoute ? 'active' : '';
	};
}]);