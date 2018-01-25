<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link rel="icon" type="image/png" href="img/Logo.jpeg">
		<script src="script/jquery.min.js"></script>
		<link rel="stylesheet" href="css/errorPage.css">
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/bootstrap-theme.min.css">
		<script src="script/bootstrap.min.js"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>E-Training</title>
	</head>
<body>
  <div class="background"></div>
  
  <div class="container marginContainer" style="margin-top: 0px;">
    <h1>E-Training</h1>
    <div class="row">
      <div class="main">
        <img alt="errore" src="img/Logo.jpeg" id="immageError">
        <h2>
        <% String error = request.getParameter("errore"); 
          if(null != error)
            out.print(error);
          else
            out.print(response.getStatus());
            %>
        </h2>
      </div>
    </div>
  </div>
</body>
</html>