import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './component/header/header.component';
import { httpInterceptorProviders } from './interceptor';
import { HttpClientModule } from '@angular/common/http';
import { ShareModule } from '../share/share.module';
import { FooterComponent } from './component/footer/footer.component';
import { LandingPageModule } from '../landing-page/landing-page.module';



@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    ShareModule,
    LandingPageModule
  ],
  providers: [
    httpInterceptorProviders
  ],
  exports: [
    HeaderComponent,
    FooterComponent
  ]
})
export class CoreModule { }
