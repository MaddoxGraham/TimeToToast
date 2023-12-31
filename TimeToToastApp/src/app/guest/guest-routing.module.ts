import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NewGuestComponent } from './component/new-guest/new-guest.component';
import { RoleGuard } from '../core/guard/role.guard';




const routes: Routes = [
 
    { path: 'newGuest/:token', component: NewGuestComponent }
  ];
  
  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule,]
  })
  export class GuestRoutingModule { }

