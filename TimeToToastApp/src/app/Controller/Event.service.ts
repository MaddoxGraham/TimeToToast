import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient } from '@angular/common/http';
import { Event } from "../Models/Event";

@Injectable({
    providedIn:'root'
})
export class EventService{
    private apiServerUrl='http://localhost:8080';

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