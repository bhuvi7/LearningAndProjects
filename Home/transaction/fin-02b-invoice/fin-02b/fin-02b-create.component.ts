import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { Fin02bInvoiceService } from '../fin-02b-invoice-service';
import * as dateFormat from 'dateformat';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { CimsHistoryFin02b } from '../model/cimshistoryfin02b';
// import { toWords } from 'number-to-words';
import { PagesService } from '../../../pages.service';
@Component({
    selector: 'fin-02b-create',
    templateUrl: './fin-02b-create.component.html',
    styleUrls: ['./fin-02b-create.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})

export class Fin02bCreateComponent implements OnInit {

    public clinicTypeCode: any;
    public districtName;
    public districtAddress = 'N/A';
    public monthYear;
    public netMaintenanceCharges;
    public chargesPayable;
    public grossCharges;
    public penaltyCharges;
    public netChargesPayable;
    public docRef = "";
    public date = ''
    public modalBodyContent;
    public cimsHistoryFin02b: CimsHistoryFin02b;
    public loading: Boolean = true;
    public disableCreate: Boolean = false;
    public userId: Number;
    public enabler: boolean

    constructor(private router: Router, private route: ActivatedRoute, private fin02bService: Fin02bInvoiceService,
        config: NgbModalConfig, private modalService: NgbModal, private pagesService: PagesService) { }

    ngOnInit() {
        this.userId = this.pagesService.getUserId();
        if (this.route.params['_value'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.fin02bService.fetchDataForFin02bCreate(par['_districtId'], par['_clinicTypeId'], par['_month'], par['_year'])).subscribe(x => {
                this.cimsHistoryFin02b = x;
                this.monthYear = this.cimsHistoryFin02b.month + '/' + this.cimsHistoryFin02b.year;
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
        if (this.modalBodyContent == 'Create') { this.createFin02b(); }
    }

    createFin02b() {
        this.disableCreate = true
        this.enabler = true
        this.fin02bService.createFin02b(this.cimsHistoryFin02b, this.userId).subscribe(x => {
            this.disableCreate = false
            this.enabler = false
            Swal.fire('', 'FIN 02B Invoice created and submitted successfully!!!', 'success');
            //  this.router.navigateByUrl('/transaction/fin-02b-invoice/fin-02b-create-list')
            history.back()
        })
    }

    navToList() {
        // this.router.navigate(['/transaction/fin-02b-invoice/fin-02b-create-list'])
        history.back()
    }
}