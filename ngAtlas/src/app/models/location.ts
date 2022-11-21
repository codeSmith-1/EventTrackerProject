export class Location {
  id: number;
  country: string | undefined;
  city: string | undefined;
  image: string | undefined;
  latitude: number | undefined;
  longitude: number | undefined;
  arrivalDate: Date | undefined;
  departureDate: Date | undefined;
  note: string | undefined;

  constructor(
    id: number = 0,
    country: string = '',
    city?: string,
    image?: string,
    latitude?: number,
    longitude?: number,
    arrivalDate?: Date,
    departureDate?: Date,
    note: string = ''
  ) {
    this.id = id;
    this.country = country;
    this.city = city;
    this.image = image;
    this.latitude = latitude;
    this.longitude = longitude;
    this.arrivalDate = arrivalDate;
    this.departureDate = departureDate;
  }
}
