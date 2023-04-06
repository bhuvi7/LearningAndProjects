import { Component, OnInit, ViewEncapsulation } from "@angular/core";
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Fin02aInvoiceService } from '../fin-02a-invoice-service';
import { InvoiceCounter } from './../../fin-01-invoice/model/invoice-counter';
@Component({
    selector: 'fin-02a-list-page',
    templateUrl: './fin-02a-list-page.component.html',
    encapsulation: ViewEncapsulation.None
})
export class Fin02aListPageComponent implements OnInit {
    
    constructor(private router: Router, private route: ActivatedRoute, private fin02aInvoiceService: Fin02aInvoiceService) { }
    
    public fin02aInvoiceCounter: InvoiceCounter = new InvoiceCounter();
    public loading: Boolean = true;
    ngOnInit() {
        this.loading = true;
        this.fin02aInvoiceService.Fin02aInvoiceService().subscribe(x => {
            this.fin02aInvoiceCounter = x;
            this.loading = false;
         })
     }
}