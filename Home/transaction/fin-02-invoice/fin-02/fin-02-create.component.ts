import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { Fin02InvoiceService } from '../fin-02-service';
import * as dateFormat from 'dateformat';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { Fin02Clinic } from '../model/fin02-clinic';
// import { toWords } from 'number-to-words';
import { PagesService } from '../../../pages.service';
@Component({
    selector: 'fin-02-create',
    templateUrl: './fin-02-create.component.html',
    styleUrls: ['./fin-02-create.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})

export class Fin02CreateComponent implements OnInit {

    public clinicTypeCode: any;
    public districtName;
    public districtAddress = 'N/A';
    public monthYear;
    public docRef = "";
    public date = ''
    public clinicDatas: any;
    public modalBodyContent;
    public fin03: any;
    public loading: Boolean = true;
    public disableCreate: Boolean = false;
    public userId: Number;
    public districtId: number;
    public clinicTypeId: number;
    public district;
    public enabler: boolean
    // public districtAddress: string;
    constructor(private router: Router, private route: ActivatedRoute, private fin02Service: Fin02InvoiceService,
        config: NgbModalConfig, private modalService: NgbModal, private pagesService: PagesService) { }

    ngOnInit() {
        this.fin03 = [];
        this.userId = this.pagesService.getUserId();
        if (this.route.params['_value'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.fin02Service.fetchDataForFin02Create(par['_id'])).subscribe(x => {
                let y = x;
                x = []
                x.push(y);
                this.fin03 = x;
                this.districtName = this.fin03[0].districtName;
                this.districtId = this.fin03[0].districtId;
                this.clinicTypeId = this.fin03[0].clinicTypeId;
                this.monthYear = this.fin03[0].month + '/' + this.fin03[0].year;
                this.clinicTypeCode = this.fin03[0].clinicTypeCode;
                this.districtName = this.fin03[0].districtName;
                this.date = dateFormat(new Date(), 'dd-mm-yyyy');
                this.fin02Service.fetchDistrictById(this.districtId).subscribe(x => {
                    this.district = x
                    if (this.clinicTypeId == 1) {
                        this.districtAddress = this.district.officePkdAddress;
                    } else if (this.clinicTypeId == 2) {
                        this.districtAddress = this.district.officePpdAddress;
                    }


                })
            })
        }
        this.loading = false;

    }

    openModal(content, modalContent) {
        this.modalBodyContent = modalContent
        this.modalService.open(content);
    }

    closeModal() {
        if (this.modalBodyContent == 'Create') { this.createFin02(); }
    }

    createFin02() {
        this.disableCreate = true
        this.enabler = true
        delete this.fin03[0].fin08;
        delete this.fin03[0].fin09;
        this.fin02Service.createFin02(this.fin03, this.userId).subscribe(x => {
            this.disableCreate = false
            this.enabler = false
            Swal.fire('', 'FIN 02 Invoice created and submitted successfully!!!', 'success').then(x => {
                history.back()
            });
        })

    }
    navToList() {
        history.back()
    }
}