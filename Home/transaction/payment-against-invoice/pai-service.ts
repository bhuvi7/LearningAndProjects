import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../../environments/environment';
import { Invoice } from './model/invoice';
import { InvoicePaymentHistory } from './model/invoice-payment-history';


@Injectable()
export class PAIService {

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

    public multipleInvoicePayment(invoicePayment) {
        return this.http.post<InvoicePaymentHistory>(this.serviceApiUrl + 'invoice-payment-history/add-payment-to-multiple-invoice', invoicePayment)
    }

    public debitPayment(invoicePayment) {
        return this.http.post<InvoicePaymentHistory>(this.serviceApiUrl + 'invoice-payment-history/add-payment-to-debit', invoicePayment)
    }

    public penaltyInvoicePayment(invoicePayment) {
        // 
        // 
        return this.http.post<InvoicePaymentHistory>(this.serviceApiUrl + 'invoice-payment-history/penalty-payment-to-invoice', invoicePayment)
    }

    public penaltyInvoicePaymentSpecial(invoicePayment) {
        return this.http.post<InvoicePaymentHistory>(this.serviceApiUrl + 'invoice-payment-history/penalty-payment-to-invoice-special', invoicePayment)
    }

    public fetchAllInvoiceFilter1(stateName: string, districtName: string, clinicType: string) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/filter/payment-status?stateName=' + stateName + "&districtName=" + districtName + "&clinicTypeId=" + clinicType)
    }

    public fetchAllInvoiceFilterForPenaltyAdjustment(stateName: string, districtName: string, clinicType: string, approvalQuater: string, approvalYear: string) {
        return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/filter/payment-status-penalty?stateName=' + stateName + "&districtName=" + districtName + "&clinicTypeId=" + clinicType
            + "&approvalQuater=" + approvalQuater + "&approvalYear=" + approvalYear)
    }
    public fetchAllInvoiceFilter(stateName: string, districtName: string) {
        return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/filter/payment-status?stateName=' + stateName + "&districtName=" + districtName)
    }

}