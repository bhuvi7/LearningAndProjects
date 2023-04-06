import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserListComponent } from "../user/user-list.component";
import { UserDetailComponent } from "../user/user-detail.component";
import { UserGroupComponent } from "./user-group/user-group.component";
import { UserGroupMasterComponent } from "./user-group-master/user-group-master.component";
import { UserRoleComponent } from "./user-role/user-role.component";
import { UserGroupRoleMappingComponent } from "./user-group-role-mapping/user-group-role-mapping.component";
import { UserGroupMenuMappingComponent } from "./user-group-menu-mapping/user-group-menu-mapping.component";

const routes: Routes = [ 
  { path: '', component:UserListComponent }, 
  { path: 'detail/:_id', component:UserDetailComponent }, 
  { path: 'user-group', component:UserGroupComponent }, 
  { path: 'user-group-master', component:UserGroupMasterComponent }, 
  { path: 'user-role', component:UserRoleComponent }, 
  { path: 'user-group-role-mapping', component:UserGroupRoleMappingComponent }, 
  { path: 'user-group-menu-mapping', component:UserGroupMenuMappingComponent }, 
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }