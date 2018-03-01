<%@page import="it.unisa.etraining.model.bean.TutorAziendale"%>
<%@page import="it.unisa.etraining.model.bean.TutorDidattico"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="css/header.css">
		<link rel="stylesheet" href="css/sidebar.css">
		<link rel="stylesheet" href="css/pageLayout.css">
		<link rel="icon" type="image/png" href="img/Logo.jpeg">
		<script src="script/jquery.min.js"></script>
		<link rel="stylesheet" href="css/bootstrap-select.min.css">
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/bootstrap-theme.min.css">
		<script src="script/bootstrap-select.min.js"></script>
		<script src="script/bootstrap.min.js"></script>
		<script src="script/sidebar.js"></script>
		<script src="script/utility.js"></script>
		<script src="script/regexCheck.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>E-Training</title>
	</head>
<body onload="activate(3);">
	<jsp:include page="header.jsp"></jsp:include>
	
	<div class="page">
    <aside> <jsp:include page="sidebarTirocinante.jsp"></jsp:include> </aside>    
		
		<div class="mycontainer" style="overflow: hidden;">
			<h2 style="padding: 15px; text-align: center;">Compila Registro</h2>
				
			<form class="form-horizontal" method="post" action="AggiungiAttivitaTirocinioServlet" 
			 style="padding: 15px; height: 80%;" onsubmit="return checkCampiAggiungiAttivita();">
				<div class="form-group">
					<label class="control-label col-sm-2">Attivita Svolta:</label>
					<div class="col-sm-6">
						<input onblur="testoCheck($(this))" data-placement="bottom" data-content="Errore inserimento testo" type="text" class="form-control" id="attivita"
							placeholder="Attività (max. 50)" name="attivita" maxlength="250">
					</div>
					<img class="help" src="img/info.png" data-toggle="tooltip" data-placement="top" title="Inserire l'attività svolta durante il tirocinio">
				</div>
				
				<div style="display: flex;" class="form-group">
					<label class="control-label col-sm-2">Data inizio:</label>
					<div class="col-xs-4">
						<input onblur="dataCheck($(this))" data-placement="top" data-content="Data non valida" 
						type="date" class="form-control" id="dataInizio" name="dataInizio">
					</div>
					
					<label class="control-label col-sm-2">Data fine:</label>                				
					<div class="col-xs-4">						
						<input onblur="dataCheck($(this))" data-placement="top" data-content="Data non valida" 
						type="date" class="form-control" id=""dataFine"" name="dataFine">
					</div>
					<img style="margin-right: 10px" class="help" src="img/info.png" data-toggle="tooltip" data-placement="top" title="Inserire data inizio e fine dell'attività">
				</div>
				
				<div class="form-group">
          <label class="control-label col-sm-2">Ore:</label>
          <div class="col-sm-2">
            <input onblur="oreCheck($(this))" data-placement="top" data-content="Ore non valide" 
            type="number" class="form-control" id="ore" placeholder="1" name="ore" min="1" max="99">
          </div>
        	<img class="help" src="img/info.png" data-toggle="tooltip" data-placement="top" title="Inserire ore durata attività">
		</div>
        
        <input type="hidden" value="<%= request.getParameter("idOfferta") %>" name="idOfferta">
				
				<div class="form-group" style="text-align: center; padding-bottom: 10px;">
				  <button type="submit" class="btn btn-primary" style="margin-right: 10px;">Conferma</button>
          <button type="button" class="btn btn-primary" onclick="ritornaRegistro();">Annulla</button>
        </div>
				
			</form>
		</div>
	</div>
	<% if(null != request.getParameter("errore")){ %>
  <script type="text/javascript">checkCampiAggiungiAttivita();</script>
  <%} %>
    <script>
		$(document).ready(function(){
    	$('[data-toggle="tooltip"]').tooltip(); 
    	});
	</script>
</body>
</html>
