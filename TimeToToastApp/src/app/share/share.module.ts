import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TabMenuModule } from 'primeng/tabmenu';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { DropdownModule } from 'primeng/dropdown';
import { ButtonModule } from 'primeng/button';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    TabMenuModule,
    FormsModule,
    ReactiveFormsModule,
    ProgressSpinnerModule,
    DropdownModule,
    ButtonModule,
  ],
  exports: [
    TabMenuModule,
    FormsModule,
    ReactiveFormsModule,
    ProgressSpinnerModule,
    DropdownModule,
    ButtonModule,
  ]
})
export class ShareModule { }
