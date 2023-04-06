import { NgModule, } from "@angular/core";
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../theme/shared/shared.module';
import { ReportRoutingModule } from "./report-routing.module";
import { ReportService } from "../report/report.service";
import { EquipmentBillingHistoryComponent } from './equipment-billing-history/equipment-billing-history.component';
import { EquipmentBillingHistoryDetailComponent } from './equipment-billing-history/equipment-billing-history-detail.component';
@NgModule({
    imports: [CommonModule, ReportRoutingModule, SharedModule],
    providers: [ReportService],
    declarations: []

})
export class ReportModule { }