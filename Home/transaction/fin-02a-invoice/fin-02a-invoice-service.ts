import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import "rxjs/add/operator/map";
import { environment } from '../../../../../environments/environment';
import { Observable } from 'rxjs';
import { IOption } from 'ng-select';
import { InvoiceCounter } from './../fin-01-invoice/model/invoice-counter';

@Injectable()
export class Fin02aInvoiceService {

    serviceApiUrl: string = environment.serviceApiUrl;

    constructor(private http: HttpClient) {

    }


    public fetchStateDetails() {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'state/all')
    }

    public fetchDistrictById(id) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'district/' + id)
    }

    public fetchDataForFin07CreateList(filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-histroy-fin-02a/fin-07-create-list?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId)
    }

    public fetchDataForFin07CreateListPendingClinics(districtId, clinicTypeId, month, year) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-histroy-fin-02a/fin-07-pending-list/' + districtId + ',' + clinicTypeId + ',' + month + ',' + year)
    }

    public fetchDataForFin07CreateListOlder(filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-histroy-fin-02a/fin-07-create-list-older?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId)
    }

    public fetchDataForFin07Create(clinicId, month, year) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-histroy-fin-02a/clinic-id/' + clinicId + ',' + month + ',' + year)
    }

    public createFin07(data, userId) {
        return this.http.post<any>(this.serviceApiUrl + 'cims-histroy-fin-02a/fin-07-create/' + userId, data)
    }

    public fetchDataForFin07Approve(id) {
        return this.http.get<any>(this.serviceApiUrl + 'fin-07/' + id)
    }

    public fetchDataForFin07InProgress(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-07/!status?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public fetchDataForFin07InProgressOlder(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-07/!status-older?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public fetchDataForFin07Approved(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-07/status?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }



    public fetchDataForFin07ApprovedOlder(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-07/status-older?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }


    public updateFin07Status(data) {
        return this.http.put(this.serviceApiUrl + 'fin-07/update-status', data)
    }

    public updateFin07(data) {
        return this.http.put(this.serviceApiUrl + 'fin-07/update', data);
    }

    public fetchDataForFin03aCreateList(filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-histroy-fin-02a/fin-03a-create-list?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId)
    }

    public fetchDataForFin03aCreateListOlder(filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-histroy-fin-02a/fin-03a-create-list-older?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId)
    }

    public fetchDataForFin03aCreate(districtId, clinicTypeId, month, year) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-07/districtAndClinicId/' + districtId + ',' + clinicTypeId + ',' + month + ',' + year)
    }
    public fetchDataForFin03aCreateForDuplicate(districtId, clinicTypeId, month, year) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-03a/districtAndClinicId/' + districtId + ',' + clinicTypeId + ',' + month + ',' + year)
    }

    public createFin03a(data, userId, backLog) {
        return this.http.post(this.serviceApiUrl + 'fin-03a/create-fin03a/' + userId + ',' + backLog, data)
    }

    public fetchDataForFin03aInProgress(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-03a/!status?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public fetchDataForFin03aInProgressOlder(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-03a/!status-older?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public fetchDataForFin03aApprove(id) {
        return this.http.get<any>(this.serviceApiUrl + 'fin-03a/find-by-fin03-id/' + id)
    }

    public fetchDataForFin03aApproved(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-03a/status?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public fetchDataForFin03aApprovedOlder(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-03a/status-older?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public updateFin03aStatus(data) {
        return this.http.put(this.serviceApiUrl + 'fin-03a/update-status', data)
    }

    public fetchDataForFin02aCreateList(filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-histroy-fin-02a/fin-02a-create-list?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId)
    }

    public fetchDataForFin02aCreateListOlder(filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-histroy-fin-02a/fin-02a-create-list-older?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId)
    }

    public fetchDataForFin02aCreate(id) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-03a/' + id)
    }

    public createFin02a(data, userId) {
        return this.http.post(this.serviceApiUrl + 'invoice/create-fin02a/' + userId, data)
    }

    public fetchDataForFin02aInProgress(invoiceTypeId, status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-!status-stateid-districtid?invoiceTypeId=' + invoiceTypeId + '&stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public fetchDataForFin02aInProgressOlder(invoiceTypeId, status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-!status-stateid-districtid-older?invoiceTypeId=' + invoiceTypeId + '&stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public fetchDataForFin02aApprove(id) {
        return this.http.get<any>(this.serviceApiUrl + 'invoice/' + id)
    }

    public fetchDataForFin02aApproved(invoiceTypeId, status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-status-stateid-districtid?invoiceTypeId=' + invoiceTypeId + '&stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public fetchDataForFin02aApprovedOlder(invoiceTypeId, status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-status-stateid-districtid-older?invoiceTypeId=' + invoiceTypeId + '&stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public updateFin02aStatus(data) {
        return this.http.put(this.serviceApiUrl + 'invoice/update-fin02a-status', data)
    }

    public findInvoiceSstPercentage(invoiceTypeId) {
        return this.http.get<any>(this.serviceApiUrl + 'invoice-type/sst-percentage/' + invoiceTypeId)
    }

    public Fin02aInvoiceService() {
        return this.http.get<InvoiceCounter>(this.serviceApiUrl + 'invoice-counter/fin02a');
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
        return this.loadOptions(Fin02aInvoiceService.PLAYER_ONE);
    }


}