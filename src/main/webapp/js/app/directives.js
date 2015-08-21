var INTEGER_REGEXP = /^\-?\d*$/;
App.directive('integer', function() {
	return {
		require : 'ngModel',
		link : function(scope, elm, attrs, ctrl) {
			ctrl.$parsers.unshift(function(viewValue) {
				if (INTEGER_REGEXP.test(viewValue)) {
					ctrl.$setValidity('integer', true);
					return viewValue;
				} else {
					ctrl.$setValidity('integer', false);
					return undefined;
				}
			});
		}
	};
});

App.directive('passwordValidate', function() {
	return {
		require : 'ngModel',
		link : function(scope, elm, attrs, ctrl) {
			ctrl.$parsers.unshift(function(viewValue) {

				scope.pwdValidLength = (viewValue
						&& viewValue.length >= 8 ? 'valid'
						: undefined);
				scope.pwdHasLetter = (viewValue && /[A-z]/
						.test(viewValue)) ? 'valid'
						: undefined;
				scope.pwdHasNumber = (viewValue && /\d/
						.test(viewValue)) ? 'valid'
						: undefined;

				if (scope.pwdValidLength
						&& scope.pwdHasLetter
						&& scope.pwdHasNumber) {
					ctrl.$setValidity('pwd', true);
					return viewValue;
				} else {
					ctrl.$setValidity('pwd', false);
					return undefined;
				}

			});
		}
	};
});

App.directive('ngEnter', function() {
	return function(scope, element, attrs) {
		element.bind("keydown keypress", function(event) {
			if(event.which == 13) {
				scope.$apply(function(){
					scope.$eval(attrs.ngEnter);
				});
				event.preventDefault();
			}
		});
	};
});

App.directive('doScroll', function() {
	return function(scope, elm, attr) {
		var raw = elm[0];
		elm.bind('scroll', function() {
			if (raw.scrollTop + raw.offsetHeight >= raw.scrollHeight) {
				scope.$apply(attr.doScroll);
			}
		});
	};
});

//diretiva responsavel por fazer uma comparação de valores entre dois campos
App.directive("compareTo", function() {
    return {
        require: "ngModel",
        scope: {
            otherModelValue: "=compareTo"
        },
        link: function(scope, element, attributes, ngModel) {
             
            ngModel.$validators.compareTo = function(modelValue) {
                return modelValue == scope.otherModelValue;
            };
 
            scope.$watch("otherModelValue", function() {
                ngModel.$validate();
            });
        }
    };
});

/*App.directive("userAvailability", ['UsuarioResource', function(UsuarioResource) {
	return {
		require: "ngModel",
		scope: {
			login: "=userAvailability"
		},
		link: function(scope, element, attributes, ngModel) {
			
			var checkUser = function(login){
				
				console.log("checkUser" + login);
				
				UsuarioResource.loginDisponivel({login : login}, function(res){
					//console.log(result);
					scope.available = res.result;
				});
			}
			
			
			ngModel.$validators.userAvailability = function(login) {
				console.log("$validators" + scope.available);
				return !scope.available;
			};
			
			scope.$watch("login", function() {
				console.log("$watch" + scope.available);
				checkUser(scope.login);
				scope.$watch("available", function() {
					ngModel.$validate();
				});
			});
			
		}
	};
}]);*/


App.directive('userAvailability', ['$q', '$http', function ($q, $http) {
    return {
        require: 'ngModel',
        scope: {
        	login: "=userAvailability"
        },
        link: function (scope, elem, attrs, ngModel) {
        	var deferred = $q.defer();
        	
        	ngModel.$asyncValidators.userAvailability = function(modelValue, viewValue){
    			return $http.get(baseUrl + 'usuarios/loginDisponivel/', { params : { login: modelValue}});
        	}
        }
    }
}]);

App.directive("uiAlert", function(){
	return {
		templateUrl: "view/alert.html",
		replace: true,
		restrict: "E",
		scope: {
			title: "@",
		},
		transclude: true
	}
});

//diretiva responsavel por facilitar a passagem de campos para a validação
App.directive("validateMsgFor", function(){
	return{
		templateUrl : "view/templates/validateMsgFor.html",
		restrict:  "E",
		scope: {
			field : "="
		}
	}
});