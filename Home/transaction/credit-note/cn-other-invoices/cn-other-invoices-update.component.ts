import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { PAIService } from '../../payment-against-invoice/pai-service';
import * as dateFormat from 'dateformat';
import { Invoice } from '../../payment-against-invoice/model/invoice';
import { InvoicePaymentHistory } from '../../payment-against-invoice/model/invoice-payment-history';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';


@Component({
  selector: 'cn-other-invoices-update',
  templateUrl: './cn-other-invoices-update.component.html',
  styleUrls: ['./cn-other-invoices-update.component.scss']
})
export class CnOtherInvoicesUpdateComponent implements OnInit {

  public invoice: Invoice;
  public invoicePayment: InvoicePaymentHistory;
  public loading: Boolean = true;
  public today = dateFormat(new Date(), 'yyyy-mm-dd');
  public disableSubmit: Boolean = false;


  constructor(public router: Router, private route: ActivatedRoute, private paiService: PAIService) { }

  ngOnInit() {
    this.invoice = new Invoice();
    this.invoicePayment = new InvoicePaymentHistory();
    if (this.route.params['_value']['_id'] != "undefined") {
      this.route.params.switchMap((par: Params) => this.paiService.fetchForPAIUpdate(par['_id'])).subscribe(x => {
        this.invoice = x;
        
        if (this.invoice.invoiceDate == null) { this.invoice.invoiceDateDisplay = null }
        else {
          this.invoice.invoiceDateDisplay = dateFormat(this.invoice.invoiceDate, 'dd-mm-yyyy')
        }
        this.loading = false;
      })
    }
  }

  // disableSubmit() {
  //   if (this.invoice.outstandingAmount != this.invoice.paymentReceived) {
  //     return true
  //   } else {
  //   return false
  //   }
  // }

  updateAsDraft() {
    delete this.invoice.fin06
    delete this.invoice.fin10b
    delete this.invoice.fin03a
    this.invoice.paymentStatus = 'PAYMENT-DRAFT'
    this.paiService.updateInvoice(this.invoice).subscribe(x => {
      this.router.navigateByUrl('transaction/credit-note/cn-other-invoices/cn-other-invoices');
    })
  }



  save() {
    

    if (this.invoicePayment.paymentRefNo == undefined || this.invoicePayment.paymentReceived == 0 || this.invoicePayment.paymentRefNo == "") {
      Swal.fire('', 'Please fill all fields', 'error');
    }
    else if (this.invoicePayment.paymentReceived > this.invoice.outstandingAmount) {
      Swal.fire('', 'Collection Receiving Amount can not be more than Outstanding Amount', 'error');
    }
    else {
      this.invoicePayment.invoiceNo = this.invoice.invoiceNo
      this.invoicePayment.paymentMode = "Credit Note"
      this.invoicePayment.updatedBy= JSON.parse(localStorage.getItem('currentUser')).id
      this.paiService.invoicePayment(this.invoicePayment).subscribe(x => {
        Swal.fire('', 'Payment Received Successfully. Transaction Reference No - ' + x.transactionRefNo, 'success');
        history.back();
      })
    }



  }

  action() {
    this.router.navigateByUrl('transaction/credit-note/cn-other-invoices/cn-other-invoices');
  }

}
