import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { Fin11CreateComponent } from './fin-11/fin-11-create.component';
import { Fin11ApprovedListComponent } from './fin-11/fin-11-approved-list.component';
import { Fin11ApproveComponent } from './fin-11/fin-11-approve.component';
import { Fin11InProgressListComponent } from './fin-11/fin-11-inprogress-list.component';
import { Fin11ListPageComponent } from './fin-11-list-page/Fin-11-list-page.component';


const routes: Routes = [
    {
        path: '',
        children: 
        [
            { path: 'fin-11-create', component: Fin11CreateComponent },
            { path: 'fin-11-list-page', component: Fin11ListPageComponent},
            { path: 'fin-11-approved-list', component: Fin11ApprovedListComponent },
            { path: 'fin-11-approve/:_id', component: Fin11ApproveComponent },
            { path: 'fin-11-inprogress-list', component: Fin11InProgressListComponent },
            
            
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class Fin11InvoiceRoutingModule { }
