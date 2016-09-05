App.controller("AdminControllerNew", ['$scope', '$location', 'ProfessorResource', 'PerfilResource', '$window', '$stateParams',
                                             
     function($scope, $location, ProfessorResource, PerfilResource, $window, $stateParams){

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
		
		masterCreate($scope, $location, ProfessorResource, root, emptyObj);
	 }
]);