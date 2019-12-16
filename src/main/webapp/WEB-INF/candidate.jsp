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
		<h3>Want to join the residence?</h3>
		<div class="container">
			<form action="/tv/candidate" method="post">
			<label>Civility</label><br>
       		<input type="radio" name="civility" value="M." checked="checked"> M.
       		<input type="radio" name="civility" value="Mme"> Mme
       		
       		<div class="row">
					<div class="col-25">
						<label for="fname"> Age </label>
					</div>
					<div class="col-75">
						<input type="number"  id="age" name="age"
						placeholder="Your Age.." size="50" style="width:100px";" required= step="1">
					</div>
			</div>
				<div class="row">
					<div class="col-25">
						<label for="fname">First Name</label>
					</div>
					<div class="col-55">
						<input type="text" id="fname" name="firstname"
							placeholder="Your name.." size="50" style="width:500px";" required>
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="lname">Last Name</label>
					</div>
					<div class="col-75">
						<input type="text" id="lname" name="lastname"
							placeholder="Your last name.."  style="width:500px";" required>
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="email">Email</label>
					</div>
					<div class="col-75">
						<input type="text" id="email" name="email"
							placeholder="Your email.."  style="width:500px";" required>
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="pass">Password (8 characters minimum):</label>
					</div>
					<div class="col-75">
						<input type="password" id="pass" name="password" placeholder="Your password.."
           				minlength="8" required    style="width:500px";">
					</div>
				</div>
				
				<div class="row">
					<input type="submit" value="Submit">
				</div>
			</form>
		</div>
	</div>

	<div w3-include-html="/tv/common/footer.html"></div>

	<script>includeHTML();</script>

</body>

</html>
