import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { Fin02bListPageComponent } from './fin-02b-list-page/fin-02b-list-page.component';
import { Fin02bCreateListComponent } from './fin-02b/fin-02b-create-list.component';
import { Fin02bCreateComponent } from './fin-02b/fin-02b-create.component';
import { Fin02bApprovedListComponent } from './fin-02b/fin-02b-approved-list.component';
import { Fin02bApproveComponent } from  './fin-02b/fin-02b-approve.component';
import { Fin02bInProgressListComponent } from './fin-02b/fin-02b-inprogress-list.component';

const routes: Routes = [
 
  {
    path: '',
    children: [
      { path: 'fin-02b-list-page', component: Fin02bListPageComponent },
      
      
      { path: 'fin-02b-create-list/:_indicator', component: Fin02bCreateListComponent },
      { path: 'fin-02b-create/:_districtId/:_clinicTypeId/:_month/:_year', component: Fin02bCreateComponent },
      { path: 'fin-02b-approved-list/:_indicator', component: Fin02bApprovedListComponent },
      { path: 'fin-02b-approve/:_id', component: Fin02bApproveComponent },
      { path: 'fin-02b-inprogress-list/:_indicator', component: Fin02bInProgressListComponent },



    ]
  },


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class Fin02bInvoiceRoutingModule { }
