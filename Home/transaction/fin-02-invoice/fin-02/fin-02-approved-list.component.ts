import { Component, OnInit, ViewChild } from '@angular/core';
import { DataTableDirective } from 'angular-datatables';
import { Router, ActivatedRoute } from '@angular/router';
import { Subject } from 'rxjs';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { Fin02InvoiceService } from '../fin-02-service';

@Component({
  selector: 'fin-02-approved-list',
  templateUrl: 'fin-02-approved-list.component.html',
})

export class Fin02ApprovedListComponent implements OnInit {

  dtOptions: DataTables.Settings = {
    pagingType: 'full_numbers',
    pageLength: 10
  };
  dtTrigger: Subject<any> = new Subject();
  @ViewChild(DataTableDirective, { static: false })
  dtElement: DataTableDirective;

  

  public filterDatas: any;
  public loading: Boolean = false;

  constructor(private router: Router, private route: ActivatedRoute, private fin02Service: Fin02InvoiceService) {

  }

  ngOnInit() {
  }


  // common filter fields ----
  selectedIds(selectedIds) {
    if (this.route.params['_value']['_indicator'] != "undefined") {
      if (this.route.params['_value']['_indicator'] == "new") {
        this.loading = true
        this.fin02Service.fetchDataForFin02Approved(3, 'APPROVED BY MOH',  selectedIds).subscribe(fin => {

          if (fin.length == 0) {
            Swal.fire('', 'All the clinics under this state/district is Approved,check Approved process', 'info');
            this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
              dtInstance.destroy();
              this.dtTrigger.next();
              this.filterDatas = [];
          });
          this.loading = false
          } else {
            fin.forEach((element, i) => {
              // if (element.status == 'IN INTERNAL APPROVAL' || element.status == 'FOR APPROVAL TO MOH') {
              //     element.approved = false
              // } else {
              //     element.approved = true
              // }
              if (element.stateName == 'SARAWAK') {
                if (element.clinicTypeId == 1) {
                  element.clinicTypeCode = 'PKB'
                }
                if (element.clinicTypeId == 2) {
                  element.clinicTypeCode = 'PPB'
                }
              }
              if (fin.length - 1 == i) {
                this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
                  dtInstance.destroy();
                  this.dtTrigger.next();
                  this.filterDatas = fin;
                })
                this.loading = false
              }
            });
          }
          // this.loading = false

        })
      } else {
        this.fin02Service.fetchDataForFin02ApprovedOlder(3, 'APPROVED BY MOH', selectedIds).subscribe(fin => {
          if (fin.length == 0) {
            Swal.fire('', 'All the clinics under this state/district is Approved,check Approved process', 'info');
            this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
              dtInstance.destroy();
              this.dtTrigger.next();
              this.filterDatas = [];
          });
          this.loading = false
          } else {
            fin.forEach((element, i) => {
              if (element.stateName == 'SARAWAK') {
                if (element.clinicTypeId == 1) {
                  element.clinicTypeCode = 'PKB'
                }
                if (element.clinicTypeId == 2) {
                  element.clinicTypeCode = 'PPB'
                }
              }

              // if (element.status == 'IN INTERNAL APPROVAL' || element.status == 'FOR APPROVAL TO MOH') {
              //     element.approved = false
              // } else {
              //     element.approved = true
              // }
              if (fin.length - 1 == i) {
                this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
                  dtInstance.destroy();
                  this.dtTrigger.next();
                  this.filterDatas = fin;
                })
                this.loading = false
              }
            });
          }
          // this.loading = false
        })
      }
    }
  }


  // --------

  ngAfterViewInit(): void {
    this.dtTrigger.next();
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }

  navigateToApproval(data) {
    this.router.navigateByUrl('transaction/fin-02-invoice/fin-02-approve/' + data);
  }
}