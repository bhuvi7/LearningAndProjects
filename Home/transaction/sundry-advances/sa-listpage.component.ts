import { Component, OnInit, ViewChild } from "@angular/core";
import { Subject } from 'rxjs';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { SAService } from './sa-service';
import { DataTableDirective } from 'angular-datatables';
import { Action } from 'rxjs/internal/scheduler/Action';

@Component({
    selector: "sa-list",
    templateUrl: "sa-listpage.component.html",
})
export class SAListPageComponent implements OnInit {

    public groups: any;
    public searchText: string;
    public listDatas: any;
    public filterDatas: Array<any>;
    public stateFilter: string = "";
    public districtFilter: string = "";
    public stateFilterDatas: Array<any>;
    public districtFilterDatas: Array<any>;
    public loading: Boolean = false;

    constructor(private router: Router, public saService: SAService) { }


    dtOptions: DataTables.Settings = {
        pagingType: 'full_numbers',
        pageLength: 10
    };
    dtTrigger: Subject<any> = new Subject();
    @ViewChild(DataTableDirective, { static: false })
    dtElement: DataTableDirective;

    ngOnInit() {
        this.filterDatas = []
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
        this.saService.fetchStateDetails().subscribe(x => {
            this.stateFilterDatas = x;
            this.dtTrigger.next()
        })
    }

    handleForm(event) {
        switch (event.target.id) {
            case "state":
                this.stateFilter = event.target.value;
                this.districtFilter = "";
                this.districtFilterDatas = this.stateFilterDatas.find(state => state.stateName == event.target.value).districts
                this.filterFunction();
                break;
            case "district":
                this.districtFilter = event.target.value;
                this.filterFunction();
                break;
            default:
                break;
        }
    }

    filterFunction() {
        this.loading = true;
        this.filterDatas = []
        this.dtOptions.destroy;
        this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
            dtInstance.destroy();
        });
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
        this.saService.fetchAllInvoiceFilter(this.stateFilter, this.districtFilter).subscribe((x) => {
            this.filterDatas = x;
            this.loading = false;
            this.dtTrigger.next();
        })
    }

    resetFilter() {
        this.stateFilter = ""
        this.districtFilter = ""
        this.districtFilterDatas = []
        this.filterDatas = []
        this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
            dtInstance.destroy();
            this.dtTrigger.next();
        });
    }

    update(id) {
        this.router.navigateByUrl('transaction/sa/sa-update/' + id);
    }

}