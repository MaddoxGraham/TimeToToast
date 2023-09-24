import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TabMenuModule } from 'primeng/tabmenu';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { DropdownModule } from 'primeng/dropdown';
import { ButtonModule } from 'primeng/button';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ToastModule } from 'primeng/toast';


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
    ConfirmDialogModule,
    ToastModule,
  ],
  exports: [
    TabMenuModule,
    FormsModule,
    ReactiveFormsModule,
    ProgressSpinnerModule,
    DropdownModule,
    ButtonModule,
    ConfirmDialogModule,
    ToastModule,
  ]
})
export class ShareModule { }
