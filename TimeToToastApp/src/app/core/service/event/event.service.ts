import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EmailDataDto } from 'src/app/share/dtos/EmailData/EmailDataDto';
import { EventDto } from 'src/app/share/dtos/event/event-dto';
import { GuestDto } from 'src/app/share/dtos/guest/guest-dto';
import { TaskDto } from 'src/app/share/dtos/task/task-dto';
import { UserDto } from 'src/app/share/dtos/user/user-dto';
import { UserEventRoleDto } from 'src/app/share/dtos/userEventRole/user-event-role-dto';
import { UserEventsDto } from 'src/app/share/dtos/userEvents/user-events-dto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private httpClient: HttpClient) { }


    addEvent(data: { adresse: string, categorie:string, description:string, eventDate:Date, title:string, cp:string, ville:Date,createdAt:Date, startTime:string}): Observable<EventDto> {
      return this.httpClient.post<EventDto>(environment.addevent, data);
    }

    getEventById(id: number): Observable<EventDto> {
      const url = `${environment.getEvent}/${id}`; 
      return this.httpClient.get<EventDto>(url);
    }

    addUserEventRole(data: { idEvent: number, idPerson: number, role: string }): Observable<UserEventRoleDto> {
      return this.httpClient.post<UserEventRoleDto>(environment.addUserEventRole, data);
    }

    getUserRolesByUserId(userId: number): Observable<UserEventsDto[]> {
      return this.httpClient.get<UserEventsDto[]>(`${environment.getUserEvents}/${userId}`);
    } 

    getUserEventRole(idUser: number, idEvent: number): Observable<UserEventRoleDto> {
      return this.httpClient.get<UserEventRoleDto>(`${environment.getUserEventRole}/${idUser}/${idEvent}`);
    }

    getUserEventRoleList(idEvent:number): Observable<UserEventRoleDto[]>{
      return this.httpClient.get<UserEventRoleDto[]>(`${environment.getUserEventRoleList}/${idEvent}`);
    }

    addGuest(emailData: EmailDataDto): Observable<GuestDto> {
      return this.httpClient.post<any>(`${environment.sendingEmail}`, emailData);
    }

    eventGuest(idEvent:number):Observable<GuestDto[]>{
      return this.httpClient.get<GuestDto[]>(`${environment.getEventGuests}/${idEvent}`);
    }

    updateModuleEvent(idEvent: number, moduleName: string): Observable<EventDto> {
      return this.httpClient.put<EventDto>(`${environment.updateModule}${idEvent}/${moduleName}`, {});
    }

    addTask(data: { description: String, dueDate : Date, urgence : number , event : number , creator : number , assignees : UserDto[] , invisibles : UserDto[] }):Observable<TaskDto>{
      return this.httpClient.post<TaskDto>(environment.addTask, data);
    }

}
