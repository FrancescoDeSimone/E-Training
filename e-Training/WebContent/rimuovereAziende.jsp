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
		<div class="mycontainer">
			<jsp:include page="asideBarName.jsp"></jsp:include>
			<div class="rightContent">
				<div class="container containerEsterno">
					<h2>Aziende</h2>
					<div class="container">
						<div class="form-group">
							<label class="control-label col-sm-2">Nome azienda:</label>
							<div class="col-sm-10">
								<span>Nome azienda 1</span>
							</div>
						</div>
						<br>
						<div class="form-group">
							<label class="control-label col-sm-2">P.IVA:</label>
							<div class="col-sm-10">
								<span>P.IVA 1</span>
							</div>
						</div>
						<br>
						<div class="form-group">
							<label class="control-label col-sm-2">Sede:</label>
							<div class="col-sm-10">
								<span>Sede 1</span>
							</div>
						</div>
						<br>
						<div class="form-group">
							<label class="control-label col-sm-2">Città:</label>
							<div class="col-sm-10">
								<span>Città 1</span>
							</div>
						</div>
						<br>
						<div class="form-group">
							<label class="control-label col-sm-2">Nome titolare:</label>
							<div class="col-sm-10">
								<span>Nome titolare 1</span>
							</div>
						</div>
						<br>
						<div class="form-group">
							<label class="control-label col-sm-2">Cognome titolare:</label>
							<div class="col-sm-10">
								<span>Cognome titolare 1</span>
							</div>
						</div>
						<br>
						<div class="form-group">
							<label class="control-label col-sm-2">Codice fiscale:</label>
							<div class="col-sm-10">
								<span>Codice fiscale 1</span>
							</div>
						</div>
						<br>
						<div class="form-group">
							<label class="control-label col-sm-2">E-mail:</label>
							<div class="col-sm-10">
								<span>E-mail 1</span>
							</div>
						</div>
						<div align="right">
							<input type="button" class="btn btn-default" name="Rimuovi1"
								value="Rimuovi">
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>
</html>