import { EventDto } from "../event/event-dto";
import { GiftContributionDto } from "../giftContribution/gift-contribution-dto";

export interface GiftDto {
    idGift:number,
    event:EventDto,
    contributions:GiftContributionDto[]
}
