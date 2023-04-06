import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../../environments/environment';
import { Invoice } from './model/invoice';
import { InvoicePaymentHistory } from './model/invoice-payment-history';


@Injectable()
export class SAService {

    serviceApiUrl: string = environment.serviceApiUrl;

    constructor(private http: HttpClient) { }

    public fetchStateDetails() {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'state/all')
    }

    public fetchForPAIList(paymentStatus1, paymentStatus2) {
        return this.http.get(this.serviceApiUrl + 'invoice/payment-status1|2/' + paymentStatus1 + ',' + paymentStatus2)
    }

    public fetchForPAIUpdate(id) {
        return this.http.get<Invoice>(this.serviceApiUrl + 'invoice/' + id)
    }

    public updateInvoice(invoice) {
        return this.http.put(this.serviceApiUrl + 'invoice/update', invoice)
    }

    public invoicePayment(invoicePayment) {
        return this.http.post<InvoicePaymentHistory>(this.serviceApiUrl + 'invoice-payment-history/add-payment-to-invoice', invoicePayment)
    }

    public fetchAllInvoiceFilter(stateName: string, districtName: string) {
        return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/filter/payment-status?stateName=' + stateName + "&districtName=" + districtName)
    }
}