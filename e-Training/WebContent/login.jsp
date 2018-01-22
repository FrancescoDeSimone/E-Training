<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<script src="script/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>E-Training</title>
</head>
<body>
	<div class="background"></div>
	<div class="container marginContainer" style="margin-top: 0px;">
		<h1>E-Training</h1>
		<div class="row">
			<div class="main">
				<h3>
					Accedi, oppure <a href="#">registrati</a>
				</h3>
				<form role="form" method="post" action="LoginServlet">
					<div class="form-group">
						<label for="inputUsernameEmail">email</label> <input type="text"
							class="form-control" id="inputEmail">
					</div>
					<div class="form-group">
						<label for="inputPassword">Password</label> <input type="password"
							class="form-control" id="inputPassword"> <label
							class="accountType">Tipo Account</label> <select
							class="selectpicker form-control" id="inputTipo">
							<option value="tutorDidattico">Tutor didattico</option>
							<option value="tutorAziendale">Tutor aziendale</option>
							<option value="funzionario">Funzionario</option>
							<option value="azienda">Azienda</option>
							<option value="tirocinante">Tirocinante</option>
						</select>
					</div>
					<button type="submit" class="btn btn btn-primary">Log In</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>