import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { Fin09NotCreatedListComponent } from './fin09/not-created-list.component';
import { Fin09CreatedListComponent } from './fin09/created-list.component';
import { Fin09CreateComponent } from './fin09/credit-note-create.component';
import { OtherInvoiceCreatedListComponent } from './other-invoices/created-list.component';
import { OtherInvoiceCreateComponent } from './other-invoices/credit-note-create.component';
import { CreatedListDetailsComponent } from './fin09/created-list-details.component';
import {CnOtherInvoicesComponent } from './cn-other-invoices/cn-other-invoices.component'
import { CnOtherInvoicesUpdateComponent } from './cn-other-invoices/cn-other-invoices-update.component';


const routes: Routes = [
    {
        path: '',
        children: [
            { path: 'fin09/not-created-list', component: Fin09NotCreatedListComponent },
            { path: 'fin09/created-list', component: Fin09CreatedListComponent },
            { path: 'fin09/credit-note-create', component: Fin09CreateComponent },
            { path: 'other-invoice/create-list', component: OtherInvoiceCreatedListComponent },
            { path: 'other-invoice/credit-note-create', component: OtherInvoiceCreateComponent },
            { path: 'fin09/created-list-details/:_id', component: CreatedListDetailsComponent },
            { path: 'cn-other-invoices/cn-other-invoices', component: CnOtherInvoicesComponent},
            { path: 'cn-other-invoices/cn-other-invoices-update/:_id',component:CnOtherInvoicesUpdateComponent}
                     
        ]
    },
    
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class CreditNoteRoutingModule { }
