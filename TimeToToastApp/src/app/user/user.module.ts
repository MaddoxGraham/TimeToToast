import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserProfileComponent } from './component/user-profile/user-profile.component';
import { AdminComponent } from './component/admin/admin.component';
import { ShareModule } from '../share/share.module';
import { UserRoutingModule } from './user-routing.module';
import { UserUpdateComponent } from './component/user-update/user-update.component';



@NgModule({
  declarations: [
    UserProfileComponent,
    AdminComponent,
    UserUpdateComponent
  ],
  imports: [
    UserRoutingModule,
    CommonModule,
    ShareModule,
  ],
})
export class UserModule { }
