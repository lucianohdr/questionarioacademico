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

masterUpdate = function($scope, $stateParams, $window, $location, Resource, root, actionUpdate) {
	Resource.get({param1: $stateParams.id}, function(res) {
		$scope.model = res;
		if (actionUpdate) actionUpdate('get');
	});

	$scope.save = function() {
		$scope.model.$update({param1: $stateParams.id}, function(res) {
			if (actionUpdate) actionUpdate('save', $stateParams.id);
			$location.path(root);
		});
	}

}

masterDelete = function($scope, $stateParams, $window, $location, Resource, root, actionUpdate){
	$scope.destroy = function(){
		var removeFunction = function(){
			$scope.model.$delete({param1: $stateParams.id}, function(res) {
				if (actionUpdate) actionUpdate('delete', $stateParams.id);
				$location.path(root);
			});
		}
		confirm("Tem certeza que deseja deletar este registro?", removeFunction);
	}
}