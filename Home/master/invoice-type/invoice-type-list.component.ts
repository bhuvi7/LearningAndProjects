import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { MasterService } from "../master.service";
import { Router } from "@angular/router";
import { DataTableDirective } from 'angular-datatables';
import { Observable } from 'rxjs/Rx';
import { Subject } from 'rxjs';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { InvoiceType } from '../model/invoice-type';


@Component({
    selector: 'invoice-type-list',
    templateUrl: './invoice-type-list.component.html'
})
export class InvoiceTypeListComponent implements OnInit, OnDestroy {
    public invoiceTypes: Array<InvoiceType>;
    public loading: Boolean = true;
    dtOptions: DataTables.Settings = {
        pagingType: 'full_numbers',
        pageLength: 10
    };
    dtTrigger: Subject<any> = new Subject();
    @ViewChild(DataTableDirective, { static: false })
    dtElement: DataTableDirective;

    constructor(public masterService: MasterService, public router: Router) { }

    ngOnInit() {
        this.invoiceTypes = Array<InvoiceType>();
        this.masterService.getAllInvoiceTypes().subscribe(x => {
            this.invoiceTypes = x;
            this.loading = false;
            this.dtTrigger.next();
        });

    }

    detailPage(user) {
        this.router.navigateByUrl("/master/invoice-type/detail/" + user.id);
    }

    // confirmAlert() {
    //     Swal.fire({
    //         title: 'Are you sure?',
    //         text: 'Once deleted, you will not be able to recover this User!',
    //         type: 'warning',
    //         showCloseButton: true,
    //         showCancelButton: true
    //     }).then((willDelete) => {
    //         if (willDelete.dismiss) {
    //             //   Swal.fire('', 'Your imaginary file is safe!', 'error');
    //         } else {
    //             //this.deleteUser(user);
    //             Swal.fire('', 'User has been deleted successfully !!!', 'error');
    //         }
    //     });
    // }

    //   deleteUser(user) {

    //     this.userManagementService.deleteUser(user).subscribe((x)=>{
    //       this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
    //         dtInstance.destroy();
    //         this.dtTrigger.next();
    //         this.getAllUser();
    //       });
    //     });

    //   }


    // ngAfterViewInit(): void {
    //     this.dtTrigger.next();
    // }

    ngOnDestroy(): void {
        this.dtTrigger.unsubscribe();
    }


}
