<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="flashcards.Flashcard" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

    <script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>

<style id="jsbin-css">
body {
    background: #ccc;
}
.flip {
    -webkit-perspective: 800;
    width: 400px;
    height: 200px;
    position: relative;
    margin: 50px auto;
}
.flip .card.flipped {
    -webkit-transform: rotatex(-180deg);
}
.flip .card {
    width: 100%;
    height: 100%;
    -webkit-transform-style: preserve-3d;
    -webkit-transition: 0.5s;
}
.flip .card .face {
    width: 100%;
    height: 100%;
    position: absolute;
    -webkit-backface-visibility: hidden;
    z-index: 2;
    font-family: Georgia;
    font-size: 3em;
    text-align: center;
    line-height: 200px;
}
.flip .card .front {
    position: absolute;
    z-index: 1;
    background: black;
    color: white;
    cursor: pointer;
}
.flip .card .back {
    -webkit-transform: rotatex(-180deg);
    background: blue;
    background: white;
    color: black;
    cursor: pointer;
}
</style>
  </head>
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
          	  <li><a href="<c:out value="${logoutURL}"/>">Sign Out</a></li>
            </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

  <!-- Carousel
    ================================================== -->
	<div id="myCarousel" class="carousel slide">
      <div class="carousel-inner">
        <div class="item active">
          <!--<img src="./images/ucla_logo.gif/1200x480" alt="">-->
          <div class="container">
           <!-- <div class="carousel-caption">-->
			
			
			          <div class="flip">
                    <div class="card">
                        <div class="face front"><c:out value="${flashcard.phrase1}"/></div>
                        <div class="face back"><c:out value="${flashcard.phrase2}"/></div>
                    </div>
                </div>
                  
                <script>
                $('.flip').click(function () {
                        $(this).find('.card').addClass('flipped').mouseleave(function () {
                            $(this).removeClass('flipped');
                        });
                        return false;
                    });
                </script>
			  
			  
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
