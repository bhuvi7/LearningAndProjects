import { Component, OnInit, ViewEncapsulation } from "@angular/core";
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Fin02bInvoiceService } from '../fin-02b-invoice-service';
import { InvoiceCounter } from './../../fin-01-invoice/model/invoice-counter';

@Component({
    selector: 'fin-02b-list-page',
    templateUrl: 'fin-02b-list-page.component.html',
    encapsulation: ViewEncapsulation.None
})
export class Fin02bListPageComponent implements OnInit {
    ngOnInit() { 
        this.loading = true;
        this.fin02bInvoiceService.fin0bInvoiceService().subscribe(x => {
            this.fin02bInvoiceCounter = x;
            this.loading = false;
         })
    }
    constructor(private router: Router, private route: ActivatedRoute, private fin02bInvoiceService: Fin02bInvoiceService) { }
    public fin02bInvoiceCounter: InvoiceCounter = new InvoiceCounter();
    public loading: Boolean = true;

}