import { Component, OnInit, ViewChild } from "@angular/core";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { DataTableDirective } from "angular-datatables";
import { Subject } from 'rxjs';
import Swal from "sweetalert2";
import { Fin01InvoiceService } from '../../fin-01-invoice-service';

@Component({
    selector: "fin-06-create-list",
    templateUrl: "./fin-06-create-list.component.html"
})
export class Fin06CreateListComponent implements OnInit {

    dtOptions: DataTables.Settings = {
        pagingType: 'full_numbers',
        pageLength: 10
    };
    dtTrigger: Subject<any> = new Subject();
    @ViewChild(DataTableDirective, { static: false })
    dtElement: DataTableDirective;

    public propsData = window.history.state.data ? window.history.state.data[0].id : 0;
    public listDatas: any;
    public buttonEnableBoolean: Boolean;
    public selectedValue: Boolean;
    public clinicCode: any;
    public invoiceType: string;
    public cardTitle: string;
    public filterDatas: any;
    public stateFilter: string = "0";
    public districtFilter: string = "0";
    public stateFilterDatas: Array<any>;
    public districtFilterDatas: Array<any>;
    public loading: Boolean = false;
    public month: any
    public year: any

    constructor(private router: Router, private route: ActivatedRoute, private fin01Service: Fin01InvoiceService) { }

    ngOnInit() {


        this.buttonEnableBoolean = true
    }

    ngAfterViewInit(): void {
        this.dtTrigger.next();
    }

    ngOnDestroy(): void {
        this.dtTrigger.unsubscribe();
    }

    customRadio(id) {
        return "customRadio" + id
    }

    buttonEnable(clinicCode, month, year) {
        this.clinicCode = clinicCode
        this.month = month
        this.year = year
        this.buttonEnableBoolean = false
    }


    selectedIds(selectedIds) {
        this.loading = true
        if (this.route.params['_value']['_indicator'] != "undefined") {
            if (this.route.params['_value']['_indicator'] == "new") {
                this.fin01Service.fetchDataForFin06CreateList(selectedIds).subscribe(fin => {

                    this.assingDataFromDb(fin, selectedIds.stateId);
                    this.loading = false

                })
            } else {
                this.fin01Service.fetchDataForFin06CreateListOlder(selectedIds).subscribe(fin => {
                    this.assingDataFromDb(fin, selectedIds.stateId);
                    this.loading = false
                })
            }
        }
    }
    assignDatas(fin: any) {
        this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
            dtInstance.destroy();
            this.dtTrigger.next();
            this.filterDatas = fin;
        })
    }
    // check for state id 8 if exists loop thru it and set clinictypeid else just assign the data from db
    assingDataFromDb(fin: any, stateId) {
        if (fin.length == 0) {
            Swal.fire('', 'All the clinics under this state/district is Approved,check Approved process', 'info');
            this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
                dtInstance.destroy();
                this.dtTrigger.next();
                this.filterDatas = [];
            });
            this.loading = false
        } else {
            if (parseInt(stateId) == 8) {

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
                        this.assignDatas(fin);
                        this.loading = false
                    }
                });
            } else {
                this.assignDatas(fin);
                this.loading = false
            }
        }
        // this.loading = false
    }



    radioSelect() {

        this.router.navigateByUrl('transaction/fin-01-invoice/fin-06-create/' + this.clinicCode + '/' + this.month + '/' + this.year);

    }

}