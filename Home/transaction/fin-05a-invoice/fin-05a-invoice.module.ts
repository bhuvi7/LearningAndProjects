import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../../theme/shared/shared.module';
import { DataTablesModule } from 'angular-datatables';
import { SelectModule } from 'ng-select';
import { Fin05aInvoiceRoutingModule } from './fin-05a-invoice-routing.module';
import { NgbProgressbarModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbTabsetModule } from '@ng-bootstrap/ng-bootstrap';
import { Fin05aCreateComponent } from './fin-05a/fin-05a-create.component';
import { Fin05aApprovedListComponent } from './fin-05a/fin-05a-approved-list.component';
import { Fin05aApproveComponent } from './fin-05a/fin-05a-approve.component';
import { Fin05aInProgressListComponent } from './fin-05a/fin-05a-inprogress-list.component';
import { Fin05aInvoiceService } from './fin-05a-invoice-service';
import { Fin05aListPageComponent } from './fin-05a-list-page/Fin-05a-list-page.component';

@NgModule({
    imports: [
        CommonModule,
        SelectModule,
        SharedModule,
        Fin05aInvoiceRoutingModule,
        DataTablesModule,
        NgbProgressbarModule,
        NgbTabsetModule
    ],
    declarations: [
        Fin05aCreateComponent, Fin05aApprovedListComponent, Fin05aApproveComponent, Fin05aInProgressListComponent, Fin05aListPageComponent,
        
    ],
    providers: [ Fin05aInvoiceService , ]

})
export class Fin05aInvoiceModule { }
