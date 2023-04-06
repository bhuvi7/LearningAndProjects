import { Component, OnInit, ViewChild } from '@angular/core';
import { DataTableDirective } from 'angular-datatables';
import { Router, ActivatedRoute } from "@angular/router";
import { Subject } from 'rxjs';
import { Fin02InvoiceService } from '../../fin-02-service';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';

@Component({
    selector: "fin-03-create-list",
    templateUrl: "./fin-03-create-list.component.html"
})
export class Fin03CreateListComponent implements OnInit {

    dtOptions: DataTables.Settings = {
        pagingType: 'full_numbers', 
        pageLength: 10
    };
    dtTrigger: Subject<any> = new Subject();
    @ViewChild(DataTableDirective, { static: false })
    dtElement: DataTableDirective;

    public buttonEnableBoolean: Boolean;
    public districtId;
    public clinicTypeId;
    public month;
    public year;
    public filterDatas: any;
    public loading: Boolean = false;
    constructor(private router: Router, private route: ActivatedRoute, private fin02Service: Fin02InvoiceService) { }

    ngOnInit() {
        this.buttonEnableBoolean = true
    }

    // common filter fields ----
    selectedIds(selectedIds) {
        if (this.route.params['_value']['_indicator'] != "undefined") {
            if (this.route.params['_value']['_indicator'] == "new") {
                this.loading = true;
                this.fin02Service.fetchDataForFin03CreateList(selectedIds).subscribe(fin => {

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
            } else {
                this.fin02Service.fetchDataForFin03CreateListOlder(selectedIds).subscribe(fin => {
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

                            // if (element.status == 'IN INTERNAL APPROVAL' || element.status == 'FOR APPROVAL TO MOH') {
                            //     element.approved = false
                            // } else {
                            //     element.approved = true
                            // }
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
            }
        }
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
        this.router.navigateByUrl('transaction/fin-02-invoice/fin-03-create/' + this.districtId + '/' + this.clinicTypeId + '/' + this.month + '/' + this.year);
    }

}