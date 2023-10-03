export interface CreateTaskDto {
    assignee: number[],
    description: string,
    dueDate: Date,
    invisibleTo: number[]
    urgency: number,
    event: number,
    creator: number
}