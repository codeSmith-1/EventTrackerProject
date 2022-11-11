window.addEventListener('load', function(e){
	console.log('document loaded');
	init();
});

function init(){
	document.locationForm.lookup.addEventListener('click', function(event){
		event.preventDefault();
		let locationId = document.locationForm.locationId.value;
		console.log(locationId);
		if (!isNaN(locationId) && locationId > 0){
			getLocation(locationId);
		}
	});
	// add listener to create location
}

function getLocation(locationId){
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/locations/' + locationId);
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			if(xhr.status === 200) {
				let location = JSON.parse(xhr.responseText);
				displayLocation(location);
			} else if (xhr.status === 404){
				displayError("Location not found.");
			} else {
				displayError("Error: " + xhr.status);
			}
		}
	}
	xhr.send();
};

function displayError(msg){
	letdataDiv = document.getElementById('locationData');
	letdataDiv.textContent = msg;
};

function displayLocation(location){
	let dataDiv = document.getElementById('locationData');
	let h1 = document.createElement('h1');
	let ul = document.createElement('ul');
	let li = document.createElement('li');
	
	h1.textContent = location.country;
	li.textContent = location.city;
	ul.appendChild(li);
	li = document.createElement('li');
	li.textContent = location.image;
	ul.appendChild(li);
	li = document.createElement('li');
	li.textContent = location.latitude; 
	ul.appendChild(li);
	
	bq.textContent = location.description;
	
	dataDiv.appendChild(h1);
	dataDiv.appendChild(ul);
};
