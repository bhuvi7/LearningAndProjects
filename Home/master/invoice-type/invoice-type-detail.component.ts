import { Component, OnInit } from '@angular/core';
import { MasterService } from "../master.service";
import 'rxjs/add/operator/switchMap';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { InvoiceType } from '../model/invoice-type';


@Component({
    selector: 'invoice-type-detail',
    templateUrl: './invoice-type-detail.component.html'
})
export class InvoiceTypeDetailComponent implements OnInit {
    public invoiceType: InvoiceType;
    public retentionDisable: Boolean = false;
    public sstDisable: Boolean = false;
    public loading: Boolean = true;
    constructor(public masterService: MasterService, public router: Router, private route: ActivatedRoute) { }

    ngOnInit() {
        this.invoiceType = new InvoiceType();
        this.retentionDisable = this.invoiceType.retentionAvailable == 'N' ? true : false
        this.sstDisable = this.invoiceType.sstIncluded == 'N' ? true : false
        if (this.route.params['_value']['_id'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.masterService.getInvoiceTypeById(par['_id'])).subscribe(x => {
                this.invoiceType = x;
                this.retentionDisable = this.invoiceType.retentionAvailable == 'N' ? true : false
                this.sstDisable = this.invoiceType.sstIncluded == 'N' ? true : false
                this.loading = false;
            });
        }
        this.loading = false;

    }

    handleRetention() {
        this.retentionDisable = this.invoiceType.retentionAvailable == 'N' ? true : false
        this.invoiceType.retentionPercentage = this.invoiceType.retentionAvailable == 'N' ? 0 : this.invoiceType.retentionPercentage
    }

    handleSst() {
        this.sstDisable = this.invoiceType.sstIncluded == 'N' ? true : false
        this.invoiceType.sstPercentage = this.invoiceType.sstIncluded == 'N' ? 0 : this.invoiceType.sstPercentage
    }


    save() {
        if (this.invoiceType.id != undefined) {
            this.masterService.updateInvoiceType(this.invoiceType).subscribe((x) => {
                Swal.fire('', 'Invoice Type updated successfully!!!', 'success');
                this.exit();
            });
        } else {
            this.masterService.addInvoiceType(this.invoiceType).subscribe((x) => {
                Swal.fire('', 'Invoice Type added successfully!!!', 'success');
                this.exit();
            });
        }

    }


    exit() {
        this.router.navigateByUrl("/master/invoice-type-list");
    }

}
