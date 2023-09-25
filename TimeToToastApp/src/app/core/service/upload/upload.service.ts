import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PhotoDto } from 'src/app/share/dtos/photo/photo-dto';

@Injectable({
  providedIn: 'root',
})
export class UploadService {

  constructor(private http: HttpClient) {}

  uploadFiles(files: FormData, idUser:number, idEvent:number): Observable<any> {
    return this.http.post(`${environment.uploadPhoto}/${idEvent}/${idUser}`, files);
  }

  getPhotos(idEvent: number): Observable<String[]> {
    return this.http.get<String[]>(`${environment.getPhotos}/${idEvent}`);
  }
}