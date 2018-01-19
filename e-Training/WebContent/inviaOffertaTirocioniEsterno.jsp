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
<link rel="stylesheet" href="css/funzionario.css">
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
		<div class="mycontainer">
			<div class="container containerEsterno">
				<h2>Offerta formativa per tirocinio esterno</h2>
				<form class="form-horizontal" action="/action_page.php">
					<div class="form-group">
						<label class="control-label col-sm-2">Tema:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="tema"
								placeholder="Tema (max. 250)" name="tema" maxlength="250">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">Periodo di
							tirocinio:</label>
						<div class="col-xs-4">
							<input type="date" class="form-control" id="gg1" name="gg1"
								maxlength="2">
						</div>
						<div class="col-xs-4">
							<input type="date" class="form-control" id="mm1" name="mm1"
								maxlength="2">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">Tutor Accademico:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="tutorAcc"
								placeholder="Tutor Accademico" name="tutorAcc" maxlength="250">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">Tutor Aziendale:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="tutorAz"
								placeholder="Tutor Aziendale" name="tutorAz" maxlength="250">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2">Eventuali
							facilitazioni previste:</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="facilitazioni"
								placeholder="Eventuali facilitazioni previste (max. 250)"
								name="facilitazioni" maxlength="250">
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
	</div>
	</div>
	</div>
</body>
</html>