import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { Fin09InvoiceService } from '../fin-09-invoice-service';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
// import { toWords } from 'number-to-words';

@Component({
    selector: 'fin-09-approved-list',
    templateUrl: './fin-09-approved-list.component.html',
    providers: [NgbModalConfig, NgbModal]
})

export class Fin09ApprovedListComponent implements OnInit {

    dtOptions: DataTables.Settings = {
        pagingType: 'full_numbers',
        pageLength: 10
    };
    dtTrigger: Subject<any> = new Subject();

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

    constructor(private router: Router, private fin09Service: Fin09InvoiceService,
        config: NgbModalConfig, private modalService: NgbModal) {
        config.backdrop = 'static';
        config.keyboard = false;
    }

    ngOnInit() {
        this.filterDatas = []
        this.fin09Service.fetchDataForFin09Approved('APPROVED BY MOH').subscribe(x => {
            this.invoiceDatas = x;
            this.invoiceDatas.forEach(element => {
                element.approved = true
                this.filterDatas.push(element)
            });
            this.loading = false;
            this.dtTrigger.next();
        })
        this.fin09Service.fetchStateDetails().subscribe(x => {
            this.stateFilterDatas = x;
        })
        this.buttonEnableBoolean = true
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
        this.filterDatas = []
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
    }

    resetFilter() {
        this.stateFilter = ""
        this.districtFilter = ""
        this.districtFilterDatas = []
        this.filterDatas = []
        this.invoiceDatas.forEach(element => {
            this.filterDatas.push(element)
        })
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
        delete data.fin10b;
        this.fin09Service.updateFin09Status(data).subscribe(x => {
            Swal.fire('', 'Fin 09 approved successfully!!!', 'success');
            this.invoiceDatas.forEach(element => {
                if (element.id == data.id) {
                    if (element.status == 'IN INTERNAL APPROVAL' || element.status == 'FOR APPROVAL TO MOH') {
                        element.approved = false
                    } else {
                        element.approved = true;
                    }
                }
            });
        })
    }

    navigateToApproval(data) {
        this.router.navigateByUrl('transaction/fin-09-invoice/fin-09-approve/' + data);
    }
}