import { Component, OnInit, ViewEncapsulation } from "@angular/core";
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Fin02InvoiceService } from '../fin-02-service';
import { InvoiceCounter } from './../../fin-01-invoice/model/invoice-counter';

@Component({
    selector: 'fin-02-list-page',
    templateUrl: 'fin-02-list-page.component.html',
    encapsulation: ViewEncapsulation.None
})
export class Fin02ListPageComponent implements OnInit {
    constructor(private router: Router, private route: ActivatedRoute, private fin02InvoiceService: Fin02InvoiceService) { }
    
    public fin02InvoiceCounter: InvoiceCounter = new InvoiceCounter();
    public loading: Boolean = true;
    ngOnInit() {
        this.loading = true;
        this.fin02InvoiceService.Fin02InvoiceService().subscribe(x => {
            this.fin02InvoiceCounter = x;
            this.loading = false;
         })
     }
}