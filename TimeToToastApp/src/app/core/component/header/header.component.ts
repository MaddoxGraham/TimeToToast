import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../service/authentication/authentication.service';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy{
  connected: boolean = false
  activeItem: any | undefined;
  items!: any[];
  private subscription!: Subscription


  constructor(private router: Router,
              protected authService: AuthenticationService) { }

  ngOnInit(): void {
    this.subscription = this.authService.isLoggedIn.subscribe(isLoggedin => {
      this.connected = isLoggedin;
      this.items = this.Routes;
      this.activeItem = this.items[0];
    })
  }

  ngOnDestroy(): void {
    console.log("isLoggedIn unsubscription triggered");
    this.subscription.unsubscribe();
  }
  
  private Routes = [
    { label: ' Mon profil ', routerLink: 'user/user' },
    { label: 'Mes Evenements', routerLink: 'event/myEvent' },
    { label: 'Créer un Evenement', routerLink: 'event/creer' },
    { label: 'Se déconnecter', command: (event: any) => this.logout() }
  ];

  logout() {
    sessionStorage.clear();
    window.localStorage.clear();
    this.authService.isLoggedIn.next(false);
    this.router.navigateByUrl('/landing');
  }

}
