<%@page import="java.util.Arrays"%>
<%@page import="it.unisa.etraining.model.bean.TutorAziendale"%>
<%@page import="it.unisa.etraining.model.bean.Utente"%>
<%@page import="it.unisa.etraining.model.bean.TutorDidattico"%>
<%@page import="it.unisa.etraining.model.bean.AttivitaRegistro"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="it.unisa.etraining.model.bean.OffertaFormativaTirocinioEsterno"%>
<%@page import="it.unisa.etraining.model.bean.Azienda"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.unisa.etraining.model.bean.Tirocinio"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    <link rel="stylesheet" href="css/bootstrap-toggle.min.css">
		<link rel="stylesheet" href="css/header.css">
		<link rel="stylesheet" href="css/sidebar.css">
		<link rel="icon" type="image/png" href="img/Logo.jpeg">
		<script src="script/jquery.min.js"></script>
		<script src="script/bootstrap-toggle.min.js"></script>		
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/bootstrap-theme.min.css">
		<link rel="stylesheet" href="css/pageLayout.css">
		<script src="script/bootstrap.min.js"></script>
		<script src="script/sidebar.js"></script>
		<script src="script/utility.js"></script>
		<script src="script/regexCheck.js"></script>		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>E-Training</title>
	</head>
<body onload="activate(linkBarra); valutaBottone(1);">
  <script type="text/javascript">var linkBarra;</script>
  
	<jsp:include page="header.jsp"></jsp:include>
	
	<div class="page">
	
    <% 
      Utente utente = (Utente) session.getAttribute("utente");
      String controllo = null;
    
      if (utente instanceof TutorDidattico) {
        controllo = AttivitaRegistro.CONVALIDATA_TUTOR_AZIENDALE;
    %>    
    <aside> <jsp:include page="sidebarTutorDidattico.jsp"></jsp:include> </aside>
    <script type="text/javascript">linkBarra = 4;</script>
    <%
      } else if (utente instanceof TutorAziendale) {
        controllo = AttivitaRegistro.IN_CONVALIDA;
    %>
	  <aside> <jsp:include page="sidebarTutorAziendale.jsp"></jsp:include> </aside>	
	  <script type="text/javascript">linkBarra = 1;</script>
	  <%
      }
	  %>
    			
    <div class="mycontainer" style="display: flex;">
    
      <div class="tab navbar navbar-default secondaBarra" role="navigation">
         <ul class="nav nav-tabs sidebar-nav">
          <%
            ArrayList<Tirocinio> listaRegistri = 
                (ArrayList<Tirocinio>) session.getAttribute("listaRegistri");
          
            for (int i = 0; i < listaRegistri.size(); i++) {
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
            <a data-toggle="pill" href="#Tirocinio<%=i+1%>" onclick="valutaBottone(<%=i+1%>);"><span>
                <%= listaRegistri.get(i).getOfferta().getTema() %></span></a></li> 
          <%
            }
          %>
          </ul>
      </div>
      
      <div class="tab-content" style="width: calc(100% - 200px); overflow: hidden; height: 100%;">
          <div class="footer" style="border-top: 0; border-bottom: 1px solid grey; height: 45px;">
           <form class="form-horizontal" role="form" method="get" style="margin-top: 2px;" 
                  action="mostraInformazioniRegistriTutor.jsp" id="cambiaPagina">
              <label>Mostra le attività da convalidare</label>
              <input onchange="$('#cambiaPagina').submit();" checked data-toggle="toggle" type="checkbox">
           </form>
           
           <div style="margin-top: 2px; margin-right: auto;">
             <button role="button" id="convalidaTutte" class="btn btn-primary" onclick="$('.btn-lg.sicuroConvalidaTutte').eq(0).click();">
             Convalida tutte</button>
           </div>
          </div> 
        <% 
          if (listaRegistri.size() == 0) {
        %>
        <div style="margin-top: 150px;">
           <h1 style="color: grey; text-align: center;">Non ci sono elementi da visualizzare</h1>
        </div>  
        <%
          } else { 
          for (int i = 0; i < listaRegistri.size(); i++) {
            if (i == 0) {
        %>    
        <div class="tab-pane fade in active" style="height: calc(100% - 45px);" id="Tirocinio<%=i+1%>">
        <%
           } else {
        %>
        <div class="tab-pane fade" style="height: calc(100% - 45px);" id="Tirocinio<%=i+1%>">
        <%
           }
        %>
         <div style="display: flex; flex-direction: column; overflow: hidden; height: 100%;">
          <span id="emailTirocinante" style="display: none;"><%= listaRegistri.get(i).getTirocinante().getEmail()%></span>
          <span id="idOfferta" style="display: none;"><%= listaRegistri.get(i).getOfferta().getId()%></span>
          
          <div class="form-horizontal" style="padding: 20px; max-height: 40%; overflow: hidden;">
            <table>
              <tr>
              <td style="padding-right: 30px; width: 300px">
              <div class="form-inline">
                <label>Tirocinante:</label>
                <span><%= listaRegistri.get(i).getTirocinante().getCognome() + " " +
                    listaRegistri.get(i).getTirocinante().getNome()%></span>
              </div>
              </td>
              </tr>
              <tr>
              <td style="padding-right: 30px; width: 300px">
              <div class="form-inline">
                <label>Tema:</label>
                <span><%= listaRegistri.get(i).getOfferta().getTema() %></span>
              </div>            
              </td>
              <td style="padding-right: 30px; width: 300px">
              <div class="form-inline">
                <label>CFU Tirocinio:</label>
                <span><%= listaRegistri.get(i).getCfu() %></span>
              </div>
              </td>
              <td style="padding-right: 30px; width: 300px"> 
              <div class="form-inline">              
                <label>Ore realizzate:</label>
                <span><%
                  int oreTotali = listaRegistri.get(i).getCfu() * 25;
                  out.println(oreTotali - listaRegistri.get(i).getOreRimanenti());%> di <%= oreTotali %></span>
              </div>
              </td>
              </tr>
              <tr>
              <td>
              <div class="form-inline">              
                <label>Azienda:</label>
                <span><%= listaRegistri.get(i).getOfferta().getAzienda().getNome() %></span>
              </div>
              </td>           
              </tr>
              <tr>
              <td style="padding-right: 30px; width: 300px">
              <div class="form-inline">
                <label>Tutor Didattico:</label>
                <span><%= listaRegistri.get(i).getOfferta().getTutorDidattico().getCognome() + " " + 
                    listaRegistri.get(i).getOfferta().getTutorDidattico().getNome()%></span>
              </div>
              </td>
              <td>
              <div class="form-inline">
                <label>Tutor Aziendale:</label>
                <span><%= listaRegistri.get(i).getOfferta().getTutorAziendale().getCognome() + " " + 
                    listaRegistri.get(i).getOfferta().getTutorAziendale().getNome()%></span>
              </div>
              </td>
              </tr>
              <tr>
              <td colspan="3">
              <div class="form-inline">
                <label>Obiettivi del tirocinio:</label>
                <% for (int z = 0; z < listaRegistri.get(i).getObiettivi().length; z++) { %>
                <span><% if (z != 0)
                           out.print(" / ");
                         out.print(listaRegistri.get(i).getObiettivi()[z]); %>
                </span>
                <% } %>             
              </div>
              </td>
              </tr>
            </table>         
         </div>     
         
         <div style="overflow: auto; height: 60%;" id="AttivitaTirocinio">
         <%          
	         ArrayList<AttivitaRegistro> listaAttivitaScremate = 
	         new ArrayList<>(Arrays.asList(listaRegistri.get(i).getAttivitaSvolte()));
	     
	         for (int indice=0; indice < listaAttivitaScremate.size(); ) {
	           if (!listaAttivitaScremate.get(indice).getConvalida().equals(controllo))
	             listaAttivitaScremate.remove(indice);
	           else
	             indice++;
	         }
         
           if (listaAttivitaScremate.size() == 0) { %>
          <div>
            <h1 style="color: grey; text-align: center;">Non ci sono attività da visualizzare</h1>
          </div>   
         <% } else {
          for (int y = 0; y < listaRegistri.get(i).getAttivitaSvolte().length; y++) {
              if(listaRegistri.get(i).getAttivitaSvolte()[y].getConvalida().equals(controllo)) {
         %>
         <div class="singolo">               
          <div class="form-horizontal">
          <table>
          <tr>
          <td style="width: 300px">
              <div class="form-inline">
                <label>Attività Svolta:</label>
                <span><%= listaRegistri.get(i).getAttivitaSvolte()[y].getAttivitaSvolta() %></span>
              </div>
           </td>
           </tr>
           <tr>
           <td style="width: 300px">
              <div class="form-inline">
                <label>Ore svolte:</label>
                <span><%= listaRegistri.get(i).getAttivitaSvolte()[y].getOreSvolte() %></span>
              </div>
           </td>
           </tr>
           <tr>
           <td style="width: 300px">
	              <label>Inizio:</label>
	              <span><% GregorianCalendar gi = listaRegistri.get(i).getAttivitaSvolte()[y].getInizio();
	                       out.println(gi.get(GregorianCalendar.DAY_OF_MONTH) + "/" 
	                           + (gi.get(GregorianCalendar.MONTH)+1) + "/" 
	                           + gi.get(GregorianCalendar.YEAR));%></span>
           </td>
           <td style="width: 400px">
                <label>Fine:</label>
                <span><% GregorianCalendar gf = listaRegistri.get(i).getAttivitaSvolte()[y].getFine();
                         out.println(gf.get(GregorianCalendar.DAY_OF_MONTH) + "/" 
                             + (gf.get(GregorianCalendar.MONTH)+1) + "/" 
                             + gf.get(GregorianCalendar.YEAR));%></span>
          </td>
          </tr>
          <tr>
          <td style="width: 300px;" colspan="2">
             <div class="form-inline">
               <label>Convalida:</label>
               <span data-toggle="popover" data-placement="top" 
                data-content="<% String motivazione =  listaRegistri.get(i).getAttivitaSvolte()[y].getMotivazioneRifiuto();
                                 if (motivazione != null) 
                                    out.print(motivazione);%>" 
                onmouseover="$(this).popover('show');" 
                onmouseout="$(this).popover('hide');">
                <%= listaRegistri.get(i).getAttivitaSvolte()[y].getConvalida() %></span>
             </div>
          </td>
          </tr>
          </table>
              <div class="form-inline" style="text-align: right;">
                <div class="col-sm-offset-2 col-sm-10">
                  <button type="button" class="btn btn-primary" 
                    onclick="convalidaSicuro('<%= listaRegistri.get(i).getTirocinante().getEmail() %>',
                      '<%= listaRegistri.get(i).getOfferta().getId() %>',
                      '<%= listaRegistri.get(i).getAttivitaSvolte()[y].getAttivitaSvolta() %>',
                      'convalida');">Convalida</button>
                  <button type="button" class="btn btn-primary" 
                   onclick="convalidaSicuro('<%= listaRegistri.get(i).getTirocinante().getEmail() %>',
                       '<%= listaRegistri.get(i).getOfferta().getId() %>',
                       '<%= listaRegistri.get(i).getAttivitaSvolte()[y].getAttivitaSvolta() %>',
                       'nonConvalida');">Non Convalida</button>                  
                </div>
              </div>
         </div>
            
        </div>
        <%
           }
          }
         }
        %>
        </div>
        
        </div>
       </div>
      <%
         }
        }
      %>
     </div>   
	 </div>
	</div>
	
	<button type="button" class="btn btn-info btn-lg sicuroConvalidaTutte" data-toggle="modal" data-target="#myModal2" style="display: none;"></button>
  <div id="myModal2" class="modal fade" role="dialog" style="margin-top: 100px;">
    <div class="modal-dialog">
    
      <div class="modal-content alert alert-warning">
        <div class="modal-body">
          <h3>Sei sicuro?</h3>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" onclick="convalidaTutte();">Invia</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Annula</button>        
        </div>
       </div>
      </div>    
     </div>
	
	<button type="button" class="btn btn-info btn-lg sicuroConvalida" data-toggle="modal" data-target="#myModal1" style="display: none;"></button>
  <div id="myModal1" class="modal fade" role="dialog" style="margin-top: 100px;">
    <div class="modal-dialog">
    
      <div class="modal-content alert alert-warning">
        <div class="modal-body">
          <h3>Sei sicuro?</h3>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" onclick="inviaConvalida();">Invia</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Annula</button>        
        </div>
       </div>
      </div>    
     </div>
     
  <button type="button" class="btn btn-info btn-lg sicuroNonConvalida" data-toggle="modal" data-target="#myModal2" style="display: none;"></button>
  <div id="myModal2" class="modal fade" role="dialog" style="margin-top: 100px;">
    <div class="modal-dialog">
    
      <div class="modal-content alert alert-warning">
        <div class="modal-body">
          <h3>Motiva la non convalida:</h3>
          <textarea onblur="motivazioniRifiutoCheck($(this))" data-placement="top" data-content="Errore inserimento testo" 
            style="resize: none;" class="form-control" id="motivazioneRifiuto"
            placeholder="Motivazione (max. 500)" maxlength="500" row="4" cols="400"></textarea>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" onclick="checkCampiConvalida();">Invia</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Annula</button>        
        </div>
       </div>
      </div>    
     </div>
     
  <button type="button" class="btn btn-info btn-lg erroreInput" data-toggle="modal" data-target="#myModal3" style="display: none;"></button>
  <div id="myModal3" class="modal fade" role="dialog" style="margin-top: 100px;">
    <div class="modal-dialog">
      <div class="modal-content alert alert-warning">
        <div class="modal-body">
          <h3>Non hai inserito correttamente la motivazione.</h3>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>        
        </div>
       </div>
      </div>    
     </div>

  <% if(null != request.getParameter("errore")){ %>
  <script type="text/javascript">$(".btn-lg.erroreInput").eq(0).click();</script>
  <%} %>
</body>
</html>