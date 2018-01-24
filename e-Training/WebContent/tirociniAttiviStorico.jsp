<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/sidebar.css">
<link rel="stylesheet" href="css/pageLayout.css">
<link rel="stylesheet" href="css/tirocini.css">
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
		<div class="mycontainer" style="display: block; overflow: auto">
			<div class="tab">
				<button class="tablinks" onclick="opentab(event, 'storicoTirocini')">Storico
					Tirocini</button>
				<button class="tablinks" onclick="opentab(event, 'tirociniAttivi')">Tirocini
					Attivi</button>
			</div>
			<div class="tabcontent" id="storicoTirocini">
				<h2>Storico dei tirocini</h2>
				<div class="container containerEsterno">
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Azienda:</label>
						<div class="col-sm-10">
							<span>Azienda 1</span>
						</div>
					</div>
					<br>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Offerta
							formativa:</label>
						<div class="col-sm-10">
							<span>Offerta Formativa 1</span>
						</div>
					</div>
					<br>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">CFU
							tirocinio:</label>
						<div class="col-sm-10">
							<span>6</span>
						</div>
					</div>
					<br>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Obiettivi
							del tirocinio:</label>
						<div class="col-sm-10">
							<span>Obiettivi del tirocinio 1</span>
						</div>
					</div>
				</div>
				<hr>
				<div class="container">
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Azienda:</label>
						<div class="col-sm-10">
							<span>Azienda 1</span>
						</div>
					</div>
					<br>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Offerta
							formativa:</label>
						<div class="col-sm-10">
							<span>Offerta Formativa 1</span>
						</div>
					</div>
					<br>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">CFU
							tirocinio:</label>
						<div class="col-sm-10">
							<span>6</span>
						</div>
					</div>
					<br>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Obiettivi
							del tirocinio:</label>
						<div class="col-sm-10">
							<span>Obiettivi del tirocinio 1</span>
						</div>
					</div>
				</div>
				<hr>
				<div class="container">
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Azienda:</label>
						<div class="col-sm-10">
							<span>Azienda 1</span>
						</div>
					</div>
					<br>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Offerta
							formativa:</label>
						<div class="col-sm-10">
							<span>Offerta Formativa 1</span>
						</div>
					</div>
					<br>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">CFU
							tirocinio:</label>
						<div class="col-sm-10">
							<span>6</span>
						</div>
					</div>
					<br>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Obiettivi
							del tirocinio:</label>
						<div class="col-sm-10">
							<span>Obiettivi del tirocinio 1</span>
						</div>
					</div>
				</div>
			</div>
			<div class="tabcontent" id="tirociniAttivi">
				<h2>Tirocini attivi</h2>
				<div class="container">
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Azienda:</label>
						<div class="col-sm-10">
							<span>Azienda 1</span>
						</div>
					</div>
					<br>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Offerta
							formativa:</label>
						<div class="col-sm-10">
							<span>Offerta Formativa 1</span>
						</div>
					</div>
					<br>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">CFU
							tirocinio:</label>
						<div class="col-sm-10">
							<span>6</span>
						</div>
					</div>
					<br>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Obiettivi
							del tirocinio:</label>
						<div class="col-sm-10">
							<span>Obiettivi del tirocinio 1</span>
						</div>
					</div>
				</div>
				<hr>
				<div class="container">
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Azienda:</label>
						<div class="col-sm-10">
							<span>Azienda 1</span>
						</div>
					</div>
					<br>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Offerta
							formativa:</label>
						<div class="col-sm-10">
							<span>Offerta Formativa 1</span>
						</div>
					</div>
					<br>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">CFU
							tirocinio:</label>
						<div class="col-sm-10">
							<span>6</span>
						</div>
					</div>
					<br>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Obiettivi
							del tirocinio:</label>
						<div class="col-sm-10">
							<span>Obiettivi del tirocinio 1</span>
						</div>
					</div>
				</div>
				<hr>
				<div class="container">
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Azienda:</label>
						<div class="col-sm-10">
							<span>Azienda 1</span>
						</div>
					</div>
					<br>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Offerta
							formativa:</label>
						<div class="col-sm-10">
							<span>Offerta Formativa 1</span>
						</div>
					</div>
					<br>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">CFU
							tirocinio:</label>
						<div class="col-sm-10">
							<span>6</span>
						</div>
					</div>
					<br>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Obiettivi
							del tirocinio:</label>
						<div class="col-sm-10">
							<span>Obiettivi del tirocinio 1</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>