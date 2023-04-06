import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../../environments/environment';
import { Fin10b } from './model/fin-10b';
import { IOption } from './model/IOption';


@Injectable()
export class Fin04InvoiceService {

    serviceApiUrl: string = environment.serviceApiUrl;

    constructor(private http: HttpClient) { }

    public fetchStateDetails() {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'state/all')
    }

    public fetchClinicTypeDetails() {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'clinic-type/all')
    }

    public fetchClinicsDetails(districtId, clinicTypeId) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'clinic/districtid-clinictypeid/' + districtId + ',' + clinicTypeId)
    }

    public cloneOptions(options: Array<IOption>): Array<IOption> {
        return options.map(option => ({ value: option.value, label: option.label }));
    }

    getCharacters(array): Array<IOption> {
        return this.cloneOptions(array);
    }

    public fetchFor10bCreateList() {
        return this.http.get<any>(this.serviceApiUrl + 'clinic/all')
    }

    public fetchForFin10bCreate(id) {
        return this.http.get<any>(this.serviceApiUrl + 'clinic/' + id)
    }

    public createFin10b(fin10b) {
        return this.http.post<Fin10b>(this.serviceApiUrl + 'fin-10b/create-fin10b', fin10b)
    }

    public fetchDataForFin10b() {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-10b/all')
    }
    public fetchDataForFin10bInprogress(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-10b/!status?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public fetchDataForFin10bApprovedList(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-10b/status-fin10b?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public fetchDataForFin07InProgress(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-07/!status?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public fetchForFin10bApprove(id) {
        return this.http.get<any>(this.serviceApiUrl + 'fin-10b/' + id)
    }

    public updateFin10b(fin10b) {
        return this.http.put(this.serviceApiUrl + 'fin-10b/update', fin10b)
    }

    public fetchDataForFin04CreateList() {
        return this.http.get(this.serviceApiUrl + 'fin-10b/fin-04-create-list');
    }       

    public fetchDataForFin04Create(districtId, clinicTypeId,month,year) {
        return this.http.get(this.serviceApiUrl + 'fin-10b/districtid-clinictypeid/' + districtId + ',' + clinicTypeId + ',' + month + ',' + year )
    }

    public createFin04(data) {
        return this.http.post(this.serviceApiUrl + 'invoice/create-fin04', data);
    }

    public fetchDataForFin04InProgress(invoiceTypeId, status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-!status?invoiceTypeId=' + invoiceTypeId + '&stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }
  

    public fetchDataForFin04Approve(id) {
        return this.http.get<any>(this.serviceApiUrl + 'invoice/' + id)
    }

    public fetchDataForFin04Approved(invoiceTypeId, status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-status?invoiceTypeId=' + invoiceTypeId + '&stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }
    // public fetchDataForFin02bApproved(invoiceTypeId, status,filter) {
    //     //   return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-status/' + invoiceTypeId + ',' + status + ',' + stateId + ',' + districtId) 
    //     return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-status-stateid-districtid?invoiceTypeId='+invoiceTypeId+'&stateId='+filter.stateId +'&circleId='+filter.circleId+'&districtId='+filter.districtId+'&status='+status)
    //    }

    public updateFin04Status(invoice) {
        return this.http.put(this.serviceApiUrl + 'invoice/update-fin04-status', invoice)
    }

    public findInvoiceSstPercentage(invoiceTypeId) {
        return this.http.get<any>(this.serviceApiUrl + 'invoice-type/sst-percentage/' + invoiceTypeId)
    }

    public fetchDateFrom10b(invoiceNo) {
        return this.http.get<any>(this.serviceApiUrl + 'invoice/invoice-no/fetchdate' + invoiceNo)
    }
    public fetchDataForFin08bCreate(clinicId, month ,year) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-history-fin-02/clinicid-fin08b/' + clinicId + ',' + month +  ',' + year)
    }
}