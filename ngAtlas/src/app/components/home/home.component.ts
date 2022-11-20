import { APP_BOOTSTRAP_LISTENER, Component, OnInit } from '@angular/core';
import { LocationService } from 'src/app/services/location.service';
import { Location } from 'src/app/models/location';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  title = 'ReCollection';

  newLocation: Location = new Location();
  editLocation: null | Location = null;
  selected: null | Location = null;
  locations: Location[] = [];
  closeResult: string = "";


  constructor(private locationService: LocationService, private modalService: NgbModal, private config: NgbCarouselConfig) {
    config.interval = 8000;
		config.wrap = false;
		config.keyboard = false;
		config.pauseOnHover = false;
  }

  ngOnInit(): void {
    this.loadLocations();
  }



  open(content: any) {
		this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then(
			(result) => {
				this.closeResult = `Closed with: ${result}`;
			},
			(reason) => {
				this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
			},
		);
	}


	private getDismissReason(reason: any): string {
		if (reason === ModalDismissReasons.ESC) {
			return 'by pressing ESC';
		} else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
			return 'by clicking on a backdrop';
		} else {
			return `with: ${reason}`;
		}
	}


  loadLocations() {
    this.locationService.index().subscribe({
      next: (locations) => {
        console.log(locations);
        this.locations = locations;
      },
      error: (err) => {
        console.error(
          'HomeComponent.loadLocations(): error loading locations.'
        );
        console.error(err);
      },
    });
  }


addLocation(){
  this.locationService.create(this.newLocation).subscribe({
    next: (result) => {
      this.loadLocations();
      this.newLocation = new Location();
    },
    error: (err) => {
      console.error('LocationComponenet.addLocation(): error');
      console.error(err);
    },
  });
}

cancelLocation() {
  this.editLocation = null;
}

updateLocation(location: Location, goToDetail = true) {
  this.locationService.update(location).subscribe({
    next: (result) => {
      if (goToDetail) {
        this.selected = result;
      } else {
        this.selected = null;
      }
      this.editLocation = null;
      this.loadLocations();
    },
    error: (err) => {
      console.error('LocationComponenet.updateLocation(): error');
      console.error(err);
    },
  });
}

deleteLocation(id: number): void {
  this.locationService.destroy(id).subscribe({
    next: (result) => {
      this.newLocation = new Location();
      this.loadLocations();
    },
    error: (err) => {
      console.error('LocationComponenet.deleteLocation(): error');
      console.error(err);
    },
  });
}



}


