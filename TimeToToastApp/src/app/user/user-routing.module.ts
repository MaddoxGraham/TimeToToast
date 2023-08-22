import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserProfileComponent } from './component/user-profile/user-profile.component';
import { AdminComponent } from './component/admin/admin.component';
import { RoleGuard } from '../core/guard/role.guard';


const routes: Routes = [
    { path: 'user', component: UserProfileComponent, canActivate: [RoleGuard], data: { expectedRole: 'USER' } },
    { path: 'admin', component: AdminComponent, canActivate: [RoleGuard], data: { expectedRole: 'ADMIN' } },
 
  ];
  
  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule,]
  })
  export class UserRoutingModule { }