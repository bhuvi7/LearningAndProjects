import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PAMIComponent } from './pami.component';

const routes: Routes = [
  {
    path: '', component: PAMIComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PAMIRoutingModule { }
