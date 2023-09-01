import { Component, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent {
  @Output() moduleDeleted = new EventEmitter();

  deleteModule() {
    if (confirm('Êtes-vous sûr de supprimer le module ?')) {
      this.moduleDeleted.emit();
    }
  }
}