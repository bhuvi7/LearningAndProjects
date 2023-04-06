import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { listpageComponent } from './pai-listpage.component';
import { updatedetailsComponent } from './pai-update.component';

const routes: Routes = [
  {
    path: '',
    children: [
      { path: 'pai-list', component: listpageComponent },
      { path: 'pai-updatedetails/:_id', component: updatedetailsComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PaymentAgainstInvoiceRoutingModule { }
