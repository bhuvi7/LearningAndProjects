import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { Fin08InvoiceService } from '../../fin-08-invoice-service';
import * as dateFormat from 'dateformat';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { Fin08b } from "../../../../model/fin08b";
import { AnyTxtRecord } from 'dns';
// import { toWords } from 'number-to-words';
import { PagesService } from '../../../../../../pages.service';
@Component({
    selector: 'fin-08c-create',
    templateUrl: './fin-08c-create.component.html',
    styleUrls: ['./fin-08c-create.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})

export class Fin08cCreateComponent implements OnInit {

    public stateName;
    public districtName;
    public clinicName;
    public clinicCode;
    public clinicAddress = "";
    public batchNo = 0;
    public installmentNo = 0 / 0;
    public equipmentValue = 0;
    public monthlyMaintenanceCharge = 0;
    public rentalCharges = 0;
    public certOfTC = "";
    public monthYear;
    public docRef = "";
    public date;
    public equipmentDatas: any;
    public equipment = { id: "", assetName: "", remarks: "" };
    public equipmentCount = 0;
    public equipmentTotalMaintenanceValue = 0.00;
    public equipmentTotalValueInWords: String;
    public Equipment: any;
    public modalBodyContent;
    public loading: Boolean = true;
    public disableCreate: Boolean = false;
    public fin08b: Fin08b;
    public userId: Number;
    public enabler: boolean
    constructor(private router: Router, private route: ActivatedRoute, private fin08Service: Fin08InvoiceService,
        config: NgbModalConfig, private modalService: NgbModal, private pagesService: PagesService) {
        config.backdrop = 'static';
        config.keyboard = false;
    }

    ngOnInit() {
        this.userId = this.pagesService.getUserId();
        this.disableCreate = false
        if (this.route.params['_value']['_clinicCode'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.fin08Service.fetchDataForFin08cCreate(par['_clinicCode'], par['_month'], par['_year'])).subscribe(x => {
                this.equipmentDatas = x;

                this.equipmentDatas.sort((a, b) => (a.beNumber > b.beNumber) ? 1 : -1)
                this.equipmentCount = x.length
                x.forEach(equip => {
                    this.equipmentTotalMaintenanceValue += equip.maintenanceCharges
                })
                this.stateName = x[0].stateName;
                this.districtName = x[0].districtName;
                this.clinicName = x[0].clinic.clinicName;
                this.clinicCode = x[0].clinic.clinicCode;
                this.clinicAddress = x[0].clinic.address;
                this.monthYear = x[0].month + '/' + x[0].year;
                this.date = dateFormat(new Date(), 'dd-mm-yyyy');
                this.loading = false;
            });
        }
    }

    openModal(content, modalContent) {
        this.modalBodyContent = modalContent
        this.modalService.open(content);
    }

    closeModal() {
        if (this.modalBodyContent == 'Create') { this.createFin08c(); }
    }



    createFin08c() {
        this.disableCreate = true
        this.enabler = true
        this.equipmentDatas.forEach(element => {
            delete element.clinic
        });
        this.fin08Service.createFin08c(this.equipmentDatas, this.userId).subscribe(x => {
            this.disableCreate = false
            this.enabler = false
            Swal.fire('', 'Fin 08c created and submitted successfully!!!', 'success').then(x => {
                history.back()
            });
        })
    }

    navToList() {
        history.back()
    }

}