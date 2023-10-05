import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreateTaskDto } from 'src/app/share/dtos/task/create-task-dto';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private httpClient: HttpClient) { }

  addTask(task: CreateTaskDto): Observable<CreateTaskDto> {
    return this.httpClient.post<CreateTaskDto>(environment.addTask, task);
  }

  getAllTask(idEvent: number): Observable<CreateTaskDto[]> {
    return this.httpClient.get<CreateTaskDto[]>(`${environment.getAllTaskOfEvent}${idEvent}`)
  }

  addAssignee(idTask: number, idPerson: number): Observable<any> {
    return this.httpClient.post(`${environment.addTaskAssignee}${idTask}`, idPerson)
  }

  giveUpTask(idTask: number, idPerson: number): Observable<any> {
    return this.httpClient.post(`${environment.removeTaskAssignee}${idTask}`, idPerson)
  }

  deleteTask(idTask: number): Observable<any> {
    return this.httpClient.delete(`${environment.deleteTask}${idTask}`)
  }
}
