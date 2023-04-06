import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { Fin06CreateComponent } from "../fin-01-invoice/supporting-document/fin-06/fin-06-create.component";
import { Fin06InProgressListComponent } from "../fin-01-invoice/supporting-document/fin-06/fin-06-inprogress.component";
import { Fin06ApproveComponent } from './supporting-document/fin-06/fin-06-approve.component';
import { Fin06CreateListComponent } from "../fin-01-invoice/supporting-document/fin-06/fin-06-create-list.component";
import { Fin06ApprovedListComponent } from './supporting-document/fin-06/fin-06-approved.component';
import { Fin01CreateListComponent } from './supporting-document/fin01/fin-01-create-list.component';
import { Fin01CreateComponent } from './supporting-document/fin01/fin-01-create.component';
import { Fin01InProgressListComponent } from './supporting-document/fin01/fin-01-inprogress-list.component';
import { Fin01ApproveComponent } from './supporting-document/fin01/fin-01-approve.component';
import { Fin01ApprovedListComponent } from './supporting-document/fin01/fin-01-approved-list.component';
import { FIN01CreateListComponent } from './FIN01/FIN-01-create-list.component';
import { FIN01CreateComponent } from './FIN01/FIN-01-create.component';
import { FIN01InProgressListComponent } from './FIN01/FIN-01-inprogress-list.component';
import { FIN01ApproveComponent } from './FIN01/FIN-01-approve.component';
import { Fin01InvExceptionComponent } from './supporting-document/fin01/fin-01-inv-exception.component';
import { FIN01InvExceptionComponent } from './FIN01/FIN-01-Inv-Exception.component';
import { FIN01ApprovedListComponent } from './FIN01/FIN-01-approved-list.component';
import { Fin01ListPageComponent } from "./supporting-document/fin-01-list-page/fin-01-list-page.component";

const routes: Routes = [
  {
    path: '',
    children: [
      { path: 'fin-01-list-page', component: Fin01ListPageComponent },
      { path: 'fin-06-create-list/:_indicator', component: Fin06CreateListComponent },
      // { path: 'fin-06-create-list/older', component: Fin06CreateListComponent },
      { path: 'fin-06-create/:_clinicCode/:_month/:_year', component: Fin06CreateComponent },
      
      { path: 'fin-06-inprogress/:_indicator', component: Fin06InProgressListComponent },
      // { path: 'fin-06-inprogress/older', component: Fin06InProgressListComponent },
      { path: 'fin-06-approve/:_id', component: Fin06ApproveComponent },
      { path: 'fin-06-approved/:_indicator', component: Fin06ApprovedListComponent },
      // { path: 'fin-06-approved/older', component: Fin06ApprovedListComponent },
      { path: 'fin-01-create-list/:_indicator', component: Fin01CreateListComponent },
      // { path: 'fin-01-create-list/older', component: Fin01CreateListComponent },
      { path: 'fin-01-create/:_districtId/:_clinicTypeId/:_month/:_year', component: Fin01CreateComponent },
      
      { path: 'fin-01-inprogress-list/:_indicator', component: Fin01InProgressListComponent },
      // { path: 'fin-01-inprogress-list/older', component: Fin01InProgressListComponent },
      { path: 'fin-01-approve/:_id', component: Fin01ApproveComponent },
      { path: 'fin-01-approved-list/:_indicator', component: Fin01ApprovedListComponent },
      // { path: 'fin-01-approved-list/older', component: Fin01ApprovedListComponent },
      { path: 'FIN-01-create-list/:_indicator', component: FIN01CreateListComponent },
      // { path: 'FIN-01-create-list/older', component: FIN01CreateListComponent },
      { path: 'FIN-01-create/:_districtId/:_clinicTypeId/:_month/:_year', component: FIN01CreateComponent },
      { path: 'FIN-01-inprogress-list/:_indicator', component: FIN01InProgressListComponent },
      // { path: 'FIN-01-inprogress-list/older', component: FIN01InProgressListComponent },
      { path: 'FIN-01-approve/:_id', component: FIN01ApproveComponent },
      { path: 'FIN-01-approved-list/:_indicator', component: FIN01ApprovedListComponent },
      // { path: 'FIN-01-approved-list/older', component: FIN01ApprovedListComponent },
      { path: 'fin-01-inv-exception/:_indicator', component: Fin01InvExceptionComponent },
      // { path: 'fin-01-inv-exception/older', component: Fin01InvExceptionComponent },
      { path: 'FIN-01-inv-exception/:_indicator', component: FIN01InvExceptionComponent },
      // { path: 'FIN-01-inv-exception/older', component: FIN01InvExceptionComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class Fin01InvoiceRoutingModule { }
