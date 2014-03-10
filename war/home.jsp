<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.net.URLEncoder" %>
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
    <link href="css/cover.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Hammersmith+One' rel='stylesheet' type='text/css'>
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
            <li class="active"><a href="/home">Home</a></li>
            <li><a href="about.jsp">About</a></li>
            <li><a href="tutorial.jsp">Get Started</a></li>
          </ul>


            <ul class="nav navbar-nav navbar-right">
          	  <li><a href="<%= userService.createLogoutURL("/login") %>">Sign Out</a></li>
            </ul>


        </div><!--/.nav-collapse -->
      </div>
    </div>
	
	
      <div class="container">
	   <% List<String> deckNameList = (List<String>) request.getAttribute("deckNameList");
		 int deckSize = deckNameList.size();
		 for (int i =0; i <= deckSize; i++) {
			if (i % 4 == 0) { %>
				<div class="row-fluid">
			<% }
			
			if (i == deckSize)
			{ %>
			 	<div class="col-md-3"><a href = "#add" data-toggle="modal"><div class="row add-deck"><img src="images/add_deck.png" alt="add a deck"></div></a></div>
	  
	  			<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="add-deck-label" aria-hidden="true">
				<div class="modal-dialog">
				  <div class="modal-content">
					<div class="modal-header">
					  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					  <h4 class="modal-title" id="add-deck-label">Add deck</h4>
					</div>
					
					<div class="modal-body">
					
							<form name="Add Deck Test Form" action="addDeck" method="post">
							New Deck Name: <input type="text" name="deckName"><br><br>

							Language1:
							<select name="language1" size="4">
								<option value="English" selected>English</option>
								<option value="Afrikaans">Afrikaans</option>
								<option value="Albanian">Albanian</option>
								<option value="Arabic">Arabic</option>
								<option value="Azerbaijani">Azerbaijani</option>
								<option value="Basque">Basque</option>
								<option value="Bengali">Bengali</option>
								<option value="Belarusian">Belarusian</option>
								<option value="Bulgarian">Bulgarian</option>
								<option value="Catalan">Catalan</option>
								<option value="Chinese Simplified">Chinese Simplified</option>
								<option value="Chinese Traditional">Chinese Traditional</option>
								<option value="Croatian">Croatian</option>
								<option value="Czech">Czech</option>
								<option value="Danish">Danish</option>
								<option value="Dutch">Dutch</option>
								<option value="Esperanto">Esperanto</option>
								<option value="Estonian">Estonian</option>
								<option value="Filipino">Filipino</option>
								<option value="Finnish">Finnish</option>
								<option value="French">French</option>
								<option value="Galician">Galician</option>
								<option value="Georgian">Georgian</option>
								<option value="German">German</option>
								<option value="Greek">Greek</option>
								<option value="Gujarati">Gujarati</option>
								<option value="Haitian Creole">Haitian Creole</option>
								<option value="Hebrew">Hebrew</option>
								<option value="Hindi">Hindi</option>
								<option value="Hungarian">Hungarian</option>
								<option value="Icelandic">Icelandic</option>
								<option value="Indonesian">Indonesian</option>
								<option value="Irish">Irish</option>
								<option value="Italian">Italian</option>
								<option value="Japanese">Japanese</option>
								<option value="Kannada">Kannada</option>
								<option value="Korean">Korean</option>
								<option value="Latin">Latin</option>
								<option value="Latvian">Latvian</option>
								<option value="Lithuanian">Lithuanian</option>
								<option value="Macedonian">Macedonian</option>
								<option value="Malay">Malay</option>
								<option value="Maltese">Maltese</option>
								<option value="Norwegian">Norwegian</option>
								<option value="Persian">Persian</option>
								<option value="Polish">Polish</option>
								<option value="Portuguese">Portuguese</option>
								<option value="Romanian">Romanian</option>
								<option value="Russian">Russian</option>
								<option value="Serbian">Serbian</option>
								<option value="Slovak">Slovak</option>
								<option value="Slovenian">Slovenian</option>
								<option value="Spanish">Spanish</option>
								<option value="Swahili">Swahili</option>
								<option value="Swedish">Swedish</option>
								<option value="Tamil">Tamil</option>
								<option value="Telugu">Telugu</option>
								<option value="Thai">Thai</option>
								<option value="Turkish">Turkish</option>
								<option value="Ukrainian">Ukrainian</option>
								<option value="Urdu">Urdu</option>
								<option value="Vietnamese">Vietnamese</option>
								<option value="Welsh">Welsh</option>
								<option value="Yiddish">Yiddish</option>

							</select>

							
							<br><br>Language2: 
							<select name="language2" size="4">
								<option value="English" selected>English</option>
								<option value="Afrikaans">Afrikaans</option>
								<option value="Albanian">Albanian</option>
								<option value="Arabic">Arabic</option>
								<option value="Azerbaijani">Azerbaijani</option>
								<option value="Basque">Basque</option>
								<option value="Bengali">Bengali</option>
								<option value="Belarusian">Belarusian</option>
								<option value="Bulgarian">Bulgarian</option>
								<option value="Catalan">Catalan</option>
								<option value="Chinese Simplified">Chinese Simplified</option>
								<option value="Chinese Traditional">Chinese Traditional</option>
								<option value="Croatian">Croatian</option>
								<option value="Czech">Czech</option>
								<option value="Danish">Danish</option>
								<option value="Dutch">Dutch</option>
								<option value="Esperanto">Esperanto</option>
								<option value="Estonian">Estonian</option>
								<option value="Filipino">Filipino</option>
								<option value="Finnish">Finnish</option>
								<option value="French">French</option>
								<option value="Galician">Galician</option>
								<option value="Georgian">Georgian</option>
								<option value="German">German</option>
								<option value="Greek">Greek</option>
								<option value="Gujarati">Gujarati</option>
								<option value="Haitian Creole">Haitian Creole</option>
								<option value="Hebrew">Hebrew</option>
								<option value="Hindi">Hindi</option>
								<option value="Hungarian">Hungarian</option>
								<option value="Icelandic">Icelandic</option>
								<option value="Indonesian">Indonesian</option>
								<option value="Irish">Irish</option>
								<option value="Italian">Italian</option>
								<option value="Japanese">Japanese</option>
								<option value="Kannada">Kannada</option>
								<option value="Korean">Korean</option>
								<option value="Latin">Latin</option>
								<option value="Latvian">Latvian</option>
								<option value="Lithuanian">Lithuanian</option>
								<option value="Macedonian">Macedonian</option>
								<option value="Malay">Malay</option>
								<option value="Maltese">Maltese</option>
								<option value="Norwegian">Norwegian</option>
								<option value="Persian">Persian</option>
								<option value="Polish">Polish</option>
								<option value="Portuguese">Portuguese</option>
								<option value="Romanian">Romanian</option>
								<option value="Russian">Russian</option>
								<option value="Serbian">Serbian</option>
								<option value="Slovak">Slovak</option>
								<option value="Slovenian">Slovenian</option>
								<option value="Spanish">Spanish</option>
								<option value="Swahili">Swahili</option>
								<option value="Swedish">Swedish</option>
								<option value="Tamil">Tamil</option>
								<option value="Telugu">Telugu</option>
								<option value="Thai">Thai</option>
								<option value="Turkish">Turkish</option>
								<option value="Ukrainian">Ukrainian</option>
								<option value="Urdu">Urdu</option>
								<option value="Vietnamese">Vietnamese</option>
								<option value="Welsh">Welsh</option>
								<option value="Yiddish">Yiddish</option>

							</select>

							<br><br>
							
					</div>

					<div class="modal-footer">
							<button type="submit" value="submit" class="btn btn-primary">Add Deck</button>
							</form>
					</div>
				  </div>
				</div>
			  </div>
			 		 
			<% }
			else {
			
			String deckName = deckNameList.get(i); 
      
      // Short deck name is the name displayed on each deck's picture.
      String shortDeckName = deckName;
      if (shortDeckName.length() > 8) {
        shortDeckName = shortDeckName.substring(0,8) + "...";
      }

			// safeDeckName is for modal names, encodedDeckName is for HTTP GET parameter passing via URL
			String safeDeckName = deckName.replaceAll("[^a-zA-Z0-9]","-");
			String encodedDeckName = URLEncoder.encode(deckName, "UTF-8"); %>

			 <div class="col-md-3"><a href="quiz?deckName=<%=encodedDeckName%>"><div class="row deck"><div style="word-wrap: break-word"><%= shortDeckName %></div></div></a><div class="row deck-buttons"><button type="button" class="btn btn-primary" onclick="window.location.href='/editDeck?deckName=<%=encodedDeckName%>'">Edit</button>&nbsp; &nbsp;<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#delete-<%=safeDeckName%>">Delete</button></div></div>

			 <div class="modal fade" id="delete-<%=safeDeckName%>" tabindex="-1" role="dialog" aria-labelledby="delete-<%=safeDeckName%>-label" aria-hidden="true">
				<div class="modal-dialog">
				  <div class="modal-content">
					
					<div class="modal-header">
					  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					  <h4 class="modal-title" id="delete-<%=safeDeckName%>-label">Are you sure you want to delete the deck &quot;<%=deckName%>&quot;?</h4>
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
		  <% } %> <!-- closes the else -->
		<% if (i % 4 == 3) { %>
			</div> <!-- closes row div if 4th element -->
		<% } %>
	  <% }%>

        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
  </body>
</html>
