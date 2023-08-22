import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { LoginDto } from 'src/app/share/dtos/login/login-dto';
import { UserDto } from 'src/app/share/dtos/user/user-dto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private httpClient: HttpClient) { }

  checkIfLoggedIn(): boolean {
    const user = window.localStorage.getItem('auth_token');
    return !!user;
  }

  getAuthToken(): string | null {
    return window.localStorage.getItem("auth_token");
  }

  isLoggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(this.checkIfLoggedIn());

  setAuthToken(token: string | null, refreshToken: string | null): void {
    if (token !== null && refreshToken !==null) {
      window.localStorage.setItem("auth_token", token);
      window.localStorage.setItem('refresh_token', refreshToken);
    } else {
      window.localStorage.removeItem("auth_token");
    }
  }

  authenticateUser(data: { login: string, password: string }): Observable<UserDto> {
    return this.httpClient.post<UserDto>(environment.userConnexion, data);
  }
}
