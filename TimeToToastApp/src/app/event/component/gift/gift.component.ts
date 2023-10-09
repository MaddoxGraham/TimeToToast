import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl,  FormBuilder, FormGroup, Validators } from '@angular/forms';
import { GiftService } from 'src/app/core/service/gift/gift.service';
import { GiftListService } from 'src/app/core/service/giftList/gift-list.service';
import { SharedService } from 'src/app/core/service/shared/shared.service';
import { EventDto } from 'src/app/share/dtos/event/event-dto';
import { GiftDto } from 'src/app/share/dtos/gift/gift-dto';
import { GiftContributionDto } from 'src/app/share/dtos/giftContribution/gift-contribution-dto';
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

  constructor(private sharedService: SharedService,
              private giftService : GiftService,
              private fb: FormBuilder,
              private giftList:GiftListService,
              ) { }


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
  displayMode:string = 'galleria';
  addGiftForm!:FormGroup;
  
  categories: any[] | undefined;
  selectedCategory!:any;
  
  imageUrl: string = '';
  showImage: boolean = false;

  ngOnInit() {
    if( this.isOpen = true){
    this.getGifts();
    console.log(this.contributionMap);
    }

    this.categories = this.giftList.getCategories()

    this.addGiftForm = this.fb.group({
      name: ['', Validators.required],
    selectedCategory: new FormControl<object | null>(null),
      url:['', Validators.required],
      photo:['', Validators.required],
      wanted:[''],
      price:[ , Validators.required],
      event:this.event.idEvent,
     
    });
   
    this.addGiftForm.get('photo')?.valueChanges.subscribe(photo => {
      console.log("URL changed to:", photo);  
      this.showImage = this.isValidUrl(photo);
    });


  }

  addGift(){  
   
    console.log("L'id de mon evenement " + this.addGiftForm.value.selectedCategory);
    if (this.addGiftForm.valid) {
      const data = {...this.addGiftForm.value};
      if (data.selectedCategory && data.selectedCategory.nom) {
        data.categorie = data.selectedCategory.nom;
      }
      delete data.selectedCategory;
      console.log('Form Submitted!', data);
      this.giftService.addGift(this.addGiftForm.value).subscribe(
        (reponse) => {console.log(reponse)
          });
    } else {
      console.log('Form is invalid!');
      this.addGiftForm.markAllAsTouched(); // Trigger validation messages
    }
  }

  isValidUrl(photo: string): boolean {
    return photo.startsWith('http'); 
  }
  
  onPriceInput(event: any) {
    let value = event.target.value;
    value = value.replace(',', '.'); 
    value = value.replace(/[^0-9.]/g, '');
    this.addGiftForm.get('price')?.setValue(value);
  }


  getSeverity(gift: GiftDto) {
    let result = { severity: '', message: '' };

  if (gift.paid) {
    result.severity = 'danger';
    result.message = 'CADEAUX DÉJÀ ACHETÉ PAR UN MEMBRE';
    return result;
  } 
   if (this.contributionMap[gift.idGift] && this.contributionMap[gift.idGift].length > 0) {
    if (this.getUpdatedPrice(gift) != 0) {
          result.severity = 'warning';
    result.message = `PARTIELLEMENT FINANCÉ`;
    return result;
    }         
  }
    result.severity = 'success';
    result.message = 'DISPONIBLE POUR ACHAT';
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

  updatePaid(gift: GiftDto){
    this.giftService.giftIsPaid(gift).subscribe(
      (reponse) => {
        console.log(reponse)
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
        const updatedPrice = this.getUpdatedPrice(this.gift)
        console.log("Je suis bloqué par : " + updatedPrice);
        if (updatedPrice <= 0 ){
          console.log("Le cadeaux est payé ! ");
          this.updatePaid(this.gift);
          this.getSeverity(this.gift);
         
        }

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
    const closeButton: HTMLElement = document.getElementById('closeModalBtn') as HTMLElement;
  closeButton.click(); 
  window.location.reload();
  }

  getUpdatedPrice(gift: GiftDto): number {
    if (this.totalAmount[gift.idGift] !== undefined) {
      return gift.price - this.totalAmount[gift.idGift];
    }
    return gift.price;
  }

  setDisplayMode(display:string){
    if (display == 'addGift') {
      this.displayMode = 'addGift';
    }
    if (display == 'galleria') {
      this.displayMode = 'galleria'
    }
   
  }


  adjustAmount() {
    if (this.amount < 0) {
      this.amount = 0;
    }
    if (this.amount > this.gift.price) {
      this.amount = this.gift.price;
    }
  }



}
