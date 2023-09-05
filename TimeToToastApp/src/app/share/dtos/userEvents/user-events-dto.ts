import { EventDto } from "../event/event-dto";
import { UserDto } from "../user/user-dto";

export interface UserEventsDto {
    userId: number;
    role: string;
    events: EventDto[];
    users: UserDto[];
}