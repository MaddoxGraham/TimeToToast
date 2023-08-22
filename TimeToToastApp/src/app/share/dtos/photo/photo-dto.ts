import { GuestDto } from "../guest/guest-dto";
import { UserDto } from "../user/user-dto";

export interface PhotoDto {
    idPhoto:number,
    user:UserDto,
    guest:GuestDto,
    event:Event,
    comments:Comment[]
}
