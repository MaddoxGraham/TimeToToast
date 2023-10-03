import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TabMenuModule } from 'primeng/tabmenu';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { DropdownModule } from 'primeng/dropdown';
import { ButtonModule } from 'primeng/button';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ToastModule } from 'primeng/toast';
import { MultiSelectModule } from 'primeng/multiselect';
import { ToggleButtonModule } from 'primeng/togglebutton';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { CardModule } from 'primeng/card';
import { FileUploadModule } from 'primeng/fileupload';
import { GalleriaModule } from 'primeng/galleria';
import { SelectButtonModule } from 'primeng/selectbutton';
import { CalendarModule } from 'primeng/calendar';
import { DataViewModule } from 'primeng/dataview';
import { RatingModule } from 'primeng/rating';
import { TagModule } from 'primeng/tag';
import { CascadeSelectModule } from 'primeng/cascadeselect';
import { InputNumberModule } from 'primeng/inputnumber';
import { FormatDatePipe } from './pipes/format-date/format-date-pipe';
import { FieldsetModule } from 'primeng/fieldset';
import { ListboxModule } from 'primeng/listbox';

@NgModule({
  declarations: [
    FormatDatePipe,
  ],
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
    MultiSelectModule,
    ToggleButtonModule,
    InputTextareaModule,
    CardModule,
    FileUploadModule,
    GalleriaModule,
    SelectButtonModule,
    CalendarModule,
    DataViewModule,
    RatingModule,
    TagModule,
    CascadeSelectModule,
    InputNumberModule,
    FieldsetModule,
    ListboxModule
    
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
    MultiSelectModule,
    ToggleButtonModule,
    InputTextareaModule,
    CardModule,
    FileUploadModule,
    GalleriaModule,
    SelectButtonModule,
    CalendarModule,
    RatingModule,
    DataViewModule,
    TagModule,
    CascadeSelectModule,
    InputNumberModule,
    FormatDatePipe,
    FieldsetModule,
    ListboxModule
  ]
})
export class ShareModule { }
