import { GuestDto } from "../guest/guest-dto";

export interface GiftContributionDto {
    idGiftContribution:number,
    amount:number,
    idGift: number,
    idPerson?: number,
    guest?:GuestDto | number,
}
