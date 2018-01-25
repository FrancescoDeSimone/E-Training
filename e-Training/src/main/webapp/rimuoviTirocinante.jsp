<%@page import="it.unisa.etraining.model.bean.Tirocinante"%>
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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>E-Training</title>
	</head>
<body onload="activate(6);">
	<jsp:include page="header.jsp"></jsp:include>
	
	<div class="page">
    <aside> <jsp:include page="sidebarTirocinante.jsp"></jsp:include> </aside>    
		
		<div class="mycontainer" style="overflow: hidden;">
			<h2 style="padding-left: 15px; border-bottom: 1px solid grey; 
			 margin-bottom: 0px; padding-bottom: 0px; background-color: #72A7C8;
			 margin-top: 0px; padding-top: 15px; color: rgb(246, 246, 246)">Profilo</h2>
				
			<%
		    Tirocinante t = (Tirocinante) session.getAttribute("utente");
			%>	
				
			<div class="singolo" style="background-color: white !important;">
			
			 <div class="form-inline">
            <label>Nome:</label>
            <span><%= t.getNome() + " " + t.getCognome()%></span>
       </div>

        <div class="form-inline">
             <label>E-mail:</label>
             <span><%= t.getEmail() %></span>
        </div>
        
        <div class="form-inline">
             <label>Matricola:</label>
             <span><%= t.getMatricola() %></span>
        </div>
        
        <div class="form-inline">
             <label>Anno Iscrizione:</label>
             <span><%= t.getAnnoIscrizione() %></span>
        </div>
        
			</div>
				
			<div class="form-group" style="text-align: center; padding-bottom: 10px;">
			   <button type="button" class="btn btn-primary" style="margin-right: 10px;" 
			       onclick="apriPopup();">Elimina Account</button>
       </div>
				
		</div>
	</div>
	
 <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal" style="display: none;"></button>
 <div id="myModal" class="modal fade" role="dialog" style="margin-top: 100px;">
    <div class="modal-dialog">
    
      <div class="modal-content alert alert-warning">
        <div class="modal-body">
          <h3>Sei sicuro?</h3>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" onclick="rimuoviTirocinante();">Si</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
        </div>
       </div>
    
     </div>
  </div>
  
  <button type="button" class="btn btn-info btn-lg tirociniAttivi" data-toggle="modal" data-target="#myModal2" style="display: none;"></button>
  <div id="myModal2" class="modal fade" role="dialog" style="margin-top: 100px;">
    <div class="modal-dialog">
    
      <div class="modal-content alert alert-warning">
        <div class="modal-body">
          <h3>L'account non può essere eliminato perchè ci sono ancora dei tirocini attivi.</h3>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
       </div>
    
     </div>
  </div>
  
  <%
    String tirociniAttivi = (String) request.getAttribute("tirociniAttivi");
  
    if (tirociniAttivi != null) {
  %>
  <script type="text/javascript">
     $(".tirociniAttivi").eq(0).click();
  </script>
  <%
    }
  %>
</body>
</html>