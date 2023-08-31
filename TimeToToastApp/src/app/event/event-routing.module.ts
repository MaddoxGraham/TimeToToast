import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoleGuard } from '../core/guard/role.guard';
import { EventCreationComponent } from './component/event-creation/event-creation.component';
import { SingleEventComponent } from './component/single-event/single-event.component';
import { EventUserComponent } from './component/event-user/event-user.component';


const routes: Routes = [
    { path: 'creer', component: EventCreationComponent, canActivate: [RoleGuard], data: { expectedRole: 'USER' } },
    { path: 'myEvent', component: EventUserComponent, canActivate: [RoleGuard], data: { expectedRole: 'USER' } },
    { path: 'singleEvent/:idEvent', component: SingleEventComponent, canActivate: [RoleGuard], data: { expectedRole: 'USER' } },
 
  ];
  
  @NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule,]
  })
  export class EventRoutingModule { }