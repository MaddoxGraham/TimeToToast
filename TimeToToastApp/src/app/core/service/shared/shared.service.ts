import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";

@Injectable({
    providedIn: 'root',
  })
  export class SharedService {
    private guestsSubject = new BehaviorSubject<any[]>([]);
    guests$ = this.guestsSubject.asObservable();
  
    updateGuests(newGuests: any[]) {
      this.guestsSubject.next(newGuests);
    }
  }