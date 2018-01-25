<%@page import="java.util.GregorianCalendar"%>
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
	  <aside> <jsp:include page="sidebarAzienda.jsp"></jsp:include> </aside>	  
    			
    <div class="mycontainer">
        <h2 style="padding-left: 15px; border-bottom: 1px solid grey; margin-bottom: 0px; padding-bottom: 0px;">Offerte Formative</h2>
			  
			  <div class="form-horizontal" style="height: 77%;">				
	        <%
	          ArrayList<OffertaFormativaTirocinioEsterno> listaOfferte = 
	          (ArrayList<OffertaFormativaTirocinioEsterno>) request.getAttribute("listaOfferte"); 
	        
	         if(listaOfferte.isEmpty()){ %>
          <div style="margin: 130px;">
            <h1 style="color: grey; text-align: center;">Non ci sono elementi da visualizzare</h1>
          </div>   
        <% }else
	          for (OffertaFormativaTirocinioEsterno o : listaOfferte) {
	        %>
	        <div class="singolo">
	        <table>
	        <tr>
	         <td style="width: 300px">
		        <div class="form-inline">
		          <label>Tema:</label>
		          <span><%= o.getTema() %></span>
		        </div>
            </td>
            <td style="width:300px; padding-right: 40px">
		        <div class="form-inline">
		          <label>Inizio:</label>
		          <span><% GregorianCalendar gi = o.getInizioTirocinio();
		                   out.println(gi.get(GregorianCalendar.DAY_OF_MONTH) + "/" 
		                       + (gi.get(GregorianCalendar.MONTH)+1) + "/" 
		                       + gi.get(GregorianCalendar.YEAR));%></span>
		        </div>
            </td>
            <td>
		        <div class="form-inline">
              <label>Fine:</label>
              <span><% GregorianCalendar gf = o.getFineTirocinio();
                       out.println(gf.get(GregorianCalendar.DAY_OF_MONTH) + "/" 
                           + (gf.get(GregorianCalendar.MONTH)+1) + "/" 
                           + gf.get(GregorianCalendar.YEAR));%></span>
            </div>
            </td>
            </tr>
		        <tr>
		        <td style="width: 300px">
		        <div class="form-inline">
	            <label>Status:</label>
	            <span><%= o.getStatus() %></span>
	          </div>
	          </td>
	          </tr>
	          <tr>
	          <td>
		        <div class="form-inline">
	            <label>Tutor Didattico:</label>
	            <span><%= o.getTutorDidattico().getCognome() + " " + o.getTutorDidattico().getNome()%></span>
	          </div>
	          </td>
	          </tr>
	          <tr>
	          <td>	        
	          <div class="form-inline">
	            <label>Tutor Aziendale:</label>
	            <span><%= o.getTutorAziendale().getCognome() + " " + o.getTutorAziendale().getNome() %></span>
	          </div>
	          </td>
	          </tr>
	          <tr>
	          <td colspan="3">
		        <div class="form-inline">
		          <label>Facilitazioni:</label>
		          <% for (int i = 0; i < o.getFacilitazioni().length; i++) { %>
		          <span><% if (i != 0)
	                         out.print(" / ");
	                       out.print(o.getFacilitazioni()[i]); %>
	              </span>
		          <% } 
                   
                   if (o.getFacilitazioni().length == 0) {
                     out.print("-");
                   }
                %>
		        </div>
            </td>
            </tr>
		        </table>
		      </div>
	        <%
	          }
	        %>
	      </div>
	      
	      <div class="footer">
		      <form role="form" method="get" action="aggiungiOffertaTirocioniEsterno.jsp">	     
	         <button type="submit" class="btn btn-primary">Aggiungi Offerta</button>
	        </form>
	      </div> 
		  </div>
			
		</div>
		
		<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal" style="display: none;"></button>
		<div id="myModal" class="modal fade" role="dialog" style="margin-top: 100px;">
		  <div class="modal-dialog">
		
		    <div class="modal-content alert alert-success">
		      <div class="modal-body">
		        <h3>Offerta Formativa aggiunta con successo.</h3>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">OK</button>
		      </div>
		    </div>
		
		  </div>
		</div>
	  <%
	   String aggiunta = (String) request.getAttribute("aggiunta");
      if (aggiunta != null) {
    %>
    <script type="text/javascript">apriPopup()</script>
    <%
      }
    %>
		
</body>
</html>
