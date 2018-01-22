<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/signin.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registrati</title>
</head>
<body>
	<div class="background"></div>
	<div class="container marginContainer containerEsterno">
		<h1>Registati</h1>
		<form class="form-horizontal" action="/action_page.php">
			<div class="form-group">
				<label class="control-label col-sm-2">Nome:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="nome"
						placeholder="Nome" name="nome" maxlength="250">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">Cognome:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="cognome"
						placeholder="Cognome" name="cognome" maxlength="250">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">E-mail istituzionale:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="email"
						placeholder="example@studenti.unisa.it" name="email"
						maxlength="250">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">Matricola:</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="matricola"
						placeholder="Matricola" name="matricola" maxlength="10">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">Anno di iscrizione:</label>
				<div class="col-sm-10">
					<input type="date" class="form-control" id="anno"
						placeholder="20xx/20xx" name="anno" maxlength="9">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">Password:</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="password"
						placeholder="password" name="password" maxlength="250">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-2">Conferma password:</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="password2"
						placeholder="password" name="password2" maxlength="250">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">Invia</button>
				</div>
			</div>
</body>
</html>