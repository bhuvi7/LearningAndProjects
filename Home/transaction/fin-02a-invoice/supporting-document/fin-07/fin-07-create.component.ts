import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { Fin02aInvoiceService } from '../../../fin-02a-invoice/fin-02a-invoice-service';
import * as dateFormat from 'dateformat';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { Fin07 } from "../../model/fin07";
import { AnyTxtRecord } from 'dns';
// import { toWords } from 'number-to-words';
import { PagesService } from '../../../../pages.service';
import { LocationStrategy } from '@angular/common';
@Component({
    selector: 'fin-07-create',
    templateUrl: './fin-07-create.component.html',
    styleUrls: ['./fin-07-create.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})

export class Fin07CreateComponent implements OnInit {

    public stateName;
    public districtName;
    public clinicName;
    public clinicCode;
    public clinicAddress = "";
    public batchNo = 0;
    public installmentNo = 0 / 0;
    public equipmentValue = 0;
    public rentalCharges = 0;
    public certOfTC = "";
    public monthYear;
    public docRef = "";
    public date = ''
    public equipmentDatas: any;
    public equipment = { id: "", assetName: "", remarks: "" };
    public equipmentCount = 0;
    public equipmentTotalRentalValue = 0.00;
    public equipmentTotalValueInWords: String;
    public disableCreate: Boolean;
    public Equipment: any;
    public modalBodyContent;
    public loading: Boolean = true;
    public fin07: Fin07;
    public userId: Number;
    public spinnerTemplate: any;
    public saveInProgress: boolean
    public enabler: boolean
    constructor(private router: Router, private route: ActivatedRoute, private fin02aService: Fin02aInvoiceService,
        config: NgbModalConfig, private modalService: NgbModal, private pagesService: PagesService, private locationStrategy: LocationStrategy) {
        config.backdrop = 'static';
        config.keyboard = false;
    }

    ngOnInit() {
        this.userId = this.pagesService.getUserId();
        this.disableCreate = false
        this.date = dateFormat(new Date(), 'dd-mm-yyyy');
        if (this.route.params['_value']['_clinicCode'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.fin02aService.fetchDataForFin07Create(par['_clinicCode'], par['_month'], par['_year'])).subscribe(x => {
                this.equipmentDatas = x;
                this.equipmentDatas.sort((a, b) => (a.beNumber > b.beNumber) ? 1 : -1)

                this.equipmentCount = x.length
                x.forEach(equipment => {
                    this.equipmentTotalRentalValue += equipment.rentalCharges
                })
                this.stateName = x[0].stateName;
                this.districtName = x[0].districtName;
                this.clinicName = x[0].clinic.clinicName;
                this.clinicCode = x[0].clinic.clinicCode;
                this.clinicAddress = x[0].clinic.address;
                this.monthYear = x[0].month + '/' + x[0].year;
                this.loading = false;
            });
        }
    }

    openModal(content, modalContent) {
        this.modalBodyContent = modalContent
        this.modalService.open(content);
    }

    closeModal() {
        if (this.modalBodyContent == 'Create') { this.createFin07(); }
    }

    // preventBackButton() {
    //     history.pushState(null, null, location.href);
    //     this.locationStrategy.onPopState(() => {
    //         history.pushState(null, null, location.href);
    //     })
    // }


    createFin07() {

        this.disableCreate = true
        this.enabler = true


        this.fin02aService.createFin07(this.equipmentDatas, this.userId).subscribe(x => {
            this.disableCreate = false
            this.enabler = false

            Swal.fire('', 'Fin 07 created and submitted successfully!!!', 'success').then(x => {
                history.back()
            });
        })
    }

    navToList() {
        history.back()
    }

}