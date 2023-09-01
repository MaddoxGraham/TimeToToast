import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GeocodingService {
  private nominatimUrl = 'https://nominatim.openstreetmap.org/search';

  constructor(private http: HttpClient) { }

  getCoordinatesByAddress(address: string): Observable<any> {
    const params = new HttpParams()
      .set('q', address)
      .set('format', 'json')
      .set('limit', '1');

    return this.http.get(this.nominatimUrl, { params });
  }
}
