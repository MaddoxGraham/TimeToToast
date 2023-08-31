import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EventService } from 'src/app/core/service/event/event.service';
import { EventDto } from 'src/app/share/dtos/event/event-dto';

@Component({
  selector: 'app-single-event',
  templateUrl: './single-event.component.html',
  styleUrls: ['./single-event.component.css']
})
export class SingleEventComponent implements OnInit {
  eventDetails: any; // Type this based on your EventDto
  event!:EventDto;
  constructor(private route: ActivatedRoute,
              private eventService: EventService) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const idEvent = params['idEvent'];
      if (idEvent) {
        this.eventService.getEventById(idEvent).subscribe(
          (response) => {
            this.event = response;
            console.log(this.event);
          },
          (error) => {
            console.error('Erreur lors de la récupération des détails de l\'événement:', error);
          }
        );
      }
    });
  }
}
