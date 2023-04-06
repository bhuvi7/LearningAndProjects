import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IncrementalApprovalFileSelectionComponent } from './incremental-approval-file-selection.component';
import { IncrementalApprovalHistoryComponent } from './incremental-approval-history.component';



const routes: Routes = [
  { path: 'file-selection', component: IncrementalApprovalFileSelectionComponent },
  { path: 'history', component: IncrementalApprovalHistoryComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class IncrementalRoutingModule { }
