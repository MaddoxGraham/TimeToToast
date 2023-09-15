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
  activeItem: any | undefined;
  connected: boolean = false
  items!: any[];
  private subscription!: Subscription
  idEvent!: number;

  constructor(private router: Router,
              protected authService: AuthenticationService) {}

  ngOnInit(): void {     
    console.log(localStorage);
    this.subscription = this.authService.isLoggedIn.subscribe(isLoggedin => {

      this.items = this.getRoutesBasedOnRole();
      this.connected = isLoggedin;
      this.activeItem = this.items[0]; 
      if (!this.activeItem) {
        localStorage.clear();
      }
           
    })

    
  }

  get isUser(): boolean {
    const user = JSON.parse(sessionStorage.getItem("user") || `{}`);
    return user.role === 'USER';
  }

  get isGuest(): Boolean {
    const user = JSON.parse(sessionStorage.getItem("user") || `{}`);
    this.idEvent = user.idEvent;
    return user.role === 'GUEST'
  }
  
  getRoutesBasedOnRole(): any[] {
    if (this.isUser) {
      return this.userRoutes;
    } else if (this.isGuest) {
      console.log(this.idEvent);
      return this.getGuestRoutes();
    } else {
      return [];
    }
  }
  
  

  private userRoutes = [
    { label: 'Mon profil ', routerLink: 'user/user' },
    { label: 'Mes Evenements', routerLink: 'event/myEvent' },
    { label: 'Créer un Evenement', routerLink: 'event/creer' },
    { label: 'Se déconnecter', command: (event: any) => this.logout() }
  ];
  
  private getGuestRoutes() {
    return [
      { label: 'S\'inscrire', routerLink: 'login' },
      { label: 'Mon Evenement', routerLink: ['/event/singleEvent', this.idEvent] },
      { label: 'Se déconnecter', command: (event: any) => this.logout() }
    ];
  }
  
  
  logout() {
    sessionStorage.clear();
    window.localStorage.clear();
    this.authService.isLoggedIn.next(false);
    this.router.navigateByUrl('/landing');
  }
  
  ngOnDestroy(): void {
    console.log("isLoggedIn unsubscription triggered");
    this.subscription.unsubscribe();
  }
}
