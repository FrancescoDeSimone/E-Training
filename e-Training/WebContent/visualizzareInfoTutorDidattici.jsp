<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/table.css">
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
				<h2>Tutor didattici</h2>
				<div class="table">
					<div class="tableRow">
						<div class="form-group">
							<label class="right">Nome:</label>
							<div class="left">
								<span>Nome 1</span>
							</div>
						</div>
					</div>
				</div>
				<div class="table">
					<div class="tableRow">
						<div class="form-group">
							<label class="right">Cognome:</label>
							<div class="left">
								<span>Cognome 1</span>
							</div>
						</div>
					</div>
				</div>
				<div class="table">
					<div class="tableRow">
						<div class="form-group">
							<label class="right">Codice fiscale:</label>
							<div class="left">
								<span>Codice fiscale 1</span>
							</div>
						</div>
					</div>
				</div>
				<div class="table">
					<div class="tableRow">
						<div class="form-group">
							<label class="right">Campi di interesse:</label>
							<div class="left">
								<span>Campi di interesse 1</span>
							</div>
						</div>
					</div>
				</div>
				<div class="table">
					<div class="tableRow">
						<div class="form-group">
							<label class="right">E-mail istituzionale:</label>
							<div class="left">
								<span>e-mail istituzionale 1</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>