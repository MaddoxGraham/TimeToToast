import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: '/landing', pathMatch: 'full' },
  { path: 'landing', loadChildren: () =>import('./landing-page/landing-page.module').then(m => m.LandingPageModule) },
  { path: 'user', loadChildren: () =>import('./user/user.module').then(m => m.UserModule) },
  { path: 'event', loadChildren: () =>import('./event/event.module').then(m => m.EventModule) },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
