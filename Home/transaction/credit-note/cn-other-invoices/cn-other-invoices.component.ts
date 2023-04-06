import { Component, OnInit, ViewChild } from '@angular/core';
import { Subject } from 'rxjs';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { PAIService } from '../../payment-against-invoice/pai-service';
import { DataTableDirective } from 'angular-datatables';
import { Action } from 'rxjs/internal/scheduler/Action';

@Component({
  selector: 'cn-other-invoices',
  templateUrl: './cn-other-invoices.component.html',
  styleUrls: ['./cn-other-invoices.component.scss']
})
export class CnOtherInvoicesComponent implements OnInit {
  public groups: any;
  public searchText: string;
  public listDatas: any;
  public filterDatas: Array<any>;
  public stateFilter: string = "";
  public districtFilter: string = "";
  public stateFilterDatas: Array<any>;
  public districtFilterDatas: Array<any>;
  public loading: Boolean = false;
  public clinicTypeFilter: string=""

  constructor(private router: Router, public paiService: PAIService) { }


  dtOptions: DataTables.Settings = {
      pagingType: 'full_numbers',
      pageLength: 10
  };
  dtTrigger: Subject<any> = new Subject();
  @ViewChild(DataTableDirective, { static: false })
  dtElement: DataTableDirective;

  ngOnInit() {
      this.filterDatas = [];
      this.stateFilterDatas =[];
      // this.paiService.fetchForPAIList("PAYMENT-DRAFT", "PAYMENT-PENDING").subscribe(x => {
      //     this.listDatas = x;
      //     this.listDatas.forEach(element => {
      //         if (element.paymentStatus == 'PAYMENT-DRAFT') {
      //             element.isInDraft = true
      //         } else {
      //             element.isInDraft = false
      //         }
      //         this.filterDatas.push(element)
      //     });
      //     this.dtTrigger.next();
      //     this.loading = false;
      // })
      this.paiService.fetchStateDetails().subscribe(x => {
          x.forEach((x=>{
              // 
              if(x.id!=0){this.stateFilterDatas.push(x)}
            }))
          // this.stateFilterDatas = x;
          // this.dtTrigger.next()
      })
  }

  handleForm(event) {
      switch (event.target.id) {
          case "state":
              this.stateFilter = event.target.value;
              this.districtFilter = "";
              this.districtFilterDatas = this.stateFilterDatas.find(state => state.stateName == event.target.value).districts
              
              break;
          case "district":
              this.districtFilter = event.target.value;
              
              break;

              case "clinicType":
                  this.clinicTypeFilter = event.target.value;
                  
                  break;
          default:
              break;
      }
  }

  filterFunction() {
      this.loading = true;
      // this.filterDatas = []
      // this.dtOptions.destroy;

      // if (this.stateFilter !== "" && this.districtFilter == "") {
      //     this.listDatas.forEach(element => {
      //         if (element.stateName == this.stateFilter) {
      //             this.filterDatas.push(element)
      //         }
      //     });
      // } else if (this.stateFilter !== "" && this.districtFilter !== "") {
      //     this.listDatas.forEach(element => {
      //         if (element.stateName == this.stateFilter && element.districtName == this.districtFilter) {
      //             this.filterDatas.push(element)
      //         }
      //     })
      // }
      this.paiService.fetchAllInvoiceFilter1(this.stateFilter, this.districtFilter, this.clinicTypeFilter).subscribe((x) => {
          this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
              dtInstance.destroy();
              this.dtTrigger.next();
              this.filterDatas = x;
          });
       
          this.loading = false;
          // this.dtTrigger.next();
      })
  }

  resetFilter() {
      this.stateFilter = ""
      this.districtFilter = ""
      this.districtFilterDatas = []
      this.filterDatas = []
      // this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
      //     dtInstance.destroy();
      //     this.dtTrigger.next();
      // });
  }

  update(id) {
      this.router.navigateByUrl('transaction/credit-note/cn-other-invoices/cn-other-invoices-update/' + id);
      

  }


ngAfterViewInit(): void {
  this.dtTrigger.next();
}

ngOnDestroy(): void {
  this.dtTrigger.unsubscribe();
}
}
