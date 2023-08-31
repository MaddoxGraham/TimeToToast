import { EventDto } from "../event/event-dto";

export interface UserEventsDto {
    userId: number;
    role: string;
    events: EventDto[];
}