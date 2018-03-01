<%@page import="it.unisa.etraining.model.bean.Utente"%>
<%@page import="it.unisa.etraining.model.bean.FunzionarioSegreteria"%>
<header class="headerDiv">
	<div class="right">
	  <%
      Utente utente = (Utente) session.getAttribute("utente");
    
      if (utente instanceof FunzionarioSegreteria) {
    %>
	  <a href="" style="text-decoration: none;">
	  <% } else { %>
	  <a href="TirociniAttiviStoricoServlet" style="text-decoration: none;">
	  <% } %>
	     <img class="imageHeader rounded-circle" src="img/Logo.jpeg">
	  </a>
		<h3 class="title">E-Training</h3>
	</div>
	<div class="left">
		<div>
      <form role="form" method="get" action="LogoutServlet">
        <span style="font-size: 15px;">Benvenuto <%= session.getAttribute("nome") %></span>
	      <button type="submit" class="btn btn-primary">Logout</button>
	    </form>
		</div>
	</div>
</header>