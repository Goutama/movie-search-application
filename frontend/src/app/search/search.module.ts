import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SearchPageComponent} from './search-page/search-page.component';
import {SearchRoutingModule} from './search-routing.module';
import {MaterialModule} from '../material.module';
import {FormsModule} from '@angular/forms';
import {MatRadioModule} from '@angular/material/radio';

@NgModule({
  declarations: [SearchPageComponent],
  imports: [
    CommonModule,
    SearchRoutingModule,
    MaterialModule,
    FormsModule,
    MatRadioModule
  ]
})
export class SearchModule { }
