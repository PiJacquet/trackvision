<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<link href="/tv/common/styleMain.css" rel="stylesheet">
	<link href="/tv/common/styleMap.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="icon" type="image/png" href="/tv/pictures/icon.png">
	<script type="text/javascript" src="/tv/script/includeHTML.js"></script>
	<script type="text/javascript" src="/tv/script/toggleMenu.js"></script>
	<title>TrackVision</title>
</head>

<body>

	<div w3-include-html="/tv/common/${menu}.html"></div>
	
	<div style="padding-left:16px">
  		<h3>Welcome to the website of our Residence!</h3>
	</div>
	
	<p>
		In here, you can access your resident account, your employee account or just ask for a place in our establishment!
	</p>
	<img src="/tv/pictures/home.gif" style="display: block; margin-left: auto;margin-right: auto">
	

	<div w3-include-html="/tv/common/footer.html"></div>
	
	<script>includeHTML();</script>
	
</body> 

</html>
