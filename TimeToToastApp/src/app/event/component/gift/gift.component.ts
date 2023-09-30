import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Subscription } from 'rxjs';
import { GiftService } from 'src/app/core/service/gift/gift.service';
import { SharedService } from 'src/app/core/service/shared/shared.service';
import { EventDto } from 'src/app/share/dtos/event/event-dto';
import { GiftDto } from 'src/app/share/dtos/gift/gift-dto';
import { GiftContributionDto } from 'src/app/share/dtos/giftContribution/gift-contribution-dto';
import { GuestDto } from 'src/app/share/dtos/guest/guest-dto';
import { UserDto } from 'src/app/share/dtos/user/user-dto';
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
  @Input() user!: UserDto;

  constructor(private sharedService: SharedService,private giftService : GiftService,) { }
  gifts: GiftDto[] = [];
  gift!:GiftDto;
  layout: 'list' | 'grid' = 'list';
  isOpen = false; 
  contributionMap: { [key: number]: GiftContributionDto[] } = {};
  showModal:boolean =false;
  amount!:number;
  showInput: boolean = false;
  fullAmount: boolean = false;
  newContribution!: GiftContributionDto;
  isContributed:boolean = false;
  totalAmount: { [key: number]: number } = {};
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
    // this.totalAmount = this.contributionMap[gift.idGift].reduce((sum, contribution) => sum + contribution.amount, 0);
    result.severity = 'warning';
    result.message = `PARTIELLEMENT FINANCÉ - Total : ${this.totalAmount}`;
  
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
        this.contributionMap[gift] = reponse; 
        this.totalAmount[gift] = reponse.reduce((sum, contribution) => sum + contribution.amount, 0);
      },
      (error) => {
      
      }
    )

  }

  openLinkAndShowModal(gift: GiftDto) {
     if (gift.url) {
    window.open(gift.url, '_blank');  
    this.gift= gift
  }
  this.showModal = true; 
  }
  
  contributeToGift(isContributed: boolean, amount?: number) {
    this.newContribution = { } as GiftContributionDto;
    console.log("amount : " + amount + " user : " + this.user + " gift :  " + this.gift.idGift )
    
    if (isContributed && amount !== undefined) {
      this.newContribution.amount = amount;
      if (this.user && this.user.idPerson !== undefined) {
        this.newContribution.idPerson = this.user.idPerson;
      }
      this.newContribution.idGift = this.gift.idGift;
      console.log('totalité de new contribution : ' + this.newContribution)
      this.giftService.addContribution(this.newContribution).subscribe(
        response => {
        console.log(response);
      },
      error => {
        console.log('Erreur lors de l’ajout de la contribution', error);
      }
    );
    }
    this.showModal = false;  
    this.isContributed=false
    this.showInput = false;  
    this.amount = 0;      
    this.fullAmount = false; 
  }

  getUpdatedPrice(gift: GiftDto): number {
    if (this.totalAmount[gift.idGift] !== undefined) {
      return gift.price - this.totalAmount[gift.idGift];
    }
    return gift.price;
  }
  

}
