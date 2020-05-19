import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SearchPageComponent} from './search-page/search-page.component';
import {SearchRoutingModule} from './search-routing.module';
import {MaterialModule} from "../material.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [SearchPageComponent],
  imports: [
    CommonModule,
    SearchRoutingModule,
    MaterialModule,
    FormsModule
  ]
})
export class SearchModule { }
