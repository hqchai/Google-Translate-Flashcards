<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
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
  </head>

    <%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    String deckName = (String) request.getAttribute("deckName");
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

		
 <!-- Navigation Sidebar -->
	<div class="container-fluid">
      <div class="row-fluid">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
			<li><a href="#rename" data-toggle="modal">Rename</a></li>			
			
            <li><a href="#delete" data-toggle="modal">Delete Deck</a></li>		
            <li><a href="./quiz.html">Quiz</a></li>
			<li><a href="#add" data-toggle="modal">Add Card</a></li>
					
          </ul>
        </div>	
		
		
		 <div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="delete-label" aria-hidden="true">
				<div class="modal-dialog">
				  <div class="modal-content">
					
					<div class="modal-header">
					  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					  <h4 class="modal-title" id="delete-label">Are you sure you want to delete the deck &quot;Deck&quot;?</h4>
					</div>
					
					<div class="modal-body">
					  <p>Once you delete this deck, it may be impossible to retrieve this information again.</p>
					</div>
					
					<form action="" Method="Post">
						<div class="modal-footer">
						  <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						  <button type="button" class="btn btn-danger">Delete</button>
						</div>
					</form>	
              </div>
            </div>
          </div>
		
			<div class="modal fade" id="rename" tabindex="-1" role="dialog" aria-labelledby="delete-food-label" aria-hidden="true">
				<div class="modal-dialog">
				  <div class="modal-content">
					<div class="modal-header">
					  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					  <h4 class="modal-title" id="delete-food-label">Rename Deck &quot;Food&quot;?</h4>
					</div>
					
					<form action="" Method="Post">
						<div class="modal-body">
								<p>New deck name: </p>
								<INPUT TYPE="text" NAME="deckname">
						</div>
						<div class="modal-footer">
						  <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						  <button type="button" class="btn btn-primary">Save Changes</button>
						</div>
					</form>
				  </div>
				</div>
			  </div>
			
			
			
			<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="add-label" aria-hidden="true">
				<div class="modal-dialog">
				  <div class="modal-content">
					<div class="modal-header">
					  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					  <h4 class="modal-title" id="add-label">Add Card</h4>
					</div>
					
					
					<form action="/addCard" method="Post">
						<div class="modal-body">
								  <p>Phrase 1: </p>
								  <INPUT TYPE="text" NAME="phrase1">
								  <br>
								  <p>Phrase 2: </p>
								  <INPUT TYPE="text" NAME="phrase2">
								  <p>Or translate with Google Translate</p>
								  <br>
								  <input type="hidden" name="deckName" value="<%= deckName %>">
						</div>
						<div class="modal-footer">
						  <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						  <button type="submit" value="submit" class="btn btn-primary">Save Changes</button>
						</div>
					</form>
				  </div>
				</div>
			  </div>
	
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<table class="table table-striped">  
        <thead>  
          <tr>  
            <th>Language 1</th>  
            <th>Language 2</th>  
          </tr>  
        </thead>  
        <tbody>  
		
		<!-- just have to make this dynamically generated -->
		
	<c:forEach var="flashcard" items="${flashcardList}">
          <tr>  
            <td>${flashcard.phrase1}</td>  
            <td>${flashcard.phrase2}</td>  
          </tr>  
	</c:forEach>
        </tbody>  
		
		
      </table>  
	
	   </div>
      </div>
    </div>

	<!-- make pop up for rename deck -->
	<!--
 -->

  
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>

	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="js/jquery-1.7.2.min.js"><\/script>')</script>
 <script src="js/bootstrap.min.js"></script>

  </body>
</html>
