<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="/tv/common/styleMain.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
				<div class="row">
					<div class="col-25">
						<label for="fname">First Name</label>
					</div>
					<div class="col-75">
						<input type="text" id="fname" name="firstname"
							placeholder="Your name..">
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="lname">Last Name</label>
					</div>
					<div class="col-75">
						<input type="text" id="lname" name="lastname"
							placeholder="Your last name..">
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="email">Email</label>
					</div>
					<div class="col-75">
						<input type="text" id="email" name="email"
							placeholder="Your email..">
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
