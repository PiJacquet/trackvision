<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="/tv/common/styleMain.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="icon" type="image/png" href="/tv/pictures/icon.png">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">


<script type="text/javascript" src="/tv/script/includeHTML.js"></script>
<script type="text/javascript" src="/tv/script/toggleMenu.js"></script>
<script type="text/javascript" src="/tv/script/questionnary.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<title>TrackVision</title>
</head>
<body>
	<div w3-include-html="/tv/common/${menu}.html"></div>

	<!-- question about the state of health -->
	<div style="padding-left: 16px">
		<br> ${optionalMessage}
		<h3>please complete this questionnary</h3>
		<div class="container">

			<div class="row">
				<label class="col-md-6">Are you sick?</label> <label
					class="col-md-6" id="question" style="display: none;">what
					are your pathologie?</label>
			</div>
			<div class="row" id="diseaseRadioDisplay">
				<div class="col-xs-6 col-md-6 col-lg-6">
					<div>
						<input type="radio" id="diseaseyes" name="isdisease" value="yes"
							onclick="displayDisease()"> <label for="yes">yes</label>

						<input type="radio" id="athleteNo" name="isdisease" value="No"
							onclick="displayDisease()" checked> <label for="No">No</label>
					</div>
				</div>

				<div class="col-xs-6 col-md-6 col-lg-6" id="whatdisease"
					style="display: none;">
					<div>
						<input type="radio" id="hypertension" name="diseasename"
							value="hypertension" checked> <label for="huey">hyper
							tension</label> <input type="radio" id="diabetes" name="diseasename"
							value=diabetes> <label for="dewey">diabetes</label>

					</div>
				</div>
			</div>

			<div class="row">
				<label class="col-md-6">Are you sporty?</label> <label
					class="col-md-6" id="sport" style="display: none;">what
					frequency?</label>
			</div>

			<div class="row" id="athleteRadioDisplay">
				<div class="col-xs-6 col-md-6 col-lg-6">
					<div>
						<input type="radio" id="athleteoui" name="athlete" value="oui"
							onclick="displayAthlete()"> <label for="oui">Oui</label>

						<input type="radio" id="athletenon" name="athlete" value="non"
							onclick="displayAthlete()" checked> <label for="non">Non</label>
					</div>
				</div>

				<div class="col-xs-6 col-md-6 col-lg-6" id="athletefreq"
					style="display: none;">
					<div>
						<input type="radio" id="once a week" name="frequence"
							value="once a week" checked> <label for="huey">once
							a week</label> <input type="radio" id="twice a week" name="frequence"
							value="twice a week"> <label for="dewey">twice a
							week</label> <input type="radio" id="three or more times a week"
							name="frequence" value="three or more times a week"> <label
							for="louie">three or more times a week</label>
					</div>
				</div>
			</div>

			<div class="row">
				<label class="col-md-6">Are you allergic?</label>
                <label class="col-md-6" id="questionallergie" style="display: none;"> what are your allergies </label>
			</div>
			
			<div class="row" id="allergieRadioDisplay">
				<div class="col-xs-6 col-md-6 col-lg-6">
					<div>
						<input type="radio" id="allergieoui" name="allergie" value="oui"
							onclick="displayAllergie()"> <label for="oui">Oui</label>

						<input type="radio" id="allergienon" name="allergie" value="non"
							onclick="displayAllergie()" checked> <label for="non">Non</label>
					</div>
				</div>

				<div class="col-xs-6 col-md-6 col-lg-6" id="allergietype"
					style="display: none;">
					<div>
						<input type="radio" id="once a week" name="frequence"
							value="once a week" checked> <label for="huey">once a week</label> 
					</div>
				</div>
			</div>
			

		</div>
	</div>
	</br>
	</br>

	<div w3-include-html="/tv/common/footer.html"></div>

	<script>
		includeHTML();
	</script>
</body>
</html>
