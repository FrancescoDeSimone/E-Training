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
			<div class="container containerEsterno">
				<h2>Richiesta di tirocinio</h2>
				<form class="form-horizontal" action="/action_page.php">
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Azienda:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="azienda"
								placeholder="Azienda (max. 100)" name="azienda" maxlength="100">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Offerta
							formativa:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="offerta"
								placeholder="Offerta formativa (max. 100)" name="offerta"
								maxlength="100">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">CFU
							tirocinio:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="CFU"
								placeholder="CFU tirocinio" name="CFU" maxlength="2">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="email">Obiettivi
							del tirocinio:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="obiettivi"
								placeholder="Obiettivi del tirocinio (max. 250)"
								name="obiettivi" maxlength="250">
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