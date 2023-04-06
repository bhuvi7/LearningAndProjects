import { Component,OnInit,ViewChild } from "@angular/core";
import { Subject } from 'rxjs';
import { UserGroup ,UserGroupMaster} from "../../model/user-group";
import { UserRole } from "../../model/user-role";
import { UserGroupRoleMapping } from "../../model/user-group-role-mapping";
import { UserManagementService } from "../../user-management.service";
import { DataTableDirective } from 'angular-datatables';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
@Component({
    selector:"user-group-role-mapping",
    templateUrl:"user-group-role-mapping.component.html"
})
export class UserGroupRoleMappingComponent implements OnInit{
    dtOptions: DataTables.Settings = { 
        pagingType: 'full_numbers',
        pageLength: 10
      };
  dtTrigger: Subject<any> = new Subject();
  @ViewChild(DataTableDirective, {static: false})
  dtElement: DataTableDirective;
    constructor (private userManagementService:UserManagementService){}

    public groups:any;
    public roles:any;
    public groupRoles:any;

    public userRoles:Array<UserRole>;
    public activeUserRoles:Array<UserRole>;
    public userRole:UserRole;
    public userGroupMasters:Array<UserGroupMaster>;
    public userGroups:Array<UserGroup>;
    public activeUserGroups:Array<UserGroup>;
    public userGroup:UserGroup;
    public userGroupMaster:UserGroupMaster;

    public userGroupRoleMappings:Array<UserGroupRoleMapping>;
    public userGroupRoleMappingList:Array<UserGroupRoleMapping>;
    public userGroupRoleMapping:UserGroupRoleMapping;
    public saveFlag:string;
    public addFlag:boolean=false;
    public loading :boolean=false;
    public filteredGroupMasterId:number;

    public actives = [{"name":"Yes","value":"Y"},{"name":"No","value":"N"}]
   
      
    ngOnInit(){ 
        this.userGroupRoleMapping = new UserGroupRoleMapping();
        this.activeUserGroups = new Array<UserGroup>();
        this.activeUserRoles = new Array<UserRole>();
        this.getAllUserGroup();
        this.getAllUserRole();
        this.getAllUserGroupRoleMapping();
        this.userManagementService.getAllUserGroupMasterData().subscribe((x)=>{
            this.userGroupMasters =x;
        });

    }


    getAllUserGroup(){
        this.userManagementService.getAllUserGroupData().subscribe((x)=>{
            this.userGroups =x;
        
        });
    }

    getAllUserRole(){
        this.userManagementService.getAllUserRoleData().subscribe((x)=>{
            this.userRoles =x;
            x.forEach((g)=>{
                if(g.isActive=='Y'){
                    this.activeUserRoles.push(g);
                }
            }) 
        });
    }
    getAllUserGroupRoleMapping(){
        this.userManagementService.getAllUserGroupRoleMappingData().subscribe((x)=>{        
            this.userGroupRoleMappings =x;
        });
    }
    handleForm(){
        this.loading = true;
        this.activeUserGroups = [];
        this.userGroupRoleMappingList=this.userGroupRoleMappings.filter((x)=>x.userGroupMasterId==this.filteredGroupMasterId);
        this.loading = false;
        this.userGroups.forEach((g)=>{
            if(g.isActive=='Y' && +g.userGroupMasterId == +this.filteredGroupMasterId){
                this.activeUserGroups.push(g);
            }
        });
      
        this.addFlag=true;
      
    }
    resetFilter(){
        this.filteredGroupMasterId=undefined;
        this.userGroupRoleMappingList = [];
        this.addFlag=false;
    }
    addUserRoleMapping(){     
        this.saveFlag = "add";
        this.userGroupRoleMapping = new UserGroupRoleMapping();
        this.userGroupRoleMapping.isActive ="Y";
    }
    editUserRoleMapping(userGroupRoleMapping :UserGroupRoleMapping){     
        this.saveFlag = "edit";
        this.userGroupRoleMapping = userGroupRoleMapping;
    }
    
    saveUserGroupRoleMapping(){
        let duplicateRoleFlag = false;
        this.userGroupRoleMappingList.forEach((list)=>{
            if(+list.userRoleId===+this.userGroupRoleMapping.userRoleId){
                duplicateRoleFlag =true;
            }
        })
        if(duplicateRoleFlag){
            Swal.fire('', 'Mapping already exist !!!', 'error');
        }else{
            if(this.saveFlag=="add"){
                this.userManagementService.addUserGroupRoleMapping(this.userGroupRoleMapping).subscribe( (x)=>{ Swal.fire('', 'Mapping added successfully!!!', 'success');this.getData()});
            }else if(this.saveFlag=="edit"){
                this.userManagementService.updateUserGroupRoleMapping(this.userGroupRoleMapping).subscribe((x)=>{Swal.fire('', 'Mapping updated successfully!!!', 'success');this.getData()});
            }
        }
      

    }

    getData(){
        this.userManagementService.getAllUserGroupRoleMappingData().subscribe((x)=>{        
            this.userGroupRoleMappings =x;
            this.userGroupRoleMappingList=this.userGroupRoleMappings.filter((x)=>x.userGroupMasterId==this.filteredGroupMasterId);
        });
    }
  
    confirmAlert(userGroupRoleMapping :UserGroupRoleMapping) {
        Swal.fire({
          title: 'Are you sure?',
          text: 'Once deleted, you will not be able to recover this Mapping!',
          type: 'warning',
          showCloseButton: true,
          showCancelButton: true
        }).then((willDelete) => {
            if (willDelete.dismiss) {
            //   Swal.fire('', 'Your imaginary file is safe!', 'error');
            } else {
                this.deleteUserGroupRoleMapping(userGroupRoleMapping);
             
            }
          });
    }

    deleteUserGroupRoleMapping(userGroupRoleMapping :UserGroupRoleMapping){
   
            this.userManagementService.deleteUserGroupRoleMapping(userGroupRoleMapping).subscribe( (x)=>{this.getData();           Swal.fire('', 'Mapping has been deleted successfully !!!', 'success');});
 
    }

    ngAfterViewInit(): void {
        this.dtTrigger.next();
      }
    
      ngOnDestroy(): void {
        this.dtTrigger.unsubscribe();
      }
}