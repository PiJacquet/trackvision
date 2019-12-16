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
		<h3>Want to choose your package?</h3>
		<div class="container">
			<form action="/tv/package" method="post">
				<div class="row">
					<div class="col-25">
						<label for="fname">Name of the package</label>
					</div>
					<div class="col-75">
						<input type="text" id="package_name" name="package_name"
							placeholder="basic formula">
					</div>
				</div>
				
				<div class="row">
					<div class="col-25">
						<label for="price">Price:</label>
					</div>
					<div class="col-75">
						<input type="number" id="price" name="price"
           				value="200" >
					</div>
				</div>
				
				<div class="row">
					<div class="col-25">
						<label for="description">Description of the package</label>
					</div>
					<div class="col-75">
						<input type="text" id="description" name="description"
							placeholder="This formula contains a single room, access to the garden, leisure activities.">
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="connected_object">Connected object</label>
					</div>
					<div class="col-75">
						<input type="checkbox" id="checkbox" name="connected_object">
					</div>
				</div>
				<div class="row">
					<div class="col-25">
						<label for="meal">Meal:</label>
					</div>
					<div class="col-75">
						<input type="checkbox" id="checkbox2" name="meal">
					</div>
				</div>
				
				
				<div class="row">
					<input type="submit" value="Choose this package">
				</div>
			</form>
		</div>
	</div>

	<div w3-include-html="/tv/common/footer.html"></div>

	<script>includeHTML();</script>

</body>

</html>
