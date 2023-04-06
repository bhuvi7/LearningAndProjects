import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';
//import { environment } from '../../../../../../../environments/environment';
// import { toWords } from 'number-to-words';
//import { dateformat } from 'dateformat';
//import { Fin01InvoiceService } from "../../fin-01-invoice-service";
import { element } from 'protractor';
import { DataTableDirective } from 'angular-datatables';
// import { PAIService } from '../../payment-against-invoice/pai-service';
import { Fin09InvoiceService } from '../../fin-09-invoice/fin-09-invoice-service';
import Swal from 'sweetalert2';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { invoicePenalties } from '../../../dashboard-progress/model/invoice-penalties';

var dateFormat = require('dateformat');

@Component({
    selector: 'fin09-credit-note-created-list',
    templateUrl: './created-list.component.html',

})

export class Fin09CreatedListComponent implements OnInit {


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
    public loading: Boolean = true;
    public storing: Array<invoicePenalties>
    public clinicTypeFilter: any = "";
    public clinicType: any = ""
    public quater: any = "";
    public year: any = "";

    constructor(private router: Router, private route: ActivatedRoute, private fin09Service: Fin09InvoiceService,
        config: NgbModalConfig, private modalService: NgbModal) {
        config.backdrop = 'static';
        config.keyboard = false;
    }

    ngOnInit() {
        this.filterDatas = []
        this.invoiceDatas = []
        // this.inProgress();
        this.fin09Service.fetchStateDetails().subscribe(x => {
            this.stateFilterDatas = x;
        })

        this.loading = false;


    }

    // inProgress() {
    //     this.invoiceDatas = []
    //     this.filterDatas = []
    //     this.fin09Service.allFin09().subscribe(x => {

    //         this.invoiceDatas = x;
    //         this.loading = false;
    //         this.dtTrigger.next();

    //     })

    // }

    handleForm(event) {
        switch (event.target.id) {
            case "state":
                this.stateFilter = event.target.value;

                this.districtFilter = "";
                this.districtFilterDatas = this.stateFilterDatas.find(state => state.stateName == event.target.value).districts
                // this.filterFunction();
                break;
            case "district":
                this.districtFilter = event.target.value;

                // this.filterFunction();
                break;
            case "clinicType":
                this.clinicType = event.target.value
                this.clinicTypeFilter = event.target.value;

            case "quater":
                this.quater = event.target.value;

                // this.filterFunction();
                break;
            case "year":
                this.year = event.target.value




                break;
            default:
                break;
        }
    }

    filterFunction() {
        this.filterDatas = [];
        this.loading = true;
        // this.dtOptions.destroy;
        // this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
        //     dtInstance.destroy();
        // })

        if (this.stateFilter == 'ALL') {
            this.stateFilter = ''
        }
        if (this.districtFilter == 'ALL') {
            this.districtFilter = ''
        }
        if (this.clinicTypeFilter == NaN) {
            this.clinicTypeFilter = ''
        }

        this.fin09Service.fetchAllUnadjustedPenaltyMultiple(this.stateFilter, this.districtFilter, this.clinicTypeFilter, this.quater, this.year).subscribe(x => {

            this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
                dtInstance.destroy();
                this.dtTrigger.next();
                this.filterDatas = x;

            });

            this.loading = false;

        })

    }

    ngAfterViewInit(): void {
        this.dtTrigger.next();
    }

    ngOnDestroy(): void {
        this.dtTrigger.unsubscribe();
    }
    resetFilter() {
        this.stateFilter = ""
        this.districtFilter = ""
        this.districtFilterDatas = []
        this.filterDatas = []
        this.loading = true;
        this.dtOptions.destroy;
        this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
            dtInstance.destroy();
        });
        // this.invoiceDatas.forEach(element => {
        //     this.filterDatas.push(element)
        // })
        this.dtTrigger.next();
        this.loading = false
    }

    update(data) {

        this.router.navigateByUrl('transaction/credit-note/fin09/created-list-details/' + data.id);
    }
}
