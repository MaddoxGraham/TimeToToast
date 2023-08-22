import { Component, OnDestroy, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { AuthenticationService } from '../../service/authentication/authentication.service';
import { Subscription } from 'rxjs';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy{

  activeItem: any | undefined;
  items!: any[];
  private subscription!: Subscription
  showMenu: boolean = false;

  constructor(private router: Router,
              protected authService: AuthenticationService) {
    this.router.events.subscribe(event => {
      if(event instanceof NavigationEnd) {
        this.showMenu = event.url !== '/';
      }
    });
  }

  ngOnInit(): void {
    this.subscription = this.authService.isLoggedIn.subscribe(isLoggedin => {
      console.log("isLoggedIn subscription triggered", isLoggedin);
      this.items = this.Routes;
      this.activeItem = this.items[0];
    })
  }

  ngOnDestroy(): void {
    console.log("isLoggedIn unsubscription triggered");
    this.subscription.unsubscribe();
  }
  
  private Routes = [
    { label: 'Se connecter', routerLink: '/login' },
    { label: ' Mon profil ', routerLink: 'user/userProfile' },
    { label: 'Mes Evenements', routerLink: 'event/userEvent' },
    { label: 'Créer un Evenement', routerLink: 'event/formEvent' },
    { label: 'Se déconnecter', command: (event: any) => this.logout() }
  ];

  logout() {
    sessionStorage.clear();
//    this.router.navigateByUrl('');
    this.showMenu = false
  }

}
