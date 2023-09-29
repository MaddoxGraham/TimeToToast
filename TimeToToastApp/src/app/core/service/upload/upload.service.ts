import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { ImportPhotoDto } from 'src/app/share/dtos/photo/import-photo-dto';

@Injectable({
  providedIn: 'root',
})
export class UploadService {

  constructor(private http: HttpClient) {}

  uploadFiles(files: FormData, idUser:number, idEvent:number): Observable<any> {
    return this.http.post(`${environment.uploadPhoto}/${idEvent}/${idUser}`, files);
  }

  getPhotos(idEvent: number): Observable<ImportPhotoDto[]> {
    return this.http.get<ImportPhotoDto[]>(`${environment.getPhotos}/${idEvent}`).pipe(
      map((response: ImportPhotoDto[]) => { 
        return response })
    )
  }


}