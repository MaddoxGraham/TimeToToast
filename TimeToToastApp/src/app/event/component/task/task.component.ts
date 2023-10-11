import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, Subscription } from 'rxjs';
import { SharedService } from 'src/app/core/service/shared/shared.service';
import { TaskService } from 'src/app/core/service/task/task.service';
import { EventDto } from 'src/app/share/dtos/event/event-dto';
import { GuestDto } from 'src/app/share/dtos/guest/guest-dto';
import { CreateTaskDto } from 'src/app/share/dtos/task/create-task-dto';
import { TaskDto } from 'src/app/share/dtos/task/task-dto';
import { UserDto } from 'src/app/share/dtos/user/user-dto';
import { UserEventRoleDto } from 'src/app/share/dtos/userEventRole/user-event-role-dto';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {
  @Output() moduleDeleted = new EventEmitter<void>();
  @Input() userEvent!: UserEventRoleDto;
  @Input() event!: EventDto;
  @Input() user!: UserDto;

  public displayMode: 'all' | 'mine' | 'create' = 'mine';
  Tasks!: TaskDto[];
  public isTaskModuleActive!: boolean;
  private guestsSubscription!: Subscription;
  guests!: GuestDto[];
  allTasks!: CreateTaskDto[];
  showInvisibleToggle: boolean = false;
  Assignee: GuestDto[] = [];
  InvisibleTo: GuestDto[] = [];
  filteredGuests: GuestDto[] = [];
  isOpen = false;
  description!: string;
  urgencies = [
    { name: 'Basse', value: 1 },
    { name: 'Moyenne', value: 2 },
    { name: 'Haute', value: 3 }
  ];
  dueDate!: Date;
  taskForm!: FormGroup;
  checked: boolean = false;

  selectedGuestId!: number;


  constructor(private fb: FormBuilder, 
              private sharedService: SharedService,
              private taskService: TaskService) { }

  ngOnInit(): void {
    this.initForm();

  this.guestsSubscription = this.sharedService.guests$.subscribe(guests => {
    this.guests = guests.map(guest => ({
      ...guest,
      label: guest.firstName && guest.lastName ? `${guest.firstName} ${guest.lastName}` : guest.email
    }));
    this.updateFilteredGuests();
    this.taskForm.get('assignee')?.valueChanges.subscribe(() => {
      this.updateFilteredGuests();
    });
  });

    this.guestsSubscription = this.sharedService.guests$.subscribe(guests => {
      this.guests = guests.map(guest => ({
        ...guest,
        label: guest.firstName && guest.lastName ? `${guest.firstName} ${guest.lastName}` : guest.email
      }));
      this.updateFilteredGuests();
      this.taskForm.get('assignee')?.valueChanges.subscribe(() => {
        this.updateFilteredGuests();    
      });
    });
    this.getAllTasks(this.event.idEvent);

  }

  initForm() {
    this.taskForm = this.fb.group({
      description: ['', Validators.required],
      urgency: ['', Validators.required],
      dueDate: ['', Validators.required],

      assignee: [''],
      invisibleTo: ['']

      assignee: [[]],
      invisibleTo: [[]],
      showInvisibleToggle: [false],

    });
  }

  updateFilteredGuests() {
    const selectedAssignees = this.taskForm.get('assignee')?.value || [];
    this.Assignee = this.guests.filter(guest => selectedAssignees.includes(guest.idPerson));

    this.filteredGuests = this.guests.filter(guest => !selectedAssignees.includes(guest.idPerson));

    console.log('selectedAssignees', selectedAssignees)
    this.filteredGuests = this.guests.filter(guest => !selectedAssignees.includes(guest));
    console.log('filteredGuests', this.filteredGuests)

  }

  createTask() {
    if (this.taskForm.valid) {
      console.log(this.taskForm.value)
      let newTask!: CreateTaskDto;
      newTask = {
        ...this.taskForm.value, // Spread pour récupérer les autres valeurs (si elles sont correctes)
        assignee: this.taskForm.value.assignee.map((assignee: any) => assignee.idPerson),
        invisibleTo: this.taskForm.value.invisibleTo.map((invisible: any) => invisible.idPerson)
      };
      console.log(newTask)
      if(this.user.idPerson)
        newTask.creator = this.user.idPerson
      newTask.event = this.event.idEvent
      this.taskService.addTask(newTask).subscribe((response: CreateTaskDto) => {
        this.getAllTasks(this.event.idEvent);
      })
      this.taskForm.reset();
      this.displayMode = 'all'
    }
  }

  getAllTasks(idEvent: number) {
    this.taskService.getAllTask(idEvent).subscribe((response: CreateTaskDto[]) => {
      this.allTasks = response;
    })
  }


  createTask() {
    if (this.taskForm.valid) {
      let newTask: CreateTaskDto = this.taskForm.value;
      this.taskService.addTask(newTask).subscribe((response: CreateTaskDto) => {
        console.log(response);
      })
    }
=======
  guestsForDropdown(ids: number[]) {
    return this.guests
      .filter(person => ids.includes(person.idPerson)) 
      .map(person => {
        return {
          label: person.firstName && person.lastName ? `${person.firstName} ${person.lastName}` : person.email,
          value: person.idPerson
        };
      });
  }

  enlisted(idTask: number) {
    if(this.user.idPerson)
      this.taskService.addAssignee(idTask, this.user.idPerson).subscribe(response => {
        this.getAllTasks(this.event.idEvent);
      })
  }

  giveUpTask(idTask: number) {
    if(this.user.idPerson)
      this.taskService.giveUpTask(idTask, this.user.idPerson).subscribe(response => {
        this.getAllTasks(this.event.idEvent);
      })
  }

  deleteTask(idTask: number) {
    if(this.user.idPerson)
      this.taskService.deleteTask(idTask).subscribe(response => {
        this.getAllTasks(this.event.idEvent);
      })

  }
  
}
