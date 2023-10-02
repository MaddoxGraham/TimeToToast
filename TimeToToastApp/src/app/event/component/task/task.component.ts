import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
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

  public displayMode: 'all' | 'mine' | 'create' = 'all';
  Tasks!: TaskDto[];
  public isTaskModuleActive!: boolean;
  private guestsSubscription!: Subscription;
  guests!: GuestDto[];
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
  }

  initForm() {
    this.taskForm = this.fb.group({
      description: ['', Validators.required],
      urgency: ['', Validators.required],
      dueDate: ['', Validators.required],
      assignee: [''],
      invisibleTo: ['']
    });
  }

  updateFilteredGuests() {
    const selectedAssignees = this.taskForm.get('assignee')?.value || [];
    this.Assignee = this.guests.filter(guest => selectedAssignees.includes(guest.idPerson));
    this.filteredGuests = this.guests.filter(guest => !selectedAssignees.includes(guest.idPerson));
  }

  saveAssignees() {
    this.updateFilteredGuests();
  }

  saveInvisibleTo() {
    // Your logic to save guests for whom the task is invisible
  }

  createTask() {
    if (this.taskForm.valid) {
      let newTask: CreateTaskDto = this.taskForm.value;
      this.taskService.addTask(newTask).subscribe((response: CreateTaskDto) => {
        console.log(response);
      })
    }
  }
}
