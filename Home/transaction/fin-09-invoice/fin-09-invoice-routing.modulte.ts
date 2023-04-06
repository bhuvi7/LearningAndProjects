import { ViewComponent } from './fin-09/view/view.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { Fin09InvoiceListComponent } from './fin-09-list-page/fin-09-invoice-list.component';
import { Fin09CreateComponent } from './fin-09/fin-09-create.component';
import { Fin09InProgressListComponent } from './fin-09/fin-09-inprogress-list.component';
import { Fin09ApprovedListComponent } from './fin-09/fin-09-approved-list.component';
import { Fin09ApproveComponent } from './fin-09/fin-09-approve.component';

const routes: Routes = [
  {
    path: '',
    children: [
      { path: 'fin-09-list-page', component: Fin09InvoiceListComponent },
      { path: 'fin-09-create', component: Fin09CreateComponent },
      { path: 'fin-09-inprogress-list', component: Fin09InProgressListComponent },
      { path: 'fin-09-approve/:_id', component: Fin09ApproveComponent },
      { path: 'fin-09-approved-list', component: Fin09ApprovedListComponent },
      { path: 'fin-09-view', component: ViewComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class Fin09InvoiceRoutingModule { }
