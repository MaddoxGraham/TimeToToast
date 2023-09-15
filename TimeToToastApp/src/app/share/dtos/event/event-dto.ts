import { CommentDto } from "../coment/comment-dto";
import { GiftDto } from "../gift/gift-dto";
import { GuestDto } from "../guest/guest-dto";
import { PhotoDto } from "../photo/photo-dto";
import { TaskDto } from "../task/task-dto";
import { UserEventRoleDto } from "../userEventRole/user-event-role-dto";

export interface EventDto {
    idEvent:number,
    categorie:string,
    description:string,
    title:string,
    adresse:string,
    ville:string,
    cp:string,
    eventDate:Date,
    createdAt:Date,
    startTime: string,
    taskModuleActive:boolean,
    GiftModuleActive:boolean,
    PhotoModuleActive:boolean,
    userEventRoles: UserEventRoleDto[],
    guests:GuestDto[],
    photos:PhotoDto[],
    gifts:GiftDto[],
    comments:CommentDto[],
    tasks:TaskDto[]
}
