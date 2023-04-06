import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../../theme/shared/shared.module';
import { Fin01InvoiceRoutingModule } from './fin-01-invoice-routing.module';
import { DataTablesModule } from 'angular-datatables';
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
import { Fin01InvExceptionComponent } from './supporting-document/fin01/fin-01-inv-exception.component';
import { FIN01CreateListComponent } from './FIN01/FIN-01-create-list.component';
import { FIN01CreateComponent } from './FIN01/FIN-01-create.component';
import { FIN01InProgressListComponent } from './FIN01/FIN-01-inprogress-list.component';
import { FIN01ApproveComponent } from './FIN01/FIN-01-approve.component';
import { FIN01ApprovedListComponent } from './FIN01/FIN-01-approved-list.component';
import { FIN01InvExceptionComponent } from './FIN01/FIN-01-Inv-Exception.component';
import { Fin01InvoiceService } from "../fin-01-invoice/fin-01-invoice-service";
import { SelectModule } from 'ng-select';
import { Fin01ListPageComponent } from "./supporting-document/fin-01-list-page/fin-01-list-page.component";
import { NgbProgressbarModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbTabsetModule } from '@ng-bootstrap/ng-bootstrap';
import { AppSharedModule } from '../../shared/app-shared.module';
@NgModule({
  imports: [
    CommonModule,
    SelectModule,
    SharedModule,
    Fin01InvoiceRoutingModule,
    DataTablesModule,
    NgbProgressbarModule,
    NgbTabsetModule,
    AppSharedModule
  ],
  declarations: [
    Fin06CreateComponent, Fin06InProgressListComponent, Fin06ApproveComponent, Fin06CreateListComponent, Fin06ApprovedListComponent,
    Fin01CreateComponent, Fin01CreateListComponent, Fin01InProgressListComponent, Fin01ApproveComponent, Fin01ApprovedListComponent, Fin01InvExceptionComponent,
    FIN01CreateComponent, FIN01CreateListComponent, FIN01InProgressListComponent, FIN01ApproveComponent, FIN01ApprovedListComponent, FIN01InvExceptionComponent,
    Fin01ListPageComponent
  ],
  providers: [Fin01InvoiceService]

})
export class Fin01InvoiceModule { }
