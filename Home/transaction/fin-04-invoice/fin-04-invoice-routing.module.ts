import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { Fin10bCreateListComponent } from './supporting-document/fin-10b/fin-10b-create-list.component';
import { Fin10bCreateComponent } from './supporting-document/fin-10b/fin-10b-create.component';
import { Fin10bListComponent } from './supporting-document/fin-10b/fin-10b-list.component';
import { Fin10bApprovedListComponent } from './supporting-document/fin-10b/fin-10b-approved-list.component';
import { Fin10bApproveComponent } from './supporting-document/fin-10b/fin-10b-approve.component';
import { Fin04CreateListComponent } from './fin-04/fin-04-create-list.component';
import { Fin04CreateComponent } from './fin-04/fin-04-create.component';
import { Fin04InProgressListComponent } from './fin-04/fin-04-inprogress-list.component';
import { Fin04ApprovedListComponent } from './fin-04/fin-04-approved-list.component';
import { Fin04ApproveComponent } from './fin-04/fin-04-approve.component';
import { Fin04InvoiceListComponent } from './fin-04-invoice-list/fin-04-invoice-list.component';

const routes: Routes = [
    {
        path: '',
        children: [
            { path: 'fin-04-list-page', component: Fin04InvoiceListComponent },
            { path: 'fin-10b-create-list', component: Fin10bCreateListComponent },
            { path: 'fin-10b-create', component: Fin10bCreateComponent },
            { path: 'fin-10b-list/:_type', component: Fin10bListComponent },
            { path: 'fin-10b-approved-list/approved', component: Fin10bApprovedListComponent },
            { path: 'fin-10b-approve/:_id', component: Fin10bApproveComponent },
            { path: 'fin-04-create-list', component: Fin04CreateListComponent },
            { path: 'fin-04-create/:_districtId/:_clinicTypeId/:_month/:_year', component: Fin04CreateComponent },
            { path: 'fin-04-inprogress-list', component: Fin04InProgressListComponent },
            { path: 'fin-04-approved-list', component: Fin04ApprovedListComponent },
            { path: 'fin-04-approve/:_id', component: Fin04ApproveComponent }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class Fin04InvoiceRoutingModule { }
