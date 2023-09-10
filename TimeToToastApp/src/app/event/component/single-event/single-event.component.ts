import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { EventService } from 'src/app/core/service/event/event.service';
import { UserService } from 'src/app/core/service/user/user.service';
import { EmailDataDto } from 'src/app/share/dtos/EmailData/EmailDataDto';
import { EventDto } from 'src/app/share/dtos/event/event-dto';
import { UserDto } from 'src/app/share/dtos/user/user-dto';
import { UserEventRoleDto } from 'src/app/share/dtos/userEventRole/user-event-role-dto';


@Component({
  selector: 'app-single-event',
  templateUrl: './single-event.component.html',
  styleUrls: ['./single-event.component.css']
})
export class SingleEventComponent implements OnInit {
  eventDetails: any; // Type this based on your EventDto
  event!: EventDto;
  user!: UserDto;
  userEventList!: UserEventRoleDto[];
  public isTaskModuleActive: boolean = false;
  public idUser = localStorage.getItem("user");
  public userEvent!: UserEventRoleDto;
  emailData: EmailDataDto = { to: [], subject: '', body: '' };  // Initialise avec des valeurs par défaut
  emailForm: FormGroup = this.fb.group({});

  emailList: string[] = [];
  emailInvalid: boolean = false;
  emailExists: boolean = false;
  successMessage: string = ''; // message de succès



  constructor(private route: ActivatedRoute,
    private eventService: EventService,
    private userService: UserService,
    private fb: FormBuilder) { 
      
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


  ngOnInit(): void {
    this.route.params.subscribe(params => {

      //Récupération de l'évènement 
      const idEvent = params['idEvent'];
      if (idEvent) {
        this.eventService.getEventById(idEvent).subscribe(
          (response) => {
            this.event = response;
          },
          (error) => {
            console.error('Erreur lors de la récupération des détails de l\'événement:', error);
          }
        );
        // Récupération de la liste des user attachés à l'Event. 
        const idUser: UserDto = JSON.parse(sessionStorage.getItem('user') || '{}');
        if (idUser.idUser && idEvent) {
          this.eventService.getUserEventRole(idUser.idUser, idEvent).subscribe(
            (response) => {
              this.userEvent = response;
            },
            (error) => {
              console.error('Erreur lors de la récupération des données utilisateur-événement:', error);
            }
          );
        }
      }
      //Récupération de la liste des user attachés à l'Event. 
      if (idEvent) {
        this.eventService.getUserEventRoleList(idEvent).subscribe(
          (response) => {
            this.userEventList = response;

            console.log(this.userEventList)

          },
          (error) => {
            console.error('Erreur lors de la récupération de la liste des utilisateurs:', error);
          }
        )

      }

    });


    // Récupération du current User 
    const idUser: UserDto = JSON.parse(sessionStorage.getItem('user') || '{}');
    this.user = idUser;

    this.isTaskModuleActive = localStorage.getItem('isTaskModuleActive') === 'true';
  }


  getUsersByRole(role: string): UserDto[] {
    const userEvents = this.userEventList.filter(userEvent => userEvent.role === role);
    const users: UserDto[] = [];
    userEvents.forEach(userEvent => {
      if (userEvent.users) {
        users.push(...userEvent.users);
      }
    });
    return users;
  }

  activateTaskModule() {
    this.isTaskModuleActive = true;
    localStorage.setItem('isTaskModuleActive', 'true');
    console.log(this.isTaskModuleActive);
  }

  activateGiftModule() { }
  activatePhotosModule() { }


  onModuleDeleted() {
    this.isTaskModuleActive = false;
  }


  dataEmail() {  
    this.emailData.subject = `${this.user.lastName} ${this.user.firstName} vous invite à l'événement : ${this.event.title}`;
    this.emailData.body = this.getEmailBody();
  }

getEmailBody() {
  return `
    <div style="font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif; text-align: center;"><strong>Corps :</strong>
      <h2 style="font-family: 'Phudu', cursive; font-size: 1.5rem; font-variant: small-caps; letter-spacing: 2px; color: #a57b45; margin-bottom: 5%;"> ${this.user.lastName} ${this.user.firstName} vous invite à l'événement : ${this.event.title} </h2>
      type de l'événement : ${this.event.categorie}.<br>
      Le ${this.event.eventDate} <br>

      <h4 style="font-size: 14px; font-variant: small-caps; letter-spacing: 2px; margin-top: 5%;"> Dites à vos proches que vous participez en vous connectant sur le lien : </h4>

      <a href="localhost://4200/event/${this.event.idEvent}" style="color: #a57b45; font-variant: small-caps; letter-spacing: 2px;"> Cliquez ici  </a> 

      <img class="img-fluid" src="/assets/images/timetotoast.webp" />
    </div>
  `;
}

sendEmail(): void {
  this.emailData.to = [...this.emailList];
  this.eventService.addGuest(this.emailData).subscribe(
    (response) => {
      this.successMessage = "E-mails envoyés avec succès !";
      document.getElementById('emailModal')?.click(); // Ferme la modale
    },
    (error) => {
      this.successMessage = "Erreur lors de l'envoi des e-mails.";
      // Vous pouvez choisir de ne pas fermer la modale ici
    }
  );
}



checkEmail(event: KeyboardEvent, inputEmail: string, inputElement: HTMLInputElement): void {
  this.emailInvalid = false;
  this.emailExists = false;
  
  if (event.code === 'Space' || event.code === 'Enter') {
    // Vérifier si l'email est valide
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    if (!emailRegex.test(inputEmail)) {
      this.emailInvalid = true;
      return;
    }

    // Vérifier si l'email existe déjà dans la liste
    if (this.emailList.includes(inputEmail)) {
      this.emailExists = true;
      return;
    }

    // Ajouter l'email à la liste et réinitialiser l'input
    this.emailList.push(inputEmail);
    inputElement.value = '';
  } else if (event.code === 'Backspace' && inputEmail === '') {
    // Supprimer la dernière adresse e-mail de la liste
    this.emailList.pop();
  }
}


}
