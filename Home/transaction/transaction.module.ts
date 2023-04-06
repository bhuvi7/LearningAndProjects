import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../theme/shared/shared.module';
import { TransactionRoutingModule } from './transaction-routing.module';
import {DataTablesModule} from 'angular-datatables';


@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    TransactionRoutingModule,
    DataTablesModule,
    
    
  ],
  declarations: [],
  providers:[]
  
})
export class TransactionModule { }
