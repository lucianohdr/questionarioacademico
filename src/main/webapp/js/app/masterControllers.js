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
	$scope.save = function() {
		$scope.model.$save(function(res) {
			$location.path(root);
		});
	}
}

masterUpdate = function($scope, $routeParams, $window, $location, Resource, root, actionUpdate) {
	
	Resource.get({param1: $routeParams.id}, function(res) {
		$scope.model = res;
		if (actionUpdate) actionUpdate('get');
	});

	$scope.save = function() {
		$scope.model.$update({param1: $routeParams.id}, function(res) {
			console.log(actionUpdate);
			if (actionUpdate) actionUpdate('save', $routeParams.id);
			$location.path(root);
		});
	
	}
	/*$scope.destroy = function() {
		console.log(actionUpdate);
		deleteConfirm('Are you sure?', $location, $scope, $routeParams, root, actionUpdate)
	}*/
}

masterDelete = function($scope, $routeParams, $window, $location, Resource, root){
	console.log("teste");
	$scope.destroy = function(){
		deleteConfirm('Are you sure?')
		/*if(deleteConfirm('Are you sure?')){
			$scope.model.$delete({param1: $routeParams.id}, function(res) {
				if (actionUpdate) actionUpdate('delete', $routeParams.id);
				$location.path(root);
			});
		} */
	}
}

deleteConfirm = function(message/*, $location, $scope, $routeParams, root, actionUpdate*/) {
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
				$(this).remove();
				return false;
			},
			"Ok" : function() {
				
				$(this).remove();
				return true;
			}
		}
	});
}
