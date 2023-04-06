import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../../theme/shared/shared.module';
import { Fin02bInvoiceRoutingModule } from './fin-02b-invoice-routing.module';
import { DataTablesModule } from 'angular-datatables';
import { Fin02bInvoiceService } from "./fin-02b-invoice-service";
import { SelectModule } from 'ng-select';
import { Fin02bListPageComponent } from './fin-02b-list-page/fin-02b-list-page.component';
import { NgbProgressbarModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbTabsetModule } from '@ng-bootstrap/ng-bootstrap';
import { Fin02bApproveComponent } from './fin-02b/fin-02b-approve.component';
import { Fin02bApprovedListComponent } from './fin-02b/fin-02b-approved-list.component';
import { Fin02bCreateComponent } from './fin-02b/fin-02b-create.component';
import { Fin02bCreateListComponent } from './fin-02b/fin-02b-create-list.component';
import { Fin02bInProgressListComponent } from './fin-02b/fin-02b-inprogress-list.component';
import { AppSharedModule } from './../../shared/app-shared.module';

@NgModule({
  imports: [
    CommonModule,
    SelectModule,
    SharedModule,
    Fin02bInvoiceRoutingModule,
    DataTablesModule,
    NgbProgressbarModule,
    NgbTabsetModule,
    AppSharedModule
  ],
  declarations: [
    Fin02bListPageComponent, Fin02bApproveComponent, Fin02bApprovedListComponent, Fin02bCreateComponent, Fin02bCreateListComponent, Fin02bInProgressListComponent],
  providers: [Fin02bInvoiceService]

})
export class Fin02bInvoiceModule { }
