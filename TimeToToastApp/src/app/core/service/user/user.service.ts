import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

      // constructor(private http: HttpClient) { }
        
    // public getUsers(): Observable<User[]> {
    //     return this.http.get<User[]>(`${this.apiServerUrl}/user/all`);
    // }

    // public addUser(user: User): Observable<User> {
    //     return this.http.post<User>(`${this.apiServerUrl}/user/add`, user);
    // }

    // public udpateUser(user: User): Observable<User> {
    //     return this.http.put<User>(`${this.apiServerUrl}/user/update`, user);
    // }

    // public deleteUser(userId: number): Observable<void> {
    //     return this.http.delete<void>(`${this.apiServerUrl}/user/delete/${userId}`);
    // }
}
