<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/sidebar.css">
<link rel="stylesheet" href="css/pageLayout.css">
<link rel="icon" type="image/png" href="img/Logo.jpeg">
<script src="script/jquery.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<script src="script/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>E-Training</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="page">
		<aside> <jsp:include page="sidebar.jsp"></jsp:include> </aside>
		<div class="mycontainer" style="overflow: auto">
			<div class="container containerEsterno">
				<h2>Aggiungi Tutor didattico</h2>
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
						<label class="control-label col-sm-2">Codice fiscale:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="CF"
								placeholder="Codice fiscale" name="CF" maxlength="16">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">Insegnamenti:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="insegnamenti"
								placeholder="Insegnamenti" name="insegnamenti" maxlength="250">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">Campi di interesse:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="interesse"
								placeholder="Campi di interesse" name="interesse"
								maxlength="250">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">E-mail
							istituzionale:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="email"
								placeholder="example@studenti.unisa.it" name="email"
								maxlength="250">
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
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">Invia</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>