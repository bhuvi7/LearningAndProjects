import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../../environments/environment';
import { Fin11 } from './model/fin-11';


@Injectable()
export class Fin11InvoiceService {

    serviceApiUrl: string = environment.serviceApiUrl;

    constructor(private http: HttpClient) { }

    public fetchStateDetails() {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'state/all')
    }

    
    public fetchDataForFin11Create(districtId, clinicTypeId) {
        return this.http.get(this.serviceApiUrl + 'fin-11/districtid-clinictypeid/' + districtId + ',' + clinicTypeId)
    }

    public createFin11(data) {
        return this.http.post(this.serviceApiUrl + 'invoice/create-fin11', data);
    }

    public fetchDataForFin11InProgress(invoiceTypeId, status) {
        return this.http.get(this.serviceApiUrl + 'invoice/invoicetypeid-!status/' + invoiceTypeId + ',' + status)
    }

    public fetchDataForFin11Approve(id) {
        return this.http.get<any>(this.serviceApiUrl + 'invoice/' + id)
    }

    public fetchDataForFin11Approved(invoiceTypeId, status) {
        return this.http.get(this.serviceApiUrl + 'invoice/invoicetypeid-status/' + invoiceTypeId + ',' + status)
    }

    public updateFin11Status(invoice) {
        return this.http.put(this.serviceApiUrl + 'invoice/update-fin11-status', invoice)
    }
}