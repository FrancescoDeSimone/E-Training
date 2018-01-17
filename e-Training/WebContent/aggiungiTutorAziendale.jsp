<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/sidebar.css">
<link rel="stylesheet" href="css/pageLayout.css">
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
	<jsp:include page="header.jsp"></jsp:include>
	<div class="page">
		<aside> <jsp:include page="sidebar.jsp"></jsp:include> </aside>
		<div class="mycontainer" style="overflow: auto">
			<div class="container containerEsterno">
				<h2>Aggiungi Tutor aziendale</h2>
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
						<label class="control-label col-sm-2">Settore di lavoro:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="lavoro"
								placeholder="Settore di lavoro" name="settore" maxlength="250">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">E-mail:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="email"
								placeholder="example@mail.com" name="email" maxlength="250">
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