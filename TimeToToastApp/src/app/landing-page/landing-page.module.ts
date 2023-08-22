import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LandingPageRoutingModule } from './landing-page-routing.module';
import { ShareModule } from '../share/share.module';
import { LandingPageComponent } from './component/landing-page/landing-page.component';
import { LoginFormComponent } from './component/login-form/login-form.component';



@NgModule({
  declarations: [
    LandingPageComponent,
    LoginFormComponent
  ],
  imports: [
    LandingPageRoutingModule,
    CommonModule,
    ShareModule
  ]
})
export class LandingPageModule { }
