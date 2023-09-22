import { GuestDto } from "../guest/guest-dto";
import { UserDto } from "../user/user-dto";

export interface TaskDto {
    idTask?:number,
    description?:String,
    urgence?:number,
    dateTask?:Date,
    event?:Event,
    creator?:UserDto,
    assigneeUser?:UserDto,
    assigneeGuest?:GuestDto
}
