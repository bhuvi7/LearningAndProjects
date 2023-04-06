import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { DataTableDirective } from 'angular-datatables';
import { Fin11InvoiceService } from '../fin-11-invoice-service';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { element } from 'protractor';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
// import { toWords } from 'number-to-words';

@Component({
    selector: 'fin-11-inprogress-list',
    templateUrl: './fin-11-inprogress-list.component.html',
    providers: [NgbModalConfig, NgbModal]
})

export class Fin11InProgressListComponent implements OnInit {

    dtOptions: DataTables.Settings = {
        pagingType: 'full_numbers',
        pageLength: 10
    };
    dtTrigger: Subject<any> = new Subject();
    @ViewChild(DataTableDirective, { static: false })
    dtElement: DataTableDirective;

    public propsData = window.history.state.data ? window.history.state.data[0].clinicCode : 0
    public invoiceDatas: any;
    public buttonEnableBoolean: Boolean;
    public createData: any;
    public grossCharges;
    public penaltyCharges;
    public netChargesPayable;
    public modalBodyContent;
    public approveData;
    public filterDatas: any;
    public stateFilter: string = "";
    public districtFilter: string = "";
    public stateFilterDatas: Array<any>;
    public districtFilterDatas: Array<any>;
    public loading: Boolean = true;

    constructor(private router: Router, private fin11Service: Fin11InvoiceService,
        config: NgbModalConfig, private modalService: NgbModal) {
        config.backdrop = 'static';
        config.keyboard = false;
    }

    ngOnInit() {
        this.filterDatas = []
        this.inProgress();
        this.fin11Service.fetchStateDetails().subscribe(x => {
            this.stateFilterDatas = x;
        })
        this.buttonEnableBoolean = true
    }

    inProgress() {
        this.invoiceDatas = []
        this.filterDatas = []
        this.fin11Service.fetchDataForFin11InProgress(7, 'APPROVED BY MOH').subscribe(x => {
            this.invoiceDatas = x;
            this.invoiceDatas.forEach(element => {
                if (element.status == 'IN INTERNAL APPROVAL' || element.status == 'FOR APPROVAL TO MOH') {
                    element.approved = false
                } else {
                    element.approved = true
                }
                this.filterDatas.push(element)
            });
            this.dtTrigger.next();
            this.loading = false;
        })




        //testdata
        // this.filterDatas=
        // [{id:"1", month:"8", year:"2020", stateName: "JOHOR", districtName: "JHR", invoiceNo:"fin05aq", status: "approved"},
        // { id:"2",month:"8", year:"2020", stateName: "JOHOR", districtName: "JHR", invoiceNo:"fin05ar", status: "not approved"},
        // { id:"3",month:"8", year:"2020", stateName: "JOHOR", districtName: "JHR", invoiceNo:"fin05aqD", status: "moh approved"},]
        // 
        // this.loading = false;





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
        this.filterDatas = [];
        this.loading = true;
        this.dtOptions.destroy;
        this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
            dtInstance.destroy();
        });
        if (this.stateFilter !== "" && this.districtFilter == "") {
            this.invoiceDatas.forEach(element => {
                if (element.stateName == this.stateFilter) {
                    this.filterDatas.push(element)
                }
            });
        } else if (this.stateFilter !== "" && this.districtFilter !== "") {
            this.invoiceDatas.forEach(element => {
                if (element.stateName == this.stateFilter && element.districtName == this.districtFilter) {
                    this.filterDatas.push(element)
                }
            })
        }
        this.dtTrigger.next()
        this.loading = false;
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
        this.invoiceDatas.forEach(element => {
            this.filterDatas.push(element)
        })
        this.dtTrigger.next();
        this.loading = false
    }

    buttonEnable(id) {
        this.invoiceDatas.forEach(element => {
            if (element.id === id) {
                element.selected = true
                this.createData = element
            } else {
                element.selected = false
            }
        });
        this.buttonEnableBoolean = false
    }

    buttonEnableValue() {
        return this.buttonEnableBoolean
    }

    customRadio(id) {
        return "customRadio" + id
    }

    openModal(content, modalContent, data) {
        this.modalBodyContent = modalContent
        this.modalService.open(content);
        this.approveData = data;
    }

    closeModal() {
        if (this.modalBodyContent == 'Approve') { this.approve(this.approveData); }
    }

    approve(data) {
        if (data.status == 'IN INTERNAL APPROVAL') { data.status = 'FOR APPROVAL TO MOH' }
        else if (data.status == 'FOR APPROVAL TO MOH') { data.status = 'APPROVED BY MOH' }
        delete data.fin03;
        this.fin11Service.updateFin11Status(data).subscribe(x => {
            Swal.fire('', 'FIN 11 Invoice approved successfully!!!', 'success');
            this.invoiceDatas.forEach(element => {
                if (element.id == data.id) {
                    if (element.status == 'IN INTERNAL APPROVAL' || element.status == 'FOR APPROVAL TO MOH') {
                        element.approved = false
                    } else {
                        element.approved = true;
                    }
                }
            });
            this.loading = true;
            this.dtOptions.destroy;
            this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
                dtInstance.destroy();
            });
            this.inProgress();
        })
    }

    navigateToApproval(data) {
        this.router.navigateByUrl('transaction/fin-11-invoice/fin-11-approve/' + data);
    }
}