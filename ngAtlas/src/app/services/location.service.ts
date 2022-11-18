import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  baseUrl = 'http://localhost:8084/';
  url = this.baseUrl + 'api/locations';

  constructor(private http: HttpClient) { }

  index(): Observable<Location[]>{
    return this.http.get<Location[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('LocationService.index(): error retrieving locations: ' + err)
        );
      })
    );
  }
}
