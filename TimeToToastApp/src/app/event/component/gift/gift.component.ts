import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { EventDto } from 'src/app/share/dtos/event/event-dto';
import { UserEventRoleDto } from 'src/app/share/dtos/userEventRole/user-event-role-dto';

@Component({
  selector: 'app-gift',
  templateUrl: './gift.component.html',
  styleUrls: ['./gift.component.css']
})
export class GiftComponent implements OnInit {
  @Output() moduleDeleted = new EventEmitter<void>();
  @Input() userEvent!: UserEventRoleDto;
  @Input() event!: EventDto;

  ngOnInit(): void {
    console.log('gift component works!')
  }
}
