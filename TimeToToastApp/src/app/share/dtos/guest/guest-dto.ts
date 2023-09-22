import { Person } from "../../models/person.interface";

export interface GuestDto extends Person{
    idGuest: number,
    
    idEvent: number,
    role: string,
    isPresent: boolean,
    token: string,
}
