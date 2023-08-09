import { Guest } from "./Guest";
import { Photo } from "./Photo";
import { User } from "./User";

export interface Comment { 
    idComment:number,
    user:User,
    guest:Guest,
    event:Event,
    photo:Photo,
    parentComment:Comment,
    replies:Comment[]
}