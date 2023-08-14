import { UserEventRole } from "./UserEventRole"
import { Task } from "./Task"
import { Comment } from "./Comment"
import { GiftContribution } from "./GiftContribution"

export interface User {
  idUser:number,
  name : string,
  password:string,
  login:string,
  userEventRoles : UserEventRole[],
  contributions : GiftContribution[],
  comments : Comment[],
  assignedTasks: Task[],
  }
