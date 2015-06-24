<!doctype html>
<html ng-app="QuestionarioAcademico" id="ng-app">
<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="description" content="QuestionarioAcademico">
	<meta name="author" content="Luciano Heleno da Rosa">

	<link href="css/jquery-ui.css" rel="stylesheet">
	<link href="css/bootstrap.css" rel="stylesheet">
	<link href="css/app.css" rel="stylesheet">
	<link href="css/ui.css" rel="stylesheet">

	<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
		<script src="js/lib/compatibility/html5.js"></script>
		<script src="js/lib/compatibility/json.js"></script>
		<script>
			document.createElement('ng-include');
			document.createElement('ng-pluralize');
			document.createElement('ng-view');
 
			// Optionally these for CSS
			document.createElement('ng:include');
			document.createElement('ng:pluralize');
			document.createElement('ng:view');
		</script>
    <![endif]-->

	<script src="js/app/base.js"></script>

	<script src="js/lib/compatibility/sprintf.js"></script>
	
	<script src="js/lib/jquery/jquery-1.10.1.min.js"></script>
	<script src="js/lib/jquery/jquery-migrate-1.2.1.min.js"></script>
	<script src="js/lib/jquery/jquery-ui-1.7.3.min.js"></script>
	
	<script src="js/lib/bootstrap/bootstrap-2.3.2.min.js"></script>
	
	<script src="js/lib/angular/angular-1.3.15.js"></script>
	<script src="js/lib/angular/angular-route-1.3.15.js"></script>
	<script src="js/lib/angular/angular-resource-1.3.15.js"></script>
	<script src="js/lib/angular/angula-messages-1.3.15.min.js"></script>
	
	<script src="js/lib/angular/ui/ui-bootstrap-0.3.0.min.js"></script>
	<script src="js/lib/angular/ui/date.js"></script>
	
	<script src="js/app/main.js"></script>
	
	<script src="js/app/services.js"></script>
	<script src="js/app/masterControllers.js"></script>

	<script src="js/app/directives.js"></script>
	
	<script src="js/app/controller/mainController.js"></script>
	<script src="js/app/controller/homeController.js"></script>
	<script src="js/app/controller/usuarioController.js"></script>
	<script src="js/app/controller/perfilController.js"></script>
	<script src="js/app/controller/contatoController.js"></script>
	<script src="js/app/controller/tarefaController.js"></script>
	<script src="js/app/controller/tipoUsuarioController.js"></script>
	<script src="js/app/controller/categoriaQuestionarioController.js"></script>
	<script src="js/app/controller/usuarioController.js"></script>
	<script src="js/app/controller/alunoController.js"></script>
	<script src="js/app/controller/professorController.js"></script>

	<title>Questionário Acadêmico</title>

</head>
<body ng-controller="MainController">

	<div id="main-menu" class="navbar">
		<div class="navbar-inner">
			<a class="brand" href="#/">Questionário Acadêmico</a>
			<ul class="nav">
				<li ng-class="navClass('usuario')"><a href="#/usuario/">Usuário</a></li>
				<li ng-class="navClass('tipousuario')"><a href="#/tipousuario/">Tipo Usuário</a></li>
				<li ng-class="navClass('categoriaquestionario')"><a href="#/categoriaquestionario/">Categoria Questionário</a></li>
				<li ng-class="navClass('aluno')"><a href="#/aluno/">Aluno</a></li>
				<li ng-class="navClass('professor')"><a href="#/professor/">Professor</a></li>
			</ul>
			
			<a class="brand" ng-click="logout()" href="#/">Sair</a>
		</div>
	</div>
	
	<!-- Aqui é rederizada a view requisitada -->
	<div class="container" ng-view></div>
	
	<div id="loginModal" class="modal hide fade" tabindex="-1" ng-enter="login()">
        <div class="modal-header"><h4>Autenticação</h4></div>
        <div class="modal-body">
			<table>
				<tr><td>Login:</td><td><input id="user" type='text' name='user'/></td></tr>
				<tr><td>Senha:</td><td><input id="pass" type='password' name='pass'/></td></tr>
			</table>
			<hr/>
			<!-- <p><b>Login</b>: admin <b>Password</b>: 123 <b>|</b> <b>Login</b>: user <b>Password</b>:123</p> -->
        </div>
        <div class="modal-footer"><button class="btn" ng-click="login()">Login</button></div>
	</div>
	<script type="text/javascript">$('#loginModal').modal('hide');</script>
	
</body>
</html>