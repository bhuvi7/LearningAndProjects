import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { Fin01InvoiceService } from '../fin-01-invoice-service';
import * as dateFormat from 'dateformat';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { PagesService } from '../../../pages.service';
import { Fin06 } from '../model/fin06';
// import { toWords } from 'number-to-words';

@Component({
    selector: 'FIN-01-create',
    templateUrl: './FIN-01-create.component.html',
    styleUrls: ['./FIN-01-create.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})

export class FIN01CreateComponent implements OnInit {

    public clinicTypeCode: any;
    public districtName;
    public monthYear;
    public docRef = "";
    public date = '';
    public clinicDatas: Array<any>
    public equipmentTotalCount = 0;
    public equipmentTotalValue = 0.0;
    public sstAmount = 0.0;
    public totalInvoiceAmount = 0.0;
    public equipmentTotalValueInWords: String;
    public modalBodyContent;
    public loading: Boolean = true;
    public disableCreate: Boolean = false;
    public userId: Number;

    constructor(private router: Router, private route: ActivatedRoute, private fin01Service: Fin01InvoiceService,
        config: NgbModalConfig, private modalService: NgbModal, private pagesService: PagesService) { }

    ngOnInit() {
        this.userId = this.pagesService.getUserId();
        // 
        if (this.route.params['_value'] != "undefined") {

            this.route.params.switchMap((par: Params) => this.fin01Service.fetchDataForFin01CreateNew(par['_districtId'], par['_clinicTypeId'], par['_month'], par['_year'])).subscribe(x => {
                // let y = x
                // x = []
                // x.push(y)
                this.clinicDatas = x;
                

                this.clinicDatas.forEach(element => {
                    element.dateDisplay = dateFormat(new Date(), 'dd-mm-yyyy')
                })
                this.clinicDatas.map(data => {
                    // data.dateDisplay = dateFormat(data.date, 'dd-mm-yyyy')
                    // this.equipmentTotalCount += data.totalEquipmentCount
                    this.equipmentTotalValue += data.totalAmount
                })
                this.clinicTypeCode = x[0].clinicTypeCode;
                

                this.districtName = x[0].districtName;
                this.monthYear = x[0].month + '/' + x[0].year;
                this.sstAmount = this.equipmentTotalValue * 0.06;
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
        if (this.modalBodyContent == 'Create') { this.createFIN01(); }
    }

    createFIN01() {
        this.disableCreate = true
        this.clinicDatas.forEach(element => {
            delete element.cimsHistoryFin01
        });
        this.fin01Service.createFIN01(this.clinicDatas, this.userId).subscribe(x => {
            this.disableCreate = false
            Swal.fire('', 'FIN 01 Invoice created and submitted successfully!!!', 'success').then(x => {
                history.back()
            });
            // this.router.navigateByUrl('/transaction/fin-01-invoice/FIN-01-create-list')
        })

    }
    navToList() {
        history.back()
        // this.router.navigate(['/transaction/fin-01-invoice/FIN-01-create-list'])
    }
}