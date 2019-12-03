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
	<a href="/tv/map" class="button-refresh">See on the map</a>

	<h3>Information about the Apartment A (level 0)</h3>
	
	<p> Information about the resident </p>

	<h4>Current alerts</h4>
	<table class="distinguishedAlertTable">
		<tr>
			<th>ID</th>
			<th>Level</th>
			<th>Date</th>
			<th>Message</th>
			<th>Object</th>
		</tr>
		<tr>
			<td>1</td>
			<td>2</td>
			<td>2019-12-02</td>
			<td>Toto</td>
		</tr>
		<tr>
			<th>ID</th>
			<th>Level</th>
			<th>Date</th>
			<th>Message</th>
			<th>Object</th>
		</tr>
		<tr>
			<td>1</td>
			<td>2</td>
			<td>2019-12-02</td>
			<td>Toto</td>
		</tr>
	</table>

	<h4>List of related Objects</h4>
	<table class="distinguishedTable">
		<tr>
			<th>ID</th>
			<th>Type</th>
			<th>State</th>
			<th>Mac address</th>
		</tr>
		<tr>
			<td>1</td>
			<td>Smoke detector</td>
			<td>On</td>
			<td>12:34:56:78</td>
		</tr>
		<tr>
			<td>2</td>
			<td>Furnace</td>
			<td>Off</td>
			<td>ab:45:ef:gh</td>
		</tr>
	</table>
	
	<h4>Old alerts</h4>
	<table class="distinguishedTable">
		<tr>
			<th>ID</th>
			<th>Level</th>
			<th>Date</th>
			<th>Message</th>
			<th>Object</th>
		</tr>
		<tr>
			<td>1</td>
			<td>2</td>
			<td>2019-12-02</td>
			<td>Toto</td>
		</tr>
		<tr>
			<th>ID</th>
			<th>Level</th>
			<th>Date</th>
			<th>Message</th>
			<th>Object</th>
		</tr>
		<tr>
			<td>1</td>
			<td>2</td>
			<td>2019-12-02</td>
			<td>Toto</td>
		</tr>
	</table>

	
	<div w3-include-html="/tv/common/footer.html"></div>

	<script>
		includeHTML();
	</script>

</body>

</html>
