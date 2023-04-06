import { Component,OnInit,ViewEncapsulation } from "@angular/core";
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Fin01InvoiceService } from '../../fin-01-invoice-service';
import { InvoiceCounter } from '../../model/invoice-counter';
@Component({
 selector:'fin-01-list-page',
 templateUrl:'./fin-01-list-page.component.html',
 encapsulation:ViewEncapsulation.None
})
export class Fin01ListPageComponent implements OnInit {

    constructor(private router: Router, private route: ActivatedRoute, private fin01Service: Fin01InvoiceService){}
    
    public fin01InvoiceCounter: InvoiceCounter = new InvoiceCounter();
    public loading: Boolean = true;

 
    ngOnInit() {
        this.loading = true;
        this.fin01Service.getFin01InvoiceCounter().subscribe(x => {
            this.fin01InvoiceCounter = x;
            this.loading = false;
        })
       
    }
    
}