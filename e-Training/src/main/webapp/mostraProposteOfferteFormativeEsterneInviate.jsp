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
<body onload="activate(1);">
	<jsp:include page="header.jsp"></jsp:include>
	
	<div class="page">
	  <aside> <jsp:include page="sidebarAzienda.jsp"></jsp:include> </aside>	  
    			
    <div class="mycontainer" style="display: flex;">
    
      <div class="tab navbar navbar-default secondaBarra" role="navigation">
         <ul class="nav nav-tabs sidebar-nav">
          <%
            ArrayList<OffertaFormativaTirocinioEsterno> listaProposte = 
                (ArrayList<OffertaFormativaTirocinioEsterno>) session.getAttribute("listaProposte");
          
            for (int i = 0; i < listaProposte.size(); i++) {
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
            <a data-toggle="pill" href="#Proposta<%=i+1%>"><span>Proposta <%=i+1%></span></a></li> 
          <%
            }
          %>
          </ul>
      </div>
      
      <div class="tab-content" style="width: calc(100% - 200px);">
        <% if(listaProposte.isEmpty()){%>
          <div style="margin: 150px;">
            <h1 style="color: grey; text-align: center;">Non ci sono elementi da visualizzare</h1>
          </div>   
          <% }
            for (int i = 0; i < listaProposte.size(); i++) {
            if (i == 0) {
        %>    
        <div class="tab-pane fade in active" id="Proposta<%=i+1%>">
        <div class="singolo">        
        <%
           } else {
        %>
        <div class="tab-pane fade" id="Proposta<%=i+1%>">
        <div class="singolo">        
        <%
           }
        %>
          <h2>Proposta <%=i+1%></h2>
          <div class="form-horizontal">
          <table>
          <tr>
          <td style="width: 400px">
              <div class="form-inline">
                <label>Tema:</label>
                <span><%= listaProposte.get(i).getTema() %></span>
              </div>
           </td>
           </tr>
           <tr>
           <td style="width: 400px">
              <div class="form-inline">
                <label>Status:</label>
                <span><%= listaProposte.get(i).getStatus() %></span>
              </div>
           </td>
           </tr>
           <tr>
           <td style="width: 400px">
              <div>
	              <label>Inizio:</label>
	              <span><% GregorianCalendar gi = listaProposte.get(i).getInizioTirocinio();
	                       out.println(gi.get(GregorianCalendar.DAY_OF_MONTH) + "/" 
	                           + (gi.get(GregorianCalendar.MONTH)+1) + "/" 
	                           + gi.get(GregorianCalendar.YEAR));%></span>
            </td>
           <td style="width: 400px">
                <label>Fine:</label>
                <span><% GregorianCalendar gf = listaProposte.get(i).getFineTirocinio();
                         out.println(gf.get(GregorianCalendar.DAY_OF_MONTH) + "/" 
                             + (gf.get(GregorianCalendar.MONTH)+1) + "/" 
                             + gf.get(GregorianCalendar.YEAR));%></span>
              </div>
          </td>
          </tr>
          <tr>
          <td style="width: 400px">
           
              <div class="form-inline">
                <label>Tutor Didattico:</label>
                <span><%= listaProposte.get(i).getTutorDidattico().getCognome() + " " +
                          listaProposte.get(i).getTutorDidattico().getNome() %></span>
              </div>
          </td>
          <td style="width: 400px">    
              <div class="form-inline">
                <label>Tutor Aziendale:</label>
                <span><%= listaProposte.get(i).getTutorAziendale().getCognome() + " " +
                          listaProposte.get(i).getTutorAziendale().getNome() %></span>
              </div>
          </td>
          </tr> 
          <tr>
          <td style="width: 400px" colspan="3">    
              <div class="form-inline">
	              <label>Facilitazioni:</label>
	              <% 
	                 ArrayList<String> facilitazioniTmp = new ArrayList<>();
		               for (int k = 0; k < listaProposte.get(i).getFacilitazioni().length; k++) {
		                 if(!listaProposte.get(i).getFacilitazioni()[k].equals("disabilita"))
		                   facilitazioniTmp.add(listaProposte.get(i).getFacilitazioni()[k]);
		               }
		               
	                 for (int y = 0; y < facilitazioniTmp.size(); y++) { %>
	              <span>
	              <%
	                     if (y != 0)
	                       out.print(" / ");
	                     out.print(facilitazioniTmp.get(y)); %>
	              </span>
	              <% }
	                 
	                 if (facilitazioniTmp.size() == 0) {
	                   out.print("-");
	                 }
	              %>
              </div>
              </td>
          </tr>
              </table>
            </div>
            
        </div>
        </div>
      <%
        }
      %>
     </div>   
	 </div>
	</div>
	   
   </div>
  </div>
</body>
</html>
