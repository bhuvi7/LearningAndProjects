import { Component, OnInit, ViewChild } from "@angular/core";
import { DataTableDirective } from 'angular-datatables';

import { Router, ActivatedRoute, Params } from "@angular/router";
import { Subject } from 'rxjs';
import { Fin04InvoiceService } from '../fin-04-invoice-service';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';

@Component({
    selector: "fin-04-create-list",
    templateUrl: "./fin-04-create-list.component.html"
})
export class Fin04CreateListComponent implements OnInit {

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
    public createData: any;
    public invoiceType: string;
    public cardTitle: string;
    public districtId: any;
    public clinicTypeId: any;
    public filterDatas: any;
    public stateFilter: string = null
    public districtFilter: string = null;
    public stateFilterDatas: Array<any>;
    public districtFilterDatas: Array<any>;
    public loading: Boolean = false;
    public districtName: any;
    public stateName: any;
    public filterElements: any;
    public selectedSd;
    public month: any;
    public year: any;

    constructor(private router: Router, private route: ActivatedRoute, private fin01Service: Fin04InvoiceService) { }
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

    // buttonEnable(id) {
    //     this.selectedSd = id
    //     this.buttonEnableBoolean = false
    // }
    // buttonEnable(clinicTypeId, districtId) {
    //     this.districtId = districtId
    //     this.clinicTypeId = clinicTypeId

    //     this.buttonEnableBoolean = false
    // }

    buttonEnable(districtId, clinicTypeId, month, year) {
        this.districtId = districtId;
        this.clinicTypeId = clinicTypeId;
        this.month = month
        this.year = year
        this.buttonEnableBoolean = false
    }

    // common filter fields ----
    selectedIds(selectedIds) {
        this.loading = true
        if (this.route.params['_value']['_indicator'] != "undefined") {
            if (this.route.params['_value']['_indicator'] == "new") {
                this.loading = true
                this.fin01Service.fetchDataForFin04CreateList().subscribe(fin => {

                    this.assingDataFromDb(fin, selectedIds.stateId);
                    this.loading = false

                })
            } else {
                this.fin01Service.fetchDataForFin04CreateList().subscribe(fin => {
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

    // customRadio(id) {
    //     return "customRadio" + id
    // }

    // buttonEnable(districtId, clinicTypeId) {
    //     this.districtId = districtId;
    //     this.clinicTypeId = clinicTypeId;
    //     this.buttonEnableBoolean = false
    // }

    // handleForm(event) {
    //     switch (event.target.id) {
    //         case "state":
    //             this.stateFilter = event.target.value;

    //             // this.districtFilter = "0";
    //             // this.districtFilterDatas = this.stateFilterDatas.find(state => state.id == event.target.value).districts
    //             // this.buttonEnableBoolean = true
    //             this.districtFilter = "";
    //             this.districtFilterDatas = this.stateFilterDatas.find(state => state.stateName == event.target.value).districts
    //             // this.filterFunction();
    //             break;
    //         case "district":

    //             this.districtFilter = event.target.value;

    //             // this.buttonEnableBoolean = true

    //             // this.filterFunction();
    //             break;
    //         default:
    //             break;
    //     }
    // }

    // filterFunction() {
    //     this.filterDatas = []
    //     this.loading = true;

    //        if(this.stateFilter =="ALL" || this.stateFilter==null || this.stateFilter=="" ){
    //         this.listDatas.forEach(element=>{
    //             this.filterDatas.push(element)


    //         })

    //   }
    //     else if (this.stateFilter !== "" && this.districtFilter == "") {
    //         this.listDatas.forEach(element => {
    //             if (element.stateName == this.stateFilter) {
    //                 this.filterDatas.push(element)


    //             }
    //         });
    //     } else if (this.stateFilter !== "" && this.districtFilter !== "") {
    //         this.listDatas.forEach(element => {
    //             if (element.stateName == this.stateFilter && element.districtName == this.districtFilter) {
    //                 this.filterDatas.push(element)


    //             }
    //         })
    //     }

    //     this.dtOptions.destroy;
    //     this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
    //         dtInstance.destroy();
    //     });
    //     this.dtTrigger.next()
    //     this.loading = false;
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

    radioSelect() {
        this.router.navigateByUrl('transaction/fin-04-invoice/fin-04-create/' + this.districtId + '/' + this.clinicTypeId + '/' + this.month + '/' + this.year);
        //this.router.navigateByUrl('transaction/fin-04-invoice/fin-04-create/' + this.selectedSd);
    }

}