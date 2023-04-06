import { Component, OnInit, ViewChild } from '@angular/core';
import { DataTableDirective } from 'angular-datatables';
import { Router, ActivatedRoute, Params } from "@angular/router";
import { Subject } from 'rxjs';
import { Fin02bInvoiceService } from '../fin-02b-invoice-service';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';

@Component({
    selector: "fin-02b-create-list",
    templateUrl: "./fin-02b-create-list.component.html"
})
export class Fin02bCreateListComponent implements OnInit {

    dtOptions: DataTables.Settings = {
        pagingType: 'full_numbers',
        pageLength: 10
    };
    dtTrigger: Subject<any> = new Subject();
    @ViewChild(DataTableDirective, { static: false })
    dtElement: DataTableDirective;


    public buttonEnableBoolean: Boolean;
    public districtId: any;
    public clinicTypeId: any;
    public month: any;
    public year: any;
    public filterDatas: any=[];
    public loading: Boolean = false;

    constructor(private router: Router, private route: ActivatedRoute, private fin02bService: Fin02bInvoiceService) { }

    ngOnInit() {
        this.buttonEnableBoolean = true
        
    }


    // common filter fields ----
    selectedIds(selectedIds) {
        if (this.route.params['_value']['_indicator'] != "undefined") {
            if (this.route.params['_value']['_indicator'] == "new") {
                this.loading = true;
                this.fin02bService.fetchDataForFin02bCreateList(selectedIds).subscribe(fin => {
                    this.assingDataFromDb(fin,selectedIds.stateId);
                    this.loading = false

                })
            } else {
                this.fin02bService.fetchDataForFin02bCreateListOlder(selectedIds).subscribe(fin => {
                    this.assingDataFromDb(fin,selectedIds.stateId);
                    this.loading = false
                })
            }
        }
    }

    // check for state id 8 if exists loop thru it and set clinictypeid else just assign the data from db
    assingDataFromDb(fin:any,stateId){
        if (fin.length == 0) {
            Swal.fire('', 'All the clinics under this state/district is Approved,check Approved process', 'info');
            this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
                dtInstance.destroy();
                this.dtTrigger.next();
                this.filterDatas = [];
            });
            this.loading = false
        } else {
            if(parseInt(stateId)==8){
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
                    }
                });
            }else{
                this.assignDatas(fin); 
            }
        }
        // this.loading = false
    }
    assignDatas(fin:any){
        this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
            dtInstance.destroy();
            this.dtTrigger.next();
            this.filterDatas = fin;
        })
    }

    // --------

    ngAfterViewInit(): void {
        this.dtTrigger.next();
    }

    ngOnDestroy(): void {
        this.dtTrigger.unsubscribe();
    }

    customRadio(id) {
        return "customRadio" + id
    }

    buttonEnable(districtId, clinicTypeId, month, year) {
        this.districtId = districtId;
        this.clinicTypeId = clinicTypeId;
        this.month = month
        this.year = year
        this.buttonEnableBoolean = false
    }

    radioSelect() {
        this.router.navigateByUrl('transaction/fin-02b-invoice/fin-02b-create/' + this.districtId + '/' + this.clinicTypeId + '/' + this.month + '/' + this.year);
    }

}