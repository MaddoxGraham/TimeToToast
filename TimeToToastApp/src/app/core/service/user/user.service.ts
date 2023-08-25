import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserDto } from 'src/app/share/dtos/user/user-dto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  
  constructor(private httpClient: HttpClient) { }

  addUser(data: { login: string, firstName:string, lastName:string, email:string, ville:string, cp:string, birthday:Date,phone:string, adresse:string,  password: string }): Observable<UserDto> {
    return this.httpClient.post<UserDto>(environment.addUser, data);
  }

  getUserByIdUser(id: number): Observable<UserDto> {
    const url = `${environment.getUser}/${id}`; 
    return this.httpClient.get<UserDto>(url);
  }

  updateUser(id: string, user: any): Observable<any> {
    return this.httpClient.put(`${environment.updateUser}/${id}`, user);
  }
}
