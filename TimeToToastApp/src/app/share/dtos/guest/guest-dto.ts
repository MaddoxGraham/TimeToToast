import { Person } from "../../models/person.interface";

export interface GuestDto extends Person{
    idPerson: number,
    
    eventId: number,
    role: string,
    isPresent: boolean,
    token: string,
}
