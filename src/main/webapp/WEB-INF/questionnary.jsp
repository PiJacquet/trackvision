<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="/tv/common/styleMain.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="icon" type="image/png" href="/tv/pictures/icon.png">
<script type="text/javascript" src="/tv/script/includeHTML.js"></script>
<script type="text/javascript" src="/tv/script/toggleMenu.js"></script>
<title>TrackVision</title>
</head>
<body>
	<div w3-include-html="/tv/common/${menu}.html"></div>
	
	<div style="padding-left: 16px">
		<br>
		${optionalMessage}
		<h3>please complete this questionnary</h3>
		<div class="container">
		
		<div class="row">
					<div class="col-25">
					<label>Are you sick </label>
    				<input type="radio" name="choix" value="Oui" checked="checked" /> Yess
					<input type="radio" name="choix" value="Non" /> No
					</div>			
		</div>
			
		
		<div class="row">
					<div class="col-25">
					<label> what are your pathologies</label><br>
		
					<label class="container">hyper tension
		 		 	<input type="checkbox" checked="checked">
					</label><br>
					<label class="container">diabetes
		  			<input type="checkbox">
					</label>
					</div>
					<div class="col-75">
					
					</div>			
		</div>
		<div class="row">
					<div class="col-25">
						<label for="fname"> what is your lifestyle </label><br>
						<label class="container">athletic
		 		 		<input type="checkbox" checked="checked">
						</label>
						<label class="container">Sociable
		  				<input type="checkbox">
						</label>
						
					</div>
					<div class="col-75">
						
					</div>
			</div>
		
		
		
		<div class="row">
							
		</div>
		<div class="row">
					<input type="submit" value="Submit">
				</div>
		
		<div w3-include-html="/tv/common/footer.html"></div>

	<script>includeHTML();</script>
		
		
		
		
		
		

</body>
</html>