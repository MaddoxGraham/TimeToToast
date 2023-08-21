import { GuestDto } from "../guest/guest-dto";
import { PhotoDto } from "../photo/photo-dto";
import { UserDto } from "../user/user-dto";

export interface CommentDto {
    idComment:number,
    user:UserDto,
    guest:GuestDto,
    event:Event,
    photo:PhotoDto,
    parentComment:Comment,
    replies:Comment[]
}
