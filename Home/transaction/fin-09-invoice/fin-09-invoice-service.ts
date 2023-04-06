import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../../environments/environment';
import { IOption } from './model/IOption';
import { invoicePenalties } from "../../dashboard-progress/model/invoice-penalties";


@Injectable()
export class Fin09InvoiceService {

  serviceApiUrl: string = environment.serviceApiUrl;

  constructor(private http: HttpClient) { }

  public fetchStateDetails() {
    return this.http.get<Array<any>>(this.serviceApiUrl + 'state/all')
  }

  //mine start

  public fetchAllInvoiceById(invoice) {
    return this.http.get<Array<any>>(this.serviceApiUrl + 'fin-09/' + invoice);
  }

  //   public fetchAllInvoiceById(invoiceId) {
  //     return this.http.get<Invoice>(this.serviceApiUrl + 'invoice/' + invoiceId)
  // }

  public fetchAllUnadjustedPenalty(status: string) {
    return this.http.get<Array<invoicePenalties>>(this.serviceApiUrl + 'fin-09/!status/' + status);
  }

  public fetchAllPenaltyForList(stateName: string, districtName: string, clinicType: string, approvalQuater: string, approvalYear: string) {
    return this.http.get<Array<invoicePenalties>>(this.serviceApiUrl + 'fin-09/filter/all/Fin09?stateName=' + stateName +
      "&districtName=" + districtName + "&clinicTypeId=" + clinicType + "&approvalQuater=" + approvalQuater + "&approvalYear=" + approvalYear);
  }

  public fetchAllUnadjustedPenaltyMultiple(stateName: string, districtName: string, clinicType: string, approvalQuater: string, approvalYear: string) {
    return this.http.get<Array<invoicePenalties>>(this.serviceApiUrl + 'fin-09/filter/!status?stateName=' + stateName +
      "&districtName=" + districtName + "&clinicTypeId=" + clinicType + "&approvalQuater=" + approvalQuater + "&approvalYear=" + approvalYear);
  }

  //   public fetchAllInvoiceFilter1(stateName: string, districtName: string, clinicType: string) {
  //     return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'fin-09/filter/!status?stateName=' + stateName + "&districtName=" + districtName + "&clinicTypeId=" + clinicType)
  // }

  public fetchAllUnadjustedPenaltyClinic(status: string, clinicTypeId: string) {
    return this.http.get<Array<invoicePenalties>>(this.serviceApiUrl + 'fin-09/!status-clinic/' + status + '/' + clinicTypeId);
  }
  public fetchAllUnadjustedPenaltyStatus(status: string) {
    return this.http.get<Array<invoicePenalties>>(this.serviceApiUrl + 'fin-09/unadjusted-penality-all/' + status);
  }

  public fetchAllUnadjustedPenaltyStates(stateName: string, status: string) {
    return this.http.get<Array<invoicePenalties>>(this.serviceApiUrl + 'fin-09/unadjusted-penality-state-name/' + stateName + '/' + status);
  }

  public fetchAllUnadjustedPenaltyCircle(circleCode: string, status: string) {
    return this.http.get<Array<invoicePenalties>>(this.serviceApiUrl + 'fin-09/unadjusted-penality-circle-code/' + circleCode + '/' + status);
  }

  public fetchAllUnadjustedPenaltyDistrict(stateName: string, circleCode: string, districtName: string, status: string) {
    return this.http.get<Array<invoicePenalties>>(this.serviceApiUrl + 'fin-09/unadjusted-penality-district-name/' + stateName + '/' + circleCode + '/' + districtName + '/' + status);
  }
  //for fin09

  public fetchAllStateAndDistrictAndStatusNot(stateName: string, districtName: string, status: string) {
    return this.http.get<Array<invoicePenalties>>(this.serviceApiUrl + 'fin-09/findBy-state-district-!status/' + stateName + '/' + districtName + '/' + status);
  }

  public fetchAllStateAndDistrictAndClinicTypeStatusNot(stateName: string, districtName: string, clinicTypeId: string, status: string) {
    return this.http.get<Array<invoicePenalties>>(this.serviceApiUrl + 'fin-09/findBy-state-district-clinicType-!status/' + stateName + '/' + districtName + '/' + clinicTypeId + '/' + status);
  }
  public fetchAllStateAndStatusNot(stateName: string, status: string) {
    return this.http.get<Array<invoicePenalties>>(this.serviceApiUrl + 'fin-09/findBy-state-!status/' + stateName + '/' + status);
  }
  //for fin09 created list

  public fetchAllById(id: string) {
    return this.http.get<Array<invoicePenalties>>(this.serviceApiUrl + 'fin-09/findBy-id/' + id);
  }

  //for quaters
  public fetchAllUnadjustedPenaltyQuaterQuater(key: string, value: string, status: string) {
    return this.http.get<Array<invoicePenalties>>(this.serviceApiUrl + 'fin-09/Unadjusted-Penality-ByQuaterQuater/' + key + '/' + value + '/' + status);
  }
  public fetchAllUnadjustedPenalityByQuaterMonth(key: string, value: string, quater: string, year: string, status: string) {
    return this.http.get<Array<invoicePenalties>>(this.serviceApiUrl + 'fin-09/Unadjusted-Penality-ByQuater-Month/' + key + '/' + value + '/' + quater + '/' + year + '/' + status);
  }
  //end

  public fetchClinicTypeDetails() {
    return this.http.get<Array<any>>(this.serviceApiUrl + 'clinic-type/all')
  }

  public fetchClinicsDetails(districtId, clinicTypeId) {
    return this.http.get<Array<any>>(this.serviceApiUrl + 'clinic/districtid-clinictypeid/' + districtId + ',' + clinicTypeId)
  }

  public createFin09(fin09ABCDE) {
    return this.http.post<any>(this.serviceApiUrl + 'fin-09/create-fin09', fin09ABCDE)
  }

  public fetchDataForFin09InProgress(status) {
    return this.http.get(this.serviceApiUrl + 'fin-09/!status/' + status)
  }

  public fetchDataForFin09Approve(id) {
    return this.http.get<any>(this.serviceApiUrl + 'fin-09/' + id)
  }

  public fetchDataForFin09Approved(status) {
    return this.http.get(this.serviceApiUrl + 'fin-09/status/' + status)
  }

  public updateFin09Status(fin09) {
    return this.http.put(this.serviceApiUrl + 'fin-09/update-status', fin09)
  }

  public allFin09() {
    return this.http.get(this.serviceApiUrl + 'fin-09/all')
  }
  public allFin09ById(id) {
    return this.http.get<any>(this.serviceApiUrl + 'fin-09/all' + id)
  }

  public cloneOptions(options: Array<IOption>): Array<IOption> {
    return options.map(option => ({ value: option.value, label: option.label }));
  }

  getCharacters(array): Array<IOption> {
    return this.cloneOptions(array);
  }

}