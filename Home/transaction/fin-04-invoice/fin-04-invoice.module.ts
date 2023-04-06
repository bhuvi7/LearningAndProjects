import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../../theme/shared/shared.module';
import { DataTablesModule } from 'angular-datatables';
import { SelectModule } from 'ng-select';
import { Fin04InvoiceRoutingModule } from './fin-04-invoice-routing.module';
import { Fin04InvoiceService } from './fin-04-invoice-service';
import { Fin10bCreateListComponent } from './supporting-document/fin-10b/fin-10b-create-list.component';
import { Fin10bApprovedListComponent } from './supporting-document/fin-10b/fin-10b-approved-list.component';
import { Fin10bCreateComponent } from './supporting-document/fin-10b/fin-10b-create.component';
import { Fin10bListComponent } from './supporting-document/fin-10b/fin-10b-list.component';
import { Fin10bApproveComponent } from './supporting-document/fin-10b/fin-10b-approve.component';
import { Fin04CreateListComponent } from './fin-04/fin-04-create-list.component';
import { Fin04CreateComponent } from './fin-04/fin-04-create.component';
import { Fin04InProgressListComponent } from './fin-04/fin-04-inprogress-list.component';
import { Fin04ApprovedListComponent } from './fin-04/fin-04-approved-list.component';
import { Fin04ApproveComponent } from './fin-04/fin-04-approve.component';
import { Fin04InvoiceListComponent } from './fin-04-invoice-list/fin-04-invoice-list.component';
import { NgbProgressbarModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbTabsetModule } from '@ng-bootstrap/ng-bootstrap';
import { AppSharedModule } from '../../shared/app-shared.module';
@NgModule({
    imports: [
        CommonModule,
        SelectModule,
        SharedModule,
        Fin04InvoiceRoutingModule,
        DataTablesModule,
        NgbProgressbarModule,
        NgbTabsetModule,
        AppSharedModule
    ],
    declarations: [
        Fin10bCreateListComponent, Fin10bCreateComponent, Fin10bListComponent, Fin10bApproveComponent, Fin10bApprovedListComponent,
        Fin04CreateListComponent, Fin04CreateComponent, Fin04InProgressListComponent, Fin04ApprovedListComponent, Fin04ApproveComponent,
        Fin04InvoiceListComponent
    ],
    providers: [Fin04InvoiceService]

})
export class Fin04InvoiceModule { }
