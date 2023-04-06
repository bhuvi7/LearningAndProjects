import { Component, OnInit,OnDestroy,ViewChild } from '@angular/core';
import { UserManagementService } from "../user-management.service";
import { Router } from "@angular/router";
import { DataTableDirective } from 'angular-datatables';
import { Observable } from 'rxjs/Rx';
import { Subject } from 'rxjs';
import { User } from "../model/user";
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';


@Component({
  selector: 'user-list',
  templateUrl: './user-list.component.html'
})
export class UserListComponent implements OnInit,OnDestroy {
  public users: Array<User>;
  dtOptions: DataTables.Settings = {
    pagingType: 'full_numbers',
    pageLength: 10
  };
  dtTrigger: Subject<any> = new Subject();
  @ViewChild(DataTableDirective, {static: false})
  dtElement: DataTableDirective;
  public loading :boolean=false;
  constructor(public userManagementService: UserManagementService, public router: Router) { }

  ngOnInit() {
   
    this.users = new Array<User>();
    this.getAllUser();


  }

  getAllUser(){
    this.userManagementService.getAllUserData().subscribe(x => {
      this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
        dtInstance.destroy();
        this.dtTrigger.next();
        this.users = x; 
      });
           
    });
  }
  loaderFalse(){
    this.loading = false;
  }

  detailPage(user) {
    this.router.navigateByUrl("/user-management/user/detail/" + user.id);
  }

  confirmAlert(user :User) {
    Swal.fire({
      title: 'Are you sure?',
      text: 'Once deleted, you will not be able to recover this User!',
      type: 'warning',
      showCloseButton: true,
      showCancelButton: true
    }).then((willDelete) => {
        if (willDelete.dismiss) {
        //   Swal.fire('', 'Your imaginary file is safe!', 'error');
        } else {
            this.deleteUser(user);
          Swal.fire('', 'User has been deleted successfully !!!', 'success');
        }
      });
  }

  deleteUser(user) {

    this.userManagementService.deleteUser(user).subscribe((x)=>{
      this.getAllUser();
    });
    // this.getAllUser();
  }
 

  ngAfterViewInit(): void {
    this.dtTrigger.next();
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }
 

}
