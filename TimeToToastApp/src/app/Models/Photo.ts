import { Guest } from "./Guest";
import { User } from "./User";

export interface Photo { 
    idPhoto:number,
    user:User,
    guest:Guest,
    event:Event,
    comments:Comment[]
}