import { Component, OnInit } from '@angular/core';
import { LocationService } from 'src/app/services/location.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {

  locations: Location[] = [];

  constructor(private locService: LocationService) {}

  ngOnInit(): void {
    this.loadLocations();
  }

  loadLocations() {
    this.locService.index().subscribe({
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
}
