import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../../theme/shared/shared.module';
import { Fin02InvoiceRoutingModule } from './fin-02-invoice-routing.module';
import { DataTablesModule } from 'angular-datatables';
import { Fin02InvoiceService } from "./fin-02-service";
import { SelectModule } from 'ng-select';
import { Fin02ListPageComponent } from './fin-02-list-page/fin-02-list-page.component';
import { NgbProgressbarModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbTabsetModule } from '@ng-bootstrap/ng-bootstrap';
import { Fin03ApproveComponent } from './supporting-document/fin-03/fin-03-approve.component';
import { Fin03ApprovedListComponent } from './supporting-document/fin-03/fin-03-approved-list.component';
import { Fin03CreateComponent } from './supporting-document/fin-03/fin-03-create.component';
import { Fin03CreateListComponent } from './supporting-document/fin-03/fin-03-create-list.component';
import { Fin03InProgressListComponent } from './supporting-document/fin-03/fin-03-inprogress-list.component';
import { Fin08ApproveComponent } from './supporting-document/fin-08/fin-08/fin-08-approve.component';
import { Fin08ApprovedListComponent } from './supporting-document/fin-08/fin-08/fin-08-approved-list.component';
import { Fin08CreateComponent } from './supporting-document/fin-08/fin-08/fin-08-create.component';
import { Fin08CreateListComponent } from './supporting-document/fin-08/fin-08/fin-08-create-list.component';
import { Fin08InProgressListComponent } from './supporting-document/fin-08/fin-08/fin-08-inprogress-list.component';
import { Fin08InvoiceService } from './supporting-document/fin-08/fin-08-invoice-service';
import { Fin02ApproveComponent } from './fin-02/fin-02-approve.component';
import { Fin02ApprovedListComponent } from './fin-02/fin-02-approved-list.component';
import { Fin02CreateComponent } from './fin-02/fin-02-create.component';
import { Fin02CreateListComponent } from './fin-02/fin-02-create-list.component';
import { Fin02InProgressListComponent } from './fin-02/fin-02-inprogress-list.component';
import { Fin01InvoiceService } from '../fin-01-invoice/fin-01-invoice-service';
import { AppSharedModule } from './../../shared/app-shared.module';
@NgModule({
  imports: [
    CommonModule,
    SelectModule,
    SharedModule,
    Fin02InvoiceRoutingModule,
    DataTablesModule,
    NgbProgressbarModule,
    NgbTabsetModule,
    AppSharedModule
  ],
  declarations: [
    Fin02ListPageComponent,
    Fin03ApproveComponent, Fin03ApprovedListComponent, Fin03CreateComponent, Fin03CreateListComponent, Fin03InProgressListComponent,
    Fin08ApproveComponent, Fin08ApprovedListComponent, Fin08CreateComponent, Fin08CreateListComponent, Fin08InProgressListComponent,
    Fin02ApproveComponent, Fin02ApprovedListComponent, Fin02CreateComponent, Fin02CreateListComponent, Fin02InProgressListComponent,
  ],
  providers: [Fin02InvoiceService, Fin08InvoiceService, Fin01InvoiceService]

})
export class Fin02InvoiceModule { }
