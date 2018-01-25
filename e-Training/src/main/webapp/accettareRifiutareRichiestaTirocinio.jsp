<%@page import="it.unisa.etraining.model.bean.TutorDidattico"%>
<%@page import="it.unisa.etraining.model.bean.TutorAziendale"%>
<%@page import="it.unisa.etraining.model.bean.Utente"%>
<%@page import="it.unisa.etraining.model.bean.Tirocinante"%>
<%@page import="it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno"%>
<%@page import="it.unisa.etraining.model.bean.Azienda"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.unisa.etraining.model.bean.Tirocinio"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="stylesheet" href="css/header.css">
		<link rel="stylesheet" href="css/sidebar.css">
		<link rel="icon" type="image/png" href="img/Logo.jpeg">
		<script src="script/jquery.min.js"></script>
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/bootstrap-theme.min.css">
		<link rel="stylesheet" href="css/pageLayout.css">
		<script src="script/bootstrap.min.js"></script>
		<script src="script/sidebar.js"></script>
		<script src="script/utility.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>E-Training</title>
	</head>
<body onload="activate(3);">
	<jsp:include page="header.jsp"></jsp:include>
	
	<div class="page">
	 <% 
      Utente utente = (Utente) session.getAttribute("utente");
    
      if (utente instanceof TutorDidattico) {
    %>    
    <aside> <jsp:include page="sidebarTutorDidattico.jsp"></jsp:include> </aside>
    <%
      } else if (utente instanceof Azienda) {
    %>
    <aside> <jsp:include page="sidebarAzienda.jsp"></jsp:include> </aside>
    <%
      }
    %>
	
    <div class="mycontainer" style="display: flex;">
    
      <div class="tab navbar navbar-default secondaBarra" role="navigation">
         <ul class="nav nav-tabs sidebar-nav">
          <%
            ArrayList<Tirocinio> listaRichieste = 
                (ArrayList<Tirocinio>) session.getAttribute("listaRichieste");
          
            for (int i = 0; i < listaRichieste.size(); i++) {
              if (i == 0) {
          %>
            <li class="active">  
          <%      
             } else {
          %>
            <li>
          <%
             }
          %>
            <a data-toggle="pill" href="#Richiesta<%=i+1%>"><span>Richiesta <%=i+1%></span></a></li> 
          <%
            }
          %>
          </ul>
      </div>
      
      <% if(!listaRichieste.isEmpty()){ %>
      <div class="tab-content" style="width: calc(100% - 200px);">
        <%
          for (int i = 0; i < listaRichieste.size(); i++) {
            if (i == 0) {
        %>    
        <div class="tab-pane fade in active" id="Richiesta<%=i+1%>">
        <div class="singolo">        
        <%
           } else {
        %>
        <div class="tab-pane fade" id="Richiesta<%=i+1%>">
        <div class="singolo">        
        <%
           }
        %>
          <h2>Richiesta <%=i+1%></h2>
          <div class="form-horizontal">
          <table>
          <tr>
          <td width="300px">
              <div class="form-inline">
                <label>Tirocinante:</label>
                <span><%= listaRichieste.get(i).getTirocinante().getCognome() + " " +
                          listaRichieste.get(i).getTirocinante().getNome() %></span>
              </div>
          </td>
          </tr>
          <tr>
          <td width="300px">
              <div class="form-inline">
                <label>Tema Offerta Formativa:</label>
                <span><%= listaRichieste.get(i).getOfferta().getTema() %></span>
              </div>
          </td>
          </tr>
          <tr>
          <td width="300px">   
              <div class="form-inline">
                <label>Azienda:</label>
                <span><%= listaRichieste.get(i).getOfferta().getAzienda().getNome() %></span>
              </div>
          </td>
          <td>
              <div class="form-inline">
                <label>Tutor Aziendale:</label>
                <span><%= listaRichieste.get(i).getOfferta().getTutorAziendale().getCognome() + " " +
                          listaRichieste.get(i).getOfferta().getTutorAziendale().getNome() %></span>
              </div>
          </td>
          </tr>
          <tr>
          <td width="300px">   
              <div class="form-inline">
                <label>CFU:</label>
                <span><%= listaRichieste.get(i).getCfu() %></span>
              </div>
          </td>
          </tr>
          <tr>
          <td width="300px" colspan="3">   
              <div class="form-inline">
               <label>Obiettivi del tirocinio:</label>
                <% for (int y = 0; y < listaRichieste.get(i).getObiettivi().length; y++) { %>
                <span><% if (y!= 0)
                           out.print(" / ");
                         out.print(listaRichieste.get(i).getObiettivi()[y]); %>
                </span>
                <% } %>             
              </div>
          </td>
          </tr>
         </table>
              <div class="form-inline" style="text-align: center;">
                <div class="col-sm-offset-2 col-sm-5">
                  <button type="button" class="btn btn-primary" 
                    onclick="accettaRifiutaRichiestaTirocinioSicuro('accetta',
                     '<%= listaRichieste.get(i).getTirocinante().getEmail() %>',
                     '<%= listaRichieste.get(i).getOfferta().getId() %>');">Accetta</button>
                  <button type="button" class="btn btn-primary" 
                   onclick="accettaRifiutaRichiestaTirocinioSicuro('rifiuta',
                      '<%= listaRichieste.get(i).getTirocinante().getEmail() %>',
                      '<%= listaRichieste.get(i).getOfferta().getId() %>');">Rifiuta</button>                  
                </div>
              </div>
            </div>
        </div>
        </div>
      <%
        }
      %>
     </div>   
	 </div>
	</div>
	<%} else { %>
	 <div style="margin: 150px;">
	   <h1 style="color: grey; text-align: center;">Non ci sono elementi da visualizzare</h1>
	 </div>   
	<%} %>

	<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal" style="display: none;"></button>
  <div id="myModal" class="modal fade" role="dialog" style="margin-top: 100px;">
    <div class="modal-dialog">
    
      <div class="modal-content alert alert-warning">
        <div class="modal-body">
          <h3>Sei sicuro?</h3>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" onclick="accettaRifiutaRichiestaTirocinio();">Si</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">No</button>        
        </div>
       </div>
    
     </div>
  </div>  
	
</body>
</html>
