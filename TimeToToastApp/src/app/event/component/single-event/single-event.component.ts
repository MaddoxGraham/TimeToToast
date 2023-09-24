import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { EventService } from 'src/app/core/service/event/event.service';
import { SharedService } from 'src/app/core/service/shared/shared.service';
import { EmailDataDto } from 'src/app/share/dtos/EmailData/EmailDataDto';
import { EventDto } from 'src/app/share/dtos/event/event-dto';
import { GuestDto } from 'src/app/share/dtos/guest/guest-dto';
import { UserDto } from 'src/app/share/dtos/user/user-dto';
import { UserEventRoleDto } from 'src/app/share/dtos/userEventRole/user-event-role-dto';


@Component({
  selector: 'app-single-event',
  templateUrl: './single-event.component.html',
  styleUrls: ['./single-event.component.css']
})
export class SingleEventComponent implements OnInit {

  event!: EventDto;
  user!: UserDto;
  userEventList!: UserEventRoleDto[];
  public isTaskModuleActive: boolean = false;
  public isGiftModuleActive: boolean = false;
  public isPhotoModuleActive: boolean = false;
  public idUser = localStorage.getItem("user");
  public userEvent!: UserEventRoleDto;
  emailData: EmailDataDto = {
    to: [],
    idPerson: 0,
    idEvent: 0
  }
  emailForm: FormGroup = this.fb.group({});
  emailList: string[] = [];
  emailInvalid: boolean = false;
  emailExists: boolean = false;
  successMessage: string = ''; // message de succès
  public guests!: GuestDto[];
  public guestsSubject: BehaviorSubject<any[]> = new BehaviorSubject<any[]>([]);



  constructor(private route: ActivatedRoute,
    private eventService: EventService,
    private fb: FormBuilder,
    private sharedService: SharedService,) {

    this.emailForm = this.fb.group({
      emails: this.fb.array([]),
    });

  }



  ngOnInit(): void {
    this.route.params.subscribe(params => {
      //Récupération de l'évènement  
      const idUser: UserDto = JSON.parse(sessionStorage.getItem('user') || '{}');
      const idEvent = params['idEvent'];
      if (idEvent) { this.getEvent(idEvent);}
      if (idUser.idPerson && idEvent) {this.getUserEventRole(idUser.idPerson, idEvent) }
      if (idEvent) { this.getUserEventRoleList(idEvent) }
    });

    const idUser: UserDto = JSON.parse(sessionStorage.getItem('user') || '{}');
    this.user = idUser;
  }

  // CURRENT USER OR EVENT  REALTED QUERY 


  getUsersByRole(role: string): UserDto[] {
    
    const userEvents = this.userEventList.filter(userEvent => userEvent.role === role);
    const users: UserDto[] = [];
    userEvents.forEach(userEvent => {
      if (userEvent.persons) {
        users.push(...userEvent.persons);
      }
    });
    return users;
  }

  getEvent(idEvent: number) {
    this.eventService.getEventById(idEvent).subscribe(
      (response) => {
        this.event = response;
        this.isTaskModuleActive = response.taskModuleActive; // Supposant que taskModuleActive est un champ dans ton EventDto
        this.isGiftModuleActive = response.giftModuleActive;
        this.isPhotoModuleActive = response.photoModuleActive;
        this.addingGuest()
      },
      (error) => {
        console.error('Erreur lors de la récupération des détails de l\'événement:', error);
      }
    );
  }

  getUserEventRoleList(idEvent:number){
    this.eventService.getUserEventRoleList(idEvent).subscribe(
      (response) => {
        this.userEventList = response;
        console.log(this.userEventList);
      },
      (error) => {
        console.error('Erreur lors de la récupération de la liste des utilisateurs:', error);
      }
    )
  }

  // Récupération de la liste des user attachés à l'Event. 
  getUserEventRole(idUser: number, idEvent: number) {

    this.eventService.getUserEventRole(idUser, idEvent).subscribe(
      (response) => {
        this.userEvent = response;
      },
      (error) => {
        console.error('Erreur lors de la récupération des données utilisateur-événement:', error);
      }
    );

  }

  
  formatStartTime(startTime: string): string {
    const hours = startTime.substr(0, 2);
    const minutes = startTime.substr(2, 2);
    return `${hours} H ${minutes} min`;
  }


  // MODULE REALTED QUERY 


  activateTaskModule() {
    this.eventService.updateModuleEvent(this.event.idEvent,'task').subscribe(
      (response) => {
        this.isTaskModuleActive = response.taskModuleActive;
      },
      (error) => {
        console.error("Le module Tâche est indisponible pour le moment", error);
      }
    )
  }

  activateGiftModule() {
    this.eventService.updateModuleEvent(this.event.idEvent,'gift').subscribe(
      (response) => {
        this.isGiftModuleActive = response.giftModuleActive;
       
      },
      (error) => {
        console.error("Le module Cadeaux est indisponible pour le moment", error);
      }
    )
   }
  activatePhotosModule() {
    this.eventService.updateModuleEvent(this.event.idEvent,'photo').subscribe(
      (response) => {
        this.isPhotoModuleActive = response.photoModuleActive;
      },
      (error) => {
        console.error("Le module Photos est indisponible pour le moment", error);
      }
    )
   }


  onModuleDeleted(moduleName:string) {
  if (moduleName ='task') {
     this.activateTaskModule()
   }
  if (moduleName ='gift') {
    this.activateGiftModule();
   }
  if (moduleName ='photo') {
    this.activatePhotosModule()
   }
   
  }


  // MAILS REALTED QUERY 

  dataEmail() {
    const idUser: UserDto = JSON.parse(sessionStorage.getItem('user') || '{}');
    if (idUser.idPerson && this.event) {
      this.emailData.idPerson = idUser.idPerson;
      this.emailData.idEvent = this.event.idEvent;

    } else {
      // Gérer le cas où les valeurs sont indéfinies
      console.error("idUser ou idEvent est indéfini");
    }
  }

  sendEmail(): void {
    this.emailData.to = [...this.emailList];
    this.eventService.addGuest(this.emailData).subscribe(
      (response) => {
        this.successMessage = "E-mails envoyés avec succès !";
        this.addingGuest()
      },
      (error) => {
        this.successMessage = "E-mails envoyés avec succès !";
        // Vous pouvez choisir de ne pas fermer la modale ici
      }
    );
    document.getElementById('emailModal')?.click(); // Ferme la modale

  }


  get emails() {
    return (this.emailForm.get('emails') as FormArray);
  }

  addEmail(email: string) {
    this.emails.push(this.fb.control(email));
  }

  handleInput(event: any) {
    const value = event.target.value;
    if (value.endsWith(' ')) {
      this.addEmail(value.trim());
      event.target.value = '';
    }
  }

  handleKeyDown(event: KeyboardEvent) {
    const input = event.target as HTMLInputElement;
    if (event.key === 'Backspace' && !input.value) {
      const emails = this.emails;
      if (emails.length > 0) {
        emails.removeAt(emails.length - 1);
      }
    }
  }


  checkEmail(event: KeyboardEvent, inputEmail: string, inputElement: HTMLInputElement): void {
    this.emailInvalid = false;
    this.emailExists = false;

    // Supprimer les espaces en début et en fin de chaîne
    inputEmail = inputEmail.trim();

    // Gérer le cas de la touche Backspace
    if (event.code === 'Backspace') {
      if (inputEmail === '') {
        this.emailList.pop();
      }
      return;
    }

    if (event.code === 'Space' || event.code === 'Enter') {
      event.preventDefault(); // Ajouté cette ligne pour empêcher le comportement par défaut

      // Votre code pour vérifier la validité de l'e-mail reste le même
      const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
      if (!emailRegex.test(inputEmail)) {
        this.emailInvalid = true;
        return;
      }

      // Votre code pour vérifier si l'e-mail existe déjà dans la liste reste le même
      if (this.emailList.includes(inputEmail)) {
        this.emailExists = true;
        return;
      }

      // Ajoutez l'e-mail à la liste et réinitialisez l'input
      this.emailList.push(inputEmail);
      inputElement.value = '';

    }
  }


  // GUESTS REALTED QUERY 

  addingGuest() {
    this.eventService.eventGuest(this.event.idEvent).subscribe(
      (response) => {
        this.guests = response;
        this.guestsSubject.next(this.guests); // Met à jour la valeur du BehaviorSubject

        this.sharedService.updateGuests(this.guests);
      }
    )
  }

}
