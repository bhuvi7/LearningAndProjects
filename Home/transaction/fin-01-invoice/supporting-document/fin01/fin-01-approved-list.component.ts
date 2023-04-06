import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Fin01InvoiceService } from '../../fin-01-invoice-service';
import { element } from 'protractor';
import { Subject } from 'rxjs';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
// import { toWords } from 'number-to-words';
import { PagesService } from '../../../../pages.service';
import { DataTableDirective } from 'angular-datatables';
import Swal from 'sweetalert2';

@Component({
  selector: 'fin-01-approved-list',
  templateUrl: './fin-01-approved-list.component.html',
  providers: [NgbModalConfig, NgbModal]
})

export class Fin01ApprovedListComponent implements OnInit {

  dtOptions: DataTables.Settings = {
    pagingType: 'full_numbers',
    pageLength: 10
  };
  dtTrigger: Subject<any> = new Subject();
  @ViewChild(DataTableDirective, { static: false })
  dtElement: DataTableDirective;
  public districtDatas: any;
  public buttonEnableBoolean: Boolean;
  public selectedValue: Boolean;
  public createData: any;
  public districtId;
  public clinicTypeId;
  public modalBodyContent;
  public approveData;
  public filterDatas: any;
  public stateFilter: string = "";
  public districtFilter: string = "";
  public stateFilterDatas: Array<any>;
  public districtFilterDatas: Array<any>;
  public loading: Boolean = false;
  public userId: Number;

  constructor(private router: Router, private route: ActivatedRoute, private fin01Service: Fin01InvoiceService,
    config: NgbModalConfig, private modalService: NgbModal, private pagesService: PagesService) {
    config.backdrop = 'static';
    config.keyboard = false;
  }

  ngOnInit() {
    this.filterDatas = []

  }
  ngAfterViewInit(): void {
    this.dtTrigger.next();
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }

  handleForm(event) {
    switch (event.target.id) {
      case "state":
        this.stateFilter = event.target.value;
        this.districtFilter = "";
        this.districtFilterDatas = this.stateFilterDatas.find(state => state.id == event.target.value).districts
        // this.filterFunction();
        break;
      case "district":
        this.districtFilter = event.target.value;
        // this.filterFunction();
        break;
      default:
        break;
    }
  }
  selectedIds(selectedIds) {

    if (this.route.params['_value']['_indicator'] != "undefined") {
      if (this.route.params['_value']['_indicator'] == "new") {
        this.loading = true
        this.fin01Service.fetchDataForfin01Approved('APPROVED BY MOH', selectedIds).subscribe(fin => {

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
        })
      } else {
        this.fin01Service.fetchDataForfin01ApprovedOlder('APPROVED BY MOH', selectedIds).subscribe(fin => {

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
        })
      }
    }
  }



  navigateToApproval(id) {
    this.router.navigateByUrl('transaction/fin-01-invoice/fin-01-approve/' + id);
  }
}