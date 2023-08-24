import { Component, OnInit } from '@angular/core';
import { UserDto } from 'src/app/share/dtos/user/user-dto';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit {

  user!: UserDto;
  showPasswordFields = false;
  passwordFieldType = 'password';
  confirmPasswordFieldType = 'password';

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
    const userString = sessionStorage.getItem("user");
    if (userString) {
      this.user = JSON.parse(userString) as UserDto;
    }
  }
}
