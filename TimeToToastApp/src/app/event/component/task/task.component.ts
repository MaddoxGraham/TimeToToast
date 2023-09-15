import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EventDto } from 'src/app/share/dtos/event/event-dto';
import { UserDto } from 'src/app/share/dtos/user/user-dto';
import { UserEventRoleDto } from 'src/app/share/dtos/userEventRole/user-event-role-dto';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit{
  @Output() moduleDeleted = new EventEmitter<void>();
  @Input() userEvent!: UserEventRoleDto;
  @Input() event!: EventDto;
  public displayMode: 'all' | 'mine' | 'create' = 'create';

  public isTaskModuleActive!:boolean;

  isOpen = true;  // Accordéon ouvert par défaut
  taskForm!: FormGroup;

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.taskForm = this.fb.group({
      description: ['', [Validators.required]],
      urgency: ['Moyenne', [Validators.required]],
      dueDate: [''],
      assignee: ['', [Validators.required]]
    });

  }

  deleteModule() {
    localStorage.setItem('isTaskModuleActive', 'false');
    this.moduleDeleted.emit();
  }

  createTask() {
    if (this.taskForm.valid) {
      console.log(this.taskForm.value);
    }


}
}
