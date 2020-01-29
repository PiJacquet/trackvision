<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="/tv/common/styleMain.css" rel="stylesheet">
<link href="/tv/common/styleMap.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="icon" type="image/png" href="/tv/pictures/icon.png">
<script type="text/javascript" src="/tv/script/includeHTML.js"></script>
<script type="text/javascript" src="/tv/script/toggleMenu.js"></script>
<title>TrackVision</title>
</head>

<body>

	<div w3-include-html="/tv/common/${menu}.html"></div>

	<a href="/tv/map" class="button-refresh">Return to the map</a>
	<form action="/tv/map" method="POST">
		<input name="apartmentId" type="hidden" value="${apartmentId}">
		<input type="submit" class="button-refresh" value="See on the map">
	</form>

	${message}

	<h3>Information about the Apartment '${apartmentName}' (level ${apartmentLevel})</h3>

	<h4>Who lives here?</h4>
	${residentsInfo}

	${activeAlerts}
	
	<h2>List of related objects</h2>

	${objects}
	
	${oldAlerts}

	<div w3-include-html="/tv/common/footer.html"></div>

	<script>
		includeHTML();
	</script>

</body>

</html>
