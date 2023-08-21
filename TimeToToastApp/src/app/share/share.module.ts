import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TabMenuModule } from 'primeng/tabmenu';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    TabMenuModule
  ],
  exports: [
    TabMenuModule,
  ]
})
export class ShareModule { }
