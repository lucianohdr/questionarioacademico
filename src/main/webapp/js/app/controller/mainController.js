App.controller("MainController", ['$scope', '$http', '$location', function($scope, $http, $location){
	$scope.login = function () {

		$http.post(loginUrl, {usuario: {
			username : $('#user').val(),
			password : $('#pass').val()
	    }}).success(function(data) {
			if (data.authenticated) {
				$location.path(baseUrl);
				$('#loginModal').modal('hide');
			} else {
				$('#loginModal').modal('hide');
				alert(data.message);
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
	
/*	$scope.setLocale = function(language) {
		console.log(language);
		localize.setLanguage(language);
	}*/

	$scope.navClass = function(page) {
		var currentRoute = $location.path().substring(1).replace('/','') || 'home';
		return page === currentRoute ? 'active' : '';
	};
	
}]);