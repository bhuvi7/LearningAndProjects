import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import "rxjs/add/operator/map";
import { environment } from '../../../../../../../environments/environment';
import { Observable } from 'rxjs';
import { IOption } from 'ng-select';

@Injectable()
export class Fin08InvoiceService {

    serviceApiUrl: string = environment.serviceApiUrl;

    constructor(private http: HttpClient) {

    }

    public fetchStateDetails() {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'state/all')
    }

    public fetchDataForFin08bCreateList(filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-history-fin-02/fin-08b-create-list?stateId='+filter.stateId +'&circleId='+filter.circleId+'&districtId='+filter.districtId)
    }

    public fetchDataForFin08bCreateListOlder(filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-history-fin-02/fin-08b-create-list-older?stateId='+filter.stateId +'&circleId='+filter.circleId+'&districtId='+filter.districtId)
    }

    public fetchDataForFin08bCreate(clinicId, month ,year) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-history-fin-02/clinicid-fin08b/' + clinicId + ',' + month +  ',' + year)
    }

    public createFin08b(data, userId) {
        return this.http.post<any>(this.serviceApiUrl + 'fin-08b/create-fin08b-chf2/' + userId, data)
    }

    public fetchDataForFin08bApprove(id) {
        return this.http.get<any>(this.serviceApiUrl + 'fin-08b/find-by-fin08b-id/' + id)
    }

    public fetchDataForFin08bInProgress(status,filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-08b/!status?stateId='+filter.stateId +'&circleId='+filter.circleId+'&districtId='+filter.districtId+'&status='+status)
    }

    public fetchDataForFin08bInProgressOlder(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-08b/!status-older?stateId='+filter.stateId +'&circleId='+filter.circleId+'&districtId='+filter.districtId+'&status='+status)
    }

    public fetchDataForFin08bApproved(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-08b/status?stateId='+filter.stateId +'&circleId='+filter.circleId+'&districtId='+filter.districtId+'&status='+status)
    }

    public fetchDataForFin08bApprovedOlder(status,filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-08b/status-older?stateId='+filter.stateId +'&circleId='+filter.circleId+'&districtId='+filter.districtId+'&status='+status)
    }

    public updateFin08bStatus(data) {
        return this.http.put(this.serviceApiUrl + 'fin-08b/update-status-chf2', data)
    }

    public updateFin08b(data) {
        return this.http.put(this.serviceApiUrl + 'fin-08b/update', data);
    }

    public fetchDataForFin08cCreateList(filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-history-fin-02/fin-08c-create-list?stateId='+filter.stateId +'&circleId='+filter.circleId+'&districtId='+filter.districtId)
    }

    public fetchDataForFin08cCreateListOlder(filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-history-fin-02/fin-08c-create-list-older?stateId='+filter.stateId +'&circleId='+filter.circleId+'&districtId='+filter.districtId)
    }

    public fetchDataForFin08cCreate(clinicId, month ,year ) {
        return this.http.get<Array<any>>(this.serviceApiUrl + '/cims-history-fin-02/clinicid-fin08c/' + clinicId + ',' + month + ',' + year)
    }

    public createFin08c(data, userId) {
        return this.http.post(this.serviceApiUrl + 'fin-08c/create-fin08c-chf2/' + userId, data)
    }

    public fetchDataForFin08cInProgress(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-08c/!status?stateId='+filter.stateId +'&circleId='+filter.circleId+'&districtId='+filter.districtId+'&status='+status)
    }

    public fetchDataForFin08cInProgressOlder(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-08c/!status-older?stateId='+filter.stateId +'&circleId='+filter.circleId+'&districtId='+filter.districtId+'&status='+status)
    }

    public fetchDataForFin08cApprove(id) {
        return this.http.get<any>(this.serviceApiUrl + 'fin-08c/find-by-fin08c-id/' + id)
    }

    public fetchDataForFin08cApproved(status,filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-08c/status?stateId='+filter.stateId +'&circleId='+filter.circleId+'&districtId='+filter.districtId+'&status='+status)
    }

    public fetchDataForFin08cApprovedOlder(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-08c/status-older?stateId='+filter.stateId +'&circleId='+filter.circleId+'&districtId='+filter.districtId+'&status='+status)
    }

    public updateFin08cStatus(data) {
        return this.http.put(this.serviceApiUrl + 'fin-08c/update-status-chf2', data)
    }

    public fetchDataForFin08CreateList(filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-history-fin-02/fin-08-create-list?stateId='+filter.stateId +'&circleId='+filter.circleId+'&districtId='+filter.districtId)
    }

    public fetchDataForFin08CreateListOlder(filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'cims-history-fin-02/fin-08-create-list-older?stateId='+filter.stateId +'&circleId='+filter.circleId+'&districtId='+filter.districtId)
    }

    // public fetchDataForFin08Create(clinicId) {
    //     return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-08/' + clinicId)
    // }

    public createFin08(clinicId, month, year, userId) {
        return this.http.post(this.serviceApiUrl + 'fin-08/create-fin08-chf2/' + clinicId + ',' + month + ',' + year + '/' + userId, null)
    }

    public fetchDataForFin08InProgress(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-08/!status?stateId='+filter.stateId +'&circleId='+filter.circleId+'&districtId='+filter.districtId+'&status='+status)
    }

    public fetchDataForFin08InProgressOlder(status,filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-08/!status-older?stateId='+filter.stateId +'&circleId='+filter.circleId+'&districtId='+filter.districtId+'&status='+status)
    }

    public fetchDataForFin08Approve(id) {
        return this.http.get<any>(this.serviceApiUrl + 'fin-08/' + id)
    }

    public fetchDataForFin08Approved(status,filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-08/status?stateId='+filter.stateId +'&circleId='+filter.circleId+'&districtId='+filter.districtId+'&status='+status)
    }

    public fetchDataForFin08ApprovedOlder(status, filter) {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-08/status-older?stateId='+filter.stateId +'&circleId='+filter.circleId+'&districtId='+filter.districtId+'&status='+status)
    }

    public updateFin08Status(data) {
        return this.http.put(this.serviceApiUrl + 'fin-08/update-status-chf2', data)
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
        return this.loadOptions(Fin08InvoiceService.PLAYER_ONE);
    }


}