import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { GiftService } from 'src/app/core/service/service/gift.service';
import { SharedService } from 'src/app/core/service/shared/shared.service';
import { EventDto } from 'src/app/share/dtos/event/event-dto';
import { GiftDto } from 'src/app/share/dtos/gift/gift-dto';
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


  ngOnInit() {
    if( this.isOpen = true){
    this.getGifts()
    }
  }

  getSeverity(gift: GiftDto) {
    switch (gift.isPaid){}
   
     // case gift.isPaid  
     // case !gift.isPaid 

      // switch (product.inventoryStatus) {
      //     case 'INSTOCK':
      //         return 'success';

      //     case 'LOWSTOCK':
      //         return 'warning';

      //     case 'OUTOFSTOCK':
      //         return 'danger';

      //     default:
      //         return null;
      // }
      // Check si le produit a été acheté , a des contributions mais il manque encore pour faire la totalité, est disponible à l'achat
      // attention le acheté = isPaid est à true. 
      //Il manque isPaid est à false mais il existe une entry dans giftContribution avec ce giftId et sinon
      // false et il existe pas d'enty pour dispo. 
  }


  getGifts(){
    this.giftService.getGifts(this.event.idEvent).subscribe(
      (reponse) => {
        this.gifts = reponse;
        console.log(this.gifts); 
      },
      (error) => {
        console.error('Erreur:', error); 
      }
    )
  }

  


}

