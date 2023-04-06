import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../../theme/shared/shared.module';
import { DataTablesModule } from 'angular-datatables';
import { NgbProgressbarModule, NgbTabsetModule } from '@ng-bootstrap/ng-bootstrap'
import { Fin09InvoiceService } from "./fin-09-invoice-service";
import { SelectModule } from 'ng-select';
import { Fin09InvoiceRoutingModule } from './fin-09-invoice-routing.modulte';
import { Fin09InvoiceListComponent } from './fin-09-list-page/fin-09-invoice-list.component';
import { Fin09CreateComponent } from './fin-09/fin-09-create.component';
import { Fin09InProgressListComponent } from './fin-09/fin-09-inprogress-list.component';
import { Fin09ApprovedListComponent } from './fin-09/fin-09-approved-list.component';
import { Fin09ApproveComponent } from './fin-09/fin-09-approve.component';
import { ViewComponent } from './fin-09/view/view.component';

@NgModule({
  imports: [
    CommonModule,
    SelectModule,
    SharedModule,
    Fin09InvoiceRoutingModule,
    DataTablesModule,
    NgbProgressbarModule,
    NgbTabsetModule
  ],
  declarations: [
    Fin09InvoiceListComponent,
    Fin09CreateComponent, Fin09InProgressListComponent, Fin09ApproveComponent, Fin09ApprovedListComponent, ViewComponent
  ],
  providers: [Fin09InvoiceService]

})
export class Fin09InvoiceModule { }
