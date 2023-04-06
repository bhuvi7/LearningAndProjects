import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import "rxjs/add/operator/map";
import { environment } from '../../../../environments/environment'; 
import { User } from "./model/user";
import { Menu } from "./model/menu";
import { UserGroup,UserGroupMaster } from "./model/user-group";
import { UserRole } from "./model/user-role";
import { UserGroupRoleMapping } from "./model/user-group-role-mapping";
import { UserGroupMenuMapping } from "./model/user-group-menu-mapping";
@Injectable({providedIn:'root'})
export class UserManagementService {

    serviceApiUrl: string = environment.serviceApiUrl;  

    constructor(private http: HttpClient) {
       
    }
    getAllUserData(){
       return this.http.get<Array<User>>(this.serviceApiUrl+"user/all");
    }

    getUserById(id){
        return this.http.get<User>(this.serviceApiUrl+"user/"+id);
     }
 

    addUser(user){     
        return this.http.post(this.serviceApiUrl+"user/add",user);
    }

    updateUser(user){
        return this.http.put(this.serviceApiUrl+"user/update",user);
    }
    deleteUser(user){  
        return this.http.put(this.serviceApiUrl+"user/delete/"+user.id,user);
    }

    getAllUserGroupData(){
        return this.http.get<Array<UserGroup>>(this.serviceApiUrl+"user-group/all");
    }
    checkUserGroupPriority(masterPriorityId:number,qroupPriorityId:number){     
        return this.http.get<Array<UserGroup>>(this.serviceApiUrl+"user-group/user-group-priority?master-priority-id="+masterPriorityId+"&group-priority-id="+qroupPriorityId);
    }
    addUserGroup(userGroup){     
        return this.http.post(this.serviceApiUrl+"user-group/add",userGroup);
    }

    updateUserGroup(userGroup){
        return this.http.put(this.serviceApiUrl+"user-group/update",userGroup);
    }

    updateUserGroupPriority(userGroupList){
        return this.http.put(this.serviceApiUrl+"user-group/update-priority",userGroupList);
    }

    deleteUserGroup(userGroup){
        // 
        return this.http.delete(this.serviceApiUrl+"user-group/delete/"+userGroup.id);
    }
    getUserGroupDataByMasterId(id){
        return this.http.get<Array<UserGroup>>(this.serviceApiUrl+"user-group/user-group-master/"+id);
    }

    getAllUserGroupMasterData(){
        return this.http.get<Array<UserGroupMaster>>(this.serviceApiUrl+"user-group-master/all");
    }
    addUserGroupMaster(userGroupMaster){     
        return this.http.post(this.serviceApiUrl+"user-group-master/add",userGroupMaster);
    }

    updateUserGroupMaster(userGroupMaster){
        return this.http.put(this.serviceApiUrl+"user-group-master/update",userGroupMaster);
    }

    deleteUserGroupMaster(userGroupMaster){
        // 
        return this.http.delete(this.serviceApiUrl+"user-group-master/delete/"+userGroupMaster.id);
    }



    getAllUserRoleData(){
        return this.http.get<Array<UserRole>>(this.serviceApiUrl+"user-role/all");
    }
    addUserRole(userRole){     
        return this.http.post(this.serviceApiUrl+"user-role/add",userRole);
    }

    updateUserRole(userRole){
        return this.http.put(this.serviceApiUrl+"user-role/update",userRole);
    }
    
    deleteUserRole(userRole){
        return this.http.delete(this.serviceApiUrl+"user-role/delete/"+userRole.id);
    }

    getUserGroupRoleMappingByGroupId(id){
        return this.http.get<Array<UserGroup>>(this.serviceApiUrl+"user-group-role-mapping/group-id/"+id);
    }
    getUserGroupRoleMappingByRoleId(id){
        return this.http.get<Array<UserGroup>>(this.serviceApiUrl+"user-group-role-mapping/role-id/"+id);
    }

    getUserDetailByGroupId(id){
        return this.http.get<Array<UserGroup>>(this.serviceApiUrl+"user-detail/group-id/"+id);
    }

    getAllUserGroupRoleMappingData(){
        return this.http.get<Array<UserGroupRoleMapping>>(this.serviceApiUrl+"user-group-role-mapping/all");
    }
    addUserGroupRoleMapping(userGroupRoleMapping){     
        return this.http.post(this.serviceApiUrl+"user-group-role-mapping/add",userGroupRoleMapping);
    }

    updateUserGroupRoleMapping(userGroupRoleMapping){
        return this.http.put(this.serviceApiUrl+"user-group-role-mapping/update",userGroupRoleMapping);
    }
    
    deleteUserGroupRoleMapping(userGroupRoleMapping){
        return this.http.delete(this.serviceApiUrl+"user-group-role-mapping/delete/"+userGroupRoleMapping.id);
    }
    getAllMenuData(){
        return this.http.get<Array<Menu>>(this.serviceApiUrl+"menu/all");
    }
    getAllUserGroupMenuMappingData(){
        return this.http.get<Array<UserGroupMenuMapping>>(this.serviceApiUrl+"user-group-menu-mapping/all");
    }
    getAllUserGroupMenuMappingByGroupId(id){
        return this.http.get<Array<UserGroupMenuMapping>>(this.serviceApiUrl+"user-group-menu-mapping/group-id/"+id);
    }
    addUserGroupMenuMapping(userGroupRoleMappingList){     
        return this.http.post(this.serviceApiUrl+"user-group-menu-mapping/add",userGroupRoleMappingList);
    }
    deleteUserGroupMenuMapping(userGroupRoleMappingList){
        return this.http.put(this.serviceApiUrl+"user-group-menu-mapping/delete",userGroupRoleMappingList);
    }
    public fetchAllState() {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'state/all')
    }

    public fetchAllCircleByStateId(stateId: number) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'circle/state-id/'+stateId)
    }
 
    public fetchAllClinic() {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'clinic/all')
    }
    
    
    public fetchAllClinicByDistrictId(districtId: number) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'clinic/district-id/'+districtId)
    }

    deleteUserDetail(userDetail){
        return this.http.delete(this.serviceApiUrl+"user/delete-user-detail/"+userDetail.id);
    }
    getUserByEmailId(emailId){
        return this.http.get<Array<UserGroup>>(this.serviceApiUrl+"user/email/"+emailId);
    }


}