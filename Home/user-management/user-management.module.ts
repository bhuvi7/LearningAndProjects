import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../theme/shared/shared.module';
import { UserManagementRoutingModule } from './user-management-routing.module';
import { UserModule } from '../user-management/user/user.module';
import { UserManagementService } from "../user-management/user-management.service";
import {DataTablesModule} from 'angular-datatables';
@NgModule({
  imports: [
    CommonModule,
    DataTablesModule,
    SharedModule,
    UserManagementRoutingModule,
    UserModule,
    
  ],
  declarations: [],
  providers:[UserManagementService]
  
})
export class UserManagementModule { }
