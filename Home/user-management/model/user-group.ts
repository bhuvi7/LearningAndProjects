import { UserRole } from "../model/user-role";
export class UserGroup{
    public id:number;
    public groupCode:string;
    public groupName:string;
    public userGroupMasterNoOfLevel:number ;
    public userGroupMasterId:number ;
    public userGroupMasterName:string;
    public isActive:string;
    public userGroupMasterPriority:number;
    public groupPriority:number ; 
    public userRoles:Array<UserRole>;
}

export class UserGroupMaster{
    public id:number;
    public name:string;
    public noOfLevel:number;
    public groupMasterPriority:number;
}