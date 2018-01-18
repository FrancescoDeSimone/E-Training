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
		<div class="mycontainer ">
			<div style="margin: 30px auto; width: 600px">
				<div class="container containerEsterno">
					<div class="row">
						<h2>Disponibilità per richieste di tirocinio</h2>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<form action="" data-toggle="validator">
								<div class="form-inline required">
									<div class="form-group has-feedback">
										<label class="input-group"> <span
											class="input-group-addon"> <input type="radio"
												name="test" value="0">
										</span>
											<div class="form-control form-control-static">
												Disponibile</div> <span class="glyphicon form-control-feedback "></span>
										</label>
									</div>
									<div class="form-group has-feedback ">
										<label class="input-group"> <span
											class="input-group-addon"> <input type="radio"
												name="test" value="1">
										</span>
											<div class="form-control form-control-static">Non
												disponibile</div> <span class="glyphicon form-control-feedback "></span>
										</label>
									</div>
									<br> <br>
									<button type="submit" class="btn btn-default">Invia</button>
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