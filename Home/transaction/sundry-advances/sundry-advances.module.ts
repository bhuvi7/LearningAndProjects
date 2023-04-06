import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../../theme/shared/shared.module';
import { DataTablesModule } from 'angular-datatables';
import { SAService } from "./sa-service";
import { SelectModule } from 'ng-select';
import { SARoutingModule } from './sundry-advances-routing.module';
import { SAListPageComponent } from './sa-listpage.component';
import { SAUpdateComponent } from './sa-update.component';

@NgModule({
  imports: [
    CommonModule,
    SelectModule,
    SharedModule,
    SARoutingModule,
    DataTablesModule,
  ],
  declarations: [
    SAListPageComponent,
    SAUpdateComponent
  ],
  providers: [SAService]

})
export class SAModule { }
