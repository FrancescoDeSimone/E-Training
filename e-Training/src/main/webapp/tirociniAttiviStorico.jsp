<%@page import="it.unisa.etraining.model.bean.Azienda"%>
<%@page import="it.unisa.etraining.model.bean.TutorDidattico"%>
<%@page import="it.unisa.etraining.model.bean.TutorAziendale"%>
<%@page import="it.unisa.etraining.model.bean.Tirocinante"%>
<%@page import="it.unisa.etraining.model.bean.Utente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.unisa.etraining.model.bean.Tirocinio"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="css/header.css">
		<link rel="stylesheet" href="css/sidebar.css">
		<link rel="stylesheet" href="css/pageLayout.css">
		<link rel="icon" type="image/png" href="img/Logo.jpeg">
		<script src="script/jquery.min.js"></script>
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/bootstrap-theme.min.css">
		<script src="script/bootstrap.min.js"></script>
		<script src="script/sidebar.js"></script>
    <script src="script/utility.js"></script>
 		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>E-Training</title>
	</head>
<body onload="activate(0);">
	<jsp:include page="header.jsp"></jsp:include>
	
	<div class="page">
	  <% 
	    Utente utente = (Utente) session.getAttribute("utente");
	  
	    if (utente instanceof Tirocinante) {
    %>    
    <aside> <jsp:include page="sidebarTirocinante.jsp"></jsp:include> </aside>
	  <%
	    } else if (utente instanceof TutorAziendale) {
	  %>
	  <aside> <jsp:include page="sidebarTutorAziendale.jsp"></jsp:include> </aside>
	  <%
	    } else if (utente instanceof TutorDidattico) {
	  %>
	  <aside> <jsp:include page="sidebarTutorDidattico.jsp"></jsp:include> </aside>
	  <%
	    } else if (utente instanceof Azienda) {
	  %>
	  <aside> <jsp:include page="sidebarAzienda.jsp"></jsp:include> </aside>	  
	  <%
	    }
    %>
    		
		<div class="mycontainer">
			<div class="tab">
			  <ul class="nav nav-pills">
			   <li class="active" style="width: calc(50% - 1px); text-align: center;"><a data-toggle="pill" href="#tirociniAttivi">Tirocini
          Attivi</a></li>
          
			    <li style="width: calc(50% - 1px); text-align: center;"><a data-toggle="pill" href="#storicoTirocini" style="outline:0">Storico
          Tirocini</a></li>
				</ul>
			</div>
			
			<div class="tab-content" style="height: calc(90% - 1px);">
				<div class="tab-pane fade" id="storicoTirocini">
				  <div class="form-horizontal">
						<%
					    ArrayList<Tirocinio> tirociniAttivi = (ArrayList<Tirocinio>) request.getAttribute("tirociniAttivi");
	            ArrayList<Tirocinio> tirociniStorico = (ArrayList<Tirocinio>) request.getAttribute("tirociniStorico");
	            if(!tirociniStorico.isEmpty()){
	              for (Tirocinio t : tirociniStorico) {
						%>
						<div class="singolo">	
						<table>
						  <%
						    if (!(utente instanceof Tirocinante)) {
						  %>
						  <tr>
						  <td style="padding-right: 30px; width: 300px">
              <div class="form-inline">
                <label>Tirocinante:</label>
                <span><%= t.getTirocinante().getCognome() + " " +
                  t.getTirocinante().getNome()%></span>
              </div>
              </td>
						  </tr>
						  <%
						    }
						  %>
						  <tr>
						  <td style="padding-right: 30px; width: 300px">
	            <div class="form-inline">
	              <label>Tema:</label>
	              <span><%= t.getOfferta().getTema() %></span>
	            </div>            
	            </td>
	            <td style="padding-right: 30px; width: 300px">
	            <div class="form-inline">
                <label>CFU Tirocinio:</label>
                <span><%= t.getCfu() %></span>
              </div>
              </td>
              <td style="padding-right: 30px; width: 300px"> 
              <div class="form-inline">              
                <label>Ore realizzate:</label>
                <span><%
                  int oreTotali = t.getCfu() * 25;
                  out.println(oreTotali - t.getOreRimanenti());%> di <%= oreTotali %></span>
              </div>
              </td>
              </tr>
	            <tr>
              <td style="padding-right: 30px; width: 300px">
	            <div class="form-inline">
                <label>Tutor Didattico:</label>
                <span><%= t.getOfferta().getTutorDidattico().getCognome() + " " + 
                t.getOfferta().getTutorDidattico().getNome()%></span>
              </div>
              </td>
              </tr>
              <tr>
              <td>
              <div class="form-inline">
                <label>Tutor Aziendale:</label>
                <span><%= t.getOfferta().getTutorAziendale().getCognome() + " " + 
                t.getOfferta().getTutorAziendale().getNome()%></span>
              </div>
              </td>
              </tr>
              <tr>
              <td colspan="3">
	            <div class="form-inline">
	              <label>Obiettivi del tirocinio:</label>
                <% for (int i = 0; i < t.getObiettivi().length; i++) { %>
                <span><% if (i != 0)
                           out.print(" / ");
                         out.print(t.getObiettivi()[i]); %>
                </span>
                <% } %>	            
              </div>
              </td>
              </tr>
            </table>
            </div>           
	          <%
	             }
	           }
	          %> 
    		  </div>
    		  <% if(tirociniStorico.isEmpty()){ %>
            <div style="margin-top: 150px;">
              <h2 style="color: grey; text-align: center;">Non ci sono elementi da visualizzare</h2>
            </div>
          <% } %>
				</div>
				
				<div class="tab-pane fade in active" id="tirociniAttivi">
				  <div class="form-horizontal">
				  
            <% if(tirociniAttivi.isEmpty()){ %>
            <div style="margin-top: 150px;">
              <h2 style="color: grey; text-align: center;">Non ci sono elementi da visualizzare</h2>
            </div>
            <%} else {%>
		        <%
		          for (Tirocinio t : tirociniAttivi) {
		        %>
		        <div class="singolo">
		        <table>
              <%
                if (!(utente instanceof Tirocinante)) {
              %>
              <tr>
              <td style="padding-right: 30px; width: 300px">
              <div class="form-inline">
                <label>Tirocinante:</label>
                <span><%= t.getTirocinante().getCognome() + " " +
                  t.getTirocinante().getNome()%></span>
              </div>
              </td>
              </tr>
              <%
                }
              %>
		          <tr>
              <td style="padding-right: 30px; width: 300px"> 
              <div class="form-inline">
                <label>Tema:</label>
                <span><%= t.getOfferta().getTema() %></span>
              </div>
              </td>
              <td style="padding-right: 30px; width: 300px">  
              <div class="form-inline">
                <label >CFU Tirocinio:</label>
                <span><%= t.getCfu() %></span>
              </div>
              </td>
              <td style="padding-right: 30px; width: 300px">
              <div class="form-inline">                
                <label>Ore realizzate:</label>
                <span><%
                  int oreTotali = t.getCfu() * 25;
                  out.println(oreTotali - t.getOreRimanenti());%> di <%= oreTotali %></span>
              </div>
              </td>
              </tr>
              <tr>
              <td style="padding-right: 30px; width: 300px">
              <div class="form-inline">
                <label>Tutor Didattico:</label>
                <span><%= t.getOfferta().getTutorDidattico().getCognome() + " " + 
                t.getOfferta().getTutorDidattico().getNome()%></span>
              </div>    
              </td>
              </tr>
              <tr>
              <td>      
              <div class="form-inline">
                <label>Tutor Aziendale:</label>
                <span><%= t.getOfferta().getTutorAziendale().getCognome() + " " + 
                t.getOfferta().getTutorAziendale().getNome()%></span>
              </div>
              </td>
              </tr>
              <tr>
              <td colspan="3">
              <div class="form-inline">
                <label>Obiettivi del tirocinio:</label>
                <% for (int i = 0; i < t.getObiettivi().length; i++) { %>
                <span><% if (i != 0)
                           out.print(" / ");
                         out.print(t.getObiettivi()[i]); %>
                </span>
                <% } %>             
              </div>
              </td>
              </tr>
            </table>
            </div> 
		        <%
		          }
		        }
		        %>
		      </div>		       
			 </div>
			
		</div>
	</div>
 </div>
 
 <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal" style="display: none;"></button>
 <div id="myModal" class="modal fade" role="dialog" style="margin-top: 100px;">
    <div class="modal-dialog">
    
      <div class="modal-content alert alert-success">
        <div class="modal-body">
          <h3></h3>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
       </div>
    
     </div>
  </div>
  <%
    String registrazione = (String) request.getAttribute("registrazione");
    String richiestaTirocinio = (String) request.getAttribute("richiestaTirocinio");
  
    if (registrazione != null) {
  %>
  <script type="text/javascript">
    $(".modal-body h3").text("Account registrato con successo.");
    apriPopup();
  </script>
  <%
    } else if (richiestaTirocinio != null) {
  %>
  <script type="text/javascript">
    $(".modal-body h3").text("Richiesta di tirocinio inviata con successo.");
    apriPopup();
  </script>
  <%
    }
  %>
</body>
</html>
