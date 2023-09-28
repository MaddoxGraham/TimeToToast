import { EventDto } from "../event/event-dto";
import { GiftContributionDto } from "../giftContribution/gift-contribution-dto";

export interface GiftDto {
    idGift:number,
    name: string,
    url:string,
    photo:string,
    wanted:number,
    price:number,
    categorie:string,
    isPaid:boolean,
    event:EventDto,
    contributions:GiftContributionDto[]
}
