import { UserGroup } from "../model/user-group";
export class User{
    public id:number;
    public userName:string;
    public email:string;
    public password?:string;
    public isActive?:string;
    public name?:string="";
    public designation?:string ="";
    public userLevel?:string;
    public userDetails?:Array<UserDetail>;
    public organisationId?:number=1;
    public applicationId?:number= 1;
    public menuMappingGroupMasterPriority?:number;
    public menuMappingGroupName?:string;
    public menuMappingGroupPriority?:number;
   
}
export class UserDetail{
    public id:number;
    public userGroupId:number;
    public userLevel:string;
    public stateId:number;
    public circleId:number;
    public districtId:number;
    public clinicId:number;
    public userGroupPriority:number;
    public userGroupMasterPriority:number;
    public userGroupCode:string;
    public userGroupName:string;
    public userGroups:Array<UserGroup>;
}