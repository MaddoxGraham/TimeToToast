import { UserDto } from "../user/user-dto";

export interface UserEventRoleDto {
    idPerson:number,
    idEvent:number,
    role:string,
    persons: UserDto[];
}
