import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../../../../theme/shared/shared.module';
import { DataTablesModule } from 'angular-datatables';
import { SelectModule } from 'ng-select';
import { NgbProgressbarModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbTabsetModule } from '@ng-bootstrap/ng-bootstrap';
import { Fin08InvoiceRoutingModule } from './fin-08-invoice-routing.module';
import { Fin08InvoiceService } from "../fin-08/fin-08-invoice-service";
// import { Fin02aListPageComponent } from './fin-02a-list-page/fin-02a-list-page.component';
import { Fin08cCreateListComponent } from './supporting-document/fin-08c/fin-08c-create-list.component';
import { Fin08cCreateComponent } from './supporting-document/fin-08c/fin-08c-create.component';
import { Fin08cApproveComponent } from './supporting-document/fin-08c/fin-08c-approve.component';
import { Fin08cApprovedComponent } from './supporting-document/fin-08c/fin-08c-approved-list.component';
// import { Fin08cInvExceptionComponent } from './supporting-document/fin-08c/fin-08c-inv-exception.component';
import { Fin08cInProgressComponent } from './supporting-document/fin-08c/fin-08c-inprogress.component';
import { Fin08bCreateListComponent } from './supporting-document/fin-08b/fin-08b-create-list.component';
import { Fin08bCreateComponent } from './supporting-document/fin-08b/fin-08b-create.component';
import { Fin08bInProgressListComponent } from './supporting-document/fin-08b/fin-08b-inprogress.component';
import { Fin08bApproveComponent } from './supporting-document/fin-08b/fin-08b-approve.component';
import { Fin08bApprovedListComponent } from './supporting-document/fin-08b/fin-08b-approved-list.component';
import { Fin08CreateListComponent } from './fin-08/fin-08-create-list.component';
import { Fin08CreateComponent } from './fin-08/fin-08-create.component';
import { Fin08InProgressListComponent } from './fin-08/fin-08-inprogress-list.component';
import { Fin08ApproveComponent } from './fin-08/fin-08-approve.component';
import { Fin08ApprovedListComponent } from './fin-08/fin-08-approved-list.component';
import { AppSharedModule } from './../../../../shared/app-shared.module';
@NgModule({
  imports: [
    CommonModule,
    SelectModule,
    SharedModule,
    Fin08InvoiceRoutingModule,
    DataTablesModule,
    NgbProgressbarModule,
    NgbTabsetModule,
    AppSharedModule
  ],
  declarations: [
    Fin08bCreateListComponent, Fin08bCreateComponent, Fin08bInProgressListComponent, Fin08bApproveComponent, Fin08bApprovedListComponent,
    Fin08cCreateListComponent, Fin08cCreateComponent, Fin08cInProgressComponent, Fin08cApproveComponent, Fin08cApprovedComponent,
    // Fin03aInvExceptionComponent,
    // Fin08CreateListComponent, Fin08CreateComponent, Fin08InProgressListComponent, Fin08ApproveComponent, Fin08ApprovedListComponent
  ],
  providers: [Fin08InvoiceService,]

})
export class Fin08InvoiceModule { }
