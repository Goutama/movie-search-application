import {SearchModule} from './search/search.module';
import {NgModule} from '@angular/core';
import {PreloadAllModules, RouterModule, Routes} from '@angular/router';


const routes: Routes = [
    {
       path: '',
       loadChildren: () => SearchModule
      }
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
