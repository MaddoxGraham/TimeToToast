import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/core/service/authentication/authentication.service';
import { UserService } from 'src/app/core/service/user/user.service';
import { GuestDto } from 'src/app/share/dtos/guest/guest-dto';
import { UserDto } from 'src/app/share/dtos/user/user-dto';

@Component({
  selector: 'app-guest-to-user-form',
  templateUrl: './guest-to-user-form.component.html',
  styleUrls: ['./guest-to-user-form.component.css']
})
export class GuestToUserFormComponent implements OnInit{

  registerForm!: FormGroup;
  guest!: GuestDto;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthenticationService,
              private userService: UserService,
              private router: Router,) {
    this.guest = JSON.parse(sessionStorage.getItem("user") || `{}`);
    console.log(this.guest)
  }
ngOnInit(): void {
  this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: [this.guest.email, Validators.required],
      phone:['',Validators.required],
      login: ['', Validators.required],
      adresse: ['', Validators.required],
      ville: ['', Validators.required],
      cp: ['', Validators.required],
      birthday: ['', Validators.required],
      password: ['', Validators.required]
  });
}

  onSubmit(form: FormGroup): void {
    if(form === this.registerForm && form.valid) {
      this.userService.fromGuestToUser(this.guest.idPerson, {
        firstName: form.value.firstName.toLowerCase(),
        lastName: form.value.lastName.toLowerCase(),

        email: form.value.email,
        phone:form.value.phone,
        adresse: form.value.adresse,
        ville: form.value.ville,
        cp: form.value.cp,
        birthday: form.value.birthday,

        login: form.value.login,
        password: form.value.password
      }).subscribe(resp => {
        this.handleLoginResponse(resp);
      })
      
    }
  }

  handleLoginResponse(response: UserDto) {
    window.localStorage.removeItem("auth_token");
    this.authService.setAuthToken(response.token, response.refreshToken);
    sessionStorage.setItem("user", JSON.stringify(response));
    this.authService.isLoggedIn.next(true);
    this.router.navigate(['/user/user']);
  }

}
