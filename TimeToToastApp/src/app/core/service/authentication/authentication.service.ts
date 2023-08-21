import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

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
}
