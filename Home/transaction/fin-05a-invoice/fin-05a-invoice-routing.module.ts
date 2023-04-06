import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { Fin05aCreateComponent } from './fin-05a/fin-05a-create.component';
import { Fin05aApprovedListComponent } from './fin-05a/fin-05a-approved-list.component';
import { Fin05aApproveComponent } from './fin-05a/fin-05a-approve.component';
import { Fin05aInProgressListComponent } from './fin-05a/fin-05a-inprogress-list.component';
import { Fin05aListPageComponent } from './fin-05a-list-page/Fin-05a-list-page.component';


const routes: Routes = [
    {
        path: '',
        children: 
        [
            { path: 'fin-05a-create', component: Fin05aCreateComponent },
            { path: 'fin-05a-list-page', component: Fin05aListPageComponent},
            { path: 'fin-05a-approved-list', component: Fin05aApprovedListComponent },
            { path: 'fin-05a-approve/:_id', component: Fin05aApproveComponent },
            { path: 'fin-05a-inprogress-list', component: Fin05aInProgressListComponent },
            
            
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class Fin05aInvoiceRoutingModule { }
