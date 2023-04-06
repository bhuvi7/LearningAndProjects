import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from '../../../../theme/shared/shared.module';
import { UserListComponent  } from '../user/user-list.component';
import { UserDetailComponent } from "../user/user-detail.component";
import {DataTablesModule} from 'angular-datatables';
import { UserRoutingModule } from "../user/user-routing.module";
import { UserGroupComponent } from "./user-group/user-group.component";
import { UserGroupMasterComponent } from "./user-group-master/user-group-master.component";
import { UserRoleComponent } from "./user-role/user-role.component";
import { UserGroupRoleMappingComponent } from "./user-group-role-mapping/user-group-role-mapping.component";
import { UserGroupMenuMappingComponent } from "./user-group-menu-mapping/user-group-menu-mapping.component";
@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    DataTablesModule,
    UserRoutingModule
  ],
  declarations: [UserListComponent,UserDetailComponent,UserGroupMasterComponent,UserGroupComponent,
    UserRoleComponent,UserGroupRoleMappingComponent,UserGroupMenuMappingComponent],

  
})
export class UserModule { }