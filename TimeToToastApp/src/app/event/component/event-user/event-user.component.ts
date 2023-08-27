import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-event-user',
  templateUrl: './event-user.component.html',
  styleUrls: ['./event-user.component.css']
})
export class EventUserComponent implements OnInit{
  events!: any[]; // Liste des événements
  selectedEvent: any; // Événement sélectionné

  constructor() { }

  ngOnInit(): void {
    // Remplir la liste des événements. Utilise tes propres données ici.
    this.events = [
      {name: 'Event 1', id: 1},
      {name: 'Event 2', id: 2},
      // ...
    ];
  }
}
