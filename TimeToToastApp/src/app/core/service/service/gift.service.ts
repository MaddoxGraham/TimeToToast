import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GiftDto } from 'src/app/share/dtos/gift/gift-dto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GiftService {

  constructor(private http: HttpClient) { }

  getGifts(  idEvent: number ): Observable<GiftDto[]> {
    return this.http.get<GiftDto[]>(`${environment.getGifts}/${idEvent}`);
  }
}
