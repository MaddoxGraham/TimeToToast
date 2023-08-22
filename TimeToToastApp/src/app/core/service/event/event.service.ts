import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private httpClient: HttpClient) { }

  
    // constructor(private http: HttpClient) { }
        
    // public getEvents(): Observable<Event[]> {
    //     return this.http.get<Event[]>(`${this.apiServerUrl}/event/all`);
    // }

    // public addEvent(event: Event): Observable<Event> {
    //     return this.http.post<Event>(`${this.apiServerUrl}/event/add`, event);
    // }

    // public udpateEvent(event: Event): Observable<Event> {
    //     return this.http.put<Event>(`${this.apiServerUrl}/event/update`, event);
    // }

    // public deleteEvent(eventId: number): Observable<void> {
    //     return this.http.delete<void>(`${this.apiServerUrl}/event/delete/${eventId}`);
    // }
}
