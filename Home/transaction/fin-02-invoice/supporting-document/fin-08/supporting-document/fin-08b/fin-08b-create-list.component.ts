import { Component, OnInit, ViewChild } from '@angular/core';
import { DataTableDirective } from 'angular-datatables';
import { Router, ActivatedRoute, Params } from "@angular/router";
import { Subject } from 'rxjs';
import { Fin08InvoiceService } from '../../fin-08-invoice-service';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';

@Component({
    selector: "fin-08b-create-list",
    templateUrl: "./fin-08b-create-list.component.html"
})
export class Fin08bCreateListComponent implements OnInit {

    dtOptions: DataTables.Settings = {
        pagingType: 'full_numbers',
        pageLength: 10
    };
    dtTrigger: Subject<any> = new Subject();
    @ViewChild(DataTableDirective, { static: false })
    dtElement: DataTableDirective;

    // common filter fields ----
    selectedIds(selectedIds) {   
     
        if (this.route.params['_value']['_indicator'] != "undefined") {
            if (this.route.params['_value']['_indicator'] == "new") {
                this.loading = true;
                this.fin08Service.fetchDataForFin08bCreateList( selectedIds).subscribe(fin => {

                    this.assingDataFromDb(fin,selectedIds.stateId);
                    this.loading = false;

                })
            } else {
                this.fin08Service.fetchDataForFin08bCreateListOlder(selectedIds).subscribe(fin => {
                    this.assingDataFromDb(fin,selectedIds.stateId);
                    this.loading = false;
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
            this.loading = false;
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
                        this.loading = false;
                    }
                });
            }else{
                this.assignDatas(fin); 
                this.loading = false;
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
  
    public buttonEnableBoolean: Boolean;
    public clinicCode: any;
    public month: any;
    public year: any;
    public filterDatas: any;
    public loading: Boolean = false;

    constructor(private router: Router, private route: ActivatedRoute, private fin08Service: Fin08InvoiceService) { }

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

    buttonEnable(clinicCode, month , year) {
        this.clinicCode = clinicCode
        this.month = month
        this.year = year
        this.buttonEnableBoolean = false
    }

    radioSelect() {

        this.router.navigateByUrl('transaction/fin-02-invoice/fin-08/fin-08b-create/' + this.clinicCode + '/' + this.month + '/' + this.year);
    }

}