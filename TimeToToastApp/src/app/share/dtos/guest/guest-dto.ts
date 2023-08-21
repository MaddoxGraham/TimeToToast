import { CommentDto } from "../coment/comment-dto";
import { EventDto } from "../event/event-dto";
import { GiftContributionDto } from "../giftContribution/gift-contribution-dto";
import { PhotoDto } from "../photo/photo-dto";
import { TaskDto } from "../task/task-dto";

export interface GuestDto {
    idGuest:number,
    token:number,
    event:EventDto,
    photos:PhotoDto[],
    contributions:GiftContributionDto[],
    comments:CommentDto[],
    assignedTasks:TaskDto[]
}
