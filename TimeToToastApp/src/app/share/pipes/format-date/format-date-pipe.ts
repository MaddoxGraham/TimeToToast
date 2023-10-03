import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'formatDate'
})
export class FormatDatePipe implements PipeTransform {

  private readonly months: string[] = [
    'janvier', 'février', 'mars', 'avril', 'mai', 'juin',
    'juillet', 'août', 'septembre', 'octobre', 'novembre', 'décembre'
  ];

  transform(input: [number, number, number] | string | Date): string {
    let day: number;
    let month: number;
    let year: number;

    if (Array.isArray(input)) { // si c'est un tableau, on déstructure les valeurs
      [year, month, day] = input;
      month -= 1; // les mois sont indexés à partir de 0 en JS
    } else if (typeof input === 'string') { // si c'est une chaîne, on convertit en Date
      const date = new Date(input);
      day = date.getDate();
      month = date.getMonth();
      year = date.getFullYear();
    } else if (input instanceof Date) { // si c'est déjà un objet Date
      day = input.getDate();
      month = input.getMonth();
      year = input.getFullYear();
    } else {
      throw new Error('Invalid input for formatDate pipe');
    }

    const monthName = this.months[month];
    return `${day} ${monthName} ${year}`;
  }
}
