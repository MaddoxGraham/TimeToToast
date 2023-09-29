import { Component, OnInit, Input, Output, EventEmitter, ChangeDetectorRef } from '@angular/core';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { EventService } from 'src/app/core/service/event/event.service';
import { UserService } from 'src/app/core/service/user/user.service';
import { EventDto } from 'src/app/share/dtos/event/event-dto';
import { UserDto } from 'src/app/share/dtos/user/user-dto';
import { UserEventsDto } from 'src/app/share/dtos/userEvents/user-events-dto';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit{
  user!: UserDto;
  userEventsList: UserEventsDto[] = [];
  eventsOptions: EventDto[] = [];
  showLogin = false;
  imagesSafeUrl = this.sanitizer.bypassSecurityTrustUrl("/assets/images/plusIcon.png");
  

  constructor(private eventService: EventService,
              private userService: UserService,
              private router: Router,
              private sanitizer: DomSanitizer){}

  ngOnInit(): void {
    const userString = sessionStorage.getItem("user");
    if (userString) {
      this.user = JSON.parse(userString) as UserDto;
      if(this.user.avatar){
        let imageUrl = 'data:image/jpeg;base64,' + this.user.avatar;
        this.imagesSafeUrl = this.sanitizer.bypassSecurityTrustUrl(imageUrl);
      }
    }
    this.loadUserEventRoles();
  }

  toggleDisplayName(event: Event): void {
    event.preventDefault();
    this.showLogin = !this.showLogin;
  }

  loadUserEventRoles() {
    const user: UserDto = JSON.parse(sessionStorage.getItem('user') || '{}');
    if (user?.idPerson) {
      this.eventService.getUserRolesByUserId(user.idPerson).subscribe(
        (userEventRoles: UserEventsDto[]) => {
          this.userEventsList = userEventRoles;
          // Appeler la méthode pour obtenir les détails de chaque événement
          this.loadEventDetails();
        },
        (error) => {
          console.error('Erreur lors de la récupération des rôles utilisateur:', error);
        }
      );
    }
  }

  loadEventDetails() {
    for (const userEvent of this.userEventsList) {
      if (userEvent?.events?.length > 0) {
        this.eventsOptions.push(...userEvent.events);
      }
    }
    // Trier le tableau eventsOptions par idEvent en ordre décroissant
    this.eventsOptions.sort((a, b) => b.idEvent - a.idEvent);
    
    // Prendre les trois premiers éléments du tableau trié
    this.eventsOptions = this.eventsOptions.slice(0, 3);
  }

  sendToDetails(enventId: number){
    this.router.navigateByUrl(`/event/singleEvent/${enventId}`)
  }

  uploadImage(event: any) {
    const file = event.target.files[0];
    
    if (file) {
        // Vérifiez la taille du fichier (2 Mo max)
        const maxSize = 2 * 1024 * 1024; // 2 Mo en octets
        if (file.size > maxSize) {
            alert('La taille de l\'image doit être inférieure à 2 Mo.');
            return;
        }

        // Vérifiez le type de fichier
        if (!file.type.startsWith('image/')) {
            alert('Veuillez sélectionner un fichier image valide.');
            return;
        }

        // Convertissez l'image en Base64
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => {
            const base64ImageWithPrefix  = reader.result as string;
            const base64Image = base64ImageWithPrefix.split(',')[1];
            if(this.user.idPerson) {
              this.userService.avatar(this.user.idPerson, base64Image).subscribe((response: UserDto) => {
                sessionStorage.setItem("user", JSON.stringify(response));
                this.refreshImage(base64Image);
              });
            }
        };
    }
  }

  refreshImage(base64Image: string) {
    let imageUrl = 'data:image/jpeg;base64,' + base64Image;
    this.imagesSafeUrl = this.sanitizer.bypassSecurityTrustUrl(imageUrl);
    
  }
}