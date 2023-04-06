import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import "rxjs/add/operator/map";
import { environment } from '../../../../../environments/environment';
import { Observable } from 'rxjs';
import { IOption } from 'ng-select';
import { InvoiceCounter } from './../fin-01-invoice/model/invoice-counter';

@Injectable()
export class Fin02InvoiceService {

    serviceApiUrl: string = environment.serviceApiUrl;

    constructor(private http: HttpClient) {

    }



    public fetchStateDetails() {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'state/all')
    }

    public fetchDistrictById(id) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'district/' + id)
    }

    public fetchDataForFin03CreateList(filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-08/fin-03-create-list?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId)
    }

    public fetchDataForFin03CreateListOlder(filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-08/fin-03-create-list-older?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId)
    }

    public fetchDataFromFin08ForFin03Create(districtId, clinicTypeId, month, year) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-08/districtid-clinictypeid/' + districtId + ',' + clinicTypeId + ',' + month + ',' + year)
    }

    public fetchDataFromFin08ForFin03CreateForDuplication(districtId, clinicTypeId, month, year) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-03/districtid-clinictypeid-duplication/' + districtId + ',' + clinicTypeId + ',' + month + ',' + year)
    }

    public fetchDataForFin03CreateListPendingListNotification(districtId) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-history-fin-02/fin-03-create-list-pending-list-notification/' + districtId)
    }

    public fetchDataFromFin03ForDuplications(districtId, clinicTypeId, month, year) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-03/districtid-clinictypeid-duplications/' + districtId + ',' + clinicTypeId + ',' + month + ',' + year)
    }

    public fetchDataFromFin09ForFin03Create(districtId, clinicTypeId) {
        return this.http.get<any>(this.serviceApiUrl + 'fin-09/fin-03-create/' + districtId + ',' + clinicTypeId)
    }

    public createFin03(districtId, clinicTypeId, month, year, userId, backLog) {
        return this.http.post(this.serviceApiUrl + 'fin-03/create-fin03-chf2/' + userId + '/' + districtId + ',' + clinicTypeId + ',' + month + ',' + year + ',' + backLog, {})
    }

    // public createFin03a(data, userId, backLog) {
    //     return this.http.post(this.serviceApiUrl + 'fin-03a/create-fin03a/' + userId + ',' + backLog, data)
    // }

    public fetchDataForFin03InProgress(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-03/!status?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public fetchDataForFin03InProgressOlder(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-03/!status-older?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public fetchDataForFin03Approve(id) {
        return this.http.get<any>(this.serviceApiUrl + 'fin-03/find-by-fin03-id/' + id)
    }

    public fetchDataForFin03Approved(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-03/status?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public fetchDataForFin03ApprovedOlder(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-03/status-older?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public updateFin03Status(data) {
        return this.http.put(this.serviceApiUrl + 'fin-03/update-status-chf2', data)
    }

    public fetchDataForFIN02CreateList(filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-03/fin02-create-list?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId)
    }

    public fetchDataForFIN02CreateListOlder(filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-03/fin02-create-list-older?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId)
    }

    public fetchDataForFin02Create(id) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-03/' + id)
    }

    public createFin02(data, userId) {
        return this.http.post(this.serviceApiUrl + 'invoice/create-fin02-chf2/' + userId, data)
    }

    public fetchDataForFin02InProgress(invoiceTypeId, status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-!status-stateid-districtid?invoiceTypeId=' + invoiceTypeId + '&stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public fetchDataForFin02InProgressOlder(invoiceTypeId, status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-!status-stateid-districtid-older?invoiceTypeId=' + invoiceTypeId + '&stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public fetchDataForFin02Approve(id) {
        return this.http.get<any>(this.serviceApiUrl + 'invoice/' + id)
    }

    public fetchDataForFin02Approved(invoiceTypeId, status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-status-stateid-districtid?invoiceTypeId=' + invoiceTypeId + '&stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public fetchDataForFin02ApprovedOlder(invoiceTypeId, status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-status-stateid-districtid-older?invoiceTypeId=' + invoiceTypeId + '&stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public updateFin02Status(data) {
        return this.http.put(this.serviceApiUrl + 'invoice/update-fin02-status-chf2', data)
    }

    public findInvoiceSstPercentage(invoiceTypeId) {
        return this.http.get<any>(this.serviceApiUrl + 'invoice-type/sst-percentage/' + invoiceTypeId)
    }

    public Fin02InvoiceService() {
        return this.http.get<InvoiceCounter>(this.serviceApiUrl + 'invoice-counter/fin02');
    }


    public static readonly PLAYER_ONE: Array<IOption> = [
        { value: '0', label: 'Alabama' },
        { value: '1', label: 'Wyoming' },
        { value: '2', label: 'Coming' },
        { value: '3', label: 'Henry Die' },
        { value: '4', label: 'John Doe' }
    ];


    public cloneOptions(options: Array<IOption>): Array<IOption> {
        return options.map(option => ({ value: option.value, label: option.label }));
    }

    private loadOptions(options: Array<IOption>): Observable<Array<IOption>> {
        return new Observable((obs) => {
            setTimeout(() => {
                obs.next(this.cloneOptions(options));
                obs.complete();
            }, 5000);
        });
    }

    loadCharacters(): Observable<Array<IOption>> {
        return this.loadOptions(Fin02InvoiceService.PLAYER_ONE);
    }


}