<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.net.URLEncoder" %>
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
    String progressAmount = (String) request.getAttribute("progressAmount");

    // safeDeckName is for modal names, encodedDeckName is for HTTP GET parameter passing via URL
	String safeDeckName = deckName.replaceAll("[^a-zA-Z0-9]","-");
	String encodedDeckName = URLEncoder.encode(deckName, "UTF-8");
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
	    	<li><a href="#add" data-toggle="modal">Add Card</a></li>
            <li><a href="#delete" data-toggle="modal">Delete Deck</a></li>		
            <li><a href="/quiz?deckName=<%= encodedDeckName %>">Quiz</a></li>
			<li><a href="#rename" data-toggle="modal">Rename</a></li>
			<li><a href="#upload-csv" data-toggle="modal">Upload CSV</a></li><br>					
          </ul>
        </div>	
		
		
			<div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="delete-label" aria-hidden="true">
				<div class="modal-dialog">
				  <div class="modal-content">
					
					<div class="modal-header">
					  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					  <h4 class="modal-title" id="delete-label">Are you sure you want to delete the deck &quot;<%=deckName%>&quot;?</h4>
					</div>
					
					<div class="modal-body">
					  <p>Once you delete this deck, it may be impossible to retrieve this information again.</p>
					</div>
					
					<div class="modal-footer">
						<form name="Delete Deck Test Form" action="deleteDeck" method="post">
							<input type="hidden" name="deckName" value="<%= deckName %>">
							<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
							<button type="submit" value="submit" class="btn btn-danger">Delete Deck</button>
							<!--<input type="submit" value="Delete Deck">-->
						</form>
					</div>
					
				  </div>
				</div>
			  </div>
		
			<div class="modal fade" id="rename" tabindex="-1" role="dialog" aria-labelledby="delete-food-label" aria-hidden="true">
				<div class="modal-dialog">
				  <div class="modal-content">
					<div class="modal-header">
					  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					  <h4 class="modal-title" id="delete-food-label">Rename Deck &quot;<%= deckName %>&quot;?</h4>
					</div>
					
					<form action="/renameDeck" Method="Post">
						<div class="modal-body">
								<p>New deck name: </p>
								<input type="text" name="newDeckName">
								<input type="hidden" name="oldDeckName" value="<%= deckName %>">
						</div>
						<div class="modal-footer">
						  <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						  <button type="submit" value="submit" class="btn btn-primary">Save Changes</button>
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
								  <button type="submit" value="submit" class="btn btn-primary">Or translate with Google Translate</button>
								  <br>
								  <input type="hidden" name="deckName" value="<%= deckName %>">
								  <input type="hidden" name="language1" value="<c:out value="${language1}"/>">
								  <input type="hidden" name="language2" value="<c:out value="${language2}"/>">

						</div>
						<div class="modal-footer">
						  <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						  <button type="submit" value="submit" class="btn btn-primary">Save Changes</button>
						</div>
					</form>
				  </div>
				</div>
			  </div>

			  <div class="modal fade" id="upload-csv" tabindex="-1" role="dialog" aria-labelledby="upload-csv-label" aria-hidden="true">
				<div class="modal-dialog">
				  <div class="modal-content">
					<div class="modal-header">
					  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					  <h4 class="modal-title" id="upload-csv-label">Upload CSV</h4>
					</div>
					
					
					<form action="/csv" method="Post" enctype="multipart/form-data">
						<div class="modal-body">
							<input type="hidden" name="deckName" value="<c:out value="${deckName}"/>">
                                                        <input type="hidden" name="language1" value="<c:out value="${language1}"/>">
							<input type="hidden" name="language2" value="<c:out value="${language2}"/>">

							<p>Upload CSV </p>
							<input type="file" name="deck">
							<br>
						</div>
						<div class="modal-footer">
						  <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						  <button type="submit" value="submit" class="btn btn-primary">Import Cards</button>
						</div>
					</form>
				  </div>
				</div>
			  </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<table class="table table-striped table-hover table-condensed">
			<div>
  				<h1 align="center"><%= deckName %></h1>
			</div>
			<div align="center" class="progress progress-striped active">
  					<div class="progress-bar" role="progressbar" aria-valuenow="<%= progressAmount %>" aria-valuemin="0" aria-valuemax="100" style="width: <%= progressAmount %>%">
  					<em>Level of Mastery: <%= progressAmount %>%</em></div>
			</div>
	        <thead>  
	          <tr>  
	            <th><c:out value="${language1}"/></th>  
	            <th><c:out value="${language2}"/></th>
	            <th>User Rating</th>
	            <th></th>
	            <th></th>  
	          </tr>  
	        </thead>  
	        <tbody>  
			
			<!-- just have to make this dynamically generated -->
			
		<c:forEach var="flashcard" items="${flashcardList}">
			<c:set var="p1" value="${flashcard.phrase1}" />
			<c:set var="userRating" value="${flashcard.userRating}" />

			  <%
			  	String phrase1 = (String) pageContext.getAttribute("p1");
			  	String safePhrase1 = phrase1.replaceAll("[^a-zA-Z0-9]","-");
			  	String userRatingNum = (String) pageContext.getAttribute("userRating");
			  	String userRatingString = "Medium";
			  	switch (userRatingNum) {
			  		case "1": 	userRatingString = "Easy";
			  					break;
			  		case "2":	userRatingString = "Medium";
			  					break;
			  		case "3":	userRatingString = "Difficult";
			  					break;
			  		default:	userRatingString = "Medium";
			  					break;
			  	}
			  %>
	          
	          <tr>  
	            <td>${flashcard.phrase1}</td>  
	            <td>${flashcard.phrase2}</td>
	            <td><%= userRatingString %></td>
	            <td>
	           		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#edit-<%=safePhrase1%>">Edit</button>
	            </td> 
	            <td>
	             	<form action="/deleteCard" method="Post">
	             		<input type="hidden" name="deckName" value="<%= deckName %>">
	             		<input type="hidden" name="phrase1" value="<c:out value="${flashcard.phrase1}"/>">
	             		<input type="hidden" name="phrase2" value="<c:out value="${flashcard.phrase2}"/>">
	             		<button type="submit" value="submit" class="btn btn-danger">Delete</button>
	             	</form>
	            </td> 
	          </tr>

	          <div class="modal fade" id="edit-<%=safePhrase1%>" tabindex="-1" role="dialog" aria-labelledby="edit--<%=safePhrase1%>-label" aria-hidden="true">
				<div class="modal-dialog">
				  <div class="modal-content">
					<div class="modal-header">
					  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					  <h4 class="modal-title" id="add-label">Edit Card</h4>
					</div>
					
					
					<form action="/editCard" method="Post">
						<div class="modal-body">
								  <p>Phrase 1: </p>
								  <input type="text" name="phrase1" value="${flashcard.phrase1}">
								  <br><br>
								  <p>Phrase 2: </p>
								  <input type="text" name="phrase2" value="${flashcard.phrase2}">
								  <br><br>
								  <input type="radio" name="userRating" value="1">Easy<br>
								  <input type="radio" name="userRating" value="2" checked="checked">Medium<br>
								  <input type="radio" name="userRating" value="3">Difficult<br>
								  <br><br>
								  <input type="hidden" name="deckName" value="<%= deckName %>">
								  <input type="hidden" name="oldPhrase1" value="<c:out value="${flashcard.phrase1}"/>">
								  <input type="hidden" name="oldPhrase2" value="<c:out value="${flashcard.phrase2}"/>">
						</div>
						<div class="modal-footer">
						  <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						  <button type="submit" value="submit" class="btn btn-primary">Save Changes</button>
						</div>
					</form>
				  </div>
				</div>
			  </div>

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
