import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SAListPageComponent } from './sa-listpage.component';
import { SAUpdateComponent } from './sa-update.component';

const routes: Routes = [
  {
    path: '',
    children: [
      { path: 'sa-list', component: SAListPageComponent },
      { path: 'sa-update/:_id', component: SAUpdateComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SARoutingModule { }
