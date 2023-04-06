import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../../theme/shared/shared.module';
import { DataTablesModule } from 'angular-datatables';
import { SelectModule } from 'ng-select';
import { NgbProgressbarModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbTabsetModule } from '@ng-bootstrap/ng-bootstrap';
import { Fin02aInvoiceRoutingModule } from './fin-02a-invoice-routing.module';
import { Fin02aInvoiceService } from "../fin-02a-invoice/fin-02a-invoice-service";
import { Fin02aListPageComponent } from './fin-02a-list-page/fin-02a-list-page.component';
import { Fin03aCreateListComponent } from './supporting-document/fin-03a/fin-03a-create-list.component';
import { Fin03aCreateComponent } from './supporting-document/fin-03a/fin-03a-create.component';
import { Fin03aApproveComponent } from './supporting-document/fin-03a/fin-03a-approve.component';
import { Fin03aApprovedComponent } from './supporting-document/fin-03a/fin-03a-approved.component';
import { Fin03aInvExceptionComponent } from './supporting-document/fin-03a/fin-03a-inv-exception.component';
import { Fin03aInProgressComponent } from './supporting-document/fin-03a/fin-03a-inprogress.component';
import { Fin07CreateListComponent } from './supporting-document/fin-07/fin-07-create-list.component';
import { Fin07CreateComponent } from './supporting-document/fin-07/fin-07-create.component';
import { Fin07InProgressListComponent } from './supporting-document/fin-07/fin-07-inprogress.component';
import { Fin07ApproveComponent } from './supporting-document/fin-07/fin-07-approve.component';
import { Fin07ApprovedListComponent } from './supporting-document/fin-07/fin-07-approved.component';
import { Fin02aCreateListComponent } from './fin-02a/fin-02a-create-list.component';
import { Fin02aCreateComponent } from './fin-02a/fin-02a-create.component';
import { Fin02aInProgressListComponent } from './fin-02a/fin-02a-inprogress-list.component';
import { Fin02aApproveComponent } from './fin-02a/fin-02a-approve.component';
import { Fin02aApprovedListComponent } from './fin-02a/fin-02a-approved-list.component';
import { AppSharedModule } from '../../shared/app-shared.module';
@NgModule({
  imports: [
    CommonModule,
    SelectModule,
    SharedModule,
    Fin02aInvoiceRoutingModule,
    DataTablesModule,
    NgbProgressbarModule,
    NgbTabsetModule,
    AppSharedModule
  ],
  declarations: [
    Fin02aListPageComponent,
    Fin07CreateListComponent, Fin07CreateComponent, Fin07InProgressListComponent, Fin07ApproveComponent, Fin07ApprovedListComponent,
    Fin03aCreateListComponent, Fin03aCreateComponent, Fin03aInProgressComponent, Fin03aApproveComponent, Fin03aApprovedComponent, Fin03aInvExceptionComponent,
    Fin02aCreateListComponent, Fin02aCreateComponent, Fin02aInProgressListComponent, Fin02aApproveComponent, Fin02aApprovedListComponent
  ],
  providers: [Fin02aInvoiceService,]

})
export class Fin02aInvoiceModule { }
