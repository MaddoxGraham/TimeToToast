import { Component } from '@angular/core';
import { AxiosService } from '../Controller/axios.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  componentToShow: String = "welcome";

  constructor(private axiosService: AxiosService) { }

  showComponent(componentToShow:string): void {
    this.componentToShow = componentToShow;
  }

  onLogin(input: any): void {
    this.axiosService.request(
      "POST",
      "/login",
      {
        login: input.login,
        password: input.password
      }
    ).then(response => {
      this.axiosService.setAuthToken(response.data.token);
      this.componentToShow="messages";
     });
  }

  onRegister(input: any): void {
    this.axiosService.request(
      "POST",
      "/register",
      {
        name: input.name,
        login: input.login,
        password: input.password
      }
      ).then(response => {
        this.axiosService.setAuthToken(response.data.token);
        this.componentToShow="messages";
       });
  }

}
