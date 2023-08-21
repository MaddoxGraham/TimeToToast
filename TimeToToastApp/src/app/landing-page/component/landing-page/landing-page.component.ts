import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})
export class LandingPageComponent implements OnInit {

  // public users!: User[];

  // constructor(private userService: UserService){}

   ngOnInit(){
  //   this.getUsers();
   }

  // public getUsers(): void {
  //        this.userService.getUsers().subscribe(
  //          (response: User[]) => {
  //            this.users = response;
  //          },
  //          (error: HttpErrorResponse) => {
  //            alert(error.message);
  //          }
  //       );
  //  }

}