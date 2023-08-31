import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EventService } from 'src/app/core/service/event/event.service';
import { UserDto } from 'src/app/share/dtos/user/user-dto';


@Component({
  selector: 'app-event-creation',
  templateUrl: './event-creation.component.html',
  styleUrls: ['./event-creation.component.css']
})
export class EventCreationComponent implements OnInit {

  public previewData: any[] = [];
  public step:number = 1;
  public createEvent!:FormGroup;
  public useCustomCategory: boolean = false;
  public categorie!:string;
  user!: UserDto;
  public minDate!: string;
  public heureEvent: string = '';
  public today: Date = new Date (); 


  constructor(private builder: FormBuilder,
              private eventService: EventService,
              private router: Router) {}

  ngOnInit(): void {
    this.createEvent = this.builder.group({
      // Put form fields here.
      //step1
      category: [''],
      customCategory: [''],

      //step2
      eventTitle:[''],
      eventDescription:[''],
      eventDate:[''],
      heure:[''],
      minutes:[''],

        //step2

       adresse:[''],
       ville:[''],
       cp:[''],
    });

    this.createEvent.valueChanges.subscribe(() => {
      this.updateCategorie(); // Mettez à jour la catégorie en fonction des valeurs des contrôles
      this.updateHeureEvent(); // Mettre à jour l'heure de l'Event. 
    });

      const date = new Date();
      this.minDate = `${date.getFullYear()}-${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}`;


  }

    updateCategorie() {
      const categoryControl = this.createEvent.get('category');
      const customCategoryControl = this.createEvent.get('customCategory');    
      if (categoryControl && customCategoryControl) {
        if (this.createEvent.valid) {
          this.categorie = categoryControl.value || customCategoryControl.value;
        } else {
          this.categorie = '';
        }
      }
    }

    updateHeureEvent() {
      const heureControl = this.createEvent.get('heure');
      const minuteControl = this.createEvent.get('minutes');
    
      if (heureControl && minuteControl) {
        const heureValue = heureControl.value;
        const minuteValue = minuteControl.value;
    
        // Traitement pour l'heure
        let heureEvent = '';
        if (heureValue < 10) {
          heureEvent = '0' + heureValue.toString();
        } else {
          heureEvent = heureValue.toString();
        }
    
        // Traitement pour les minutes
        let minuteEvent = '';
        if (minuteValue < 10) {
          minuteEvent = '0' + minuteValue.toString();
        } else {
          minuteEvent = minuteValue.toString();
        }
    
        // Concaténation de l'heure et des minutes
        this.heureEvent = heureEvent + minuteEvent;
      }
    }

    isContinueButtonDisabled(): boolean {
      return !this.categorie;
    }

    isvalidButtonDisbled(): boolean {
      const adresseControl = this.createEvent.get('adresse');
      const villeControl = this.createEvent.get('ville');
      const cpControl = this.createEvent.get('cp');
    
      return !(adresseControl?.value && villeControl?.value && cpControl?.value);
    }

  onPrevious(){ this.step --; }

  onContinue(){ 
      this.previewData = [];
    this.previewData.push(this.createEvent.value);

    console.log(this.previewData)
    console.log(this.step)
    this.step ++;

    }

  onValidate(){ 
    this.previewData = [];
    this.previewData.push(this.createEvent.value);
    console.log('validate')  }

  toggleInput() {
    this.useCustomCategory = !this.useCustomCategory;
    const categoryControl = this.createEvent.get('category');
    const customCategoryControl = this.createEvent.get('customCategory');
    if (this.useCustomCategory) {
      categoryControl?.disable();
      customCategoryControl?.enable();
      customCategoryControl?.setValidators([Validators.required]);
    } else {
      customCategoryControl?.disable();
      categoryControl?.enable();
      categoryControl?.setValidators([Validators.required]);
    }
    
    categoryControl?.updateValueAndValidity();
    customCategoryControl?.updateValueAndValidity();
  }

  cancelModal(){
    const cancelButton = document.getElementById("cancelButton");
      if (cancelButton) {
    cancelButton.click(); // Simule le clic sur le bouton
  }
  }

  submitEvent(createEvent: FormGroup) {
    if (createEvent.valid) {
      this.eventService.addEvent({
        categorie: this.categorie,
        title: createEvent.value.eventTitle,
        description: createEvent.value.eventDescription,
        eventDate: createEvent.value.eventDate,
        startTime: this.heureEvent,
        createdAt: this.today,
        adresse: createEvent.value.adresse,
        ville: createEvent.value.ville,
        cp: createEvent.value.cp,
      }).subscribe(
        (response) => {
          console.log('Événement ajouté avec succès:');
          const userString = sessionStorage.getItem("user");
          if (userString) {
            this.user = JSON.parse(userString) as UserDto;
            if (this.user.idUser !== undefined) { // Vérification ici
              this.addUserEventRole(response.idEvent, this.user.idUser);
            } else {
              console.error('ID utilisateur non défini.');
            }
          }
        },
        (error) => {
          console.error('Erreur lors de l\'ajout de l\'événement:', error);
        }
      );
    }
  }

  addUserEventRole(idEvent: number, idUser: number) {
    this.eventService.addUserEventRole({
      idUser: idUser,
      idEvent: idEvent,
      role: 'CREATEUR'
    }).subscribe(
      (response) => {
        console.log('Role CREATEUR ajouté avec succès:');
        this.cancelModal();
        this.router.navigate(['/event/singleEvent', idEvent]);
      },
      (error) => {
        console.error('Erreur lors de l\'ajout du rôle CREATEUR:', error);
      }
    );
  }


}
