import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardProgress } from "../dashboard-progress/dashboard-progress.component";
import { DashboardProgressRoutingModule } from './dashboard-progress-routing.module';
import { NgbTabsetModule } from '@ng-bootstrap/ng-bootstrap';
import { InvoiceGenerationComponent } from "../dashboard-progress/invoice-generation/invoice-generation.component";
import { SharedModule } from '../../../theme/shared/shared.module';
import { AccountReceivablesComponent } from "../dashboard-progress/account-receivables/account-receivables.component";
import { BacklogInvoicesComponent } from "../dashboard-progress/backlog-invoices/backlog-invoices.component";
import { PendingPenalitiesComponent } from "../dashboard-progress/pending-penalities/pending-penalities.component";
import { RetentionComponent } from "../dashboard-progress/retention/retention.component";
import { RevenueAnalysisComponent } from "../dashboard-progress/revenue-analysis/revenue-analysis.component";
import { RevenueBudgetComponent } from "../dashboard-progress/revenue-budget/revenue-budget.component";
import { UnadjustedPenaltiesComponent } from "../dashboard-progress/unadjusted-penalties/unadjusted-penalties.component";
import { ChartsModule } from 'ng2-charts';
import { NgbProgressbarModule, NgbTooltipModule } from '@ng-bootstrap/ng-bootstrap';
import { DashboardProgressService } from "../dashboard-progress/dashboard-progress.service";
import { DataTablesModule } from 'angular-datatables';

@NgModule({
  imports: [
    CommonModule, NgbTabsetModule,
    DashboardProgressRoutingModule,
    SharedModule, ChartsModule,
    NgbProgressbarModule, NgbTooltipModule,
    DataTablesModule
  ],
  declarations: [DashboardProgress, InvoiceGenerationComponent, AccountReceivablesComponent, BacklogInvoicesComponent,
    PendingPenalitiesComponent, RetentionComponent, RevenueAnalysisComponent, UnadjustedPenaltiesComponent, RevenueBudgetComponent],
  providers: [DashboardProgressService]
})
export class DashboardProgressModule { }
