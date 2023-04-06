import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import "rxjs/add/operator/map";
import { environment } from '../../../../../environments/environment';
import { Observable } from 'rxjs';
import { IOption } from 'ng-select';
import { CimsHistoryFin02b } from './model/cimshistoryfin02b';
import { Fin02b } from './model/fin02b';
import { InvoiceCounter } from './../fin-01-invoice/model/invoice-counter';
@Injectable()
export class Fin02bInvoiceService {

    serviceApiUrl: string = environment.serviceApiUrl;

    constructor(private http: HttpClient) { }

    public fetchStateDetails() {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'state/all')
    }
    // public fetchDataForFin02bCreateList() {
    //     return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-history-fin-02b/fin-02b-create-list')
    // }

    public fetchClinicsDetails(districtId, clinicTypeId) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'clinic/districtid-clinictypeid/' + districtId + ',' + clinicTypeId)
    }
    
    public fetchDistrictById(id) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'district/' + id)
    }

    public fetchDataForFin02bCreateList(filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-history-fin-02b/fin-02b-create-list?stateId='+filter.stateId +'&circleId='+filter.circleId+'&districtId='+filter.districtId)
    }

    public fetchDataForFin02bCreateListOlder(filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-history-fin-02b/fin-02b-create-list-older?stateId='+filter.stateId +'&circleId='+filter.circleId+'&districtId='+filter.districtId)
    }


    public fetchDataForFin02bCreate(districtId, clinicTypeId, month, year) {
        return this.http.get<CimsHistoryFin02b>(this.serviceApiUrl + 'cims-history-fin-02b/districtid-clinictypeid/' + districtId + ',' + clinicTypeId + ',' + month + ',' + year)
    }

    public createFin02b(data,userId) {
        return this.http.post(this.serviceApiUrl + 'invoice/create-fin02b/'+userId, data);
    }

    public fetchDataForFin02bInProgress(invoiceTypeId, status,filter) {
       // return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-!status-stateid-districtid/' + invoiceTypeId + ',' + status + ',' + stateId  + ',' + districtId)
       return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-!status-stateid-districtid?invoiceTypeId='+invoiceTypeId+'&stateId='+filter.stateId +'&circleId='+filter.circleId+'&districtId='+filter.districtId+'&status='+status)
    }

    public fetchDataForFin02bInProgressOlder(invoiceTypeId, status,filter) {
       // return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-!status-older/' + invoiceTypeId + ',' + status + ',' + stateId + ',' + districtId)
       return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-!status-stateid-districtid-older?invoiceTypeId='+invoiceTypeId+'&stateId='+filter.stateId +'&circleId='+filter.circleId+'&districtId='+filter.districtId+'&status='+status)
    }


    public fetchDataForFin02bApprove(id) {
        return this.http.get<Fin02b>(this.serviceApiUrl + 'invoice/' + id)
    }

    public fetchDataForFin02bApproved(invoiceTypeId, status,filter) {
     //   return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-status/' + invoiceTypeId + ',' + status + ',' + stateId + ',' + districtId) 
     return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-status-stateid-districtid?invoiceTypeId='+invoiceTypeId+'&stateId='+filter.stateId +'&circleId='+filter.circleId+'&districtId='+filter.districtId+'&status='+status)
    }

    public fetchDataForFin02bApprovedOlder(invoiceTypeId, status,filter) {
    //    return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-status-older/' + invoiceTypeId + ',' + status + ',' + stateId + ',' + districtId)
    return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoicetypeid-status-stateid-districtid-older?invoiceTypeId='+invoiceTypeId+'&stateId='+filter.stateId +'&circleId='+filter.circleId+'&districtId='+filter.districtId+'&status='+status)
    }

    public updateFin02bStatus(invoice) {
        return this.http.put(this.serviceApiUrl + 'invoice/update-fin02b-status', invoice)
    }

    public findInvoiceSstPercentage(invoiceTypeId) {
        return this.http.get<any>(this.serviceApiUrl + 'invoice-type/sst-percentage/' + invoiceTypeId)
    }

    public fin0bInvoiceService() {
        return this.http.get<InvoiceCounter>(this.serviceApiUrl + 'invoice-counter/fin02b');
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
        return this.loadOptions(Fin02bInvoiceService.PLAYER_ONE);
    }
}