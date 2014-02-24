<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">

  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Home | UCLA Translate Flashcards</title>

   </head>

   <body>
	<% List<String> deckNameList = (List<String>) request.getAttribute("deckNameList");
	   for(String deckName : deckNameList) { %>
		<p><%= deckName %></p>
	   <% } %>
	<form action="/csv" enctype="multipart/form-data" Method="Post">
							  <p>New deck name: </p>
							  <INPUT TYPE="text" NAME="deckname">
							  <br>
							  <br>
								<p>Upload CSV </p>
								<input type="file" name="deck" accept="text/csv">
							<br>
							  <button type="button" class="btn btn-primary">Fetch from Phrasebook</button>
					</div>
	<div class="modal-footer">
						  <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
						  <button type="submit" value="submit" class="btn btn-primary">Save Changes</button>
						</div>
					</form>

   </body>
</html> 

