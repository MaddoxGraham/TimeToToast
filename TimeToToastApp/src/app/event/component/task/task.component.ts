import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EventDto } from 'src/app/share/dtos/event/event-dto';
import { GuestDto } from 'src/app/share/dtos/guest/guest-dto';
import { TaskDto } from 'src/app/share/dtos/task/task-dto';
import { UserEventRoleDto } from 'src/app/share/dtos/userEventRole/user-event-role-dto';
import { Subscription } from 'rxjs';
import { SharedService } from 'src/app/core/service/shared/shared.service';
import { EventService } from 'src/app/core/service/event/event.service';



@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit{
  @Output() moduleDeleted = new EventEmitter<void>();
  @Input() userEvent!: UserEventRoleDto;
  @Input() event!: EventDto;


  public displayMode: 'all' | 'mine' | 'create' = 'all';
  Tasks!:TaskDto[];
  public isTaskModuleActive!:boolean;
  public minDate: string = '';  // La date minimale
  public maxDate: string = '';  // La date maximale
  private guestsSubscription!: Subscription;
  guests!: GuestDto[];
  showInvisibleToggle: boolean = false;

  Assignee: GuestDto[] = [];
  InvisibleTo: GuestDto[] = [];

  filteredGuests: GuestDto[] = [];

  isOpen = false;  // Accordéon fermé par défaut
  taskForm!: FormGroup;

  constructor(private fb: FormBuilder,
    private sharedService: SharedService) { }


  ngOnInit(): void {   
    //  this.initializeDateRange();
    this.taskForm = this.fb.group({
      description: ['', [Validators.required]],
      urgency: ['', [Validators.required]],
      dueDate: [''],
    });
    this.guestsSubscription = this.sharedService.guests$.subscribe(guests => {
      this.guests = guests;
      this.guests = this.guests.map(guest => ({
      ...guest,
      displayLabel: guest.firstName ? guest.firstName : guest.email
    }));
    this.updateFilteredGuests();
    });
    this.taskForm = this.fb.group({
      Assignee: [null],
      InvisibleTo: [null],
      // autres champs...
    });

}

updateFilteredGuests() {
  this.filteredGuests = this.guests.filter(guest => !this.Assignee.includes(guest));
}

saveAssignees() {
  // Votre logique pour enregistrer les assignés
  this.updateFilteredGuests();
}

saveInvisibleTo() {
  // Votre logique pour enregistrer les invités pour qui la tâche est invisible
}


createTask() {
  const taskData = this.taskForm.value;
  console.log(this.taskForm.value); }

// ngOnDestroy() {
//   this.guestsSubscription.unsubscribe();
// }


  // createTask() {  
  // if (this.taskForm.valid) {  
  //   const taskData = this.taskForm.value;

  //   // au click : création de la lache puis envoie des data qui permettent de faire une entrée dans user task. 
  //   // userTask (ceux qui ont la tache sont dans un tableau : isVisible[] . UserTask (ceux qui ne voient pas la tâche sont dans un tableau isInvisible[]))
  //   const data = {
  //     description: taskData.description,
  //     dateTask: new Date(taskData.dueDate),
  //     urgence: taskData.urgence,

  //   }
  //   console.log(this.taskForm.value);
  // }
// }
// initializeDateRange(): void {
//   if (this.event && this.event.eventDate) {
//     const eventDate = new Date(this.event.eventDate);
//     this.maxDate = this.formatDate(eventDate);
//   // }
//   const currentDate = new Date();
//   this.minDate = this.formatDate(currentDate);
// }

// private formatDate(date: Date): string {
//   const year = date.getFullYear();
//   const month = String(date.getMonth() + 1).padStart(2, '0');
//   const day = String(date.getDate()).padStart(2, '0');
//   return `${year}-${month}-${day}`;
// }

// addGuest(selectedValue: string) {
//     const guest = this.guests.find(g => g.idPerson === Number(selectedValue));
//     if (guest) {
//       this.hiddenGuest.push(guest);
//     }
//   }

//   removeGuest(guest: GuestDto) {
//     const index = this.hiddenGuest.indexOf(guest);
//     if (index > -1) {
//       this.hiddenGuest.splice(index, 1);
//     }
//   }

}
