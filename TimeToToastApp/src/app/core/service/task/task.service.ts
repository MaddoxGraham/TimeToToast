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
}
