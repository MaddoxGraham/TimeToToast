import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GiftDto } from 'src/app/share/dtos/gift/gift-dto';
import { GiftMessageDto } from 'src/app/share/dtos/gift/gift-message-dto';
import { GiftContributionDto } from 'src/app/share/dtos/giftContribution/gift-contribution-dto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GiftService {

  constructor(private http: HttpClient) { }

  getGifts( idEvent: number ): Observable<GiftDto[]> {
    return this.http.get<GiftDto[]>(`${environment.getGifts}/${idEvent}`);
  }

  getContributions(idGift:number): Observable<GiftContributionDto[]>{
    return this.http.get<GiftContributionDto[]>(`${environment.getContributions}/${idGift}`);
  }

  addContribution(data: GiftContributionDto ): Observable<GiftContributionDto> {
    return this.http.post<GiftContributionDto>(`${environment.addContribution}` , data);
  }

  giftIsPaid(gift:GiftDto ): Observable<GiftDto> {
    return this.http.put<GiftDto>(`${environment.giftIsPaid}` , gift)
  }

  addGift(data: {name:string, url:string, photo:string, wanted:number, price:number, categorie:string, event:number }): Observable<GiftDto> {
    return this.http.post<GiftDto>(environment.addGift, data);
  }

  deleteGift(id: number): Observable<GiftMessageDto> {
    return this.http.delete<GiftMessageDto>(`${environment.deleteGift}${id}`);
  }

}
