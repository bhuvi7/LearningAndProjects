import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { Fin08bCreateListComponent } from './supporting-document/fin-08b/fin-08b-create-list.component';
import { Fin08bCreateComponent } from './supporting-document/fin-08b/fin-08b-create.component';
import { Fin08bInProgressListComponent } from './supporting-document/fin-08b/fin-08b-inprogress.component';
import { Fin08bApproveComponent } from './supporting-document/fin-08b/fin-08b-approve.component';
import { Fin08bApprovedListComponent } from './supporting-document/fin-08b/fin-08b-approved-list.component';
import { Fin08cCreateListComponent } from './supporting-document/fin-08c/fin-08c-create-list.component';
import { Fin08cCreateComponent } from './supporting-document/fin-08c/fin-08c-create.component';
import { Fin08cApproveComponent } from './supporting-document/fin-08c/fin-08c-approve.component';
// import { Fin08cInvExceptionComponent } from './supporting-document/fin-08c/fin-08c-inv-exception.component';
import { Fin08cInProgressComponent } from './supporting-document/fin-08c/fin-08c-inprogress.component';
import { Fin08cApprovedComponent } from './supporting-document/fin-08c/fin-08c-approved-list.component';
import { Fin08CreateListComponent } from './fin-08/fin-08-create-list.component';
import { Fin08CreateComponent } from './fin-08/fin-08-create.component';
import { Fin08InProgressListComponent } from './fin-08/fin-08-inprogress-list.component';
import { Fin08ApproveComponent } from './fin-08/fin-08-approve.component';
import { Fin08ApprovedListComponent } from './fin-08/fin-08-approved-list.component';

const routes: Routes = [
  // {
  //   path: '',
  //   children: [

  //     { path: 'fin-08-create-list', component: Fin08CreateListComponent },
  //     { path: 'fin-08-create/:_clinicCode', component: Fin08CreateComponent },
  //     { path: 'fin-08-create', component: Fin08CreateComponent },
  //     { path: 'fin-08-inprogress-list', component: Fin08InProgressListComponent },
  //     { path: 'fin-08-approve/:_id', component: Fin08ApproveComponent },
  //     { path: 'fin-08-approved-list', component: Fin08ApprovedListComponent }

  //   ]
  // },
  {
    path: '',
    children: [

      { path: 'fin-08b-create-list/:_indicator', component: Fin08bCreateListComponent },
      { path: 'fin-08b-create/:_clinicCode/:_month/:_year', component: Fin08bCreateComponent },
      { path: 'fin-08b-inprogress-list/:_indicator', component: Fin08bInProgressListComponent },
      { path: 'fin-08b-approve/:_id', component: Fin08bApproveComponent },
      { path: 'fin-08b-approved-list/:_indicator', component: Fin08bApprovedListComponent },
      { path: 'fin-08c-create-list/:_indicator', component: Fin08cCreateListComponent },
      { path: 'fin-08c-create/:_clinicCode/:_month/:_year', component: Fin08cCreateComponent },
      { path: 'fin-08c-inprogress-list/:_indicator', component: Fin08cInProgressComponent },
      { path: 'fin-08c-approved-list/:_indicator', component: Fin08cApprovedComponent },
      // { path: 'fin-08c-inv-exception-list', component: Fin08cInvExceptionComponent },
      { path: 'fin-08c-approve/:_id', component: Fin08cApproveComponent },

    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class Fin08InvoiceRoutingModule { }
