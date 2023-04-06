import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Fin02aInvoiceService } from '../../../fin-02a-invoice/fin-02a-invoice-service';

import * as dateFormat from 'dateformat';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
// import { toWords } from 'number-to-words';
import { PagesService } from '../../../../pages.service';
@Component({
    selector: 'fin-03a-create',
    templateUrl: './fin-03a-create.component.html',
    styleUrls: ['./fin-03a-create.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})

export class Fin03aCreateComponent implements OnInit {

    public enableSaveButton: Boolean = window.history.state.approvalPage ? true : false;

    public clinicTypeCode: any;
    public districtName;
    public monthYear;
    public noOfBEUnits;
    public districtId: number;
    public clinicTypeId: number;
    public district;
    public districtAddress: string;
    public rentalCharge;
    public docRef = "";
    public date = ''
    public clinicDatas: any;
    public equipmentTotalCount = 0;
    public equipmentTotalValue = 0;
    public equipmentTotalValueInWords: String;
    public modalBodyContent;
    public loading: Boolean = true;
    public disableCreate: Boolean = false;
    public enabler: Boolean;
    public backLog: number = 0;
    public checkingPendingClinics: number;

    public userId: Number;
    constructor(private router: Router, private route: ActivatedRoute, private fin02aService: Fin02aInvoiceService,
        config: NgbModalConfig, private modalService: NgbModal, private pagesService: PagesService) {
        config.backdrop = 'static';
        config.keyboard = false;
    }

    ngOnInit() {
        this.userId = this.pagesService.getUserId();
        if (this.route.params['_value'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.fin02aService.fetchDataForFin03aCreate(par['_districtId'], par['_clinicTypeId'], par['_month'], par['_year'])).subscribe(x => {
                this.clinicDatas = x;
                this.clinicDatas.forEach(element => {
                    element.dateDisplay = dateFormat(element.date, 'dd-mm-yyyy')
                    element.clinicName = element.clinic.clinicName
                    element.clinicCode = element.clinic.clinicCode
                });
                this.clinicDatas.map(data => {
                    this.equipmentTotalCount += data.totalEquipmentCount
                    this.equipmentTotalValue += data.totalRentalCharge
                    this.noOfBEUnits = data.totalEquipmentCount
                })
                if (x[0].stateName == 'SARAWAK') {
                    if (x[0].clinicTypeCode == 'PKD') {
                        this.clinicTypeCode = 'PKB';
                    } else if (x[0].clinicTypeCode == 'PPD') {
                        this.clinicTypeCode = 'PPB';
                    }
                } else {
                    this.clinicTypeCode = x[0].clinicTypeCode;
                }
                this.districtName = x[0].districtName;
                this.monthYear = x[0].month + '/' + x[0].year;
                this.districtId = x[0].districtId;
                this.clinicTypeId = x[0].clinicTypeId;
                this.date = dateFormat(new Date(), 'dd-mm-yyyy');
                this.fin02aService.fetchDistrictById(this.districtId).subscribe(x => {
                    this.district = x
                    if (this.clinicTypeId == 1) {
                        this.districtAddress = this.district.officePkdAddress;
                    } else if (this.clinicTypeId == 2) {
                        this.districtAddress = this.district.officePpdAddress;
                    }


                })
            })
        }
        //TO find backlog
        if (this.route.params['_value'] != "undefined") {
            this.route.params.switchMap((par: Params) =>
                this.fin02aService.fetchDataForFin03aCreateForDuplicate(par['_districtId'], par['_clinicTypeId'], par['_month'], par['_year'])).subscribe(x => {
                    this.backLog = (x.length)
                    console.log('this.backLog: ', this.backLog);
                })
        }

        //To find unfinished clinics
        if (this.route.params['_value'] != "undefined") {
            this.route.params.switchMap((par: Params) =>
                this.fin02aService.fetchDataForFin07CreateListPendingClinics(par['_districtId'], par['_clinicTypeId'], par['_month'], par['_year'])).subscribe(x => {

                    this.checkingPendingClinics = x.length
                    console.log('this.checkingPendingClinics: ', this.checkingPendingClinics);
                })
        }

        this.loading = false;
    }

    openModal(content, modalContent) {
        this.modalBodyContent = modalContent
        this.modalService.open(content);
    }

    closeModal() {
        if (this.modalBodyContent == 'Create') { this.createFin03a(); }
    }



    createFin03a() {
        if (this.checkingPendingClinics > 0) {

            Swal.fire({
                title: 'Are you sure?',
                text: 'Still there are some clinics pending to be processed!',
                type: 'warning',
                showConfirmButton: false,
                showCancelButton: true
            }).then((pay) => {
                if (pay.dismiss) {
                    // Swal.fire('', 'Transaction Cancelled', 'error');
                    history.back();
                }
                //this is accepted part...
                else {
                    this.disableCreate = true
                    this.enabler = true
                    this.clinicDatas.forEach(element => {
                        delete element.cimsHistoryFin02a;
                        delete element.clinic;
                    });
                    this.fin02aService.createFin03a(this.clinicDatas, this.userId, this.backLog).subscribe(x => {
                        this.disableCreate = false
                        this.enabler = false
                        Swal.fire('', 'Fin 03A created and submitted successfully!!!', 'success').then(x => {
                            history.back();
                        });
                    })
                }
            });
        }
        else if (this.checkingPendingClinics == 0) {
            this.disableCreate = true
            this.enabler = true
            this.clinicDatas.forEach(element => {
                delete element.cimsHistoryFin02a;
                delete element.clinic;
            });
            this.fin02aService.createFin03a(this.clinicDatas, this.userId, this.backLog).subscribe(x => {
                this.disableCreate = false
                this.enabler = false
                Swal.fire('', 'Fin 03A created and submitted successfully!!!', 'success').then(x => {
                    history.back();
                });
            })
        }

    }

    navToList() {
        history.back();
    }
}