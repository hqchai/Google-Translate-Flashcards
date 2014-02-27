<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="flashcards.Flashcard" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Home | UCLA Translate Flashcards</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Hammersmith+One' rel='stylesheet' type='text/css'>
	  <link href="css/carousel.css" rel="stylesheet">
  
    <link rel="icon" type="image/jpg" href="images/icon.jpg">
  </head>

      <%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
  %>
  
  <body>

    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <div class="navbar-brand">UCLA Translate Flashcards</div>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="/home">Home</a></li>
            <li><a href="about.jsp">About</a></li>
            <li><a href="tutorial.jsp">Get Started</a></li>
          </ul>
		  
		    <ul class="nav navbar-nav navbar-right">
          	  <li><a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">Sign Out</a></li>
            </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

  <!-- Carousel
    ================================================== -->
	<div id="myCarousel" class="carousel slide">
      <div class="carousel-inner">
        <div class="item active">
          <img src="./images/ucla_logo.gif" alt="">
          <div class="container">
            <div class="carousel-caption">
			
			
			
              <h1>Flashcard 1</h1>
              <p class="lead">Content 1</p>
              <a class="btn btn-large btn-primary" button onclick="myFunction()">Flip over!</a>
			  
			    
			  
			  
			  
			  
            </div>
          </div>
        </div>
        <div class="item">
          <img src="./images/ucla_logo2.gif" alt="">
          <div class="container">
            <div class="carousel-caption">
              <h1>Flashcard 2</h1>
              <p class="lead">Content 2</p>
              <a class="btn btn-large btn-primary" href="#">Flip over!</a>
            </div>
          </div>
        </div>


      </div>

	  <h2 style="text-align:right; padding-right: 130px">Rate card difficulty </h2>

	  <div class="row-fluid">
		<div style = "float:right">
			<form name="User Feedback" action="ProcessCardServlet" method="post">
				<input class="btn btn-lg btn-primary" type="submit" name="cardRating" value="I got it! (Easy)">
				<input class="btn btn-lg btn-primary" type="submit" name="cardRating" value="Took a little time (Medium)">
				<input class="btn btn-lg btn-primary" type="submit" name="cardRating" value="That was tricky (Difficult)">
			</form>
		</div>
	</div>     


	 <!-- <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a> -->	  
      <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
    </div><!-- /.carousel -->
  
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>

	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="js/jquery-1.7.2.min.js"><\/script>')</script>
 <script src="js/bootstrap.min.js"></script>

  </body>
</html>
