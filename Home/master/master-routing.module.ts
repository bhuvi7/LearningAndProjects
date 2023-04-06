import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
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

const routes: Routes = [
  { path: 'state-list', component: StateListComponent },
  { path: 'district-list', component: DistrictListComponent },
  { path: 'clinic-list', component: ClinicListComponent },
  { path: 'circle-list', component: CircleListComponent },
  { path: 'invoice-type-list', component: InvoiceTypeListComponent },
  { path: 'invoice-type/detail/:_id', component: InvoiceTypeDetailComponent },
  { path: 'district/detail/:_id', component: DistrictDetailComponent },
  { path: 'clinic/detail/:_id', component: ClinicDetailComponent },
  { path: 'circle/detail/:_id', component: CircleDetailComponent },
  { path: 'state/detail/:_id', component: StateDetailComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MasterRoutingModule { }
