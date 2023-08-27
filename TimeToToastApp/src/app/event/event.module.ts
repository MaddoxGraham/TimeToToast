import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EventCreationComponent } from './component/event-creation/event-creation.component';
import { EventUserComponent } from './component/event-user/event-user.component';
import { SingleEventComponent } from './component/single-event/single-event.component';
import { ShareModule } from '../share/share.module';
import { EventRoutingModule } from './event-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; // Ajouté
import { DropdownModule } from 'primeng/dropdown'; // Ajouté



@NgModule({
  declarations: [
    EventCreationComponent,
    EventUserComponent,
    SingleEventComponent
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
