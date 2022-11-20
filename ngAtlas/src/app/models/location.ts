export class Location {
  id: number;
  country: string | undefined;
  city: string | undefined;
  image: string | undefined;
  latitude: number | undefined;
  longitude: number | undefined;

  constructor(
    id: number = 0,
    country: string = '',
    city?: string,
    image?: string,
    latitude?: number,
    longitude?: number
  ) {
    this.id = id;
    this.country = country;
    this.city = city;
    this.image = image;
    this.latitude = latitude;
    this.longitude = longitude;
  }
}
