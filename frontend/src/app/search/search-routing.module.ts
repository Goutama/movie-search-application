import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';

import {SearchPageComponent} from './search-page/search-page.component';

const searchRoutes: Routes = [
  { path: '', component: SearchPageComponent }
];

@NgModule({
  imports: [RouterModule.forChild(searchRoutes)],
  exports: [RouterModule]
})
export class SearchRoutingModule {}
