import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { Fin02ListPageComponent } from './fin-02-list-page/fin-02-list-page.component';
import { Fin03CreateListComponent } from './supporting-document/fin-03/fin-03-create-list.component';
import { Fin03CreateComponent } from './supporting-document/fin-03/fin-03-create.component';
import { Fin03ApproveComponent } from './supporting-document/fin-03/fin-03-approve.component';
import { Fin03InProgressListComponent } from './supporting-document/fin-03/fin-03-inprogress-list.component';
import { Fin03ApprovedListComponent } from './supporting-document/fin-03/fin-03-approved-list.component';
import { Fin08CreateListComponent } from './supporting-document/fin-08/fin-08/fin-08-create-list.component';
import { Fin08CreateComponent } from './supporting-document/fin-08/fin-08/fin-08-create.component';
import { Fin08InProgressListComponent } from './supporting-document/fin-08/fin-08/fin-08-inprogress-list.component';
import { Fin08ApproveComponent } from './supporting-document/fin-08/fin-08/fin-08-approve.component';
import { Fin08ApprovedListComponent } from './supporting-document/fin-08/fin-08/fin-08-approved-list.component';
import { Fin02CreateListComponent } from './fin-02/fin-02-create-list.component';
import { Fin02CreateComponent } from './fin-02/fin-02-create.component';
import { Fin02ApprovedListComponent } from './fin-02/fin-02-approved-list.component';
import { Fin02ApproveComponent } from './fin-02/fin-02-approve.component';
import { Fin02InProgressListComponent } from './fin-02/fin-02-inprogress-list.component';

const routes: Routes = [
  {
    path: 'fin-08',
    loadChildren: () => import('./supporting-document/fin-08/fin-08-invoice.module').then(module => module.Fin08InvoiceModule)
  },
  {
    path: '',
    children: [
      { path: 'fin-02-list-page', component: Fin02ListPageComponent },
      { path: 'fin-03-create-list/:_indicator', component: Fin03CreateListComponent },
      { path: 'fin-03-create/:_districtId/:_clinicTypeId/:_month/:_year', component: Fin03CreateComponent },
      { path: 'fin-03-approve/:_id', component: Fin03ApproveComponent },
      { path: 'fin-03-inprogress-list/:_indicator', component: Fin03InProgressListComponent },
      { path: 'fin-03-approved-list/:_indicator', component: Fin03ApprovedListComponent },
      { path: 'fin-08-create-list/:_indicator', component: Fin08CreateListComponent },
      { path: 'fin-08-create/:_clinicCode/:_month/:_year', component: Fin08CreateComponent },
      { path: 'fin-08-create', component: Fin08CreateComponent },
      { path: 'fin-08-inprogress-list/:_indicator', component: Fin08InProgressListComponent },
      { path: 'fin-08-approve/:_id', component: Fin08ApproveComponent },
      { path: 'fin-08-approved-list/:_indicator', component: Fin08ApprovedListComponent },
      { path: 'fin-02-create-list/:_indicator', component: Fin02CreateListComponent },
      { path: 'fin-02-create/:_id', component: Fin02CreateComponent },
      { path: 'fin-02-approved-list/:_indicator', component: Fin02ApprovedListComponent },
      { path: 'fin-02-approve/:_id', component: Fin02ApproveComponent },
      { path: 'fin-02-inprogress-list/:_indicator', component: Fin02InProgressListComponent },



    ]
  },


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class Fin02InvoiceRoutingModule { }
