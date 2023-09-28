import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { GiftService } from 'src/app/core/service/gift/gift.service';
import { SharedService } from 'src/app/core/service/shared/shared.service';
import { EventDto } from 'src/app/share/dtos/event/event-dto';
import { GiftDto } from 'src/app/share/dtos/gift/gift-dto';
import { GiftContributionDto } from 'src/app/share/dtos/giftContribution/gift-contribution-dto';
import { UserEventRoleDto } from 'src/app/share/dtos/userEventRole/user-event-role-dto';

@Component({
  selector: 'app-gift',
  templateUrl: './gift.component.html',
  styleUrls: ['./gift.component.css']
})
export class GiftComponent implements OnInit {
  @Output() moduleDeleted = new EventEmitter<void>();
  @Input() userEvent!: UserEventRoleDto;
  @Input() event!: EventDto;

  constructor(private sharedService: SharedService,private giftService : GiftService) { }
  gifts: GiftDto[] = [];
  layout: 'list' | 'grid' = 'list';
  isOpen = false; 
  contributionMap: { [key: number]: GiftContributionDto[] } = {};
  showModal:boolean =false;
  amount!:number;

  ngOnInit() {
    if( this.isOpen = true){
    this.getGifts();
    console.log(this.contributionMap);
    }
  }

  getSeverity(gift: GiftDto) {
    let result = { severity: '', message: '' };

  if (gift.isPaid) {
    result.severity = 'danger';
    result.message = 'CADEAUX DÉJÀ ACHETÉ PAR UN MEMBRE';
  } else if (this.contributionMap[gift.idGift] && this.contributionMap[gift.idGift].length > 0) {
    result.severity = 'warning';
    result.message = 'PARTIELLEMENT FINANCÉ';
  } else {
    result.severity = 'success';
    result.message = 'DISPONIBLE POUR ACHAT';
  }

  return result;
  }


  getGifts(){
    this.giftService.getGifts(this.event.idEvent).subscribe(
      (reponse) => {
        this.gifts = reponse;
        this.gifts.forEach(gift => {
          this.getContributions(gift.idGift);
        });
      },
      (error) => {
        console.error('Erreur:', error); 
      }
    )
  }

  getContributions(gift:number){
    this.giftService.getContributions(gift).subscribe(
      (reponse) => {
        this.contributionMap[gift] = reponse; // faire un tableau avec pour clée l'id du cadeau et un tableau des contributions.
      },
      (error) => {
      
      }
    )

  }

  openLinkAndShowModal(gift: any) {
     if (gift.url) {
    window.open(gift.url, '_blank');  // Ouvre le lien du cadeau dans un nouvel onglet
  }
  this.showModal = true; // Affiche la modale
  console.log(this.showModal);
  }
  
  contributeToGift(isContributed: boolean, amount?: number) {
    if (isContributed) {
      // Ajouter une nouvelle entrée dans giftContribution
      // Utilise l'argument 'amount' pour savoir de combien la personne a participé
    }
    this.showModal = false;  // Ferme la modale
  }

}

