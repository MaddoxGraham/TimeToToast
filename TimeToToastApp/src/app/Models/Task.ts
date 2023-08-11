import { Guest } from "./Guest";
import { User } from "./User";

export interface Task { 
    idTask:number,
    event:Event,
    creator:User,
    assigneeUser:User,
    assigneeGuest:Guest
}