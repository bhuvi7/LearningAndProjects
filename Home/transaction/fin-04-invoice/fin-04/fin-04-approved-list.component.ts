import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subject } from 'rxjs';
import { DataTableDirective } from 'angular-datatables';
import { Fin04InvoiceService } from '../fin-04-invoice-service';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
// import { toWords } from 'number-to-words';

@Component({
  selector: 'fin-04-approved-list',
  templateUrl: './fin-04-approved-list.component.html',
  providers: [NgbModalConfig, NgbModal]
})

export class Fin04ApprovedListComponent implements OnInit {

  dtOptions: DataTables.Settings = {
    pagingType: 'full_numbers',
    pageLength: 10
  };
  dtTrigger: Subject<any> = new Subject();
  @ViewChild(DataTableDirective, { static: false })
  dtElement: DataTableDirective;

  public invoiceDatas: any;
  public buttonEnableBoolean: Boolean;
  public selectedValue: Boolean;
  public createData: any;
  public modalBodyContent;
  public approveData;
  public filterDatas: any;
  public stateFilter: string = "";
  public districtFilter: string = "";
  public stateFilterDatas: Array<any>;
  public districtFilterDatas: Array<any>;
  public loading: Boolean;

  constructor(private router: Router, private route: ActivatedRoute, private fin04Service: Fin04InvoiceService,
    config: NgbModalConfig, private modalService: NgbModal) {
    config.backdrop = 'static';
    config.keyboard = false;
  }

  ngOnInit() {
    // this.loading = true

    // this.dtOptions = {
    //     pagingType: 'full_numbers',
    //     pageLength: 10,
    //     processing: true
    //     // lengthMenu:{5,10,15},
    // };
    // this.filterDatas = []
    // this.fin04Service.fetchDataForFin04Approved(5, 'APPROVED BY MOH').subscribe(x => {
    //     this.invoiceDatas = x;



    // this.invoiceDatas.sort((a,b) => new Date(b.invoice_date).getTime() - new Date(a.invoice_date).getTime())
    // this.invoiceDatas.sort((a, b) => (a[state] || "").toString().localeCompare((b[state] || "").toString()) || (a[district] || "").toString().localeCompare((b[district] || "").toString()));
    // this.invoiceDatas.forEach(element => {
    //     element.approved = true
    //     this.filterDatas.push(element)
    //     
    // });
    // this.loading = false;
    // this.dtTrigger.next();
    // })
    // this.fin04Service.fetchStateDetails().subscribe(x => {
    //     this.stateFilterDatas = x;
    // })
    // this.buttonEnableBoolean = true
  }

  ngAfterViewInit(): void {
    this.dtTrigger.next();
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }


  // common filter fields ----
  selectedIds(selectedIds) {
    // if (this.route.params['_value']['_indicator'] != "undefined") {
    // if (this.route.params['_value']['_indicator'] == "new") {
    this.loading = true
    this.fin04Service.fetchDataForFin04Approved(5, 'APPROVED BY MOH', selectedIds).subscribe(fin => {

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
    // }
    //  else {
    //   this.fin04Service.fetchDataForFin04Approved(2, 'APPROVED BY MOH').subscribe(fin => {
    //     if (fin.length == 0) {
    //       Swal.fire('', 'All the clinics under this state/district is Approved,check Approved process', 'info');
    //       this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
    //         dtInstance.destroy();
    //         this.dtTrigger.next();
    //         this.filterDatas = [];
    //       });
    //       this.loading = false
    //     } else {
    //       fin.forEach((element, i) => {
    //         if (element.stateName == 'SARAWAK') {
    //           if (element.clinicTypeId == 1) {
    //             element.clinicTypeCode = 'PKB'
    //           }
    //           if (element.clinicTypeId == 2) {
    //             element.clinicTypeCode = 'PPB'
    //           }
    //         }


    //         if (fin.length - 1 == i) {
    //           this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
    //             dtInstance.destroy();
    //             this.dtTrigger.next();
    //             this.filterDatas = fin;
    //           })
    //           this.loading = false
    //         }
    //       });
    //     }
    //     // this.loading = false
    //   })
    // }
    // }
  }


  // handleForm(event) {
  //     switch (event.target.id) {
  //         case "state":
  //             this.stateFilter = event.target.value;
  //             this.districtFilter = "";
  //             this.districtFilterDatas = this.stateFilterDatas.find(state => state.stateName == event.target.value).districts

  //             // this.filterFunction();
  //             break;
  //         case "district":
  //             this.districtFilter = event.target.value;

  //             // this.filterFunction();
  //             break;
  //         default:
  //             break;
  //     }
  // }

  // filterFunction() {
  //     this.filterDatas = []
  //     this.loading = true;

  //     if (this.stateFilter == "ALL" || this.stateFilter == null || this.stateFilter == "") {
  //         this.invoiceDatas.forEach(element => {
  //             this.filterDatas.push(element)


  //         })

  //     } else if (this.stateFilter !== "" && this.districtFilter == "") {
  //         this.invoiceDatas.forEach(element => {
  //             if (element.stateName == this.stateFilter) {
  //                 this.filterDatas.push(element)


  //             }
  //         });
  //     } else if (this.stateFilter !== "" && this.districtFilter !== "") {
  //         this.invoiceDatas.forEach(element => {
  //             if (element.stateName == this.stateFilter && element.districtName == this.districtFilter) {
  //                 this.filterDatas.push(element)

  //             }
  //         })
  //     } this.dtOptions.destroy;
  //     this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
  //         dtInstance.destroy();
  //     });
  //     this.dtTrigger.next()
  //     this.loading = false;
  //     //inprogress
  // }

  // resetFilter() {
  //     this.stateFilter = ""
  //     this.districtFilter = ""
  //     this.districtFilterDatas = []
  //     this.buttonEnableBoolean = true

  //     this.loading = true;
  //     this.dtOptions.destroy;
  //     this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
  //         dtInstance.destroy();
  //         this.dtTrigger.next();
  //         this.filterDatas = []

  //     });

  //     this.loading = false
  // }

  // buttonEnable(id) {
  //     this.invoiceDatas.forEach(element => {
  //         if (element.id === id) {
  //             element.selected = true
  //             this.createData = element
  //         } else {
  //             element.selected = false
  //         }
  //     });
  //     this.buttonEnableBoolean = false
  // }

  // buttonEnableValue() {
  //     return this.buttonEnableBoolean
  // }

  // customRadio(id) {
  //     return "customRadio" + id
  // }

  // openModal(content, modalContent, data) {
  //     this.modalBodyContent = modalContent
  //     this.modalService.open(content);
  //     this.approveData = data;
  // }

  // closeModal() {
  //     if (this.modalBodyContent == 'Approve') { this.approve(this.approveData); }
  // }

  // approve(data) {
  //     if (data.status == 'IN INTERNAL APPROVAL') { data.status = 'FOR APPROVAL TO MOH' }
  //     else if (data.status == 'FOR APPROVAL TO MOH') { data.status = 'APPROVED BY MOH' }
  //     delete data.fin10b;
  //     this.fin04Service.updateFin04Status(data).subscribe(x => {
  //         Swal.fire('', 'Fin 04 approved successfully!!!', 'success');
  //         this.invoiceDatas.forEach(element => {
  //             if (element.id == data.id) {
  //                 if (element.status == 'IN INTERNAL APPROVAL' || element.status == 'FOR APPROVAL TO MOH') {
  //                     element.approved = false
  //                 } else {
  //                     element.approved = true;
  //                 }
  //             }
  //         });
  //     })
  // }

  navigateToApproval(data) {
    this.router.navigateByUrl('transaction/fin-04-invoice/fin-04-approve/' + data);
  }
}