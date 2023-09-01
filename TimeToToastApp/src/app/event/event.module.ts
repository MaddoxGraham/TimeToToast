import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EventCreationComponent } from './component/event-creation/event-creation.component';
import { EventUserComponent } from './component/event-user/event-user.component';
import { SingleEventComponent } from './component/single-event/single-event.component';
import { ShareModule } from '../share/share.module';
import { EventRoutingModule } from './event-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; // Ajouté
import { DropdownModule } from 'primeng/dropdown'; // Ajouté
import { MapComponent } from './component/map/map.component';
import { TaskComponent } from './component/task/task.component';



@NgModule({
  declarations: [
    EventCreationComponent,
    EventUserComponent,
    SingleEventComponent,
    MapComponent,
    TaskComponent
  ],
  imports: [
    CommonModule,
    ShareModule,
    EventRoutingModule,
    DropdownModule, // Ajouté
    FormsModule,
    ReactiveFormsModule
  ]
})
export class EventModule { }
