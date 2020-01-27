function displayAthlete(){
		var radios = document.getElementsByName('athlete');
		var valeur;
		for(var i = 0; i < radios.length; i++){
		 if(radios[i].checked){
		 valeur = radios[i].value;
		 }
		}
		if(valeur=='oui'){
			showStuff('athletefreq');
			showStuff('sport');
		}
		else{
			hideStuff('athletefreq');
			hideStuff('sport');
		}
	}
   function showStuff(id) {
            document.getElementById(id).style.display = 'block';
             
    }
	 function hideStuff(id) {
            document.getElementById(id).style.display = 'none';
            
    }
	 
	 
 	 
	 function displayDisease(){
			var radios = document.getElementsByName('isdisease');
			var valeur;
			for(var i = 0; i < radios.length; i++){
			 if(radios[i].checked){
			 valeur = radios[i].value;
			 }
			}
			if(valeur=='yes'){
				showStuff('whatdisease');
				showStuff('question');
			}
			else{
				hideStuff('whatdisease');
				hideStuff('question');
			}				
		}
	   function showStuff(id) {
	            document.getElementById(id).style.display = 'block';
	             
	    }
		 function hideStuff(id) {
	            document.getElementById(id).style.display = 'none';
	             
	    }
		 
		 
		 function displayAllergie(){
				var radios = document.getElementsByName('allergie');
				var valeur;
				for(var i = 0; i < radios.length; i++){
				 if(radios[i].checked){
				 valeur = radios[i].value;
				 }
				}
				if(valeur=='oui')
					showStuff('allergietype');
					
				
				else{
					hideStuff('allergietype');
					hideStuff('question');
				}				
			}
		   function showStuff(id) {
		            document.getElementById(id).style.display = 'block';
		             
		    }
			 function hideStuff(id) {
		            document.getElementById(id).style.display = 'none';
		             
		    }
		 
		 
		 