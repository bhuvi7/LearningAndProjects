import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { Fin08InvoiceService } from '../fin-08-invoice-service';
import * as dateFormat from 'dateformat';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
// import { toWords } from 'number-to-words';
import { PagesService } from '../../../../../pages.service';
@Component({
    selector: 'fin-08-create',
    templateUrl: './fin-08-create.component.html',
    styleUrls: ['./fin-08-create.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})

export class Fin08CreateComponent implements OnInit {

    public clinicCode: any;
    public districtName;
    public clinicName;
    public clinicAddress;
    public stateName;
    public clinicId;
    public monthYear;
    public docRef = "";
    public date = ''
    public clinicDatas: any;
    public clinicDatasTwo: any;
    public newEquipmentMaintenanceCharge = 0;
    public purchasedEquipmentMaintenanceCharge = 0.0;
    public totalMaintenanceCharge;
    public equipmentTotalValueInWords: String;
    public modalBodyContent;
    public loading: Boolean = true;
    public disableCreate: Boolean = false;
    public userId: Number;
    public enabler: boolean

    constructor(private router: Router, private route: ActivatedRoute, private fin08Service: Fin08InvoiceService,
        config: NgbModalConfig, private modalService: NgbModal, private pagesService: PagesService) { }

    ngOnInit() {
        this.userId = this.pagesService.getUserId();
        if (this.route.params['_value'] != "undefined") {

            this.route.params.switchMap((par: Params) => this.fin08Service.fetchDataForFin08bCreate(par['_clinicCode'], par['_month'], par['_year'])).subscribe(x => {
                if (x[0]) {
                    this.clinicDatas = x;
                    this.clinicDatas.forEach(element => {
                        element.dateDisplay = dateFormat(new Date(), 'dd-mm-yyyy')
                    });
                    this.clinicDatas.map(data => {
                        // this.equipmentTotalCount += data.totalEquipmentCount
                        // this.totalRentalCharge += data.totalRentalCharge;
                        this.newEquipmentMaintenanceCharge += data.maintenanceCharges;

                    })
                    this.clinicCode = x[0].clinicCode;
                    this.districtName = x[0].districtName;
                    this.monthYear = x[0].month + '/' + x[0].year;
                    this.stateName = x[0].stateName;
                    this.clinicAddress = x[0].clinic.address;
                    this.clinicName = x[0].clinic.clinicName;
                    this.clinicId = x[0].clinicId;
                }
                this.route.params.switchMap((par: Params) => this.fin08Service.fetchDataForFin08cCreate(par['_clinicCode'], par['_month'], par['_year'])).subscribe(x => {
                    // this.purchasedEquipmentMaintenanceCharge += x[0].clinic.maintenanceCharges;
                    // 
                    if (x[0]) {
                        this.clinicDatas = x;
                        this.clinicDatas.map(data => {
                            this.purchasedEquipmentMaintenanceCharge += data.maintenanceCharges;
                        })
                        this.clinicCode = x[0].clinicCode;
                        this.districtName = x[0].districtName;
                        this.monthYear = x[0].month + '/' + x[0].year;
                        this.stateName = x[0].stateName;
                        this.clinicAddress = x[0].clinic.address;
                        this.clinicName = x[0].clinic.clinicName;
                        this.clinicId = x[0].clinicId;
                    }
                    this.totalMaintenanceCharge = this.newEquipmentMaintenanceCharge + this.purchasedEquipmentMaintenanceCharge
                    this.date = dateFormat(new Date(), 'dd-mm-yyyy');
                    this.loading = false;
                });
            })
            // 
            // this.clinicDatasTwo.map(data => {
            // this.purchasedEquipmentMaintenanceCharge += data.maintenanceCharges;
            // })



        }

    }

    openModal(content, modalContent) {
        this.modalBodyContent = modalContent
        this.modalService.open(content);
    }

    closeModal() {
        if (this.modalBodyContent == 'Create') { this.createFin08(); }
    }

    createFin08() {
        this.disableCreate = true
        this.enabler = true
        this.clinicDatas.forEach(element => {
            delete element.fin08
        });
        this.fin08Service.createFin08(this.clinicId, this.clinicDatas[0].month, this.clinicDatas[0].year, this.userId).subscribe(x => {
            this.disableCreate = false
            this.enabler = false
            Swal.fire('', 'Fin 08 created and submitted successfully!!!', 'success').then(x => {
                history.back();
            });
        })

    }
    navToList() {
        history.back();
    }
}