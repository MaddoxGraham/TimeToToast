import { GiftContributionDto } from "../giftContribution/gift-contribution-dto";
import { TaskDto } from "../task/task-dto";
import { UserEventRoleDto } from "../userEventRole/user-event-role-dto";

export interface UserDto {
    idUser?:number,


    firstName?:string;
    lastName?:string;
    adresse?:string;
    cp?:string;
    ville?:string;
    birthday?:Date;
    avatar?:string;
    phone?:string,
    email:string;

    password:string,
    login:string,
    token:string,
    role?:string,
    refreshToken: string,
    userEventRoles?: UserEventRoleDto[],
    contributions?: GiftContributionDto[],
    comments?: Comment[],
    assignedTasks?: TaskDto[],
}
