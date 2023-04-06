import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { Fin04InvoiceService } from '../fin-04-invoice-service';
import * as dateFormat from 'dateformat';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
// import { toWords } from 'number-to-words';

@Component({
    selector: 'fin-04-create',
    templateUrl: './fin-04-create.component.html',
    styleUrls: ['./fin-04-create.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})

export class Fin04CreateComponent implements OnInit {

    public clinicTypeCode: any;
    public districtName;
    public monthYear;
    public docRef = "";
    public date = ''
    public clinicDatas: any;
    public equipmentTotalCount = 0;
    public equipmentTotalValue = 0.0;
    public sstAmount = 0.0;
    public totalInvoiceAmount = 0.0;
    public equipmentTotalValueInWords: String;
    public modalBodyContent;
    public loading: Boolean = true;
    public disableCreate: Boolean = false;

    constructor(private router: Router, private route: ActivatedRoute, private fin04Service: Fin04InvoiceService,
        config: NgbModalConfig, private modalService: NgbModal) { }

    ngOnInit() {
        if (this.route.params['_value'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.fin04Service.fetchDataForFin04Create(par['_districtId'], par['_clinicTypeId'], par['_month'], par['_year'])).subscribe(x => {
                this.clinicDatas = x;
                console.log(this.clinicDatas);
                this.clinicDatas.forEach(element => {
                    element.dateDisplay = dateFormat(new Date(), 'dd-mm-yyyy')
                });
                this.clinicDatas.map(data => {
                    // this.equipmentTotalCount += data.totalEquipmentCount
                    this.equipmentTotalValue += data.totalAmount
                })
                this.clinicTypeCode = x[0].clinicTypeCode;
                this.districtName = x[0].districtName;
                this.monthYear = x[0].month + '/' + x[0].year;
                this.sstAmount = this.equipmentTotalValue * 0.00;
                this.totalInvoiceAmount = this.equipmentTotalValue + this.sstAmount;
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
        if (this.modalBodyContent == 'Create') { this.createFin04(); }
    }

    createFin04() {
        this.disableCreate = true
        this.clinicDatas.forEach(element => {
            delete element.fin10bConstructionWorks

        });
        this.fin04Service.createFin04(this.clinicDatas).subscribe(x => {
            this.disableCreate = false
            Swal.fire('', 'FIN 04 created and submitted successfully!!!', 'success');
            this.router.navigateByUrl('/transaction/fin-04-invoice/fin-04-create-list')

        })

    }
    navToList() {
        this.router.navigate(['/transaction/fin-04-invoice/fin-04-create-list'])
    }
}