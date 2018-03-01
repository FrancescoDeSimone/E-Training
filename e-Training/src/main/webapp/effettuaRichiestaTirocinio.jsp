<%@page import="it.unisa.etraining.model.bean.Azienda"%>
<%@page import="it.unisa.etraining.model.bean.TutorAziendale"%>
<%@page import="it.unisa.etraining.model.bean.TutorDidattico"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	   <script type="text/javascript" src="script/regexCheck.js"></script>
		<link rel="stylesheet" href="css/header.css">
		<link rel="stylesheet" href="css/sidebar.css">
		<link rel="stylesheet" href="css/pageLayout.css">
		<link rel="icon" type="image/png" href="img/Logo.jpeg">
		<script src="script/jquery.min.js"></script>
		<script type="text/javascript" src="script/regexCheck.js"></script>
		<link rel="stylesheet" href="css/bootstrap-select.min.css">
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/bootstrap-theme.min.css">
		<script src="script/bootstrap-select.min.js"></script>
		<script src="script/bootstrap.min.js"></script>
		<script src="script/sidebar.js"></script>
		<script src="script/utility.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>E-Training</title>
	</head>
<body onload="activate(1); ricercaOfferteFormative($('#azienda'));">
	<jsp:include page="header.jsp"></jsp:include>
	
	<div class="page">
    <aside> <jsp:include page="sidebarTirocinante.jsp"></jsp:include> </aside>    
		
		<div class="mycontainer" style="overflow: hidden;">
			<h2 style="padding: 15px; text-align: center;">Richiesta Tirocinio</h2>
				
			<form class="form-horizontal" method="post" action="RichiestaTirocinioServlet" 
			 style="padding: 15px; height: 80%; display: flex; padding-top: 0px;"
			 onsubmit="return checkCampiRichiestaTirocinio();">
				<div>
				<div class="form-group">
          <label class="control-label col-sm-2">Azienda:</label>
          <div class="col-sm-6">
            <select class="selectpicker form-control" name="azienda" 
              onchange="ricercaOfferteFormative($(this));" id="azienda" data-size="6">
              <%
                ArrayList<Azienda> aziende = (ArrayList<Azienda>) session.getAttribute("aziende");
              
                if (aziende.size() == 0) {
              %>
              <option>-</option>                
              <%
                } else {
                for (Azienda a : aziende) {
              %>
              <option value="<%= a.getEmail()%>"><%= a.getNome() %></option>                            
              
              <%
                }
               }
              %>
            </select>
          </div>
          <img class="help" src="img/info.png" data-toggle="tooltip" data-placement="top" title="Selezionare un azienda convenzionata">
        </div>
				
				<div class="form-group">
          <label class="control-label col-sm-2">Offerta Formativa:</label>
          <div class="col-sm-6">
            <select class="selectpicker form-control" name="offertaFormativa"
              onchange="ricercaOffertaFormativa($(this));" id="offertaFormativa" disabled data-size="4">
              <option>-</option>
            </select>
          </div>
          <img class="help" src="img/info.png" data-toggle="tooltip" data-placement="top" title="Selezionare un offerta formativa">
        </div>
				
				<div class="form-group">
					<label class="control-label col-sm-2">CFU Tirocinio curriculare:</label>
					<div class="col-sm-6">
					  <select class="selectpicker form-control" name="cfu">
					   <option value="6">6</option>
					   <option value="12">12</option>
					  </select>
					</div>
					  <img class="help" src="img/info.png" data-toggle="tooltip" data-placement="top" title="Selezionare il numero di CFU inerenti al tirocinio">
				</div>
				<div class="form-group" style="padding-bottom: 0px;">
					<label class="control-label col-sm-2">Obiettivi del tirocinio:</label>
          <div style="display: flex; flex-direction: column;">
            <div class="numeroLabel">
              <div class="col-xs-7">
                <input onblur="testoCheck($(this))" data-placement="top" data-content="Errore inserimento testo" style="margin-bottom: 10px" type="text" class="form-control" name="label0"
                  placeholder="Obiettivo (max. 50)" maxlength="50">
              </div>
              <div class="col-xs-5">
                <button style="margin-bottom: 10px" type="button" class="btn btn-primary bottoni"
                  onclick="newLabel('Obiettivo (max. 50)');">+</button>
                  <img style="margin-bottom: 10px" class="help" src="img/info.png" data-toggle="tooltip" data-placement="top" title="Inserire gli obbiettivi inerenti al tirocinio">
              </div>
            </div>
          </div>
				</div>
				
				<div class="form-group" style="text-align: center; padding-bottom: 0px;">
				  <button type="submit" class="btn btn-primary" style="margin-right: 10px;">Conferma</button>
          <button type="button" class="btn btn-primary" onclick="ritornaTirociniAttiviStorico();">Annulla</button>
        </div>
        </div>
        <div style="min-width: 400px ">
        <fieldset class="singolo" style="background-color: white;">
          <legend style="border: 0; margin-bottom: 0; padding-left: 10px; width: 265px; font-size: 16px;">
            Dettagli Offerta Formativa
          </legend>
          
          <div class="form-inline">
            <label>Tema:</label>
          </div>
          
          <div class="form-inline">
            <label>Inizio:</label>
          </div>
          
          <div class="form-inline">
            <label>Fine:</label>
          </div>
          
          <div class="form-inline">
            <label>Tutor Didattico:</label>
          </div>

          <div class="form-inline">
            <label>Tutor Aziendale:</label>
          </div>
                    
          <div class="form-inline">
            <label>Facilitazioni:</label>
          </div>
        </fieldset> 
				</div>
			</form>
		</div>
	</div>
	
	<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal1" style="display: none;"></button>
  <div id="myModal1" class="modal fade" role="dialog" style="margin-top: 100px;">
    <div class="modal-dialog">
    
      <div class="modal-content alert alert-warning">
        <div class="modal-body">
          <h3>Hai gia richiesto un tirocinio per questa offerta formativa.</h3>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>        
        </div>
       </div>
      </div>    
     </div>
	
	<% 
	String errore = request.getParameter("errore");
	
	if(null == errore) {
	  // niente
	} else if(errore.equals("1")) { %>
  <script type="text/javascript">checkCampiRichiestaTirocinio();</script>
  <% } else if(errore.equals("2")) {%>
  <script type="text/javascript">apriPopup();</script>  
  <% } %>
  <script>
		$(document).ready(function(){
    	$('[data-toggle="tooltip"]').tooltip(); 
    	});
	</script>
</body>
</html>
