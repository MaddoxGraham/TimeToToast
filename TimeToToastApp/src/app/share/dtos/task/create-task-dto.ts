
import { GuestDto } from "../guest/guest-dto";
import { UserDto } from "../user/user-dto";

export interface CreateTaskDto {
export interface CreateTaskDto {
    idTask: number,

    assignee: number[],
    description: string,
    dueDate: Date,
    invisibleTo: number[]
    urgency: number,
    event: number,
    creator: number

}