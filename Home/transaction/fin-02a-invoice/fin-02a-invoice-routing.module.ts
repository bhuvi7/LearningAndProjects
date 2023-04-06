import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { Fin02aListPageComponent } from './fin-02a-list-page/fin-02a-list-page.component';
import { Fin07CreateListComponent } from './supporting-document/fin-07/fin-07-create-list.component';
import { Fin07CreateComponent } from './supporting-document/fin-07/fin-07-create.component';
import { Fin07InProgressListComponent } from './supporting-document/fin-07/fin-07-inprogress.component';
import { Fin07ApproveComponent } from './supporting-document/fin-07/fin-07-approve.component';
import { Fin07ApprovedListComponent } from './supporting-document/fin-07/fin-07-approved.component';
import { Fin03aCreateListComponent } from './supporting-document/fin-03a/fin-03a-create-list.component';
import { Fin03aCreateComponent } from './supporting-document/fin-03a/fin-03a-create.component';
import { Fin03aApproveComponent } from './supporting-document/fin-03a/fin-03a-approve.component';
import { Fin03aInvExceptionComponent } from './supporting-document/fin-03a/fin-03a-inv-exception.component';
import { Fin03aInProgressComponent } from './supporting-document/fin-03a/fin-03a-inprogress.component';
import { Fin03aApprovedComponent } from './supporting-document/fin-03a/fin-03a-approved.component';
import { Fin02aCreateListComponent } from './fin-02a/fin-02a-create-list.component';
import { Fin02aCreateComponent } from './fin-02a/fin-02a-create.component';
import { Fin02aInProgressListComponent } from './fin-02a/fin-02a-inprogress-list.component';
import { Fin02aApproveComponent } from './fin-02a/fin-02a-approve.component';
import { Fin02aApprovedListComponent } from './fin-02a/fin-02a-approved-list.component';

const routes: Routes = [
  {
    path: '',
    children: [
      { path: 'fin-02a-list-page', component: Fin02aListPageComponent },
      { path: 'fin-07-create-list/:_indicator', component: Fin07CreateListComponent },
      { path: 'fin-07-create/:_clinicCode/:_month/:_year', component: Fin07CreateComponent },
      { path: 'fin-07-inprogress-list/:_indicator', component: Fin07InProgressListComponent },
      { path: 'fin-07-approve/:_id', component: Fin07ApproveComponent },
      { path: 'fin-07-approved-list/:_indicator', component: Fin07ApprovedListComponent },
      { path: 'fin-03a-create-list/:_indicator', component: Fin03aCreateListComponent },
      { path: 'fin-03a-create/:_districtId/:_clinicTypeId/:_month/:_year', component: Fin03aCreateComponent },
      { path: 'fin-03a-inprogress-list/:_indicator', component: Fin03aInProgressComponent },
      { path: 'fin-03a-approved-list/:_indicator', component: Fin03aApprovedComponent },
      { path: 'fin-03a-inv-exception-list', component: Fin03aInvExceptionComponent },
      { path: 'fin-03a-approve/:_id', component: Fin03aApproveComponent },
      { path: 'fin-02a-create-list/:_indicator', component: Fin02aCreateListComponent },
      { path: 'fin-02a-create/:_id', component: Fin02aCreateComponent },
      { path: 'fin-02a-inprogress-list/:_indicator', component: Fin02aInProgressListComponent },
      { path: 'fin-02a-approve/:_id', component: Fin02aApproveComponent },
      { path: 'fin-02a-approved-list/:_indicator', component: Fin02aApprovedListComponent }

    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class Fin02aInvoiceRoutingModule { }
