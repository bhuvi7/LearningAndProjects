import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DebitNoteListComponent } from './debit-note-list.component';
import { DebitNoteCreateComponent } from './debit-note-create.component';

const routes: Routes = [
    {
        path: '',
        children: [
            { path: 'create-list', component: DebitNoteListComponent },
            { path: 'debit-note-create', component: DebitNoteCreateComponent },
            { path: 'create-list/:_id',component:DebitNoteListComponent}

        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class DebitNoteRoutingModule { }
