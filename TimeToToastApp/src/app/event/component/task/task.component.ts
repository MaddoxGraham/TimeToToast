import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit{
  @Output() moduleDeleted = new EventEmitter<void>();
  public isTaskModuleActive!:boolean;
  isOpen = true;  // Accordéon ouvert par défaut

  ngOnInit(): void {
    this.isTaskModuleActive = localStorage.getItem('isTaskModuleActive') ? localStorage.getItem('isTaskModuleActive') === 'true' : false;
    console.log(this.isTaskModuleActive);
  }

  deleteModule() {
    localStorage.setItem('isTaskModuleActive', 'false');
    this.moduleDeleted.emit();
  }


}
