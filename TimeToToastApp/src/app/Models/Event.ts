import { Gift } from "./Gift";
import { Guest } from "./Guest";
import { Photo } from "./Photo";
import { Task } from "./Task";
import { UserEventRole } from "./UserEventRole";

export interface Event {
    idEvent:number,
    userEventRoles: UserEventRole[],
    guests:Guest[],
    photos:Photo[],
    gifts:Gift[],
    comments:Comment[],
    tasks:Task[]
 }