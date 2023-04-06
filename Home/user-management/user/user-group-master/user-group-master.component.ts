import { Component,OnInit } from "@angular/core";
import { Subject } from 'rxjs';
import { UserManagementService } from "../../user-management.service";
import { UserGroupMaster } from "../../model/user-group";
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';

@Component({
    selector:"user-group-master",
    templateUrl:"user-group-master.component.html"
})
export class UserGroupMasterComponent implements OnInit{

    constructor (private userManagementService:UserManagementService){}
    public userGroupMasters:Array<UserGroupMaster>;
    public userGroupMaster:UserGroupMaster;
    private saveFlag:string;
    private noOfLevels:number ; 
    public loading :boolean=false;
    private userGroupLength:number =0;

    dtOptions: DataTables.Settings = { 
        pagingType: 'full_numbers',
        pageLength: 10
      };
      dtTrigger: Subject<any> = new Subject();
    
      
    ngOnInit(){ 
        this.userGroupMasters = new Array<UserGroupMaster>();
        this.userGroupMaster = new UserGroupMaster();
        this.getAllUserGroupMaster();
    }

    getAllUserGroupMaster(){
        this.loading = true;
        this.userManagementService.getAllUserGroupMasterData().subscribe((x)=>{
            this.userGroupMasters =x;
            this.loading = false;
        });
    }

    addUserGroupMaster(){     
        this.saveFlag = "add";
        this.userGroupMaster = new UserGroupMaster();
    }
    editUserGroupMaster(userGroupMaster :UserGroupMaster){     
        this.saveFlag = "edit";
        this.userGroupMaster = userGroupMaster;
        this.noOfLevels = +userGroupMaster.noOfLevel;
    }
    saveUserGroupMaster(){
        if(this.saveFlag=="add"){
            this.userManagementService.addUserGroupMaster(this.userGroupMaster).subscribe( (x)=>{ Swal.fire('', 'User Group Level added successfully!!!', 'success');this.getAllUserGroupMaster()});
        }else if(this.saveFlag=="edit"){
            this.userManagementService.getUserGroupDataByMasterId(this.userGroupMaster.id).subscribe((x)=>{
                this.userGroupLength = x.length;
                this.updateUserGroupMaster();
            })
           
        }
    }
    updateUserGroupMaster(){
        if(+this.userGroupMaster.noOfLevel < this.noOfLevels && this.userGroupMaster.noOfLevel<this.userGroupLength){
            this.userGroupMaster.noOfLevel = this.noOfLevels;
            Swal.fire('', 'No of levels cannot be reduced !!!', 'error')
        }else{
            this.userManagementService.updateUserGroupMaster(this.userGroupMaster).subscribe((x)=>{Swal.fire('', 'User Group Level updated successfully!!!', 'success');this.getAllUserGroupMaster()});
        }
    }
    confirmAlert(userGroupMaster :UserGroupMaster) {
        Swal.fire({
          title: 'Are you sure?',
          text: 'Once deleted, you will not be able to recover this User Group Level!',
          type: 'warning',
          showCloseButton: true,
          showCancelButton: true
        }).then((willDelete) => {
            if (willDelete.dismiss) {
            //   Swal.fire('', 'Your imaginary file is safe!', 'error');
            } else {
                this.deleteUserGroupMaster(userGroupMaster);
           
            }
          });
    }

    deleteUserGroupMaster(userGroupMaster :UserGroupMaster){
        this.userManagementService.getUserGroupDataByMasterId(userGroupMaster.id).subscribe((x)=>{
            if(+x.length>0){
                Swal.fire('', 'Cannot delete - mapped with User Group', 'error');
            }else if(+x.length ==0){
                this.userManagementService.deleteUserGroupMaster(userGroupMaster).subscribe( (x)=>{this.getAllUserGroupMaster()});
                Swal.fire('', 'User Group Level has been successfully !!!', 'success');
            }
        })
        
    }

}