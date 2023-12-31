import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GuestDto } from 'src/app/share/dtos/guest/guest-dto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GuestService {
  constructor(private httpClient: HttpClient) { }

  verifyGuest(token: string): Observable<GuestDto> {
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.httpClient.post<GuestDto>(environment.verifyGuest, token, { headers: headers });
  }

  addDetailsToGuest({lastName, firstName}: {lastName: string, firstName: string}, idPerson: number): Observable<GuestDto> {
    const data = {lastName, firstName};
    return this.httpClient.put<GuestDto>(`${environment.addDetailsToGuest}${idPerson}`, data)
  }

  deleteGuest(idPerson: number, idEvent: number): Observable<any> {
    return this.httpClient.delete(`${environment.deleteGuest}${idPerson}/${idEvent}`);
  }
}
