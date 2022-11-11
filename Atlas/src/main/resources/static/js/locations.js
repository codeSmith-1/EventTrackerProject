window.addEventListener('load', function(e) {
	init();
});

function init() {
	getLocations();
	
	// add listener to create location
}

function addLocation(){
	document.locationForm.lookup.addEventListener('click', function(event) {
		event.preventDefault();
		let locationId = document.locationForm.locationId.value;
		console.log(locationId);
		if (!isNaN(locationId) && locationId > 0) {
			getLocation(locationId);
		}
	});
}

function getLocations() {
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/locations');
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				let locations = JSON.parse(xhr.responseText);
				showLocations(locations);
			} else if (xhr.status === 404) {
				displayError("Location not found.");
			} else {
				displayError("Error: " + xhr.status);
			}
		}
	}
	xhr.send();
}

function getLocation(locationId) {
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/locations/' + locationId);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status === 200) {
				let location = JSON.parse(xhr.responseText);
				displayLocation(location);
			} else if (xhr.status === 404) {
				displayError("Location not found.");
			} else {
				displayError("Error: " + xhr.status);
			}
		}
	}
	xhr.send();
};

function displayError(msg) {
	letdataDiv = document.getElementById('locationData');
	letdataDiv.textContent = msg;
};

function displayLocation(location) {
	let dataDiv = document.getElementById('locationData');
	dataDiv.textContent = '';
	let h1 = document.createElement('h1');
	let ul = document.createElement('ul');
	let li = document.createElement('li');

	h1.textContent = location.country;
	dataDiv.appendChild(h1);

	li.textContent = 'City: ' + location.city;
	ul.appendChild(li);
	li = document.createElement('li');
	li.textContent = 'Longitude: ' + location.longitude;
	ul.appendChild(li);
	li = document.createElement('li');
	li.textContent = 'Latitude: ' + location.latitude;
	ul.appendChild(li);
	//	li = document.createElement('li');
	//	li.textContent = location.image;
	//	ul.appendChild(li);

	dataDiv.appendChild(ul);
};

function showLocations(locations){
	let tbody = document.getElementById('locTableBody');
	tbody.textContent = "";
	if (locations && Array.isArray(locations) && locations.length>0){
		for (let location of locations){
			let tr = document.createElement('tr');
			let td = document.createElement('td');
			td.textContent = location.country;
			tr.appendChild(td);
			tbody.append(tr);
			tr.addEventListener('click', function(evt){
				evt.preventDefault();
				getVisits(location.id);
				
			})
		}
	}
}


function getVisits(locationId) {
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/locations/'+ locationId + '/visits');
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				let visits = JSON.parse(xhr.responseText);
				console.log(visits);
				showVisits(visits);
			} else if (xhr.status === 404) {
				displayError("Visits not found.");
			} else {
				displayError("Error: " + xhr.status);
			}
		}
	}
	xhr.send();
}

 function showVisits(visits) {
	locationForm.style.display='none';
	let locationTable = document.getElementById('locationTable');
	locationTable.style.display='none';
	addLocation.style.display='none';
//	let backButton = document.createElement('button');
//	backbutton.textContent = 'back to locations';
	//apend wherever
//	backButton.addEventListener('click', function(){
//		locationDiv
//	})

	for (let visit of visits){
	let cardDiv = document.getElementById('cardDiv');
	let card = document.createElement('div');
	card.class = "card";
//	card.style= "width: 18rem;"
	
	let img = document.createElement('img');
	img.classList.add("card-img-top");
	img.classList.add("visitImg");
	img.src = visit.photo;
	
	card.appendChild(img);
	
	let cardBody = document.createElement('div');
	cardBody.class = "card-body";
	
	let h5 = document.createElement('h5');
	h5.class= "card-title";
		
	let p = document.createElement('p');
	p.class ="card-text";
	p.textContent = visit.note;
	
	cardBody.appendChild(h5);
	cardBody.appendChild(p);
	card.appendChild(cardBody);
	cardDiv.appendChild(card);
	}
}
