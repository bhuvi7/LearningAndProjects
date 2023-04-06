import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { Fin02InvoiceService } from '../../fin-02-service';
import * as dateFormat from 'dateformat';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { Fin03Clinic } from '../../model/fin03-clinic';
// import { toWords } from 'number-to-words';
import { PagesService } from '../../../../pages.service';
@Component({
    selector: 'fin-03-create',
    templateUrl: './fin-03-create.component.html',
    styleUrls: ['./fin-03-create.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})

export class Fin03CreateComponent implements OnInit {

    public clinicTypeCode: any;
    public districtName;

    public monthYear;
    public docRef = "";
    public date = ''
    public clinicDatas: Array<Fin03Clinic>;
    public fin03Clinic: Fin03Clinic;
    public modalBodyContent;
    public loading: Boolean = true;
    public disableCreate: Boolean = false;
    public fin08Datas: Array<any>;
    public fin09Datas: Array<any>;
    public userId: Number;
    public newBeTotal: Number = 0;
    public purchasedBeTotal: Number = 0;
    public totalMaintenanceCharge: Number = 0;
    public netMaintenanceCharge = 0;
    public netChargesPayable: Number = 0;
    public districtId: any;
    public district;
    public clinicTypeId: number;
    public districtAddress: any;
    public enabler: boolean
    public backLog: number = 0;
    public checkingPendingClinics: number;

    constructor(private router: Router, private route: ActivatedRoute, private fin02Service: Fin02InvoiceService,
        config: NgbModalConfig, private modalService: NgbModal, private pagesService: PagesService) { }

    ngOnInit() {
        this.userId = this.pagesService.getUserId();
        this.fin08Datas = [];
        this.fin09Datas = [];
        this.fin03Clinic = new Fin03Clinic();
        this.clinicDatas = []
        if (this.route.params['_value'] != "undefined") {
            this.fin08ForFin03();
        }
        this.date = dateFormat(new Date(), 'dd-mm-yyyy');
    }
    //TO find backlog
    //  if (this.route.params['_value'] != "undefined") {
    //     this.route.params.switchMap((par: Params) =>
    //         this.fin02aService.fetchDataForFin03aCreateForDuplicate(par['_districtId'], par['_clinicTypeId'], par['_month'], par['_year'])).subscribe(x => {
    //             this.backLog = (x.length)
    //             console.log('this.backLog: ', this.backLog);
    //         })
    // }
    fin08ForFin03() {
        this.route.params.switchMap((par: Params) => this.fin02Service.fetchDataFromFin08ForFin03Create(par['_districtId'], par['_clinicTypeId'], par['_month'], par['_year'])).subscribe(x => {
            //
            this.fin08Datas = x;
            this.clinicTypeId = x[0].clinicTypeId;
            this.districtId = x[0].districtId;
            this.clinicTypeCode = x[0].clinicTypeCode;
            this.districtName = x[0].districtName;
            this.monthYear = x[0].month + '/' + x[0].year;
            this.fin09ForFin03();
        })
        //TO find backlog
        this.route.params.switchMap((par: Params) => this.fin02Service.fetchDataFromFin08ForFin03CreateForDuplication(par['_districtId'], par['_clinicTypeId'], par['_month'], par['_year'])).subscribe(x => {
            this.backLog = (x.length)
            console.log('this.backLog: ', this.backLog);

        })

        //To popup notification
        this.route.params.switchMap((par: Params) => this.fin02Service.fetchDataForFin03CreateListPendingListNotification(par['_districtId'])).subscribe(x => {
            this.checkingPendingClinics = x.length
            console.log('this.checkingPendingClinics: ', this.checkingPendingClinics);
        })
    }

    fin09ForFin03() {
        this.route.params.switchMap((par: Params) => this.fin02Service.fetchDataFromFin09ForFin03Create(par['_districtId'], 32)).subscribe(x => {
            //
            if (x == null) {
                this.fin09Datas = []
            } else {
                this.fin09Datas = x.fin09Clinic;
            }

            //
            this.fin08Datas.forEach(clinic => {
                //
                let fin09ForFin08 = this.fin09Datas.find(fin09Clinic => fin09Clinic.clinicId == clinic.clinicId)
                //
                if (clinic.fin08b[0]) {
                    this.fin03Clinic.fin08bEquipmentCount = clinic.fin08b[0].totalEquipmentCount
                    this.fin03Clinic.fin08bMaintenanceCharge = clinic.fin08b[0].totalMaintenanceCharges
                    this.newBeTotal += clinic.fin08b[0].totalMaintenanceCharges
                }
                if (clinic.fin08c[0]) {
                    this.fin03Clinic.fin08cEquipmentCount = clinic.fin08c[0].totalEquipmentCount
                    this.fin03Clinic.fin08cMaintenanceCharge = clinic.fin08c[0].totalMaintenanceCharges
                    this.purchasedBeTotal += clinic.fin08c[0].totalMaintenanceCharges
                }
                this.fin03Clinic.fin08MaintenanceCharge = clinic.totalMaintenanceCharges
                this.totalMaintenanceCharge += clinic.totalMaintenanceCharges
                this.fin03Clinic.clinicName = clinic.clinic.clinicName
                this.fin03Clinic.clinicCode = clinic.clinicCode
                if (fin09ForFin08) {
                    this.fin03Clinic.fin09PenaltyCharge = fin09ForFin08.totalPenalty
                } else {
                    this.fin03Clinic.fin09PenaltyCharge = 0
                }
                this.fin03Clinic.netMaintenanceCharge = this.fin03Clinic.fin08MaintenanceCharge - this.fin03Clinic.fin09PenaltyCharge
                this.netMaintenanceCharge += this.fin03Clinic.netMaintenanceCharge
                this.clinicDatas.push(this.fin03Clinic)
                this.fin03Clinic = new Fin03Clinic();
            })
            this.fin02Service.fetchDistrictById(this.districtId).subscribe(x => {

                this.district = x
                if (this.clinicTypeId == 1) {
                    this.districtAddress = this.district.officePkdAddress;
                } else if (this.clinicTypeId == 2) {
                    this.districtAddress = this.district.officePpdAddress;
                }


            })
            this.loading = false;
            //
        })
    }

    openModal(content, modalContent) {
        this.modalBodyContent = modalContent
        this.modalService.open(content);
    }

    closeModal() {
        if (this.modalBodyContent == 'Create') { this.createFin03(); }
    }

    createFin03() {
        if (this.checkingPendingClinics > 0) {

            Swal.fire({
                title: 'Are you sure?',
                text: 'Still there are some clinics pending to be processed!',
                type: 'warning',
                // showCloseButton: true,
                showConfirmButton: false,
                showCancelButton: true
            }).then((pay) => {
                if (pay.dismiss) {
                    Swal.fire('', 'Transaction Cancelled', 'error');
                    history.back();
                }
                //this is accepted part...
                else {
                    this.disableCreate = true
                    this.enabler = true
                    this.fin02Service.createFin03(this.fin08Datas[0].districtId, this.fin08Datas[0].clinicTypeId, this.fin08Datas[0].month, this.fin08Datas[0].year, this.userId, this.backLog).subscribe(x => {
                        this.disableCreate = false
                        this.enabler = false
                        Swal.fire('', 'Fin 03 created and submitted successfully!!!', 'success').then(x => {
                            history.back()
                        });
                    })
                }
            });
        }

        else if (this.checkingPendingClinics == 0) {
            this.disableCreate = true
            this.enabler = true
            this.fin02Service.createFin03(this.fin08Datas[0].districtId, this.fin08Datas[0].clinicTypeId, this.fin08Datas[0].month, this.fin08Datas[0].year, this.userId, this.backLog).subscribe(x => {
                this.disableCreate = false
                this.enabler = false
                Swal.fire('', 'Fin 03 created and submitted successfully!!!', 'success').then(x => {
                    history.back()
                });
            })
        }

    }
    navToList() {
        history.back()
    }
}