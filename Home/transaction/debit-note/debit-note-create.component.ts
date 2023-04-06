import { Component, OnInit, ViewChild } from '@angular/core';
import { Subject } from 'rxjs';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { PAIService } from './../payment-against-invoice/pai-service';
import { DataTableDirective } from 'angular-datatables';
import { HttpClient } from '@angular/common/http';
//import { environment } from '../../../../../../../environments/environment';
//import { Fin01InvoiceService } from '../../fin-01-invoice-service';
// import { toWords } from 'number-to-words';

@Component({
    selector: 'debit-note-create',
    templateUrl: './debit-note-create.component.html',
    styleUrls: ['./debit-note-create.component.scss']
})

export class DebitNoteCreateComponent implements OnInit {
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
        this.router.navigateByUrl('transaction/debit-note/create-list/' + id);
  
    }
  
  
  ngAfterViewInit(): void {
    this.dtTrigger.next();
  }
  
  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }
}
  
