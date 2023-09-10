import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { from } from 'rxjs';
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
              private fb: FormBuilder) {}


  ngOnInit(): void {
    this.verifyGuest();
    this.initForm();
  }

  verifyGuest() {
    this.token = this.route.snapshot.paramMap.get('token');
    if(this.token) {
      this.guestService.verifyGuest(this.token).subscribe((response: GuestDto) => {
        this.guest = response;
        if(this.guest.firstName == null && this.guest.lastName == null) {
          this.state = 'form';
        } else {
          this.state = 'text'
        }
      })
    }
  }

  initForm() {
    this.userForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.userForm.valid) {
      
    }
  }


}
