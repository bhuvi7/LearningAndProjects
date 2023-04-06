import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Subject } from 'rxjs';
import { Fin04InvoiceService } from '../../fin-04-invoice-service';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { DataTableDirective } from 'angular-datatables';
import { PagesService } from '../../../../pages.service';
// import { toWords } from 'number-to-words';

@Component({
    selector: 'fin-10b-list',
    templateUrl: './fin-10b-list.component.html',
    providers: [NgbModalConfig, NgbModal]
})

export class Fin10bListComponent implements OnInit {

    dtOptions: DataTables.Settings = {
        pagingType: 'full_numbers',
        pageLength: 10
    };
    dtTrigger: Subject<any> = new Subject();
    @ViewChild(DataTableDirective, { static: false })
    dtElement: DataTableDirective;

    public propsData = window.history.state.data ? window.history.state.data[0].clinicCode : 0
    public pageType = this.route.params['_value']['_type'];
    public listDatas: any;
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
    public loading: Boolean = false;
    public invoiceDatas: any;
    public invoiceFilterDatas: any;
    public userId: Number;



    constructor(private router: Router, private route: ActivatedRoute, private fin04Service: Fin04InvoiceService,
        config: NgbModalConfig, private modalService: NgbModal) {
        config.backdrop = 'static';
        config.keyboard = false;
    }

    ngOnInit() {
        // this.buttonEnableBoolean = true
        // if (this.route.params['_value']['_type'] != "undefined") {
        //     if (this.pageType == 'inprogress') {
        //         this.forInprogress();
        //     } else {
        //         this.forApproved();
        //     }
        //     this.fin04Service.fetchStateDetails().subscribe(x => {
        //         this.stateFilterDatas = x;
        //         this.dtTrigger.next();
        //         this.loading = false;
        //     })
        // }
    }
    ngAfterViewInit(): void {
        this.dtTrigger.next();
    }

    ngOnDestroy(): void {
        this.dtTrigger.unsubscribe();
    }


    selectedIds(selectedIds) {
        // if (this.route.params['_value']['_indicator'] != "undefined") {
        // if (this.route.params['_value']['_indicator'] == "new") {
        this.loading = true
        this.fin04Service.fetchDataForFin10bInprogress('APPROVED BY MOH', selectedIds).subscribe(fin => {

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
        // } 
        // }
    }


    // forInprogress() {
    //     this.listDatas = []
    //     this.filterDatas = []
    //     this.fin04Service.fetchDataForFin10b().subscribe(x => {
    //         x.forEach(element => {
    //             if (element.status != 'APPROVED BY MOH') {
    //                 this.listDatas.push(element)
    //             }
    //         });
    //         this.listDatas.forEach(element => {
    //             if (element.status == 'IN INTERNAL APPROVAL' || element.status == 'FOR APPROVAL TO MOH') {
    //                 element.approved = false
    //             } else {
    //                 element.approved = true
    //             }
    //             this.filterDatas.push(element)
    //         });
    //     })
    // }

    // forApproved() {
    //     this.listDatas = []
    //     this.filterDatas = []
    //     this.fin04Service.fetchDataForFin10b().subscribe(x => {
    //         x.forEach(element => {
    //             if (element.status == 'APPROVED BY MOH') {
    //                 element.approved = true
    //                 this.listDatas.push(element)
    //                 this.filterDatas.push(element)
    //             }
    //         });
    //     })
    // }

    // handleForm(event) {
    //     switch (event.target.id) {
    //         case "state":
    //             this.stateFilter = event.target.value;
    //             this.districtFilter = "";
    //             this.districtFilterDatas = this.stateFilterDatas.find(state => state.stateName == event.target.value).districts
    //             this.filterFunction();
    //             break;
    //         case "district":
    //             this.districtFilter = event.target.value;
    //             this.filterFunction();
    //             break;
    //         default:
    //             break;
    //     }
    // }

    // filterFunction() {
    //     this.filterDatas = []
    //     if (this.stateFilter !== "" && this.districtFilter == "") {
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
    // }

    // resetFilter() {
    //     this.stateFilter = ""
    //     this.districtFilter = ""
    //     this.districtFilterDatas = []
    //     this.filterDatas = []
    //     this.listDatas.forEach(element => {
    //         this.filterDatas.push(element)
    //     })
    // }

    // buttonEnable(id) {
    //     this.listDatas.forEach(element => {
    //         if (element.id === id) {
    //             element.selected = true
    //             this.createData = element
    //         } else {
    //             element.selected = false
    //         }
    //     });
    //     this.buttonEnableBoolean = false
    // }

    // buttonEnableValue() {
    //     return this.buttonEnableBoolean
    // }

    // customRadio(id) {
    //     return "customRadio" + id
    // }

    // openModal(content, modalContent, data) {
    //     this.modalBodyContent = modalContent
    //     this.modalService.open(content);
    //     this.approveData = data;
    // }

    // closeModal() {
    //     if (this.modalBodyContent == 'Approve') { this.approve(this.approveData); }
    // }

    // approve(data) {
    //     delete data.fin10bConstructionWorks
    //     if (data.status == 'IN INTERNAL APPROVAL') { data.status = 'FOR APPROVAL TO MOH' }
    //     else if (data.status == 'FOR APPROVAL TO MOH') { data.status = 'APPROVED BY MOH' }
    //     this.fin04Service.updateFin10b(data).subscribe(x => {
    //         Swal.fire('', 'Fin 10b approved successfully!!!', 'success');
    //         this.listDatas.forEach(element => {
    //             if (element.id == data.id) {
    //                 if (element.status == 'IN INTERNAL APPROVAL' || element.status == 'FOR APPROVAL TO MOH') {
    //                     element.approved = false
    //                 } else {
    //                     element.approved = true;
    //                 }
    //             }
    //         });
    //         this.forInprogress();
    //     })

    // }

    navigateToApproval(id) {
        this.router.navigateByUrl('transaction/fin-04-invoice/fin-10b-approve/' + id);
    }
}