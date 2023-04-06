import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { Invoice } from "../report/model/invoice";
import { InvoiceType } from "../report/model/invoice-type";
import { InvoicePaymentHistory } from './model/invoice-payment-history';

@Injectable()
export class ReportService {
    serviceApiUrl: string = environment.serviceApiUrl;
    constructor(private http: HttpClient) { }

    public fetchAllInvoice() {
        return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/all')
    }

    public fetchStateDetails() {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'state/all')
    }

    public fetchcollectionReport(stateName: string, districtName: string, invoiceTypeName, clinicTypeCode: string, transactionRefNo: string, paymentRefNo: string, dateFrom: string, dateTo: string, paymentMode: string) {
        return this.http.get<Array<InvoicePaymentHistory>>(this.serviceApiUrl + 'invoice-payment-history/collection-report?stateName=' + stateName + "&districtName=" + districtName + "&invoiceTypeName=" + invoiceTypeName + "&clinicTypeCode=" + clinicTypeCode + "&transactionRefNo=" + transactionRefNo + "&paymentRefNo=" + paymentRefNo + "&dateFrom=" + dateFrom + "&dateTo=" + dateTo + "&paymentMode=" + paymentMode);
    }



    public fetchconsolidatedReport(stateName: string, invoiceTypeName, month, year) {
        return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/consolidated-report?stateName=' + stateName + "&invoiceTypeName=" + invoiceTypeName + '&month=' + month + "&year=" + year)
    }

    public fetchAllInvoiceFilter(stateName: string, districtName: string, invoiceTypeName, clinicType: string) {
        return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/filter?stateName=' + stateName + "&districtName=" + districtName + "&invoiceTypeName=" + invoiceTypeName + "&clinicTypeId=" + clinicType)
    }

    public fetchAllInvoiceFilterTest(stateName: string, districtName: string) {
        return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/filter?stateName=' + stateName + "&districtName=" + districtName)
    }

    public fetchAllInvoiceFilterInvReport(stateName: string, districtName: string, invoiceTypeName, clinicType: string, month, year) {
        return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/filter-inv-rep?stateName=' + stateName + "&districtName=" + districtName + "&invoiceTypeName=" + invoiceTypeName + "&clinicTypeId=" + clinicType + "&month=" + month + "&year=" + year)
    }

    public fetchAllInvoiceFilterWithInvoiceDate(stateName: string, districtName: string, invoiceTypeName: string, clinicType: string, invoiceDateFrom: string, invoiceDateTo: string) {
        return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/filter/invoice-no?stateName=' + stateName + "&districtName=" + districtName + "&invoiceTypeName=" + invoiceTypeName + "&clinicTypeId=" + clinicType + "&invoiceDateFrom=" + invoiceDateFrom + "&invoiceDateTo=" + invoiceDateTo);
    }

    public fetchAllInvoiceNumberFilterWithInvoiceDate(invoiceNo: string, paymentRefNo: string, invoiceDateFrom: string, invoiceDateTo: string) {
        return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/filter/invoice-no?invoiceNo=' + invoiceNo + "&paymentRefNo=" + paymentRefNo + "&invoiceDateFrom=" + invoiceDateFrom + "&invoiceDateTo=" + invoiceDateTo);
    }

    public fetchAllInvoiceNumberFilterWithoutInvoiceDate(invoiceNo: string, paymentRefNo: string) {
        return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/filter/invoice-no?invoiceNo=' + invoiceNo + "&paymentRefNo=" + paymentRefNo);
    }

    public fetchAllPaymentHistoryFilterWithInvoiceDate(transactionRefNo: string, paymentRefNo: string, paymentMode, paymentDateFrom: string, paymentDateTo: string) {
        return this.http.get<Array<InvoicePaymentHistory>>(this.serviceApiUrl + 'invoice-payment-history/filter?transactionRefNo=' + transactionRefNo + "&paymentRefNo=" + paymentRefNo + "&paymentMode=" + paymentMode + "&paymentDateFrom=" + paymentDateFrom + "&paymentDateTo=" + paymentDateTo);
    }

    public fetchAllPaymentHistoryFilterWithEntryDate(transactionRefNo: string, paymentRefNo: string, paymentMode, paymentDateFrom: string, paymentDateTo: string) {
        return this.http.get<Array<InvoicePaymentHistory>>(this.serviceApiUrl + 'invoice-payment-history/filter-entry-date?transactionRefNo=' + transactionRefNo + "&paymentRefNo=" + paymentRefNo + "&paymentMode=" + paymentMode + "&paymentDateFrom=" + paymentDateFrom + "&paymentDateTo=" + paymentDateTo);
    }

    public fetchAllPaymentHistoryFilterWithoutInvoiceDate(transactionRefNo: string, paymentRefNo: string, paymentMode) {
        return this.http.get<Array<InvoicePaymentHistory>>(this.serviceApiUrl + 'invoice-payment-history/filter-new?transactionRefNo=' + transactionRefNo + "&paymentRefNo=" + paymentRefNo + "&paymentMode=" + paymentMode);
    }

    public fetchAllInvoiceByIdPaymentHistory(id) {
        return this.http.get<InvoicePaymentHistory>(this.serviceApiUrl + 'invoice-payment-history/fetch-by-id/' + id)
    }

    public fetchAllInvoiceById(invoiceId) {
        return this.http.get<Invoice>(this.serviceApiUrl + 'invoice/' + invoiceId)
    }

    public fetchAllInvoiceByInvoiceNo(invoiceNo) {
        return this.http.get<Invoice>(this.serviceApiUrl + 'invoice/invoice-no/' + invoiceNo)
    }
    public fetchAllInvoiceByCnDnId(invoiceNo) {
        return this.http.get<Invoice>(this.serviceApiUrl + 'invoice/invoice-no/cndn-id' + invoiceNo)
    }

    public fetchAllInvoiceByPaymentHistoryId(id) {
        return this.http.get<Invoice>(this.serviceApiUrl + 'invoice/invoice-no-service/' + id)
    }

    public fetchAllInvoiceType() {
        return this.http.get<Array<InvoiceType>>(this.serviceApiUrl + 'invoice-type/all')
    }

    public fetchDistrictById(id) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'district/' + id)
    }

}