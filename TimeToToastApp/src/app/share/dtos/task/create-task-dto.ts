import { GuestDto } from "../guest/guest-dto";
import { UserDto } from "../user/user-dto";

export interface CreateTaskDto {
    assignee: number[],
    description: string,
    dueDate: Date,
    invisibleTo: number[]
    urgency: number,
}