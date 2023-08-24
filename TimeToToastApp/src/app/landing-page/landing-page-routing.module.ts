import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from './component/landing-page/landing-page.component';
import { LoginFormComponent } from './component/login-form/login-form.component';
import { AboutUsComponent } from './component/about-us/about-us.component';
import { RoleGuard } from '../core/guard/role.guard';


const routes: Routes = [
  { path: '', component: LandingPageComponent }, // pour l'URL de base
  { path: 'login', component: LoginFormComponent },
  { path: 'about', component:AboutUsComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule,]
})
export class LandingPageRoutingModule { }