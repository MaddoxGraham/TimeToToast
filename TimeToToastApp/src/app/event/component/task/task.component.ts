import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { EventDto } from 'src/app/share/dtos/event/event-dto';
import { GuestDto } from 'src/app/share/dtos/guest/guest-dto';
import { TaskDto } from 'src/app/share/dtos/task/task-dto';
import { UserEventRoleDto } from 'src/app/share/dtos/userEventRole/user-event-role-dto';
import { Subscription } from 'rxjs';
import { SharedService } from 'src/app/core/service/shared/shared.service';
import { UserDto } from 'src/app/share/dtos/user/user-dto';




@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit{
  @Output() moduleDeleted = new EventEmitter<void>();
  @Input() userEvent!: UserEventRoleDto;
  @Input() event!: EventDto;
  @Input() user!:UserDto;
  
  public displayMode: 'all' | 'mine' | 'create' = 'all';
  Tasks!:TaskDto[];
  public isTaskModuleActive!:boolean;
  public minDate: string = '';  // La date minimale
  public maxDate: string = '';  // La date maximale
  private guestsSubscription!: Subscription;
  guests!: GuestDto[];
  showInvisibleToggle: boolean = false;
  value!: number;
  currentStep!:number;
  paymentOptions: any[] = [
      { name: 'Basse', value: 1 },
      { name: 'Moyenne', value: 2 },
      { name: 'Haute', value: 3 }
  ];
  Assignee: GuestDto[] = [];
  InvisibleTo: GuestDto[] = [];
  filteredGuests: GuestDto[] = [];

  isOpen = false;  // Accordéon fermé par défaut
  description!: string;
  urgency!: number;
  dueDate!: Date;

  rangeDates: Date[] | undefined;


  constructor(private fb: FormBuilder,
    private sharedService: SharedService) { }


   ngOnInit(): void {   
    this.guestsSubscription = this.sharedService.guests$.subscribe(guests => {
      this.guests = guests;
      this.guests = this.guests.map(guest => ({
      ...guest,
      displayLabel: guest.firstName ? guest.firstName : guest.email
    }));
    this.updateFilteredGuests();
    });

}

updateFilteredGuests() {
  this.filteredGuests = this.guests.filter(guest => !this.Assignee.includes(guest));
}

saveAssignees() {
  this.updateFilteredGuests();
}

saveInvisibleTo() {
  // Votre logique pour enregistrer les invités pour qui la tâche est invisible
}


createTask() {
  console.log("Description:", this.description);
   const taskData = {
    description: this.description,
      urgency: this.urgency,
      dueDate: this.dueDate,
      Assignee:this.Assignee,
    };
    // const mappedDatasTask={
    //   description:taskData.description,
    //   dueDate:taskData.dueDate,
    //   urgence:taskData.urgency,
    //   creator: this.user.idPerson,
    //   event:this.event.idEvent,
    //   assignees : this.Assignee,
    //   invisibles:this.InvisibleTo
    // }
    // console.log(mappedDatasTask);
  }


  }

