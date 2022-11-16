window.addEventListener('load', function(e) {
	init();
});

function init() {
	getLocations();
	countLocations();
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
				countLocations(locations);
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

function countLocations(locations) {
	let countDiv = document.getElementById('countDiv');
	let p = document.createElement('p');
	console.log('locations ' + locations);
	p.textContent = "Total locations visited: " + locations.length;
	countDiv.appendChild(p);
}

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
				showVisits(visits);
			} else if (xhr.status === 404) {
				displayError("Visits not found.");
			} else {
				displayError("Error: " + xhr.status);
			}
		}
	}
	xhr.send();
};


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
};

function showVisits(visits) {

	let countDiv = document.getElementById('countDiv');
	countDiv.style.display = 'none';
	let locationTable = document.getElementById('locationTable');
	locationTable.style.display = 'none';
	document.addLocation.style.display = 'none';
	document.editVisitForm.style.display = 'none';

	let cardDiv = document.getElementById('cardDiv');
	cardDiv.textContent = "";
	for (let visit of visits) {
		let card = document.createElement('div');
		card.class = "card";
		//	card.style= "width: 18rem;"

		let img = document.createElement('img');
		img.classList.add("card-img-top");
		img.classList.add("visitImg");
		console.log(visit);
		if (visit.photo === null || visit.photo === "") {
			visit['photo'] = "https://www.freeiconspng.com/thumbs/no-image-icon/no-image-icon-6.png";
		}
		img.src = visit.photo;

		card.appendChild(img);

		let cardBody = document.createElement('div');
		cardBody.class = "card-body";

		let header = document.createElement('h5');
		header.class = "card-title";
		header.textContent = visit.note;

		let p = document.createElement('p');
		p.class = "card-text";
		p.textContent = "Arrival: " + visit.arrivalDate + ", Departure: " + visit.departureDate;

		cardBody.appendChild(header);
		cardBody.appendChild(p);
		card.appendChild(cardBody);

		// add a delete button and delete xhr

		let editButton = document.createElement('button');
		editButton.textContent = 'edit visit';
		card.appendChild(editButton);

		
		editButton.addEventListener('click', function(e) {
			let cardDiv = document.getElementById('cardDiv');
			cardDiv.style.display = 'none';
			editForm(visit);

		});

		let backButton = document.createElement('button');
		backButton.textContent = 'back to locations';
		card.appendChild(backButton);

		backButton.addEventListener('click', function(e) {
			e.preventDefault();
			cardDiv.style.display = 'none';
			document.addLocation.style.display = 'block';
			getLocations();
			window.location.reload();
		});



		cardDiv.appendChild(card);
	}
}

function editForm(visit) {
	document.editVisitForm.style.display = 'block';
	let countDiv = document.getElementById('countDiv');
	countDiv.style.display = 'none';
	document.addLocation.style.display = 'none';

	document.editVisitForm.note.value = visit.note;
	document.editVisitForm.photo.value = visit.photo;
	document.editVisitForm.arrivalDate.value = visit.arrivalDate;
	document.editVisitForm.departureDate.value = visit.departureDate;
	
	document.editVisitForm.submitEdit.addEventListener('click', function(e) {
		e.preventDefault();
		updatedVisit = {
			id: visit.id,
			note: document.editVisitForm.note.value,
			photo: document.editVisitForm.photo.value,
			arrivalDate: document.editVisitForm.arrivalDate.value,
			departureDate: document.editVisitForm.departureDate.value
		}
		putVisit(updatedVisit);
	}, {once:true});

	document.editVisitForm.deleteVisit.addEventListener('click', function(e) {
		e.preventDefault();
		deleteVisit(visit);
		window.location.reload();
	}, {once:true});
}

function deleteVisit(visit) {
	let xhr = new XMLHttpRequest();
	xhr.open('DELETE', "api/visits/" + visit.id);
	xhr.onreadystatechange = function() {
		if (xhr.status == 4) {
			if (xhr.status == 200 || xhr.status == 204) {
				let visit = JSON.parse(xhr.responseText);
				window.location.reload();
			} else {
				console.error(xhr.status + ': ' + xhr.responseText);
			}
		}
	}
	xhr.send();
}

function putVisit(visit) {
	let xhr = new XMLHttpRequest();
	xhr.open('PUT', "api/visits/" + visit.id)
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200 || xhr.status === 201) {
				let visit = JSON.parse(xhr.responseText);
				let cardDiv = document.getElementById('cardDiv');
				cardDiv.style.display = 'block';
				getVisits(visit.location['id']);
			} else {
				console.error(xhr.status + ': ' + xhr.responseText);
			}
		}
	}
	xhr.send(JSON.stringify(visit));
}

