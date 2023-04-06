import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { SharedModule } from '../../../../theme/shared/shared.module';
import { InvoiceListComponent } from './invoice-list/invoice-list.component';
import { InvoiceNumberSearchComponent } from "./invoice-number-search/invoice-number-search.component";
import { InvoiceRoutingModule } from "./invoice-routing.module";
import { DataTablesModule } from 'angular-datatables';
import { SummaryReportComponent } from './summary-report/summary-report.component';
import { AgingComponent } from '../Aging Report/aging.component';
import { CollectionSummaryReportComponent } from './collection-report/collection-summary-report.component';
import { CreditDebitNoteListComponent } from './credit-debit-note-list/credit-debit-note-list.component';
import { EquipmentBillingHistoryComponent } from "../equipment-billing-history/equipment-billing-history.component";
import { EquipmentBillingHistoryDetailComponent } from "../equipment-billing-history/equipment-billing-history-detail.component";
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { DatePipe } from '@angular/common'

@NgModule({
    imports: [CommonModule, InvoiceRoutingModule, SharedModule, DataTablesModule, NgMultiSelectDropDownModule.forRoot()],
    declarations: [InvoiceListComponent, InvoiceNumberSearchComponent, SummaryReportComponent,
        AgingComponent, CollectionSummaryReportComponent, CreditDebitNoteListComponent, EquipmentBillingHistoryComponent, EquipmentBillingHistoryDetailComponent],
    exports: [InvoiceListComponent],
    providers: [DatePipe]
})
export class InvoiceModule { }