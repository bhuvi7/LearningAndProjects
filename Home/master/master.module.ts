import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../theme/shared/shared.module';
import { MasterRoutingModule } from './master-routing.module';
import { MasterService } from "../master/master.service";
import { DataTablesModule } from 'angular-datatables';
import { StateListComponent } from "./state/state-list.component";
import { DistrictListComponent } from "./district/district-list.component";
import { ClinicListComponent } from './clinic/clinic-list.component';
import { CircleListComponent } from './circle/circle-list.component';
import { InvoiceTypeListComponent } from './invoice-type/invoice-type-list.component';
import { InvoiceTypeDetailComponent } from './invoice-type/invoice-type-detail.component';
import { DistrictDetailComponent } from './district/district-detail.component';
import { ClinicDetailComponent } from './clinic/clinic-detail.component';
import { CircleDetailComponent } from './circle/circle-detail.component';
import { StateDetailComponent } from './state/state-detail.component';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    MasterRoutingModule,
    DataTablesModule
  ],
  declarations: [StateListComponent, DistrictListComponent, ClinicListComponent, CircleListComponent,
    InvoiceTypeListComponent, InvoiceTypeDetailComponent,DistrictDetailComponent,ClinicDetailComponent,
    CircleDetailComponent,StateDetailComponent],
  providers: [MasterService]

})
export class MasterModule { }
