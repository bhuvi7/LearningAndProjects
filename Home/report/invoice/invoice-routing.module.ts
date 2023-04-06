import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { AgingComponent } from '../Aging Report/aging.component';
import { CollectionSummaryReportComponent } from './collection-report/collection-summary-report.component';
import { InvoiceListComponent } from './invoice-list/invoice-list.component';
import { InvoiceNumberSearchComponent } from "./invoice-number-search/invoice-number-search.component";
import { SummaryReportComponent } from './summary-report/summary-report.component';
import { CreditDebitNoteListComponent } from './credit-debit-note-list/credit-debit-note-list.component';
import { EquipmentBillingHistoryComponent } from "../equipment-billing-history/equipment-billing-history.component";
import { EquipmentBillingHistoryDetailComponent } from "../equipment-billing-history/equipment-billing-history-detail.component";

const routes: Routes = [
  {
    path: '',
    children: [
      //{ path: 'invoice-list/:dashboard-source/:invoice-type/:state/:district', component: InvoiceListComponent },
      { path: 'invoice-list', component: InvoiceListComponent },
      { path: 'invoice-number-search', component: InvoiceNumberSearchComponent },
      { path: 'summary-report', component: SummaryReportComponent },
      { path: 'ageing-report', component: AgingComponent },
      { path: 'collection-summary-report', component: CollectionSummaryReportComponent },
      { path: 'credit-debit-note-list', component: CreditDebitNoteListComponent },
      { path: 'equipment-billing-history', component: EquipmentBillingHistoryComponent },
      { path: 'app-equipment-billing-history-detail', component: EquipmentBillingHistoryDetailComponent }
    ]
  }
]
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class InvoiceRoutingModule { }