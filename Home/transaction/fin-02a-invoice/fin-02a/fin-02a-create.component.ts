import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { Fin02aInvoiceService } from '../fin-02a-invoice-service';
import * as dateFormat from 'dateformat';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
// import { toWords } from 'number-to-words';
import { PagesService } from '../../../pages.service';
@Component({
    selector: 'fin-02a-create',
    templateUrl: './fin-02a-create.component.html',
    styleUrls: ['./fin-02a-create.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})

export class Fin02aCreateComponent implements OnInit {

    public clinicTypeCode: any;
    public districtName;
    public districtId: number;
    public clinicTypeId: number;
    public district;
    public districtAddress: string;
    public monthYear;
    public docRef = "";
    public date = ''
    public clinicDatas: any;
    public equipmentTotalCount = 0;
    public totalRentalCharge = 0.0;
    public sstAmount = 0.0;
    public totalInvoiceAmount = 0.0;
    public equipmentTotalValueInWords: String;
    public modalBodyContent;
    public loading: Boolean = true;
    public disableCreate: Boolean = false;

    public userId: Number;
    public enabler: boolean
    public backLog: number = 0;
    constructor(private router: Router, private route: ActivatedRoute, private fin02aService: Fin02aInvoiceService,
        config: NgbModalConfig, private modalService: NgbModal, private pagesService: PagesService) { }

    ngOnInit() {
        this.userId = this.pagesService.getUserId();
        if (this.route.params['_value'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.fin02aService.fetchDataForFin02aCreate(par['_id'])).subscribe(x => {
                let y = x
                x = []
                x.push(y)
                this.clinicDatas = x;
                this.clinicDatas.forEach(element => {
                    element.dateDisplay = dateFormat(new Date(), 'dd-mm-yyyy')
                });
                this.clinicDatas.map(data => {
                    // this.equipmentTotalCount += data.totalEquipmentCount
                    this.totalRentalCharge += data.totalRentalCharge
                })
                this.clinicTypeCode = x[0].clinicTypeCode;
                this.districtName = x[0].districtName;
                this.districtId = x[0].districtId;
                this.clinicTypeId = x[0].clinicTypeId;
                this.monthYear = x[0].month + '/' + x[0].year;
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

        // //TO find backlog

        // if (this.route.params['_value'] != "undefined") {
        //     this.route.params.switchMap((par: Params) => this.fin02aService.fetchDataForFin02aCreate(par['_id'])).subscribe(x => {
        //         this.backLog = (x.length)
        //         console.log('this.backLog: ', this.backLog);
        //     })
        // }

        this.loading = false;
    }

    openModal(content, modalContent) {
        this.modalBodyContent = modalContent
        this.modalService.open(content);
    }

    closeModal() {
        if (this.modalBodyContent == 'Create') { this.createFin02A(); }
    }

    createFin02A() {
        this.disableCreate = true
        this.enabler = true
        this.clinicDatas.forEach(element => {
            delete element.fin07
        });
        this.fin02aService.createFin02a(this.clinicDatas, this.userId).subscribe(x => {
            this.disableCreate = false
            this.enabler = false
            Swal.fire('', 'FIN 02A Invoice created and submitted successfully!!!', 'success').then(x => {
                history.back()
            });
        })

    }
    navToList() {
        history.back()
    }
}