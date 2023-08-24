import { Component, OnInit } from '@angular/core';
import { UserDto } from 'src/app/share/dtos/user/user-dto';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit{

  user!: UserDto;

  showLogin = false;
  constructor() { }

  

  ngOnInit(): void {
    const userString = sessionStorage.getItem("user");
    if (userString) {
      this.user = JSON.parse(userString) as UserDto;
    }
  }

  toggleDisplayName(event: Event): void {
    event.preventDefault();
    this.showLogin = !this.showLogin;
}

}