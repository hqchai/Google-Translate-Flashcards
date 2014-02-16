<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
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
    <link rel="icon" type="image/jpg" href="images/icon.jpg">
  </head>

  <%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
  %>

  <body>

    <!-- Navigation header -->
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
            <li><a href="home.jsp">Home</a></li>
            <li><a href="about.jsp">About</a></li>
            <li class="active"><a href="tutorial.jsp">Get Started</a></li>
          </ul>

          <% if (user != null) { %>

            <ul class="nav navbar-nav navbar-right">
              <li><a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">Sign Out</a></li>
            </ul>

          <% } %>

        </div><!--/.nav-collapse -->
      </div>
    </div>
    
    
    <!-- Navigation Sidebar -->
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li><a href="#">Getting Started</a></li>
            <li><a href="#signup">Sign Up</a></li>
            <li><a href="#login">Login</a></li>
            <li><a href="#create">Create a Deck</a></li>
            <!--<li><a href="#add">Add Cards to a Deck</a></li> -->
            <li><a href="#edit">Edit a Deck</a></li>
            <li><a href="#delete">Delete a Deck</a></li>
            <li><a href="#quiz">Quiz Yourself</a></li>
			<li><a href="#signout">Sign Out</a></li>
          </ul>
        </div>
    
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1>Getting Started</h1>
          <h2 id="signup">Sign Up</h2>
            <p>If you already have a Google account, this part is easy! UCLA Translate Flashcards uses your Google account and data from Google Translate to personalize your learning experience. Go ahead and sign in using your existing Google account information.</p>
            <p>If you do not have a Google account, <a href="https://accounts.google.com/SignUp">sign up for one now!</a> It's fast, free, easy, and will allow you to get started with UCLA Translate Flashcards instantly.</p>
          <h2 id="login">Login</h2>
            <p>Follow <a href="home.jsp">this link</a> to enter your Google account information and to begin your UCLA Translate Flashcards experience. If you have already logged in with your Google account, this will automatically redirect you to your dashboard.</p>
          <h2 id="create">Create a Deck</h2>
            <p>From <a href="home.jsp">your homepage</a>, click the "add a deck" icon in the lower-right corner of the screen. This will prompt you to fill out information regarding the deck you wish to create, including the deck name, language pair, and the source of the flashcard content.</p>
          <!--<h2 id="add">Add Cards to a Deck</h2>
            <p>See "Edit a Deck" </p>-->
          <h2 id="edit">Edit a Deck</h2>
            <p>From <a href="home.jsp">your homepage</a>, click the <a href="deck.html?name=Food">Edit</a> deck option. From there, you may edit the contents of a flashcard, add/remove flashcards, rename your deck, or quiz yourself (in case you didn't mean to click edit).</p>
          <h2 id="delete">Delete a Deck</h2>
            <p>From <a href="home.jsp">your homepage</a>, click on the "Delete" button below the deck you want to delete. You will then be prompted to confirm that you want to delete this deck. You may select "Confirm" to delete the deck, or click "Cancel" if you do not want to delete the deck.</p>
          <h2 id="quiz">Quiz Yourself</h2>
            <p>The best part! From <a href="home.jsp">your homepage</a>, click on the image of the deck you would like to be quizzed on. To flip a flashcard over, simply click anywhere on the flashcard. To advance to the next card, hover your mouse over the right arrow.</p>
		 <h2 id="signout">Sign Out</h2>
            <p>To sign out, simply click on the "Sign Out" button on the top-right corner of the screen. <br>Note: It will log you out of any associated Google accounts.</p>
	   </div>
      </div>
    </div>
  </body>
</html>
