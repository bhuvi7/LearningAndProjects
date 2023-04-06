import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import "rxjs/add/operator/map";
import { environment } from '../../../../environments/environment';
import { InvoiceType } from './model/invoice-type';
import { District } from './model/district';
import { Clinic } from './model/clinic';
import { State } from './model/state';
import { Circle } from './model/circle';

@Injectable()
export class MasterService {

    serviceApiUrl: string = environment.serviceApiUrl;

    constructor(private http: HttpClient) {

    }

    getAllInvoiceTypes() {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice-type/all')
    }

    getInvoiceTypeById(id) {
        return this.http.get<InvoiceType>(this.serviceApiUrl + 'invoice-type/' + id)
    }

    addInvoiceType(invoiceType) {
        return this.http.post(this.serviceApiUrl + 'invoice-type/add', invoiceType);
    }

    updateInvoiceType(invoiceType) {
        return this.http.put(this.serviceApiUrl + 'invoice-type/update', invoiceType)
    }

    getAllDistrict() {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'district/all')
    }

    getDistrictById(id) {
        return this.http.get<District>(this.serviceApiUrl + 'district/' + id)
    }

    addDistrict(district) {
        return this.http.post(this.serviceApiUrl + 'district/add', district);
    }

    updateDistrict(district) {
        return this.http.put(this.serviceApiUrl + 'district/update', district)
    }
    getAllClinic() {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'clinic/all')
    }

    getClinicById(id) {
        return this.http.get<Clinic>(this.serviceApiUrl + 'clinic/' + id)
    }

    addClinic(clinic) {
        return this.http.post(this.serviceApiUrl + 'clinic/add', clinic);
    }

    updateClinic(clinic) {
        return this.http.put(this.serviceApiUrl + 'clinic/update', clinic)
    }
    getAllState() {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'state/all')
    }

    getStateById(id) {
        return this.http.get<State>(this.serviceApiUrl + 'state/' + id)
    }

    addState(state) {
        return this.http.post(this.serviceApiUrl + 'state/add', state);
    }

    updateState(state) {
        return this.http.put(this.serviceApiUrl + 'state/update', state)
    }
    getAllCircle() {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'circle/all')
    }

    getCircleById(id) {
        return this.http.get<Circle>(this.serviceApiUrl + 'circle/' + id)
    }

    addCircle(circle) {
        return this.http.post(this.serviceApiUrl + 'circle/add', circle);
    }

    updateCircle(circle) {
        return this.http.put(this.serviceApiUrl + 'circle/update', circle)
    }

}