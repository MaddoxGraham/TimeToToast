import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GiftListService {

  getCategories() {
    return [
      {
        nom: 'Technologie',
        sousCategories: [
          { nom: 'Ordinateurs' },
          { nom: 'Smartphones' },
          { nom: 'Jeux vidéo' }
        ]
      },
      {
        nom: 'Maison',
        sousCategories: [
          { nom: 'Électroménager'},
          { nom: 'Décoration' },
          { nom: 'Meubles' },
          { nom: 'Luminaires' },
        ]
      },
      {
        nom: 'Vêtements',
        sousCategories: [
          { nom: 'Homme' },
          { nom: 'Femme' },
          { nom: 'Enfants' }
        ]
      },
      {
        nom: 'Loisirs',
        sousCategories: [
          { nom: 'Livres'},
          { nom: 'Sports' },
          { nom: 'Musique' }
        ]
      },
      {
        nom: 'Alimentation',
     
        sousCategories: [
          { nom: 'Boissons' },
          { nom: 'Snacks'},
          { nom: 'Produits frais' }
        ]
      },
      {
        nom: 'Autre',
        sousCategories: [ { nom: 'Autre' }]
      }
    ];
  }
}
