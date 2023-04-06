import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../../environments/environment';
import { Fin05a } from './model/fin-05a';


@Injectable()
export class Fin05aInvoiceService {

    serviceApiUrl: string = environment.serviceApiUrl;

    constructor(private http: HttpClient) { }

    public fetchStateDetails() {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'state/all')
    }      

    public fetchDataForFin05aCreate(districtId, clinicTypeId) {
        return this.http.get(this.serviceApiUrl + 'fin-05a/districtid-clinictypeid/' + districtId + ',' + clinicTypeId)
    }

    public createFin05a(data) {
        return this.http.post(this.serviceApiUrl + 'invoice/create-fin05a', data);
    }

    public fetchDataForFin05aInProgress(invoiceTypeId, status) {
        return this.http.get(this.serviceApiUrl + 'invoice/invoicetypeid-!status/' + invoiceTypeId + ',' + status)
    }

    public fetchDataForFin05aApprove(id) {
        return this.http.get<any>(this.serviceApiUrl + 'invoice/' + id)
    }

    public fetchDataForFin05aApproved(invoiceTypeId, status) {
        return this.http.get(this.serviceApiUrl + 'invoice/invoicetypeid-status/' + invoiceTypeId + ',' + status)
    }

    public updateFin05aStatus(invoice) {
        return this.http.put(this.serviceApiUrl + 'invoice/update-fin05a-status', invoice)
    }
}