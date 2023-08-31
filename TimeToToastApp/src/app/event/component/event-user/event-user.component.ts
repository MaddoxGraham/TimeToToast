import { Component, OnInit } from '@angular/core';
import { EventService } from 'src/app/core/service/event/event.service';
import { UserDto } from 'src/app/share/dtos/user/user-dto';
import { UserEventRoleDto } from 'src/app/share/dtos/userEventRole/user-event-role-dto';
import { EventDto } from 'src/app/share/dtos/event/event-dto';
import { UserEventsDto } from 'src/app/share/dtos/userEvents/user-events-dto';

@Component({
  selector: 'app-event-user',
  templateUrl: './event-user.component.html',
  styleUrls: ['./event-user.component.css']
})
export class EventUserComponent implements OnInit {
  userEventRoles: UserEventRoleDto[] = []; // Liste des rôles utilisateur
  eventDetails: { [idEvent: number]: EventDto } = {}; // Détails des événements
  userEventsList: UserEventsDto[] = [];

  constructor(private eventService: EventService) { }

  ngOnInit(): void {
    this.loadUserEventRoles();
  }

  loadUserEventRoles() {
    const user: UserDto = JSON.parse(sessionStorage.getItem('user') || '{}');
    if (user?.idUser) {
      this.eventService.getUserRolesByUserId(user.idUser).subscribe(
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
    for (const userEventRole of this.userEventsList) {
      console.log(userEventRole);
      // this.eventService.getEventById(userEventRole.idEvent).subscribe(
      //   (eventDetails) => {
      //     this.eventDetails[userEventRole.idEvent] = eventDetails;
      //   },
      //   (error) => {
      //     console.error(`Erreur lors de la récupération des détails de l'événement ${userEventRole.idEvent}:`, error);
      //   }
      // );
    }
  }
}