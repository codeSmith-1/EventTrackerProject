import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Location } from 'src/app/models/location';

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

  show(id: number): Observable<Location>{
    return this.http.get<Location>(`${this.url}/${id}`).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('LocationService.show(): error retrieving location: ' + err)
        );
      })
    );
  }

  create(location: Location){
    return this.http.post<Location>(this.url, location).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError(
           () => new Error( 'LocationService.create(): error creating Location: ' + err )
        );
      })
    );
  }

  update(location: Location){
    return this.http.put<Location>(this.url+ "/"+location.id, location).pipe(
      catchError((err: any)=> {
        console.error(err);
        return throwError(
          () => new Error('LocationService.update(): error updating Location: ' + err)
        );
      })
    );
  }

  destroy(id: number): Observable<void>{
    return this.http.delete<void>(this.url+"/"+id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('LocationService.destroy(): error deleting location '+ err)
        );
      })
    );
  }








}

