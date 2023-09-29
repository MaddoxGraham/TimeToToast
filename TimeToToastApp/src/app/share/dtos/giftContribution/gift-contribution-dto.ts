import { GiftDto } from "../gift/gift-dto";
import { GuestDto } from "../guest/guest-dto";
import { UserDto } from "../user/user-dto";

export interface GiftContributionDto {
    idGiftContribution:number,
    amount:number,
    idGift: number,
    idPerson?: number,
    guest?:GuestDto | number,
}
