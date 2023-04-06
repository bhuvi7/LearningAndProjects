import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../theme/shared/shared.module';
import { DataTablesModule } from 'angular-datatables';
import { IncrementalRoutingModule } from './incremental-routing.module';
import { IncrementalService } from './incremental.service';
import { IncrementalApprovalFileSelectionComponent } from './incremental-approval-file-selection.component';

import { IncrementalApprovalHistoryComponent } from './incremental-approval-history.component';
import { NgbProgressbarModule } from '@ng-bootstrap/ng-bootstrap';
import { NgbTabsetModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    IncrementalRoutingModule,
    DataTablesModule,
    NgbProgressbarModule,
    NgbTabsetModule,
  ],
  declarations: [IncrementalApprovalFileSelectionComponent, IncrementalApprovalHistoryComponent,],
  providers: [IncrementalService]

})
export class IncrementalModule { }
