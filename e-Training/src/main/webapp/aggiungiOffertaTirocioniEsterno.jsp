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
<body onload="activate(2);">
	<jsp:include page="header.jsp"></jsp:include>
	
	<div class="page">
    <aside> <jsp:include page="sidebarAzienda.jsp"></jsp:include> </aside>    
		
		<div class="mycontainer" style="overflow: hidden;">
			<h2 style="padding: 0px; text-align: center;">Aggiungi Offerta Formativa</h2>
				
			<form class="form-horizontal" method="post" action="AggiungiOffertaFormativaEsternaServlet" 
			 style="padding: 15px; height: 85%;" onsubmit="return checkCampiAggiungiOfferta();">
				<div style="display: flex;" class="form-group">
					<label class="control-label col-sm-2">Tema:</label>
					<div class="col-sm-10">
						<input onblur="temaCheck($(this))" data-placement="bottom" data-content="Errore inserimento testo" type="text" class="form-control" id="tema"
							placeholder="Tema (max. 250)" name="tema" maxlength="250">
					</div>
					<img style="margin-right: 10px;" class="help" src="img/info.png" data-toggle="tooltip" data-placement="top" title="Aggiungere tema del tirocinio">
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
					<img style="margin-right: 10px;" class="help" src="img/info.png" data-toggle="tooltip" data-placement="top" title="Inserire data inizio e fine tirocinio ">
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2">Tutor Accademico:</label>
					<div class="col-sm-4">
						<select class="selectpicker form-control" name="tutorAccademico" id="tutorAcc" data-size="3">
	            <%
	              ArrayList<TutorDidattico> tutorDidattici = (ArrayList<TutorDidattico>) session.getAttribute("tutorDidattici");
	            
	              for (TutorDidattico t : tutorDidattici) {
	            %>
	            <option value="<%= t.getEmail()%>"><%= t.getCognome() + " " + t.getNome() %></option>                            
	            
	            <%
	              }
	            %>
	          </select>
					</div>
					<img class="help" src="img/info.png" data-toggle="tooltip" data-placement="top" title="Scegliere tutor didattico">
				</div>
				
				<div class="form-group">
					<label class="control-label col-sm-2">Tutor Aziendale:</label>
					<div class="col-sm-4">
						<select class="selectpicker form-control" name="tutorAziendale" id="tutorAz" data-size="3">
	              <%
	                ArrayList<TutorAziendale> tutorAziendali= (ArrayList<TutorAziendale>) session.getAttribute("tutorAziendali");
	              
	                for (TutorAziendale t : tutorAziendali) {
	              %>
	              <option value="<%= t.getEmail()%>"><%= t.getCognome() + " " + t.getNome() %></option>                            
	              
	              <%
	                }
	              %>
	           </select>
					</div>
					<img class="help" src="img/info.png" data-toggle="tooltip" data-placement="top" title="Scegliere tutor aziendale">
				</div>
				
				<div style="display: flex;" class="form-group">
					<label class="control-label col-sm-2">Facilitazioni previste:</label>
					
          <div style="display: flex; flex-direction: column;">
            <div class="numeroLabel">
              <div class="col-xs-7">
                <input onblur="testoCheck($(this))" data-placement="top" data-content="Errore inserimento testo" style="margin-bottom: 10px" type="text" class="form-control" name="label0"
                  placeholder="Facilitazione (max. 50)" maxlength="50">
              </div>

              <div class="col-xs-5">
                <button style="margin-bottom: 10px" type="button" class="btn btn-primary bottoni"
                  onclick="newLabel('Facilitazione (max. 50)');">+</button>
              </div>
            </div>
          </div>
					<img class="help" src="img/info.png" data-toggle="tooltip" data-placement="top" title="Inserire facilitazioni disponibili">
				</div>
				<div class="form-group">
				  <div class="checkbox" style="margin-left: 10%">
			      <label><input type="checkbox" name="disabilita" value="si" style="vertical-align:bottom;position:relative;top:-3px;">
			      Disponibili attrezzature per studenti con disabilità motorie</label>
			    </div>
				</div>
				<div class="form-group" style="text-align: center; padding-bottom: 10px;">
				  <button type="submit" class="btn btn-primary" style="margin-right: 10px;">Conferma</button>
          <button type="button" class="btn btn-primary" onclick="ritornaMostraOfferte();">Annulla</button>
        </div>
				
			</form>
		</div>
	</div>
	<% if(null != request.getParameter("errore")){ %>
  <script type="text/javascript">checkCampiAggiungiOfferta();</script>
  <%} %>
  
  <script>
		$(document).ready(function(){
    	$('[data-toggle="tooltip"]').tooltip(); 
    	});
	</script>
</body>
</html>
