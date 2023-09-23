import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from 'src/app/core/service/authentication/authentication.service';
import { GuestService } from 'src/app/core/service/guest/guest.service';
import { GuestDto } from 'src/app/share/dtos/guest/guest-dto';

@Component({
  selector: 'app-new-guest',
  templateUrl: './new-guest.component.html',
  styleUrls: ['./new-guest.component.css']
})
export class NewGuestComponent implements OnInit{
  token: string | null = null;
  guest!: GuestDto;
  userForm!: FormGroup;
  state: 'form' | 'text' | 'spinner' = 'spinner';
  

  constructor(private route: ActivatedRoute,
              private guestService: GuestService,
              private fb: FormBuilder,
              private authService: AuthenticationService,
              private router : Router) { }
              
              
  ngOnInit(): void {

    this.verifyGuest();
    this.initForm();
    const storedGuestInfo = localStorage.getItem('guestInfo');
    if (storedGuestInfo) {
      const guest: GuestDto = JSON.parse(storedGuestInfo);
    }
  }

  verifyGuest() {
    this.token = this.route.snapshot.paramMap.get('token');
    if(this.token) {
      this.guestService.verifyGuest(this.token).subscribe((response: GuestDto) => {
        this.guest = response;
        console.log (this.guest)
        sessionStorage.setItem('user',JSON.stringify(this.guest));
        window.localStorage.setItem("auth_token", this.guest.token);
        this.authService.isLoggedIn.next(true);
        if(this.guest.firstName == null && this.guest.lastName == null) {
          this.state = 'form';
        } else {
          this.state = 'text'
        }
      })
    }
  }
  storeGuestInfo(): void {
    if (this.guest) {
      localStorage.setItem('guestInfo', JSON.stringify(this.guest));
    }
  }

  initForm() {
    this.userForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
    });
  }

  

  onSubmit(): void {
    if (this.userForm.valid) {
      this.guestService.addDetailsToGuest(
        {
          lastName: this.userForm.value.lastName,
          firstName: this.userForm.value.firstName 
        }, this.guest.idPerson).subscribe((response: GuestDto) => {
          this.guest = response;
          console.log(this.guest)
          this.router.navigateByUrl(`/event/singleEvent/${this.guest.eventId}`)
          //localStorage.setItem('guestInfo', JSON.stringify(this.guest));
      })
    }
  }


}
