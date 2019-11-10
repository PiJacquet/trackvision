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
		<br> ${optionalMessage}
		<h3>Sign in!</h3>
		<div class="container">
			<form action="/tv/connect" method="post">

				<div class="row">
					<div class="col-25">
						<label for="login">Login</label>
					</div>
					<div class="col-75">
						<input type="text" id="login" name="login"
							placeholder="Your login..">
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="password">Password</label>
					</div>
					<div class="col-75">
						<input type="password" id="password" name="password"
							placeholder="Your password...">
					</div>
				</div>

				<div class="row">
					<div class="col-25">
						<label for="isResident">I am a resident</label>
					</div>
					<div class="col-75">
						<label class="switch"> <input type="checkbox"
							id="isResident" name="isResident" checked> <span
							class="slider round"></span>
						</label>
					</div>

				</div>

				<div class="row">
					<input type="submit" value="Submit">
				</div>

			</form>
			<p>
				Do you want to become a resident? Please <a href="/tv/candidate">contact us!</a>
			</p>
		</div>
	</div>

	<div w3-include-html="/tv/common/footer.html"></div>

	<script>
		includeHTML();
	</script>

</body>

</html>
