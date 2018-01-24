<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/sidebar.css">
<link rel="stylesheet" href="css/table.css">
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
				<h2>Aziende</h2>
				<div class="table">
					<div class="tableRow">
						<div class="form-group">
							<label class="right">Nome azienda:</label>
							<div class="left">
								<span id="nomeAzienda">Nome 1</span>
							</div>
						</div>
					</div>
				</div>
				<div class="table">
					<div class="tableRow">
						<div class="form-group">
							<label class="right">P.IVA: </label>
							<div class="left">
								<span id="pivaAzienda">iva</span>
							</div>
						</div>
					</div>
				</div>
				<div class="table">
					<div class="tableRow">
						<div class="form-group">
							<label class="right">Sede:</label>
							<div class="left">
								<span id="sedeAzienda">sede</span>
							</div>
						</div>
					</div>
				</div>
				<div class="table">
					<div class="tableRow">
						<div class="form-group">
							<label class="right">Città:</label>
							<div class="left">
								<span id="cittaAzienda"></span>
							</div>
						</div>
					</div>
				</div>
				<div class="table">
					<div class="tableRow">
						<div class="form-group">
							<label class="right">Nome titolare:</label>
							<div class="left">
								<span id="nomeTitolareAzienda"></span>
							</div>
						</div>
					</div>
				</div>
				<div class="table">
					<div class="tableRow">
						<div class="form-group">
							<label class="right">Cognome titolare:</label>
							<div class="left">
								<span id="cognomeTitolareAzienda"></span>
							</div>
						</div>
					</div>
				</div>
				<div class="table">
					<div class="tableRow">
						<div class="form-group">
							<label class="right">Codice fiscale:</label>
							<div class="left">
								<span id="codiceFiscaleTitolareAzienda"></span>
							</div>
						</div>
					</div>
				</div>
				<div class="table">
					<div class="tableRow">
						<div class="form-group">
							<label class="right">E-mail:</label>
							<div class="left">
								<span id="emailAzienda"></span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>