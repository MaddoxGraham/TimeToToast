import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { SharedService } from 'src/app/core/service/shared/shared.service';
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

  constructor(private sharedService: SharedService) { }

  
  isOpen = true;  // Accordéon ouvert par défaut



  ngOnInit(): void {
    console.log('gift component works!')
  }
  
  //Gere la suppression du module en BDD
  deleteModule() {
    localStorage.setItem('isGiftModuleActive', 'false');
    this.moduleDeleted.emit();
  }

}
