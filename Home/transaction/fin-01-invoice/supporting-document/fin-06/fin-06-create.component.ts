import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { Fin01InvoiceService } from '../../fin-01-invoice-service';
import * as dateFormat from 'dateformat';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { Fin06 } from "../../model/fin06";
import { PagesService } from '../../../../pages.service';
// import { toWords } from 'number-to-words'; 

@Component({
    selector: 'fin-06-create',
    templateUrl: './fin-06-create.component.html',
    styleUrls: ['./fin-06-create.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})

export class Fin06CreateComponent implements OnInit {

    public stateName;
    public districtName;
    public clinicName;
    public clinicCode;
    public monthYear;
    public docRef = "";
    public date = '';
    public equipmentDatas: any;
    public equipment = { id: "", assetName: "", remarks: "" };
    public equipmentCount = 0;
    public equipmentTotalValue = 0.00;
    public equipmentTotalValueInWords: String;

    public displaySelectedEquip: Boolean = false;
    public disableCreate: Boolean = false;
    public selectedEquipments: any;
    public notSelectedEquipments: any;
    public modalBodyContent;
    public loading: Boolean = true;
    public fin06: Fin06;
    public isFin06Created: Boolean = false;
    public userId: Number;

    constructor(private router: Router, private route: ActivatedRoute, private fin01service: Fin01InvoiceService,
        config: NgbModalConfig, private modalService: NgbModal, private pagesService: PagesService, ) {
        config.backdrop = 'static';
        config.keyboard = false;
    }

    ngOnInit() {
        this.disableCreate = false
        this.selectedEquipments = []
        this.notSelectedEquipments = [];
        this.userId = this.pagesService.getUserId();
         
        if (this.route.params['_value']['_clinicCode'] != "undefined") {
           
            this.route.params.switchMap((par: Params) => this.fin01service.fetchDataForFin06Create(par['_clinicCode'],par['_month'],par['_year'],'N')).subscribe(x => {

                this.equipmentDatas = x;
                this.equipmentDatas.forEach(element => {
                    element.isFin06Created = 'Y'
                    this.selectedEquipments.push(element);
                    element.checked = true
                    element.installedDateDisplay = dateFormat(element.installedDate, 'dd-mm-yyyy')
                    element.purchasedDateDisplay = dateFormat(element.purchasedDate, 'dd-mm-yyyy')
                    element.acceptedDateDisplay = dateFormat(element.acceptedDate, 'dd-mm-yyyy')
                    element.tcCertificate = element.beNumber
                });
                this.stateName = x[0].stateName;
                this.districtName = x[0].districtName;
                this.clinicName = x[0].clinic.clinicName;
                this.clinicCode = x[0].clinic.clinicCode;
                this.monthYear = x[0].month + '/' + x[0].year;
                this.date = dateFormat(new Date(), 'dd-mm-yyyy');
            });
            this.route.params.switchMap((par: Params) => this.fin01service.isFin06Created(par['_clinicCode'])).subscribe(x => {
                this.isFin06Created = x;
                this.loading = false;
            })
        }
    }

    openModal(content, modalContent) {
        this.modalBodyContent = modalContent
        this.modalService.open(content);
    }

    closeModal() {
        if (this.modalBodyContent == 'Create') { this.createFin06(); }
    }

    closeModal1() {
        this.notSelectedEquipments.forEach(element => {
            if (element.id == this.equipment.id) {
                element.remarks = this.equipment.remarks
            }
        })
        this.equipment = { id: "", assetName: "", remarks: "" };
    }

    addRemoveEquip(event, content1) {
        this.disableCreate = false
        if (event.target.checked) {
            this.equipmentDatas.forEach(element => {
                if (element.id == event.target.value) {
                    element.isFin06Created = 'Y'
                    element.checked = true
                    this.selectedEquipments.push(element)
                    this.selectedEquipments.sort((a, b) => a.id - b.id);
                }
            });
            const index = this.notSelectedEquipments.findIndex(data => data.id == event.target.value);
            this.notSelectedEquipments.splice(index, 1);
        } else {
            this.equipmentDatas.forEach(element => {
                if (element.id == event.target.value) {
                    element.checked = false
                    element.isFin06Created = 'N'
                    const index = this.selectedEquipments.findIndex(data => data.id == event.target.value);
                    this.selectedEquipments.splice(index, 1);
                    this.selectedEquipments.sort((a, b) => a.id - b.id);
                    this.equipment = element
                    this.notSelectedEquipments.push(element);
                    this.modalService.open(content1);
                }
            })
        }

        if (this.selectedEquipments.length == 0) {
            this.disableCreate = true
            this.displaySelectedEquip = false
        }
    }

    displaySelectedEquipment() {
        this.equipmentCount = this.selectedEquipments.length
        this.selectedEquipments.forEach(equip => {
            this.equipmentTotalValue += equip.purchaseAmount
        })
        this.displaySelectedEquip = true
    }

    handleChange(event, id) {
        this.selectedEquipments.forEach(element => {
            if (element.id == id) {
                element.tcCertificate = event.target.value
            }
        });
    }

    createFin06() {
        this.disableCreate = true
        this.equipmentDatas = []
        this.equipmentDatas = [...this.selectedEquipments, ...this.notSelectedEquipments]
        this.fin01service.createFin06(this.equipmentDatas, this.userId).subscribe(x => {
            this.disableCreate = false
            Swal.fire('', 'Fin 06 created and submitted successfully!!!', 'success').then(x => {
                history.back()
            });
            // this.router.navigateByUrl('/transaction/fin-01-invoice/fin-06-create-list')
        })
    }

    navToList() {
        history.back()
    }
    // navToList() {
    //     this.router.navigate(['/transaction/fin-01-invoice/fin-06-create-list'])
    // }
}