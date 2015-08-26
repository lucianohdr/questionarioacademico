App.controller("MenuController", ['$scope', '$location', '$window', '$stateParams', '$state', 'authService', 'identity',
                                             
     function($scope, $location, $window, $stateParams, $state, authService, identity){
		$scope.telas = identity.telas;
		console.log(identity);

	 }
]);