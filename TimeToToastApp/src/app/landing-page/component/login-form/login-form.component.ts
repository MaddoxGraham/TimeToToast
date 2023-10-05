import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { AuthenticationService } from 'src/app/core/service/authentication/authentication.service';
import { UserService } from 'src/app/core/service/user/user.service';
import { GuestDto } from 'src/app/share/dtos/guest/guest-dto';
import { UserDto } from 'src/app/share/dtos/user/user-dto';

@Component({
    selector: 'app-login-form',
    templateUrl: './login-form.component.html',
    styleUrls: ['./login-form.component.css'],
    providers: [MessageService]
})
export class LoginFormComponent implements OnInit{

    active: string = "login";
    loginForm!: FormGroup;
    registerForm!: FormGroup;
    guest!: GuestDto;
    date = new Date();
    maxdate: any = `${this.date.getFullYear()}-${(this.date.getMonth() + 1).toString().padStart(2, '0')}-${this.date.getDate().toString().padStart(2, '0')}`;
    formSubmitted = false;

    constructor(private formBuilder: FormBuilder,
                private authService: AuthenticationService,
                private userService: UserService,
                private router: Router,
                private messageService: MessageService) {
      this.guest = JSON.parse(sessionStorage.getItem("user") || `{}`);
    }
  ngOnInit(): void {
    this.formInit();
  }

  formInit(){
    let namePattern = /^[\p{L}' -]+$/u;
    let emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    let phonePattern = /^0\d{9}$/;
    let loginPattern = /^[a-zA-Z0-9]+$/;
    let adressPattern = /^[\p{L}0-9\s,'-]+$/u;
    let cpPattern = /^\d{5}$/;
    let passwordPattern = /^(?=.*\d).{5,}$/;

    this.loginForm = this.formBuilder.group({
      login: ['', [Validators.required, Validators.pattern(loginPattern)]],
      password: ['', [Validators.required, Validators.pattern(passwordPattern)]]
    });

    this.registerForm = this.formBuilder.group({
        firstName: ['', [Validators.required, Validators.pattern(namePattern)]],
        lastName: ['', [Validators.required, Validators.pattern(namePattern)]],
        email: ['', [Validators.required, Validators.pattern(emailPattern)]],
        phone:['',[Validators.required, Validators.pattern(phonePattern)]],
        login: ['', [Validators.required, Validators.pattern(loginPattern)]],
        adresse: ['', [Validators.required, Validators.pattern(adressPattern)]],
        ville: ['', [Validators.required, Validators.pattern(namePattern)]],
        cp: ['', [Validators.required, Validators.pattern(cpPattern)]],
        birthday: ['', Validators.required],
        password: ['', [Validators.required, Validators.pattern(passwordPattern)]],
        confirmPassword: ['', [Validators.required, Validators.pattern(passwordPattern)]]
    });
  }

    onLoginTab(): void {
        this.active = "login";
    }

    onRegisterTab(): void {
        this.active = "register";
    }

    onSubmit(form: FormGroup): void {
      this.formSubmitted = true;
      
      if(this.authService.getAuthToken() !== null) {
        window.localStorage.removeItem("auth_token");
      }
      if(form === this.loginForm){
          Object.keys(this.loginForm.controls).forEach(key => {
            const controlErrors: ValidationErrors | null | undefined = this.loginForm.get(key)?.errors;
            if (controlErrors != null) {
              let champ: string = '';
              switch(key){
                case 'login':
                  champ = 'login';
                  break;
                case 'password':
                  champ = 'mot de passe';
                  break;
              }
              this.messageService.add({severity:'warn', summary: 'Attention', detail: `Il semble y avoir un probléme avec le champ ${champ}`});
            }
          });
          if(this.loginForm.valid){
            this.authService.authenticateUser({
              login: form.value.login.toLowerCase(),
              password: form.value.password
            }).subscribe(resp => {
              this.handleLoginResponse(resp);
            })
          }

        } else if(form === this.registerForm) {
          Object.keys(this.registerForm.controls).forEach(key => {
            const controlErrors: ValidationErrors | null | undefined = this.registerForm.get(key)?.errors;
            if (controlErrors != null) {
              let champ: string = '';
              switch(key){
                case 'firstName':
                  champ = 'prénom';
                  break;
                case 'lastName':
                  champ = 'nom'
                  break;
                case 'login':
                  champ = 'login';
                  break;
                case 'birthday':
                  champ = 'date de naissance'
                  break;
                case 'password':
                  champ = 'mot de passe';
                  break;
                case 'email':
                  champ = 'e-mail'
                  break;
                case 'phone':
                  champ = 'téléphone';
                  break;
                case 'adresse':
                  champ = 'adresse'
                  break;
                case 'ville':
                  champ = 'ville';
                  break;
                case 'cp':
                  champ = 'code postal'
                  break;
              }
              this.messageService.add({severity:'warn', summary: 'Attention', detail: `Il semble y avoir un probléme avec le champ ${champ}`});
            }
          });
          if(this.registerForm.valid){
            if(this.registerForm.get('password')?.value === this.registerForm.get('confirmPassword')?.value || (this.registerForm.get('password')?.value === '' && this.registerForm.get('confirmPassword')?.value === '')){
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
            } else {
              this.messageService.add({severity:'error', summary: 'Erreur', detail: 'Les mots de passes ne sont pas identiques.'});
            }
          }
        }
    }

    handleLoginResponse(response: UserDto) {
      this.authService.setAuthToken(response.token, response.refreshToken);
      sessionStorage.setItem("user", JSON.stringify(response));
      this.authService.isLoggedIn.next(true);
      if(response.role === "USER"){
          this.router.navigate(['/user/user']);
        }
    }

}
