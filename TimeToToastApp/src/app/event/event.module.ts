import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EventCreationComponent } from './component/event-creation/event-creation.component';
import { EventUserComponent } from './component/event-user/event-user.component';
import { SingleEventComponent } from './component/single-event/single-event.component';
import { ShareModule } from '../share/share.module';
import { EventRoutingModule } from './event-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; // Ajouté
import { MapComponent } from './component/map/map.component';
import { TaskComponent } from './component/task/task.component';
import { GiftComponent } from './component/gift/gift.component';
import { PhotoComponent } from './component/photo/photo.component';



@NgModule({
  declarations: [
    EventCreationComponent,
    EventUserComponent,
    SingleEventComponent,
    MapComponent,
    TaskComponent,
    GiftComponent,
    PhotoComponent
  ],
  imports: [
    CommonModule,
    ShareModule,
    EventRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class EventModule { }
