window.addEventListener('load', function(e) {
	init();
});

function init() {
	getLocations();
	addLocation();
}

function addLocation() {
	document.addLocation.submitLocation.addEventListener('click', function(event) {
		event.preventDefault();
		let location = {
			country: document.addLocation.country.value,
			city: document.addLocation.city.value,
			latitude: document.addLocation.latitude.value,
			longitude: document.addLocation.longitude.value
		};
		addNewLocation(location);
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

function showLocations(locations) {
	document.editVisitForm.style.display = 'none';
	let tbody = document.getElementById('locTableBody');

	tbody.textContent = "";
	if (locations && Array.isArray(locations) && locations.length > 0) {
		for (let location of locations) {
			let tr = document.createElement('tr');
			let td = document.createElement('td');
			td.textContent = location.country;
			tr.appendChild(td);
			td = document.createElement('td');
			td.textContent = location.city;
			tr.appendChild(td);
			td = document.createElement('td');
			td.textContent = location.latitude;
			tr.appendChild(td);
			td = document.createElement('td');
			td.textContent = location.longitude;
			tr.appendChild(td);
			td = document.createElement('td');


			tr.addEventListener('click', function(evt) {
				evt.preventDefault();
				getVisits(location.id);

			});

			// create location edit and delete

			tr.appendChild(td);
			tbody.append(tr);
		}
	}
	// append count of locations to table
}


function getVisits(locationId) {
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/locations/' + locationId + '/visits');
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

function addNewLocation(location) {
	let xhr = new XMLHttpRequest();
	xhr.open('POST', 'api/locations')

	xhr.setRequestHeader("Content-type", "application/json");

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200 || xhr.status === 201) {
				let location = JSON.parse(xhr.responseText);
				getLocations();
			} else {
				console.error(xhr.status + ': ' + xhr.responseText);
			}
		}
	}
	xhr.send(JSON.stringify(location));
	showLocations();
}

function showVisits(visits) {
	locationForm.style.display = 'none';
	let locationTable = document.getElementById('locationTable');
	locationTable.style.display = 'none';
	document.addLocation.style.display = 'none';
	document.editVisitForm.style.display = 'none';


	for (let visit of visits) {
		let cardDiv = document.getElementById('cardDiv');
		let backDiv = document.createElement('div');
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
		h5.class = "card-title";

		let p = document.createElement('p');
		p.class = "card-text";
		p.textContent = visit.note;

		cardBody.appendChild(h5);
		cardBody.appendChild(p);
		card.appendChild(cardBody);
		cardDiv.appendChild(card);

		let editButton = document.createElement('button');
		editButton.textContent = 'edit visit';
		card.appendChild(editButton);

		editButton.addEventListener('click', function(e) {
			editForm(visit);

		});



		let backButton = document.createElement('button');
		backButton.textContent = 'back to locations';
		card.appendChild(backButton);

		backButton.addEventListener('click', function(e) {
			e.preventDefault();
			cardDiv.style.display = 'none';
			document.locationForm.style.display = 'block';
			document.addLocation.style.display = 'block';
			getLocations();
			window.location.reload();
		});
	}

	function editForm(visit) {
		cardDiv.style.display = 'none';
		document.locationForm.style.display = 'none';
		document.addLocation.style.display = 'none';
		document.editVisitForm.style.display = 'block';
		console.log(visit.note.value);
		console.log(visit);
		document.editVisitForm.note.value = visit.note;
		document.editVisitForm.photo.value = visit.photo;
		document.editVisitForm.arrivalDate.value = visit.arrivalDate;
		document.editVisitForm.departureDate.value = visit.departureDate;
		document.editVisitForm.submitEdit.addEventListener('click', function(e) {
			e.preventDefault();
			console.log(visit);
		let visit = {
			id : visit.id,
			note: document.addLocation.country.value,
			photo: document.editVisitForm.photo.value,
			arrivalDate: document.editVisitForm.arrivalDate.value,
			departureDate: document.editVisitForm.arrivalDate.value,
		}
		putVisit(visit);
	});
};

function putVisit(visit) {
	let xhr = new XMLHttpRequest();
	xhr.open('PUT', "locations/" + { lid } + "/visits/" + visit.id)

	xhr.setRequestHeader("Content-type", "application/json");

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200 || xhr.status === 201) {
				let visit = JSON.parse(xhr.responseText);
				getLocations();
			} else {
				console.error(xhr.status + ': ' + xhr.responseText);
			}
		}
	}
	xhr.send(JSON.stringify(visit));
	showVisits();
}

}	
