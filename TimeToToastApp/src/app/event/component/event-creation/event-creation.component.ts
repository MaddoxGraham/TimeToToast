import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-event-creation',
  templateUrl: './event-creation.component.html',
  styleUrls: ['./event-creation.component.css']
})
export class EventCreationComponent implements OnInit {
  public form!: FormGroup;
  public useCustomCategory: boolean = false;
  public completedSteps: any[] = [];
  public currentStep: number = 1;

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      category: ['', Validators.required],
      customCategory: [''],
      eventTitle: ['', Validators.required],
      eventDescription: [''],
      eventDate: ['', Validators.required]
    });

    // Désactiver les champs inutilisés au départ
    this.form.get('eventTitle')?.disable();
    this.form.get('eventDescription')?.disable();
    this.form.get('eventDate')?.disable();
  }

  toggleInput() {
    this.useCustomCategory = !this.useCustomCategory;
    if (this.useCustomCategory) {
      this.form.get('category')?.disable();
      this.form.get('category')?.reset(); // Ajouté
      this.form.get('customCategory')?.enable();
      this.form.get('customCategory')?.setValidators([Validators.required]);
      this.form.get('customCategory')?.updateValueAndValidity();
    } else {
      this.form.get('customCategory')?.disable();
      this.form.get('customCategory')?.reset(); // Ajouté
      this.form.get('category')?.enable();
      this.form.get('category')?.setValidators([Validators.required]);
      this.form.get('category')?.updateValueAndValidity();
    }
  }

  continue() {
    this.completedSteps.push({ step: this.currentStep, value: this.form.value });
    this.currentStep++;
    if (this.currentStep === 2) {
      this.form.get('category')?.disable();
      this.form.get('customCategory')?.disable();
      this.form.get('eventTitle')?.enable();
      this.form.get('eventDescription')?.enable();
      this.form.get('eventDate')?.enable();
    }
    this.form.reset();
  }

  goBack() {
    this.currentStep--;
    const lastStep = this.completedSteps.pop();
    if (lastStep) {
      this.form.patchValue(lastStep.value);
    }
    if (this.currentStep === 1) {
      this.form.get('eventTitle')?.disable();
      this.form.get('eventDescription')?.disable();
      this.form.get('eventDate')?.disable();
      if (this.useCustomCategory) {
        this.form.get('category')?.disable();
        this.form.get('customCategory')?.enable();
      } else {
        this.form.get('category')?.enable();
        this.form.get('customCategory')?.disable();
      }
    }
  }
}
