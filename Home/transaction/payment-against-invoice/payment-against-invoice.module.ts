import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../../theme/shared/shared.module';
import { DataTablesModule } from 'angular-datatables';
import { PAIService } from "./pai-service";
import { SelectModule } from 'ng-select';
import { PaymentAgainstInvoiceRoutingModule } from './payment-against-invoice-routing.module';
import { listpageComponent } from './pai-listpage.component';
import { updatedetailsComponent } from './pai-update.component';

@NgModule({
  imports: [
    CommonModule,
    SelectModule,
    SharedModule,
    PaymentAgainstInvoiceRoutingModule,
    DataTablesModule,
  ],
  declarations: [
    listpageComponent,
    updatedetailsComponent
  ],
  providers: [PAIService]

})
export class PaymentAgainstInvoiceModule { }
