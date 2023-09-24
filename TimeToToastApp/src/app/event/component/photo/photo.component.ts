import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MessageService } from 'primeng/api';
import { PhotoService } from 'src/app/core/service/photo/photo.service';
import { UploadService } from 'src/app/core/service/upload/upload.service';
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
export class PhotoComponent implements OnInit {
  @Output() moduleDeleted = new EventEmitter<void>();
  @Input() userEvent!: UserEventRoleDto;
  @Input() event!: EventDto;

  public displayMode: 'welcome' | 'galleria' | 'upload' = 'welcome';
  uploadedFiles: any[] = []; // Ajouté ici
  maxFileSize: number = 4000000;  // 1 MB
  uploadUrl!: string;

  displayCustom: boolean | undefined;

  activeIndex: number = 0;

  images: any[] | undefined;

  responsiveOptions: any[] = [
      {
          breakpoint: '1500px',
          numVisible: 5
      },
      {
          breakpoint: '1024px',
          numVisible: 3
      },
      {
          breakpoint: '768px',
          numVisible: 2
      },
      {
          breakpoint: '560px',
          numVisible: 1
      }
  ];

  constructor(private uploadService:UploadService, private messageService: MessageService,private photoService: PhotoService ) {
    this.uploadUrl = this.uploadService.getUploadUrl();
  }
 
  
  isOpen = false;
//gestion de l'upload
  onUpload(event: any) {
    for(let file of event.files) {
      this.uploadedFiles.push(file);
    }
    this.messageService.add({severity: 'info', summary: 'File Uploaded', detail: ''});
  }

  //gestion de la gallerie

  ngOnInit() {
    this.photoService.getImages().then((images) => (this.images = images));
}

imageClick(index: number) {
    this.activeIndex = index;
    this.displayCustom = true;
}

}
