import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/core/service/authentication/authentication.service';
import { UserService } from 'src/app/core/service/user/user.service';
import { GuestDto } from 'src/app/share/dtos/guest/guest-dto';
import { UserDto } from 'src/app/share/dtos/user/user-dto';

@Component({
    selector: 'app-login-form',
    templateUrl: './login-form.component.html',
    styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit{

    active: string = "login";
    loginForm!: FormGroup;
    registerForm!: FormGroup;
    guest!: GuestDto;

    constructor(private formBuilder: FormBuilder,
                private authService: AuthenticationService,
                private userService: UserService,
                private router: Router,) {
      this.guest = JSON.parse(sessionStorage.getItem("user") || `{}`);
    }
  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      login: ['', Validators.required],
      password: ['', Validators.required]
    });

    this.registerForm = this.formBuilder.group({
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        email: ['', Validators.required],
        phone:['',Validators.required],
        login: ['', Validators.required],
        adresse: ['', Validators.required],
        ville: ['', Validators.required],
        cp: ['', Validators.required],
        birthday: ['', Validators.required],
        password: ['', Validators.required]
    });
  }

    onLoginTab(): void {
        this.active = "login";
    }

    onRegisterTab(): void {
        this.active = "register";
    }

    onSubmit(form: FormGroup): void {
      console.log(this.guest.role == 'GUEST')
      if(this.guest.role == 'GUEST'){
        this.userService.deleteGuest(this.guest.idGuest).subscribe();
      }
      if(this.authService.getAuthToken() !== null) {
        window.localStorage.removeItem("auth_token");
      }
      if(form.valid){
        if(form === this.loginForm){
          this.authService.authenticateUser({
            login: form.value.login.toLowerCase(),
            password: form.value.password
          }).subscribe(resp => {
            this.handleLoginResponse(resp);
          })
        } else if(form === this.registerForm) {
          this.userService.addUser({
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
    }

    handleLoginResponse(response: UserDto) {
      this.authService.setAuthToken(response.token, response.refreshToken);
      sessionStorage.setItem("user", JSON.stringify(response));
      this.authService.isLoggedIn.next(true);
      if(response.role)
        if(response.role ==="ADMIN"){
          this.router.navigate(['/admin']);
        } else if(response.role === "USER"){
          this.router.navigate(['/user/user']);
        }
    }

}
