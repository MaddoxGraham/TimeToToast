import { GiftContribution } from "./GiftContribution";
import { Photo } from "./Photo";
import { Task } from "./Task";

export interface Guest {
    idGuest:number,
    token:number,
    event:Event,
    photos:Photo[],
    contributions:GiftContribution[],
    comments:Comment[],
    assignedTasks:Task[]
 }