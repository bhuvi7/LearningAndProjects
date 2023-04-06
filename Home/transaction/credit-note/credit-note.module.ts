import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../../theme/shared/shared.module';
import { DataTablesModule } from 'angular-datatables';
import { Fin01InvoiceService } from "../fin-01-invoice/fin-01-invoice-service";
import { CreditNoteRoutingModule } from './credit-note-routing.module';
import { Fin09CreatedListComponent } from './fin09/created-list.component';
import { Fin09NotCreatedListComponent } from './fin09/not-created-list.component'
import { Fin09CreateComponent } from './fin09/credit-note-create.component';
import { OtherInvoiceCreatedListComponent } from './other-invoices/created-list.component';
import { OtherInvoiceCreateComponent } from './other-invoices/credit-note-create.component';
import { PAIService } from '../payment-against-invoice/pai-service';
import { Fin09InvoiceService } from '../fin-09-invoice/fin-09-invoice-service';
import { CreatedListDetailsComponent } from './fin09/created-list-details.component';
import {CnOtherInvoicesComponent } from './cn-other-invoices/cn-other-invoices.component'
import { CnOtherInvoicesUpdateComponent } from './cn-other-invoices/cn-other-invoices-update.component';

@NgModule({
    imports: [
        CommonModule,
        SharedModule,
        DataTablesModule,
        CreditNoteRoutingModule,
        DataTablesModule,
    ],
    declarations: [
        Fin09CreatedListComponent,
        Fin09NotCreatedListComponent,
        Fin09CreateComponent,
        OtherInvoiceCreatedListComponent,
        OtherInvoiceCreateComponent,
        CreatedListDetailsComponent,
        CnOtherInvoicesComponent ,
        CnOtherInvoicesUpdateComponent
    ],
    providers: [Fin09InvoiceService,PAIService]

})
export class CreditNoteModule { }
