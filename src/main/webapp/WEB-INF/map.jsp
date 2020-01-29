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

	<a href="/tv/map" class="button-refresh">Refresh</a>

	<h3>Residence Map</h3>

	<p>The red color indicates there is an active alert or malfunction
		in this apartment. Click on it to investigate</p>

	<div class="residence">
		${map}
	</div>
	
	<h4>Legend</h4>
	<div class='legend'><div class='legend-dot noalert'></div> Everything is fine!</div>
	<div class='legend'><div class='legend-dot malfunction'></div> At least one malfunction was detected in the apartment!</div>
	<div class='legend'><div class='legend-dot alert'></div> At least one alert was detected! The number represent the amount of alerts and malfunctions</div>

	<div w3-include-html="/tv/common/footer.html"></div>

	<script>
		includeHTML();
	</script>

</body>

</html>
