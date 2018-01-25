<header class="headerDiv">
	<div class="right">
		<img class="imageHeader rounded-circle" src="img/Logo.jpeg">
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