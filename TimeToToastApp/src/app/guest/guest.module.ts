import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NewGuestComponent } from './component/new-guest/new-guest.component';
import { ShareModule } from '../share/share.module';
import { GuestRoutingModule } from './guest-routing.module';


@NgModule({
  declarations: [
    NewGuestComponent,
  ],
  imports: [
    CommonModule,
    ShareModule,
    GuestRoutingModule
    
  ]
})
export class GuestModule { }
