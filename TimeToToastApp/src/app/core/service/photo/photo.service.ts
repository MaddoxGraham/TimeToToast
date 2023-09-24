import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root',
})
export class PhotoService {

    private photos: any[] = [
        { source: 'assets/photo1.jpg', alt: 'Description 1', title: 'Title 1' },
        { source: 'assets/photo2.jpg', alt: 'Description 2', title: 'Title 2' },
        // Ajoute plus de photos si nécessaire
      ];
    
      constructor() {}
    
      getImages(): Promise<any[]> {
        return new Promise<any[]>((resolve, reject) => {
          // Ici, tu pourrais effectuer une requête HTTP pour récupérer les photos
          // Pour le moment, retourne simplement le tableau de photos
          resolve(this.photos);
        });
      }
}