import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LandingPageComponent } from './View/landing-page/landing-page.component';
import { HttpClientModule } from '@angular/common/http';
import { AuthContentComponent } from './auth-content/auth-content.component';

@NgModule({
  declarations: [
    AppComponent,
    LandingPageComponent,
    AuthContentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
