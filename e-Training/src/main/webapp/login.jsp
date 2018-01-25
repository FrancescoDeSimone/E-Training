<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="icon" type="image/png" href="img/Logo.jpeg">
		<script src="script/jquery.min.js"></script>
		<script type="text/javascript" src="script/regexCheck.js"></script>
		<link rel="stylesheet" href="css/login.css">
		<link rel="stylesheet" href="css/bootstrap-select.min.css">
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/bootstrap-theme.min.css">
		<script src="script/bootstrap-select.min.js"></script>
		<script src="script/bootstrap.min.js"></script>
		<script src="script/utility.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>E-Training</title>
	</head>
<body>
	<div class="background"></div>
	
	<div class="container marginContainer" style="margin-top: 0px;">
		<h1>E-Training</h1>
		<div class="row">
			<div class="main">
				<h3>
					Accedi, oppure <a href="registraTirocinante.jsp">registrati</a>.
				</h3>
				<form role="form" method="post" action="LoginServlet" onsubmit="return checkCampiLogin();">
					<div class="form-group">
						<label for="inputUsernameEmail">Email</label> <input onblur="emailCheck($(this))" 
						data-placement="top" data-content="Email non valida" type="text"
							class="form-control" name="inputEmail">
					</div>
					<div class="form-group">
						<label for="inputPassword">Password</label> <input onblur="passwdCheck($(this))" 
						data-placement="top" data-content="Password non valida" type="password"
							class="form-control" name="inputPassword"> <label
							class="accountType">Tipo Account</label> <select
							class="selectpicker form-control" name="inputTipo">
							<option value="tirocinante">Tirocinante</option>							
							<option value="tutorDidattico">Tutor didattico</option>
							<option value="tutorAziendale">Tutor aziendale</option>
							<option value="funzionario">Funzionario</option>
							<option value="azienda">Azienda</option>
						</select>
					</div>
					<div style="text-align: center;">
					<button type="submit" class="btn btn btn-primary">Log In</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
 <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal" style="display: none;"></button>
 <div id="myModal" class="modal fade" role="dialog" style="margin-top: 100px;">
    <div class="modal-dialog">
    
      <div class="modal-content alert alert-success">
        <div class="modal-body">
          <h3>Account eliminato.</h3>
        </div>
        <div class="modal-footer" style="text-align: center;">          
          <button type="button" class="btn btn-default" data-dismiss="modal">Chiudi</button>
        </div>
       </div>
    
     </div>
  </div>
  <%
     String accountEliminato = (String) request.getAttribute("accountEliminato");
      if (accountEliminato != null) {
  %>
    <script type="text/javascript">apriPopup()</script>
  <%
     }
  %>
  <% if(null != request.getParameter("errore")){ %>
  <script type="text/javascript">checkCampiLogin();</script>
  <%} %>
</body>
</html>