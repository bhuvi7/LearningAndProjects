import { Component, OnInit, ViewChild } from '@angular/core';
import { DataTableDirective } from 'angular-datatables';
import { Router, ActivatedRoute } from '@angular/router';
import { Subject } from 'rxjs';
import { Fin02aInvoiceService } from '../../../fin-02a-invoice/fin-02a-invoice-service';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
// import { toWords } from 'number-to-words';
import { PagesService } from '../../../../pages.service';
@Component({
    selector: 'fin-07-inprogress',
    templateUrl: './fin-07-inprogress.component.html',
    providers: [NgbModalConfig, NgbModal]
})

export class Fin07InProgressListComponent implements OnInit {

    dtOptions: DataTables.Settings = {
        pagingType: 'full_numbers',
        pageLength: 10
    };
    dtTrigger: Subject<any> = new Subject();
    @ViewChild(DataTableDirective, { static: false })
    dtElement: DataTableDirective;

    public listDatas: any;
    public buttonEnableBoolean: Boolean;
    public selectedValue: Boolean;
    public createData: any;
    public modalBodyContent;
    public approveData;
    public noOfBEUnits = 0;
    public filterDatas: any;
    public stateFilter: string = "0";
    public districtFilter: string = "0";
    public stateFilterDatas: Array<any>;
    public districtFilterDatas: Array<any>;
    public loading: Boolean = false;
    public userId: Number;

    constructor(private router: Router, private route: ActivatedRoute, private fin02aService: Fin02aInvoiceService,
        config: NgbModalConfig, private modalService: NgbModal, private pagesService: PagesService) {
        config.backdrop = 'static';
        config.keyboard = false;
    }

    ngOnInit() {

    }

    ngAfterViewInit(): void {
        this.dtTrigger.next();
    }

    ngOnDestroy(): void {
        this.dtTrigger.unsubscribe();
    }


    selectedIds(selectedIds) {
        if (this.route.params['_value']['_indicator'] != "undefined") {
            if (this.route.params['_value']['_indicator'] == "new") {
                this.loading=true;
                this.fin02aService.fetchDataForFin07InProgress('APPROVED BY MOH', selectedIds).subscribe(fin => {

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
                this.fin02aService.fetchDataForFin07InProgressOlder('APPROVED BY MOH', selectedIds).subscribe(fin => {
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
                    // this.loading = false
                })
            }
        }
    }



    navigateToApproval(id) {
        this.router.navigateByUrl('transaction/fin-02a-invoice/fin-07-approve/' + id);
    }
}