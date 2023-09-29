import { Component, OnInit } from '@angular/core';
import { UserDto } from 'src/app/share/dtos/user/user-dto';
import { FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { UserService } from 'src/app/core/service/user/user.service';
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
  date = new Date();
  maxdate: any = `${this.date.getFullYear()}-${(this.date.getMonth() + 1).toString().padStart(2, '0')}-${this.date.getDate().toString().padStart(2, '0')}`;
  formSubmitted = false;

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
      this.user.password = '';
    }
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

    this.updateForm = this.formBuilder.group({
      firstName: [this.user.firstName || '', [Validators.required, Validators.pattern(namePattern)]],
      lastName: [this.user.lastName || '', [Validators.required, Validators.pattern(namePattern)]],
      login: [this.user.login || '', [Validators.required, Validators.pattern(loginPattern)]],
      birthday: [this.user.birthday || '', Validators.required],
      password: [this.user.password || '', Validators.pattern(passwordPattern)],
      confirmPassword: [this.user.password || '', Validators.pattern(passwordPattern)],
      email: [this.user.email || '', [Validators.required, Validators.pattern(emailPattern)]],
      phone: [this.user.phone || '', [Validators.required, Validators.pattern(phonePattern)]],
      adresse: [this.user.adresse || '', [Validators.required, Validators.pattern(adressPattern)]],
      ville: [this.user.ville || '', [Validators.required, Validators.pattern(namePattern)]],
      cp: [this.user.cp || '', [Validators.required, Validators.pattern(cpPattern)]],
      // Ajouter un contrôle pour le mot de passe de confirmation si nécessaire
    });
  }

  onSubmit(updateForm: FormGroup): void {

    Object.keys(this.updateForm.controls).forEach(key => {
      const controlErrors: ValidationErrors | null | undefined = this.updateForm.get(key)?.errors;
      if (controlErrors != null) {
        console.log('Nom du champ avec erreur:', key);
        console.log('Liste des erreurs:', controlErrors);
      }
    });
    this.formSubmitted = true;
    if(updateForm.valid) {
      if(updateForm.get('password')?.value === updateForm.get('confirmPassword')?.value || (updateForm.get('password')?.value === '' && updateForm.get('confirmPassword')?.value === '')){
        this.updatedUser = this.updateForm.value
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
      } else {
        this.messageService.add({severity:'error', summary: 'Erreur', detail: 'Les mots de passes ne sont pas identiques.'});
      }
    } else {
      this.messageService.add({severity:'error', summary: 'Erreur', detail: 'Veuillez corriger les erreurs dans le formulaire.'});
    }
 }

}
