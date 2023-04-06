import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { SpecialApprovalComponent } from './penalty/special-approval.component';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../theme/shared/shared.module';
import { DataTablesModule } from 'angular-datatables';
import { Fin09InvoiceService } from "../transaction/fin-09-invoice/fin-09-invoice-service";
import { CreatedListApprovalComponent }from './penalty/created-list-approval.component';
import { PAIService } from "../transaction/payment-against-invoice/pai-service";



const routes: Routes = [
    { path: '', component: SpecialApprovalComponent },
    { path: 'created-list-approval/:_id', component: CreatedListApprovalComponent}
    
]

@NgModule({
    declarations: [SpecialApprovalComponent, CreatedListApprovalComponent],
    imports: [RouterModule.forChild(routes),CommonModule,SharedModule,DataTablesModule],
    exports: [RouterModule],
    providers: [ Fin09InvoiceService, PAIService ]

})
export class SpecialRoutingModule { }
