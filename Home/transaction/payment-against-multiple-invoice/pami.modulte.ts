import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../../theme/shared/shared.module';
import { DataTablesModule } from 'angular-datatables';
import { PAIService } from "../payment-against-invoice/pai-service";
import { SelectModule } from 'ng-select';
import { PAMIRoutingModule } from './pami-routing.modulte';
import { PAMIComponent } from './pami.component';

@NgModule({
  imports: [
    CommonModule,
    SelectModule,
    SharedModule,
    PAMIRoutingModule,
    DataTablesModule,
  ],
  declarations: [
    PAMIComponent
  ],
  providers: [PAIService]

})
export class PAMIModule { }
