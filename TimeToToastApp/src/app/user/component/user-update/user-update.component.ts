import { Component, OnInit } from '@angular/core';
import { UserDto } from 'src/app/share/dtos/user/user-dto';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from 'src/app/core/service/authentication/authentication.service';
import { UserService } from 'src/app/core/service/user/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit {
  user!: UserDto;
  updatedUser!: UserDto;
  showPasswordFields = false;
  passwordFieldType = 'password';
  confirmPasswordFieldType = 'password';
  updateForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthenticationService,
    private userService: UserService,
    private router: Router
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
      firstName: [this.user.firstName || '', Validators.required],
      lastName: [this.user.lastName || '', Validators.required],
      email: [this.user.email || '', Validators.required],
      phone: [this.user.phone || '', Validators.required],
      login: [this.user.login || '', Validators.required],
      adresse: [this.user.adresse || '', Validators.required],
      ville: [this.user.ville || '', Validators.required],
      cp: [this.user.cp || '', Validators.required],
      birthday: [this.user.birthday || ''],
      password: [this.user.password || '']
      // Ajouter un contrôle pour le mot de passe de confirmation si nécessaire
    });
  }

  onSubmit(updateForm: FormGroup): void {
    if(updateForm.valid) {
      this.updatedUser = this.updateForm.value
      console.log(this.updatedUser)
      console.log(this.user.idUser)
      if (this.user.idUser) {
        this.userService.updateUser(this.user.idUser, this.updatedUser).subscribe(
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
