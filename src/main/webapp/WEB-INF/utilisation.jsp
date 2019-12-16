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
		<h3>Manipuler  mes objets connectés : </h3>
		<div class="container">
		
			<form action="/tv/utilisation" method="post">
			
			<h2>Manipuler ma baignoire connectée : </h2>
			
				<div class="row">
					<div class="col-25">
						<label for="niveauEau">Niveau d'eau de la baignoire:</label>
					</div>
					<div class="col-75">
						<input type="number" id="niveauEau" name="niveauEau"
							placeholder="Niveau d'eau..">
					</div>
				</div>
				
				<div class="row">
					<div class="col-25">
						<label for="degréTemperature">Température de l'eau :</label>
					</div>
					<div class="col-75">
						<input type="number" id="degréTemperature" name="degréTemperature"
							placeholder="Degré de température..">
					</div>
				</div>
				
				<h2>Manipuler mes ampoules connectées: </h2>
				
				<div class="row">
					<div class="col-25">
						<label for="actMax">Eteindre/Allumer mes ampoules connectées:</label>
					</div>
					<div class="col-75">
						<input type="text" id="actMax" name="actMax"
							placeholder="ON/OFF..">
					</div>
				</div>
				
				<div class="row">
					<div class="col-25">
						<label for="actMax">Degré de luminosité:</label>
					</div>
					<div class="col-75">
						<input type="number" id="actMax" name="actMax"
							placeholder="Degré de luminosité..">
					</div>
				</div>
				
				<div class="row">
					<div class="col-25">
						<label for="actMax">Durée d'activité:</label>
					</div>
					<div class="col-75">
						<input type="number" id="actMax" name="actMax"
							placeholder="Durée d'activité..">
					</div>
				</div>
				
				
				<h2>Manipuler mes stores connectés: </h2>
				
				<div class="row">
					<div class="col-25">
						<label for="actMax">Ouvrir/Fermer mes stores connectés:</label>
					</div>
					<div class="col-75">
						<input type="text" id="actMax" name="actMax"
							placeholder="YES/NO..">
					</div>
				</div>
				
				
				<div class="row">
					<div class="col-25">
						<label for="actMax">Durée d'ouverture:</label>
					</div>
					<div class="col-75">
						<input type="number" id="actMax" name="actMax"
							placeholder="Durée d'ouverture..">
					</div>
				</div>
				
				
				
				<h2>Manipuler les fours connectés : </h2>
				
				<div class="row">
					<div class="col-25">
						<label for="actMax">Eteindre/Allumer mes plaques:</label>
					</div>
					<div class="col-75">
						<input type="text" id="actMax" name="actMax"
							placeholder="ON/OFF..">
					</div>
				</div>
				
				
				<div class="row">
					<div class="col-25">
						<label for="actMax">Durée d'activité:</label>
					</div>
					<div class="col-75">
						<input type="number" id="actMax" name="actMax"
							placeholder="Durée d'activité..">
					</div>
				</div>
				
				<div class="row">
					<div class="col-25">
						<label for="actMax">Température des plaques de cuisson:</label>
					</div>
					<div class="col-75">
						<input type="number" id="actMax" name="actMax"
							placeholder="température des plaques">
					</div>
				</div>
				
				
				
				<div class="row">
					<input type="submit" value="Submit">
				</div>
				</div>
			</form>
		</div>
	</div>

	<div w3-include-html="/tv/common/footer.html"></div>

	<script>includeHTML();</script>

</body>

</html>
