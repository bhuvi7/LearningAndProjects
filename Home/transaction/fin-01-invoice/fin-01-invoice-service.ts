import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import "rxjs/add/operator/map";
import { environment } from '../../../../../environments/environment';
import { Observable } from 'rxjs';
import { IOption } from 'ng-select';
import { Fin06 } from './model/fin06';
import { CimsHistoryFin01 } from './model/cims-history-fin01';
import { InvoiceCounter } from './model/invoice-counter';
@Injectable()
export class Fin01InvoiceService {

    serviceApiUrl: string = environment.serviceApiUrl;

    constructor(private http: HttpClient) {

    }

    public fetchStateDetails() {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'state/all')
    }

    public fetchDataForFin06CreateList(filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-histroy-fin-01/fin-06-create-list?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId)
    }
    public fetchDataForFin06CreateListOlder(filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-histroy-fin-01/fin-06-create-list-older?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId)
    }

    // public fetchDataForFin06CreateList(stateId) {
    //     return this.http.get<Array<CimsHistoryFin01>>(this.serviceApiUrl + 'cims-histroy-fin-01/fin-06-create-list?state-id='+stateId)
    // }

    public fetchDataForFin06Create(clinicId, month, year, isFin06Created) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-histroy-fin-01/clinic_id-is_fin06_created/' + clinicId + ',' + month + ',' + year + ',' + isFin06Created)
    }


    public isFin06Created(clinicId) {
        return this.http.get<Boolean>(this.serviceApiUrl + 'fin-06/is_fin06_created/' + clinicId)
    }

    public createFin06(data, userId) {
        return this.http.post<Fin06>(this.serviceApiUrl + 'cims-histroy-fin-01/fin-06-create/' + userId, data)
    }

    public fetchDataForFin06Approve(id) {
        return this.http.get<Fin06>(this.serviceApiUrl + 'fin-06/' + id)
    }

    public fetchDataForFin06InProgress(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-06/!status?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public fetchDataForFin06InProgressOlder(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-06/!status-older?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public fetchDataForFin06Approved(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-06/status?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public fetchDataForFin06ApprovedOlder(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-06/status-older?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }


    public updateFin06Status(data) {
        return this.http.put(this.serviceApiUrl + 'fin-06/updateStatus', data)
    }

    public updateFin06(data) {
        return this.http.put(this.serviceApiUrl + 'fin-06/update', data);
    }

    public fetchDataForFin01CreateList(filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-histroy-fin-01/fin-01-create-list?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId)
    }

    public fetchDataForFin01CreateListOlder(filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-histroy-fin-01/fin-01-create-list-olders?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId)
    }
    public fetchDataForfin01Create(districtId, clinicTypeId, month, year) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-06/districtAndClinicId/' + districtId + ',' + clinicTypeId + ',' + month + ',' + year)
    }

    // public fetchDataForFin01CreateNew(id) {
    //     return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-06/byId/' + id)
    // }

    public fetchDataForFin01CreateNew(districtId, clinicTypeId, month, year) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-06/byDistrictId-clinicTypeId/' + districtId + ',' + clinicTypeId + ',' + month + ',' + year)
    }



    public createFin01(data, userId) {
        return this.http.post(this.serviceApiUrl + 'fin-01/create-fin01/' + userId, data)
    }

    public fetchDataForFin01InProgress(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-01/!status?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public fetchDataForFin01InProgressOlder(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-01/!status-older?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public fetchDataForFin01Approve(id) {
        return this.http.get<any>(this.serviceApiUrl + 'fin-01/' + id)
    }
    public fetchDataForFin02aApproved(invoiceTypeId, status, stateId, districtId) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-status-stateid-districtid/' + invoiceTypeId + ',' + status + ',' + stateId + ',' + districtId)
    }

    public fetchDataForFin02aApprovedOlder(invoiceTypeId, status, stateId, districtId) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-status-stateid-districtid-older/' + invoiceTypeId + ',' + status + ',' + stateId + ',' + districtId)
    }

    public fetchDataForfin01Approved(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-01/status?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public fetchDataForfin01ApprovedOlder(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-01/status-older?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }



    public fetchDataForFin01Approved(invoiceTypeId, status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-status-stateid-districtid-fin01?invoiceTypeId=' + invoiceTypeId + '&stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }

    public fetchDataForFin01ApprovedOlder(invoiceTypeId, status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-status-stateid-districtid-fin01-older?invoiceTypeId=' + invoiceTypeId + '&stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }
    // public fetchDataForFin01Approved(invoiceTypeId, status, stateId, districtId) {
    //     return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-01/status/' + status+ ',' + stateId + ',' + districtId)
    // }

    // public fetchDataForFin01ApprovedOlder(invoiceTypeId, status, stateId, districtId) {
    //     return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-status-stateid-districtid-older/' + invoiceTypeId + ',' + status + ',' + stateId + ',' + districtId)
    // }

    public updateFin01Status(data) {
        return this.http.put(this.serviceApiUrl + 'fin-01/update-status', data)
    }

    public fetchDataForFIN01CreateList(filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-histroy-fin-01/FIN-01-create-list?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId)
    }

    public fetchDataForFIN01CreateListOlder(filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-histroy-fin-01/FIN-01-create-list-older?stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId)

    }

    public fetchDataForFIN01Create(districtId, clinicTypeId) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-06/districtAndClinicId/' + districtId + ',' + clinicTypeId)
    }

    public createFIN01(data, userId) {
        return this.http.post(this.serviceApiUrl + 'invoice/createFIN01/' + userId, data)
    }

    public fetchDataForFIN01InProgress(invoiceTypeId, status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-!status-stateid?invoiceTypeId=' + invoiceTypeId + '&stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }


    public fetchDataForFIN01InProgressOlder(invoiceTypeId, status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-!status-stateid-older?invoiceTypeId=' + invoiceTypeId + '&stateId=' + filter.stateId + '&circleId=' + filter.circleId + '&districtId=' + filter.districtId + '&status=' + status)
    }



    public fetchDataForFIN01Approve(id) {
        return this.http.get<any>(this.serviceApiUrl + 'invoice/' + id)
    }

    public fetchDistrictById(id) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'district/' + id)
    }

    public fetchDataForFIN01Approved(invoiceTypeId, status) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-status/' + invoiceTypeId + ',' + status)
    }

    public updateFIN01Status(data) {
        return this.http.put(this.serviceApiUrl + 'invoice/update-status', data)
    }

    public findInvoiceSstPercentage(invoiceTypeId) {
        return this.http.get<any>(this.serviceApiUrl + 'invoice-type/sst-percentage/' + invoiceTypeId)
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
        return this.loadOptions(Fin01InvoiceService.PLAYER_ONE);
    }

    public getFin01InvoiceCounter() {
        return this.http.get<InvoiceCounter>(this.serviceApiUrl + 'invoice-counter/fin01');
    }



}