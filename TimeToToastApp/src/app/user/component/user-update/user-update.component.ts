import { Component, OnInit } from '@angular/core';
import { UserDto } from 'src/app/share/dtos/user/user-dto';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from 'src/app/core/service/authentication/authentication.service';
import { UserService } from 'src/app/core/service/user/user.service';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css'],
  providers: [MessageService]

})
export class UserUpdateComponent implements OnInit {
  user!: UserDto;
  updatedUser!: UserDto;
  showPasswordFields = false;
  passwordFieldType = 'password';
  confirmPasswordFieldType = 'password';
  updateForm!: FormGroup;
  maxFileSize: number =1000000;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private messageService: MessageService
  ) {}

  togglePasswordFields() {
    this.showPasswordFields = !this.showPasswordFields;
  }

  togglePasswordVisibility() {
    this.passwordFieldType = this.passwordFieldType === 'password' ? 'text' : 'password';
  }

  toggleConfirmPasswordVisibility() {
    this.confirmPasswordFieldType = this.confirmPasswordFieldType === 'password' ? 'text' : 'password';
  }

  ngOnInit(): void {
    const userString = sessionStorage.getItem('user');
    if (userString) {
      this.user = JSON.parse(userString) as UserDto;
    }

    this.updateForm = this.formBuilder.group({
      firstName: [this.user.firstName || '', [Validators.required, Validators.pattern("^[\p{L}' -]+$")]],
      lastName: [this.user.lastName || '', [Validators.required, Validators.pattern("^[\p{L}' -]+$")]],
      email: [this.user.email || '', [Validators.required, Validators.pattern("/^[^\s@]+@[^\s@]+\.[^\s@]+$/")]],
      phone: [this.user.phone || '', [Validators.required, Validators.pattern("/^\+?[1-9]\d{1,14}$/")]],
      login: [this.user.login || '', [Validators.required, Validators.pattern("/^[a-zA-Z0-9]+$/")]],
      adresse: [this.user.adresse || '', [Validators.required, Validators.pattern("/^[a-zA-Z0-9 .,-]+$/")]],
      ville: [this.user.ville || '', [Validators.required, Validators.pattern("^[\p{L}' -]+$")]],
      cp: [this.user.cp || '', [Validators.required, Validators.pattern("/^\d{5}$/")]],
      birthday: [this.user.birthday || ''],
      password: [this.user.password || '', Validators.pattern("/^\d{5}$/")],
      confirmPassword: [this.user.password || '', Validators.pattern("/^\d{5}$/")]
      // Ajouter un contrôle pour le mot de passe de confirmation si nécessaire
    });
  }

  onSubmit(updateForm: FormGroup): void {
    if(updateForm.valid) {
      this.updatedUser = this.updateForm.value
      console.log(this.updatedUser)
      console.log(this.user.idPerson)
      if (this.user.idPerson) {
        this.userService.updateUser(this.user.idPerson, this.updatedUser).subscribe(
            success => {
              sessionStorage.setItem('user', JSON.stringify(success));
            },
            error => {
                console.log("Erreur lors de la mise à jour", error);
            }
        );
      }
    }
 }

}
