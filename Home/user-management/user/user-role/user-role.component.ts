import { Component,OnInit } from "@angular/core";
import { Subject } from 'rxjs';
import { UserManagementService } from "../../user-management.service";
import { UserRole } from "../../model/user-role";
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
@Component({
    selector:"user-role",
    templateUrl:"user-role.component.html"
})
export class UserRoleComponent implements OnInit{
    constructor (private userManagementService:UserManagementService){}
    public userRoles:Array<UserRole>;
    public userRole:UserRole;
    private saveFlag:string;
    public loading :boolean=false;

    public actives = [{"name":"Yes","value":"Y"},{"name":"No","value":"N"}]


    dtOptions: DataTables.Settings = { 
        pagingType: 'full_numbers',
        pageLength: 10
      };
      dtTrigger: Subject<any> = new Subject();
    
      
    ngOnInit(){ 
        this.userRoles = new Array<UserRole>();
        this.userRole = new UserRole();
        this.getAllUserRole();
    }

    getAllUserRole(){ 
        this.loading = true;
        this.userManagementService.getAllUserRoleData().subscribe((x)=>{
            this.userRoles =x;
            this.loading = false;
        });
    }

    addUserRole(){     
        this.saveFlag = "add";
        this.userRole = new UserRole();
        this.userRole.isActive ="Y";
    }
    editUserRole(userRole :UserRole){     
        this.saveFlag = "edit";
        this.userRole = userRole;
    }
    saveUserRole(){
        if(this.saveFlag=="add"){
            this.userManagementService.addUserRole(this.userRole).subscribe( (x)=>{Swal.fire('', 'User Role added successfully!!!', 'success');this.getAllUserRole()});
        }else if(this.saveFlag=="edit"){
            this.userManagementService.updateUserRole(this.userRole).subscribe((x)=>{Swal.fire('', 'User Role updated successfully!!!', 'success');this.getAllUserRole()});
        }
    }

    confirmAlert(userRole :UserRole) {
        Swal.fire({
          title: 'Are you sure?',
          text: 'Once deleted, you will not be able to recover this User Role!',
          type: 'warning',
          showCloseButton: true,
          showCancelButton: true
        }).then((willDelete) => {
            if (willDelete.dismiss) {
            //   Swal.fire('', 'Your imaginary file is safe!', 'error');
            } else {
                this.deleteUserRole(userRole);
           
            }
          });
      }

      deleteUserRole(userRole :UserRole){
        this.userManagementService.getUserGroupRoleMappingByRoleId(userRole.id).subscribe((x)=>{
            if(+x.length>0){
                Swal.fire('', 'Cannot delete - mapped with User Group', 'error');
            }else if(+x.length ==0){
                this.userManagementService.deleteUserRole(userRole).subscribe( (x)=>{this.getAllUserRole()});
                Swal.fire('', 'User Role has been deleted successfully !!!', 'success');
            }
        })
    }

}