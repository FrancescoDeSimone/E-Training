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
<body onload="activate(2);">
	<jsp:include page="header.jsp"></jsp:include>
	
	<div class="page">
    <aside> <jsp:include page="sidebarTirocinante.jsp"></jsp:include> </aside>
    		
    <div class="mycontainer">    
		  <div class="form-horizontal">
				<%
			    ArrayList<Tirocinio> listaProposte = (ArrayList<Tirocinio>) request.getAttribute("listaProposte");
		      if(!listaProposte.isEmpty()){
		        for (Tirocinio t : listaProposte) {
				%>
				<div class="singolo">
				<table>
				  <tr>
				    <td style="width: 400px;">
		           <div class="form-inline">
		             <label>Tema:</label>
		               <span><%= t.getOfferta().getTema() %></span>
		            </div>
            </td>
		      </tr>
		      <tr>
		         <td style="width: 400px;">
		          <div class="form-inline">  
		            <label>CFU Tirocinio:</label>
		            <span><%= t.getCfu() %></span>
		          </div>
		        </td>
		        <td style="width: 400px;">   
              <div class="form-inline">
		                <label>Ore realizzate:</label>
		                <span><%
		              int oreTotali = t.getCfu() * 25;
		              out.println(oreTotali - t.getOreRimanenti());%> di <%= oreTotali %></span>
		           </div>
		        </td>
          </tr>
          <tr>
           <td style="width: 400px;">
		          <div class="form-inline">
                <label>Status:</label>
                <span><%= t.getStatus()%></span>
              </div>
          </td>
          </tr>
          <tr>
          <td style="width: 400px;">
		         <div class="form-inline">
		            <label>Tutor Didattico:</label>
		            <span><%= t.getOfferta().getTutorDidattico().getCognome() + " " + 
		            t.getOfferta().getTutorDidattico().getNome()%></span>
		          </div>
		      </td>
		      <td style="width: 400px;">
		          <div class="form-inline">
		            <label>Tutor Aziendale:</label>
		            <span><%= t.getOfferta().getTutorAziendale().getCognome() + " " + 
		            t.getOfferta().getTutorAziendale().getNome()%></span>
		          </div>
          </td>
          </tr>
          <tr>
          <td style="width: 400px;" colspan="3">
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
		       %>
		   </div>
  <% } else { %>
      <div style="margin-top: 150px">
        <h2 style="color: grey; text-align: center;">Non ci sono elementi da visualizzare</h2>
      </div>
 <% } %>  
	  </div>
	</div>
</body>
</html>
