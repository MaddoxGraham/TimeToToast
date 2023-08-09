import { Gift } from "./Gift";
import { Guest } from "./Guest";
import { User } from "./User";

export interface GiftContribution { 
    idGiftContribution:number,
    amount:number,
    gift:Gift,
    user?:User,
    guest?:Guest
}