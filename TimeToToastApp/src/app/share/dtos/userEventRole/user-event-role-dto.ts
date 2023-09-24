import { UserDto } from "../user/user-dto";

export interface UserEventRoleDto {
    idUser:number,
    idEvent:number,
    role:string,
    persons: UserDto[];
}
