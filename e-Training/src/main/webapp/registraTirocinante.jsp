<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="icon" type="image/png" href="img/Logo.jpeg">
		<script src="script/jquery.min.js"></script>
		<link rel="stylesheet" href="css/bootstrap-select.min.css">
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/bootstrap-theme.min.css">
		<script src="script/bootstrap-select.min.js"></script>
		<script src="script/bootstrap.min.js"></script>
    <script type="text/javascript" src="script/regexCheck.js"></script>
		<link rel="stylesheet" href="css/signin.css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Registrati</title>
	</head>
<body>
	<div class="background"></div>
	
	<div class="container">
		<h1>Registrati</h1>
		</br>
		
		<form class="form-horizontal" method="post" action="RegistraTirocinanteServlet"
		onsubmit="return checkCampiRegistrazione();">
			<div class="form-group">
				<label class="control-label col-sm-2">Nome:</label>
				<div class="col-sm-5">
					<input onblur="nomeCheck($(this))" data-placement="right" data-content="Nome non valido" 
					type="text" class="form-control" id="nome"
						placeholder="Nome" name="nome" maxlength="50">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2">Cognome:</label>
				<div class="col-sm-5">
					<input onblur="cognomeCheck($(this))" data-placement="right" data-content="Cognome non valido"
					type="text" class="form-control" id="cognome"
						placeholder="Cognome" name="cognome" maxlength="50">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2">E-mail istituzionale:</label>
				<div class="col-sm-5">
					<input onblur="emailCheck($(this))" data-placement="right" data-content="Email non valida"  
					type="text" class="form-control" id="email"
						placeholder="e.example@studenti.unisa.it" name="email"
						maxlength="250">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2">Matricola:</label>
				<div class="col-sm-5">
					<input onblur="matricolaCheck($(this))" data-placement="right" data-content="Matricola non valida"  
					type="text" class="form-control" id="matricola"
						placeholder="Matricola" name="matricola" maxlength="10">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2">Anno di iscrizione:</label>
				<div class="col-sm-5">
				  <select class="selectpicker form-control" name="anno" data-size="4">
				    <%
				      for (int i = 2000; i <= 2050; i++) {
				        
				    %> 
				    <option value="<%= i + "/" + (i+1) %>"><%= i + "/" + (i+1) %></option>
				    <%
				      }
				    %>
				  </select>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2">Password:</label>
				<div class="col-sm-5">
					<input onblur="passwdCheck($(this));" data-placement="right" data-content="Password non valida"  
					type="password" class="form-control" id="password"
						placeholder="Password" name="password" maxlength="50">
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2">Conferma password:</label>
				<div class="col-sm-5">
					<input onblur="passwdEquals($(this));" data-placement="right" data-content="Password non combacia con la precendente" 
					type="password" class="form-control" id="password2"
						placeholder="Password" name="password2" maxlength="50">
				</div>
			</div>
			
			<div class="form-group" style="text-align: center;">
				<div class="col-sm-offset-2 col-sm-5">
					<button type="submit" class="btn btn-primary">Invia</button>
				</div>
			</div>
		</form>
	</div>
	<% if(null != request.getParameter("errore")){ %>
  <script type="text/javascript">checkCampiRegistrazione();</script>
  <%} %>
</body>
</html>