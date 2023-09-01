import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EventService } from 'src/app/core/service/event/event.service';
import { EventDto } from 'src/app/share/dtos/event/event-dto';
import { UserDto } from 'src/app/share/dtos/user/user-dto';
import { UserEventRoleDto } from 'src/app/share/dtos/userEventRole/user-event-role-dto';


@Component({
  selector: 'app-single-event',
  templateUrl: './single-event.component.html',
  styleUrls: ['./single-event.component.css']
})
export class SingleEventComponent implements OnInit {
  eventDetails: any; // Type this based on your EventDto
  event!:EventDto;
  public isTaskModuleActive: boolean = false;
  public idUser = localStorage.getItem("user");
  public userEvent!: UserEventRoleDto;

  


  constructor(private route: ActivatedRoute,
              private eventService: EventService) {}

              ngOnInit(): void {
                this.route.params.subscribe(params => {
                  const idEvent = params['idEvent'];
                  if (idEvent) {
                    this.eventService.getEventById(idEvent).subscribe(
                      (response) => {
                        this.event = response;
                      },
                      (error) => {
                        console.error('Erreur lors de la récupération des détails de l\'événement:', error);
                      }
                    );
              
                    const idUser: UserDto = JSON.parse(sessionStorage.getItem('user') || '{}');
                     
                    if (idUser.idUser && idEvent) {   
                     this.eventService.getUserEventRole(idUser.idUser, idEvent).subscribe(
                       (response) => {
                         this.userEvent = response;
                       },
                       (error) => {
                         console.error('Erreur lors de la récupération des données utilisateur-événement:', error);
                       }
                     );
                    }
                  }
                });

                this.isTaskModuleActive = localStorage.getItem('isTaskModuleActive') === 'true';
              }

  activateTaskModule() {
    this.isTaskModuleActive = true;
    localStorage.setItem('isTaskModuleActive', 'true');
    console.log(this.isTaskModuleActive);
  }

  activateGiftModule() {}
  activatePhotosModule() {}


  onModuleDeleted() {
    this.isTaskModuleActive = false;
  }


}
