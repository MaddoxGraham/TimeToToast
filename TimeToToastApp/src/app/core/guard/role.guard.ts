import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { UserDto } from 'src/app/share/dtos/user/user-dto';


@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
      const userData = sessionStorage.getItem('user');
      if (userData) {
        const user = JSON.parse(userData) as UserDto;
        if (route.data['expectedRole'] === user.role) {
          return true;
        }
      }
      this.router.navigate(['']);
      return false;
  }
};
