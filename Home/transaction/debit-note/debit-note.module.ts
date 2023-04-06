import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../../theme/shared/shared.module';
import { DataTablesModule } from 'angular-datatables';
import { Fin01InvoiceService } from "../fin-01-invoice/fin-01-invoice-service";
import { DebitNoteRoutingModule } from './debit-note-routing.module';
import { DebitNoteListComponent } from './debit-note-list.component';
import { DebitNoteCreateComponent } from './debit-note-create.component';
import { PAIService } from '../payment-against-invoice/pai-service';

@NgModule({
    imports: [
        CommonModule,
        SharedModule,
        DataTablesModule,
        DebitNoteRoutingModule
    ],
    declarations: [
        DebitNoteListComponent,
        DebitNoteCreateComponent
    ],
    providers: [Fin01InvoiceService,PAIService]

})
export class DebitNoteModule { }
