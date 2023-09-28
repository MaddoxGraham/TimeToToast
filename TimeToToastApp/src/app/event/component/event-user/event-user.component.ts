import { Component, OnInit } from '@angular/core';
import { EventService } from 'src/app/core/service/event/event.service';
import { UserDto } from 'src/app/share/dtos/user/user-dto';
import { UserEventRoleDto } from 'src/app/share/dtos/userEventRole/user-event-role-dto';
import { EventDto } from 'src/app/share/dtos/event/event-dto';
import { UserEventsDto } from 'src/app/share/dtos/userEvents/user-events-dto';
import { Router } from '@angular/router';
import { ConfirmationService, MessageService, ConfirmEventType } from 'primeng/api';



@Component({
  selector: 'app-event-user',
  templateUrl: './event-user.component.html',
  styleUrls: ['./event-user.component.css'],
  providers: [ConfirmationService, MessageService]
})
export class EventUserComponent implements OnInit {
  userEventRoles: UserEventRoleDto[] = []; // Liste des rôles utilisateur
  eventDetails: { [idEvent: number]: EventDto } = {}; // Détails des événements
  userEventsList: UserEventsDto[] = [];
  eventsOptions: EventDto[] = [];
  selectedEvent: EventDto | null = null;

  constructor(private eventService: EventService,
              private router: Router,
              private confirmationService: ConfirmationService, 
              private messageService: MessageService) { }

  ngOnInit(): void {
    this.loadUserEventRoles();
    
  }

  loadUserEventRoles() {
    const user: UserDto = JSON.parse(sessionStorage.getItem('user') || '{}');
    if (user?.idPerson) {
      this.eventService.getUserRolesByUserId(user.idPerson).subscribe(
        (userEventRoles: UserEventsDto[]) => {
          this.userEventsList = userEventRoles;
          // Appeler la méthode pour obtenir les détails de chaque événement
          this.loadEventDetails();
        },
        (error) => {
          console.error('Erreur lors de la récupération des rôles utilisateur:', error);
        }
      );
    }
  }

  loadEventDetails() {
    for (const userEvent of this.userEventsList) {
      if (userEvent?.events?.length > 0) {
        this.eventsOptions.push(...userEvent.events);
      }
    }
  }

  formatStartTime(startTime: string): string {
    const hours = startTime.substr(0, 2);
    const minutes = startTime.substr(2, 2);
    return `${hours} H ${minutes} min`;
  }
  
  sendToDetails(enventId: number){
    this.router.navigateByUrl(`/event/singleEvent/${enventId}`)
  }


  confirm1(idEvent: number) {
    this.confirmationService.confirm({
        message: 'Are you sure that you want to proceed?',
        icon: 'pi pi-exclamation-triangle',
        accept: () => {
            this.messageService.add({ severity: 'info', summary: 'Confirmed', detail: 'You have accepted' });
            console.log(idEvent)
            //ici le delete event 
        },
        reject: (type: ConfirmEventType) => {
            switch (type) {
                case ConfirmEventType.REJECT:
                    this.messageService.add({ severity: 'error', summary: 'Rejected', detail: 'You have rejected' });
                    break;
                case ConfirmEventType.CANCEL:
                    this.messageService.add({ severity: 'warn', summary: 'Cancelled', detail: 'You have cancelled' });
                    break;
            }
        }
    });
  }

}
