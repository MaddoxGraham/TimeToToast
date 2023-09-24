import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MessageService } from 'primeng/api';
import { EventDto } from 'src/app/share/dtos/event/event-dto';
import { UserEventRoleDto } from 'src/app/share/dtos/userEventRole/user-event-role-dto';



interface UploadEvent {
    originalEvent: Event;
    files: File[];
}

@Component({
  selector: 'app-photo',
  templateUrl: './photo.component.html',
  styleUrls: ['./photo.component.css'],
  providers: [MessageService] // Ajouté ici
})
export class PhotoComponent {
  @Output() moduleDeleted = new EventEmitter<void>();
  @Input() userEvent!: UserEventRoleDto;
  @Input() event!: EventDto;
  uploadedFiles: any[] = []; // Ajouté ici
  maxFileSize: number = 4000000;  // 1 MB
  constructor(private messageService: MessageService) { } // MessageService ajouté ici
  
  isOpen = false;

  onUpload(event: any) {
    for(let file of event.files) {
      this.uploadedFiles.push(file);
    }
    this.messageService.add({severity: 'info', summary: 'File Uploaded', detail: ''});
  }
}
