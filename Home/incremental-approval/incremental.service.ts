import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import "rxjs/add/operator/map";
import { environment } from '../../../../environments/environment'


@Injectable()
export class IncrementalService {

    serviceApiUrl: string = environment.serviceApiUrl;
    public  fileComment:string;
    public  fileFlag:string;

    constructor(private http: HttpClient) {

    }
     putCommentAndFlag(comment,flag){
        this.fileComment = comment;
        
        this.fileFlag = flag
        
        }
    
    getcomment(){
       
       return "dshfdkjfkjf"
    //    return this.fileComment;      
    }
    getflag()
    {
        
        return "sdkjsjd"
        // return this.fileFlag;
    }
    

    fetchStateDetails()  {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'state/all')
    }
    fetchDistrictDetails()  {
        return this.http.get<Array<any>>(this.serviceApiUrl + 'district/all')
    }
    updateDistrict(district)  {
        return this.http.put(this.serviceApiUrl + 'district/update' , district)
    }
    updateState(state)  {
        return this.http.put(this.serviceApiUrl + 'state/update' , state)
    }
    fetchClinicDetails()    {
        return this.http.get(this.serviceApiUrl + 'clinic/all')
    }
    updateClinic(clinic)  {
        return this.http.put(this.serviceApiUrl + 'clinic/update' , clinic)
    }
    // getStateById(id) {
    //     return this.http.get<State>(this.serviceApiUrl + 'state/' + id)
    // }
    // getDistrictById(id) {
    //     return this.http.get<District>(this.serviceApiUrl + 'district/' + id)
    // }
    // getClinicById(id) {
    //     return this.http.get<Clinic>(this.serviceApiUrl + 'clinic/' + id)
    // }


    // getAllInvoiceTypes() {
    //     return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice-type/all')
    // }

    // getInvoiceTypeById(id) {
    //     return this.http.get<InvoiceType>(this.serviceApiUrl + 'invoice-type/' + id)
    // }

    // addInvoiceType(invoiceType) {
    //     return this.http.post(this.serviceApiUrl + 'invoice-type/add', invoiceType);
    // }

    // updateInvoiceType(invoiceType) {
    //     return this.http.put(this.serviceApiUrl + 'invoice-type/update', invoiceType)
    // }

}