import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MessageService } from 'primeng/api';
import { PhotoService } from 'src/app/core/service/photo/photo.service';
import { UploadService } from 'src/app/core/service/upload/upload.service';
import { EventDto } from 'src/app/share/dtos/event/event-dto';
import { UserDto } from 'src/app/share/dtos/user/user-dto';
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
  @Input() user! : UserDto;

  public displayMode: 'welcome' | 'galleria' | 'upload' = 'welcome';
  uploadedFiles: any[] = []; // Ajouté ici
  maxFileSize: number = 4000000;  // 1 MB
  uploadUrl!: string;

  displayCustom: boolean | undefined;

  activeIndex: number = 0;

  images!: String[] ;

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
  }
 
  
  isOpen = false;

//gestion de l'upload

onUpload(event: any) {
  const files = event.files;
  const formData: FormData = new FormData();
  // Assumons que tu aies les valeurs pour idUser et idEvent quelque part dans ton composant
  const idUser = this.userEvent.idUser;
  const idEvent = this.event.idEvent;
  console.log("click on upload")
  console.log(this.user.idPerson)
 
  for (let file of files) {
    formData.append('files', file, file.name); // Note 'files' au lieu de 'file'
  }
if (this.user.idPerson && this.event.idEvent) {
    this.uploadService.uploadFiles(formData, this.user.idPerson, this.event.idEvent).subscribe(
    (response) => {
      this.messageService.add({severity: 'success', summary: 'Succès', detail: 'Fichiers uploadés avec succès'});
    },
    (error) => {
      this.messageService.add({severity: 'error', summary: 'Erreur', detail: 'Upload a échoué'});
    }
  );
} else { console.log("WARNING - L'utilisateur ou l'évènement ne sont pas reconnus. ")}

}
  //gestion de la gallerie

  ngOnInit() {

    this.fetchGalleria();
   
}
fetchGalleria() {
  this.uploadService.getPhotos(this.event.idEvent).subscribe(
    (images) => {
      this.images = images;
      console.log(this.images)
    },
    (error) => {
      console.error("Une erreur s'est produite :", error);
    }
  );
}
imageClick(index: number) {
    this.activeIndex = index;
    this.displayCustom = true;
}

}
