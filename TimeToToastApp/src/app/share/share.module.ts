import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TabMenuModule } from 'primeng/tabmenu';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    TabMenuModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  exports: [
    TabMenuModule,
    FormsModule,
    ReactiveFormsModule,
  ]
})
export class ShareModule { }
