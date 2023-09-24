import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LandingPageRoutingModule } from './landing-page-routing.module';
import { ShareModule } from '../share/share.module';
import { LandingPageComponent } from './component/landing-page/landing-page.component';
import { LoginFormComponent } from './component/login-form/login-form.component';
import { AboutUsComponent } from './component/about-us/about-us.component';
import { GuestToUserFormComponent } from './component/guest-to-user-form/guest-to-user-form.component';




@NgModule({
  declarations: [
    LandingPageComponent,
    LoginFormComponent,
    AboutUsComponent,
    GuestToUserFormComponent,

  ],
  imports: [
    LandingPageRoutingModule,
    CommonModule,
    ShareModule
  ],
  exports:[
    LoginFormComponent
  ]
})
export class LandingPageModule { }
