import { Component,OnInit } from "@angular/core";
import { Subject } from 'rxjs';
import { UserManagementService } from "../../user-management.service";
import { UserGroup,UserGroupMaster } from "../../model/user-group";
import { UserRole } from "../../model/user-role";
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';

@Component({
    selector:"user-group",
    templateUrl:"user-group.component.html"
})
export class UserGroupComponent implements OnInit{

    constructor (private userManagementService:UserManagementService){}
    public userGroupMasters:Array<UserGroupMaster>;
    public userGroups:Array<UserGroup>;
    public userGroupList:Array<UserGroup>;
    public priorityUserGroupList:Array<UserGroup>;
    public userRoles = new Array<UserRole>();
    public userGroup:UserGroup;
    public userGroupMaster:UserGroupMaster;
    private saveFlag:string;
    public filteredGroupMasterId:number;
    public addFlag = false;
    public loading :boolean=false;
    public priorityList : Array<any>;

    public actives = [{"name":"Yes","value":"Y"},{"name":"No","value":"N"}]


    dtOptions: DataTables.Settings = { 
        pagingType: 'full_numbers',
        pageLength: 10
      };
      dtTrigger: Subject<any> = new Subject();
    
      
    ngOnInit(){ 
        this.userGroups = new Array<UserGroup>();
        this.userGroupList = new Array<UserGroup>();
        this.userGroup = new UserGroup();
        this.getAllUserGroup();
        this.userManagementService.getAllUserGroupMasterData().subscribe((x)=>{
            this.userGroupMasters =x;
        });
    }

    getAllUserGroup(){
        this.loading = true;
        this.userManagementService.getAllUserGroupData().subscribe((x)=>{
            this.userGroups =x;
            this.loading = false;
        });
    }

    addUserGroup(){     
        this.saveFlag = "add";
        this.userGroup = new UserGroup();
        this.userGroup.isActive="Y";
        this.userGroup.userGroupMasterId = this.filteredGroupMasterId;
        this.userGroup.userGroupMasterName = this.userGroupMaster.name;
        this.userGroup.userGroupMasterPriority = this.userGroupMaster.groupMasterPriority;
        var i;
        this.priorityList = [];
        this.userGroup.groupPriority= this.userGroupList.length+1;
        this.userGroupList.sort(function(a,b) {return (a.id - b.id)})
        for (i = 0; i < this.userGroupList.length+1; i++) {
               this.priorityList.push(({"id":i+1}))            
         }
    }
    editUserGroup(userGroup :UserGroup){     
        this.saveFlag = "edit";
        this.userGroup = userGroup;
    }
    saveUserGroup(){

        this.userManagementService.checkUserGroupPriority(this.userGroup.userGroupMasterPriority,this.userGroup.groupPriority).subscribe((groups)=>{
            
            if(groups.length==0){
                if(this.saveFlag=="add"){
                    this.userManagementService.addUserGroup(this.userGroup).subscribe( (x)=>{ Swal.fire('', 'User Group added successfully!!!', 'success');this. getData()});
                }else if(this.saveFlag=="edit"){
                    this.userManagementService.updateUserGroup(this.userGroup).subscribe((x)=>{Swal.fire('', 'User Group updated successfully!!!', 'success');this. getData()});
                }
            }else{
                Swal.fire('', 'Cannot add/update priority already mapped', 'error');
            }
        })
      
        this.addFlag = false;
        // this.filteredGroupMasterId=undefined;
    }

    assignUserGroupPriority(){
        this.priorityUserGroupList = [];
        this.priorityUserGroupList = (JSON.parse(JSON.stringify(this.userGroupList)));
    }
    saveUserGroupPriority(){

        let checkPriority = [];
        let duplicate: boolean=false;
        this.priorityUserGroupList.forEach((group,index)=>{
            if (checkPriority.indexOf(parseInt(group.groupPriority.toString())) == -1) {
                checkPriority.push(parseInt(group.groupPriority.toString()));
            }else{
                duplicate = true;
            }
            if(this.userGroupList.length -1 == index){
                if(duplicate){
                    Swal.fire('', 'Cannot update priority, Priority should be unique ', 'error');
                }else{
                    this.userManagementService.updateUserGroupPriority(this.priorityUserGroupList).subscribe((x)=>{Swal.fire('', 'User Group updated successfully!!!', 'success');this. getData()});
                }
            }
        })
    }
    confirmAlert(userGroup :UserGroup) {
        Swal.fire({
          title: 'Are you sure?',
          text: 'Once deleted, you will not be able to recover this User Group!',
          type: 'warning',
          showCloseButton: true,
          showCancelButton: true
        }).then((willDelete) => {
            if (willDelete.dismiss) {
            //   Swal.fire('', 'Your imaginary file is safe!', 'error');
            } else {
                this.deleteUserGroup(userGroup);
         
            }
          });
    }

    deleteUserGroup(userGroup :UserGroup){
        this.userManagementService.getUserGroupRoleMappingByGroupId(userGroup.id).subscribe((x)=>{
            if(+x.length>0){
                Swal.fire('', 'Cannot delete - mapped with User Role', 'error');
            }else if(+x.length ==0){
                 this.userManagementService.deleteUserGroup(userGroup).subscribe( (x)=>{this.getData()});
                 Swal.fire('', 'User Group has been deleted successfully !!!', 'success');
            }
        });
        // this.filteredGroupMasterId=undefined; 
        this.addFlag = false;
    }
    handleForm(){
        this.userGroupList=this.userGroups.filter((x)=>x.userGroupMasterId==this.filteredGroupMasterId);
         this.userGroupMaster = this.userGroupMasters.filter((x)=>x.id==this.filteredGroupMasterId)[0];
         var i;
         this.priorityList = [];
         this.userGroupList.sort(function(a,b) {return (a.id - b.id)})
         for (i = 0; i < this.userGroupList.length; i++) {
                this.priorityList.push(({"id":i+1}))            
          }
          
        if(+this.userGroupList.length <+this.userGroupMaster.noOfLevel){
            // 
            this.addFlag = true;
        }else{
            this.addFlag = false;
        }
    }

    openRoles(userRoles:Array<UserRole>){
        this.userRoles = userRoles;
    }
    getData(){
        this.loading = true;
        this.userManagementService.getAllUserGroupData().subscribe((x)=>{        
            this.userGroups =x;
            this.userGroupList=this.userGroups.filter((x)=>x.userGroupMasterId==this.filteredGroupMasterId);
            this.loading = false;
        });
    }
    resetFilter(){
        this.filteredGroupMasterId=undefined;
        this.userGroupList = [];
    }

}