import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../../theme/shared/shared.module';
import { DataTablesModule } from 'angular-datatables';
import { SelectModule } from 'ng-select';
import { Fin11InvoiceRoutingModule } from './fin-11-invoice-routing.module';
import { NgbProgressbarModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbTabsetModule } from '@ng-bootstrap/ng-bootstrap';
import { Fin11CreateComponent } from './fin-11/fin-11-create.component';
import { Fin11ApprovedListComponent } from './fin-11/fin-11-approved-list.component';
import { Fin11ApproveComponent } from './fin-11/fin-11-approve.component';
import { Fin11InProgressListComponent } from './fin-11/fin-11-inprogress-list.component';
import { Fin11InvoiceService } from './fin-11-invoice-service';
import { Fin11ListPageComponent } from './fin-11-list-page/Fin-11-list-page.component';

@NgModule({
    imports: [
        CommonModule,
        SelectModule,
        SharedModule,
        Fin11InvoiceRoutingModule,
        DataTablesModule,
        NgbProgressbarModule,
        NgbTabsetModule
    ],
    declarations: [
        Fin11CreateComponent, Fin11ApprovedListComponent, Fin11ApproveComponent, Fin11InProgressListComponent, Fin11ListPageComponent,
        
    ],
    providers: [ Fin11InvoiceService , ]

})
export class Fin11InvoiceModule { }
