import { GiftContribution } from "./GiftContribution";

export interface Gift {
    idGift:number,
    event:Event,
    contributions:GiftContribution[]
 }