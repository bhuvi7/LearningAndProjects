import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Fin01InvoiceService } from '../../fin-01-invoice-service';
import * as dateFormat from 'dateformat';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
// import { toWords } from 'number-to-words';
import { PagesService } from '../../../../pages.service';
@Component({
    selector: 'fin-01-create',
    templateUrl: './fin-01-create.component.html',
    styleUrls: ['./fin-01-create.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})

export class Fin01CreateComponent implements OnInit {

    public enableSaveButton: Boolean = window.history.state.approvalPage ? true : false;

    public clinicTypeCode: any;
    public districtName;
    public monthYear;
    public docRef = "";
    public date = ''
    public clinicDatas: any;
    public equipmentTotalCount = 0;
    public equipmentTotalValue = 0;
    public equipmentTotalValueInWords: String;
    public modalBodyContent;
    public loading: Boolean = true;
    public disableCreate: Boolean = false;
    public userId: Number;

    constructor(private router: Router, private route: ActivatedRoute, private fin01Service: Fin01InvoiceService,
        config: NgbModalConfig, private modalService: NgbModal, private pagesService: PagesService) {
        config.backdrop = 'static';
        config.keyboard = false;
    }

    ngOnInit() {
        this.userId = this.pagesService.getUserId();
        if (this.route.params['_value'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.fin01Service.fetchDataForfin01Create(par['_districtId'], par['_clinicTypeId'], par['_month'] , par['_year'])).subscribe(x => {
                this.clinicDatas = x;
                
                this.clinicDatas.forEach(element => {
                    element.dateDisplay = dateFormat(element.date, 'dd-mm-yyyy')
                });
                this.clinicDatas.map(data => {
                    this.equipmentTotalCount += data.totalEquipmentCount
                    this.equipmentTotalValue += data.totalAmount
                })
                this.clinicTypeCode = x[0].clinicTypeCode;
                this.districtName = x[0].districtName;
                this.monthYear = x[0].month + '/' + x[0].year;
                this.date = dateFormat(new Date(), 'dd-mm-yyyy');
                this.loading = false;
            })
        }

    }

    openModal(content, modalContent) {
        this.modalBodyContent = modalContent
        this.modalService.open(content);
    }

    closeModal() {
        if (this.modalBodyContent == 'Create') { this.createFin01(); }
    }



    createFin01() {
        this.disableCreate = true
        this.clinicDatas.forEach(element => {
            delete element.cimsHistoryFin01;
        });
        this.fin01Service.createFin01(this.clinicDatas, this.userId).subscribe(x => {
            this.disableCreate = false
            Swal.fire('', 'Fin 01 created and submitted successfully!!!', 'success').then(x => {
            history.back();
        });
        })
    }
    navToList() {
        history.back();
    }
}