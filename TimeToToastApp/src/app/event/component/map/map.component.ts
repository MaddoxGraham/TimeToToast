import { AfterViewInit, Component, Input, OnInit } from '@angular/core';

import * as L from 'leaflet';
import { GeocodingService } from 'src/app/core/service/geocoding/geocoding.service';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: []
})
export class MapComponent implements AfterViewInit  {
 @Input() completeAddress!: string;
 @Input() eventId!: number;
  map!: L.Map;

  constructor(private geocodingService: GeocodingService) { }

  ngAfterViewInit(): void {
    setTimeout(() => {
        this.initializeMap();
      }, 0);
    console.log(this.eventId)
  }

  async initializeMap(): Promise<void> {
    
    this.map = L.map(`map-${this.eventId}`).setView([0, 0], 13);
const response = await this.geocodingService.getCoordinatesByAddress(this.completeAddress).toPromise();

if (response.length > 0) {
  const { lat, lon } = response[0];
  this.map.setView([lat, lon], 13);

  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '© OpenStreetMap contributors'
  }).addTo(this.map);

  L.marker([lat, lon]).addTo(this.map).bindPopup(this.completeAddress).openPopup();
}
  }
}
