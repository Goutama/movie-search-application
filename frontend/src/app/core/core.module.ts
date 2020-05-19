import {AppRoutingModule} from '../app-routing.module';
import {HeaderComponent} from './header/header.component';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatToolbarModule} from '@angular/material/toolbar';


@NgModule({
  declarations: [HeaderComponent],
  imports: [
    CommonModule,
    AppRoutingModule,
    MatToolbarModule
  ],
  exports: [HeaderComponent],
  providers: [
  ]
})
export class CoreModule { }
