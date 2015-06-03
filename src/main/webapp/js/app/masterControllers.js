masterRead = function($scope, $location, Resource) {
	var start = 0;
	$scope.list = [];
	$scope.load = function() {
		
		
		Resource.query({param1: string(start), param2: limit}, function (res) {
			$.each(res, function(index, item) {
				if (item.id) {
					$scope.list.push(item);
					start++;
				}
			});
		});
	}
	$scope.load();
}

masterCreate = function($scope, $location, Resource, root, emptyObj) {
	
	$scope.model = new Resource(emptyObj);
	//console.log($scope.model);
	$scope.save = function() {
		$scope.model.$save(function(res) {
			$location.path(root);
		});
	}
}

masterUpdate = function($scope, $routeParams, $window, $location, Resource, root, actionUpdate) {
	
	console.log($routeParams.id);
	
	Resource.get({param1: $routeParams.id}, function(res) {
		
		
		$scope.model = res;
		if (actionUpdate) actionUpdate('get');
	});

	$scope.save = function() {
		$scope.model.$update({param1: $routeParams.id}, function(res) {
			if (actionUpdate) actionUpdate('save', $routeParams.id);
			$location.path(root);
		});
	}

}

masterDelete = function($scope, $routeParams, $window, $location, Resource, root, actionUpdate){
	$scope.destroy = function(){
		var removeFunction = function(){
			$scope.model.$delete({param1: $routeParams.id}, function(res) {
				if (actionUpdate) actionUpdate('delete', $routeParams.id);
				$location.path(root);
			});
		}
		
		confirm("Tem certeza que deseja deletar os registros?", removeFunction);
	}
}



/*//TODO: tentar usar o tal do "confirm(mensagem)"
deleteConfirm = function(message, callback, $location, $scope, $routeParams, root, actionUpdate) {
	"use strict"
	$(document.createElement('div')).attr({
		title : 'Warning',
		'class' : 'alert'
	}).html(message).dialog({
		width : 'auto',
		modal : true,
		resizable : false,
		buttons : {
			"Cancelar" : function() {
				console.log("cancelar");
				$(this).remove();
				return false;
			},
			"Ok" : function() {
				console.log("OK");
				$(this).remove();
				return true;
			}
		}
	});
}
*/