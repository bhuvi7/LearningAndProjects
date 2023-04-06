import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import "rxjs/add/operator/map";
import { environment } from '../../../../environments/environment';
import { Invoice } from "./model/invoice";
import { InvoiceCounter } from '../../home/transaction/fin-01-invoice/model/invoice-counter';
import { InvoiceGeneration } from './model/invoice-generation'
import { invoicePenalties } from './model/invoice-penalties';
@Injectable()
export class DashboardProgressService {


  serviceApiUrl: string = environment.serviceApiUrl;

  constructor(private http: HttpClient) {

  }
  public fetchAllReceivables(retention: string) {
    return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/receivable/' + retention);
  }

  public fetchAllReceivableByStateName(retention: string, stateName: string) {
    return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/receivable-state-name/' + retention + '/' + stateName);
  }
  public fetchAllReceivableByCircleCode(retention: string, circleCode: string) {
    return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/receivable-circle-code/' + retention + '/' + circleCode);
  }


  public fetchAllReceivableByDistrictName(retention: string, districtName: string) {
    return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/receivable-district-name/' + retention + '/' + districtName);
  }

  public fetchAllReceivableByInvoiceType(retention: string, key: string, value: string) {
    return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/receivable-invoice/' + retention + '/' + key + '/' + value);
  }

  public fetchAllReceivableByQuater(retention: string, key: string, value: string) {
    return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/receivable-quater/' + retention + '/' + key + '/' + value);
  }

  public fetchAllRetention() {
    return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/retention');
  }

  public fetchAllRetentionByStateName(stateName: string) {
    return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/retention-state-name/' + stateName);
  }
  public fetchAllRetentionByCircleCode(circleCode: string) {
    return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/retention-circle-code/' + circleCode);
  }

  //mine start

  public fetchAllUnadjustedPenalty(status: string) {
    return this.http.get<Array<invoicePenalties>>(this.serviceApiUrl + 'fin-09/!status/' + status);
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

  //for quaters
  public fetchAllUnadjustedPenaltyQuaterQuater(key: string, value: string, status: string) {
    return this.http.get<Array<invoicePenalties>>(this.serviceApiUrl + 'fin-09/Unadjusted-Penality-ByQuaterQuater/' + key + '/' + value + '/' + status);
  }
  //end

  public fetchAllUnadjustedPenalityByQuaterMonth(key: string, value: string, quater: string, year: string, status: string) {
    return this.http.get<Array<invoicePenalties>>(this.serviceApiUrl + 'fin-09/Unadjusted-Penality-ByQuater-Month/' + key + '/' + value + '/' + quater + '/' + year + '/' + status);
  }

  public fetchAllRetentionByDistrictName(districtName: string) {
    return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/retention-district-name/' + districtName);
  }

  public fetchAllRetentionByInvoiceType(key: string, value: string) {
    return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/retention-invoice/' + key + '/' + value);
  }

  public fetchAllRetentionByQuater(key: string, value: string) {
    return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/retention-quater/' + key + '/' + value);
  }


  public fetchAllRevenueAnalysisLastQuater() {
    return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/ra-last-quater');
  }

  public fetchAllRevenueAnalysisLastQuaterByStateName(stateName: string) {
    return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/ra-last-quater-state-name/' + stateName);
  }

  public fetchAllRevenueAnalysisLastQuaterByCircleCode(circleCode: string) {
    return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/ra-last-quater-circle-code/' + circleCode);
  }
  public fetchAllRevenueAnalysisLastQuaterByDistrictName(districtName: string) {
    return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/ra-last-quater-district-name/' + districtName);
  }

  public fetchAllRevenueAnalysisLastQuaterByInvoiceType(key: string, value: string) {
    return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/ra-last-quater-invoice/' + key + '/' + value);
  }

  public fetchAllRevenueAnalysisLastQuaterByQuater(key: string, value: string) {
    return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/ra-last-quater-quater/' + key + '/' + value);
  }

  public fetchDataByInvoiceTypeId(invoiceTypeId) {
    return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/invoiceTypeId/' + invoiceTypeId)
  }

  public fetchAllInvoiceFilterForFin09(stateName: string, circleCode: string, districtName: string, clinicType: string) {
    return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'fin09/filter-for-fin09?stateName=' + stateName + "&circleCode=" + circleCode + "&districtName=" + districtName + "&clinicTypeId=" + clinicType)
  }
  public fetchAllInvoiceFilter(stateName: string, circleCode: string, districtName: string, invoiceTypeName: string, clinicType: string) {
    return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/filter?stateName=' + stateName + "&circleCode=" + circleCode + "&districtName=" + districtName + "&invoiceTypeName=" + invoiceTypeName + "&clinicTypeId=" + clinicType)
  }

  public fetchAllInvoiceFilterByYear(stateName: string, circleCode: string, districtName: string, invoiceTypeName: string, clinicType: string, year: string) {
    return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/filterYear?stateName=' + stateName + "&circleCode=" + circleCode + "&districtName=" + districtName + "&invoiceTypeName=" + invoiceTypeName + "&clinicTypeId=" + clinicType + "&year=" + year)
  }

  public fetchInvoiceById(id) {
    return this.http.get<Invoice>(this.serviceApiUrl + 'invoice/' + id)
  }

  public fetchInvoiceByIdForRA(id) {
    return this.http.get<Invoice>(this.serviceApiUrl + 'invoice/find-id-RA/' + id)
  }

  public fetchAllReceivableByMonth(retention: string, key: string, value: string, quater: string, year: string) {
    return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/receivable-month/' + retention + '/' + key + '/' + value + '/' + quater + '/' + year);
  }

  public fetchAllRetentionByMonth(key: string, value: string, quater: string, year: string) {
    return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/retention-month/' + key + '/' + value + '/' + quater + '/' + year);
  }

  public fetchAllRevenueAnalysisLastQuaterByMonth(key: string, value: string, quater: string, year: string) {
    return this.http.get<Array<Invoice>>(this.serviceApiUrl + 'invoice/ra-last-quater-month/' + key + '/' + value + '/' + quater + '/' + year);
  }


  public getMonthName(month: number) {
    const monthNames = ["", "January", "February", "March", "April", "May", "June",
      "July", "August", "September", "October", "November", "December"
    ];
    return monthNames[month];

  }

  public fetchAllInvoiceType() {
    return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice-type/all')
  }

  public getInvoiceByMonthWise(stateName: string, circleCode: string, districtName: string, quater: string, month: string, year: string) {
    let url = "?quater=" + quater + "&month=" + month + "&year=" + year;
    if (stateName != "") {
      url = url + "&stateName=" + stateName;
    }
    if (districtName != "") {
      url = url + "&districtName=" + districtName;
    }
    if (circleCode != "") {
      url = url + "&circleCode=" + circleCode;
    }

    // return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/monthwise/?stateName=' + stateName + "&districtName=" + districtName + "&circleCode=" + circleCode  );
    return this.http.get<Array<any>>(this.serviceApiUrl + 'invoice/monthwise/' + url);
  }

  // public getFin01InvoiceCounter(invoiceCounterRegion) {
  //   return this.http.post<InvoiceCounter>(this.serviceApiUrl + 'invoice-counter/fin01',invoiceCounterRegion);
  // }
  // public getFin02bInvoiceCounter(invoiceCounterRegion) {
  //   return this.http.post<InvoiceCounter>(this.serviceApiUrl + 'invoice-counter/fin02b',invoiceCounterRegion);
  // }
  // public getFin02aInvoiceCounter(invoiceCounterRegion) {
  //   return this.http.post<InvoiceCounter>(this.serviceApiUrl + 'invoice-counter/fin02a',invoiceCounterRegion);
  // }
  // public getFin02InvoiceCounter(invoiceCounterRegion) {
  //   return this.http.post<InvoiceCounter>(this.serviceApiUrl + 'invoice-counter/fin02',invoiceCounterRegion);
  // }
  public getFin01InvoiceCounter() {
    return this.http.get<InvoiceCounter>(this.serviceApiUrl + 'invoice-counter/fin01');
  }
  public getFin02bInvoiceCounter() {
    return this.http.get<InvoiceCounter>(this.serviceApiUrl + 'invoice-counter/fin02b');
  }
  public getFin02aInvoiceCounter() {
    return this.http.get<InvoiceCounter>(this.serviceApiUrl + 'invoice-counter/fin02a');
  }
  public getFin02InvoiceCounter() {
    return this.http.get<InvoiceCounter>(this.serviceApiUrl + 'invoice-counter/fin02');
  }

  public getInvoiceGenerationFin06Intitate(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-01/invoice-generation-fin06-initiate' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getInvoiceGenerationFin06Inprogress(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-01/invoice-generation-fin06-inprogress' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getInvoiceGenerationFin06Approved(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-01/invoice-generation-fin06-approved' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getInvoiceGenerationFin01Intitate(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-01/invoice-generation-fin01-initiate' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getInvoiceGenerationFin01Inprogress(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-01/invoice-generation-fin01-inprogress' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getInvoiceGenerationFin01Approved(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-01/invoice-generation-fin01-approved' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getInvoiceGenerationFin01InvIntitate(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-01/invoice-generation-fin01-inv-initiate' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getInvoiceGenerationFin01InvInprogress(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-01/invoice-generation-fin01-inv-inprogress' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getInvoiceGenerationFin01InvApproved(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-01/invoice-generation-fin01-inv-approved' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }




  public getInvoiceGenerationFin07Intitate(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-02a/invoice-generation-fin07-initiate' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getInvoiceGenerationFin07Inprogress(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-02a/invoice-generation-fin07-inprogress' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getInvoiceGenerationFin07Approved(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-02a/invoice-generation-fin07-approved' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getInvoiceGenerationFin03aIntitate(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-02a/invoice-generation-fin03a-initiate' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getInvoiceGenerationFin03aInprogress(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-02a/invoice-generation-fin03a-inprogress' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getInvoiceGenerationFin03aApproved(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-02a/invoice-generation-fin03a-approved' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getInvoiceGenerationFin02aInvIntitate(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-02a/invoice-generation-fin02a-inv-initiate' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getInvoiceGenerationFin02aInvInprogress(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-02a/invoice-generation-fin02a-inv-inprogress' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getInvoiceGenerationFin02aInvApproved(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-02a/invoice-generation-fin02a-inv-approved' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getInvoiceGenerationFin08bIntitate(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin08b-initiate' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getInvoiceGenerationFin08bInprogress(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin08b-inprogress' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getInvoiceGenerationFin08bApproved(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin08b-approved' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }


  public getInvoiceGenerationFin08cIntitate(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin08c-initiate' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getInvoiceGenerationFin08cInprogress(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin08c-inprogress' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getInvoiceGenerationFin08cApproved(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin08c-approved' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }


  public getInvoiceGenerationFin08Intitate(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin08-initiate' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getInvoiceGenerationFin08Inprogress(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin08-inprogress' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getInvoiceGenerationFin08Approved(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin08-approved' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }


  public getInvoiceGenerationFin03Intitate(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin03-initiate' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getInvoiceGenerationFin03Inprogress(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin03-inprogress' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getInvoiceGenerationFin03Approved(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin03-approved' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getInvoiceGenerationFin02Intitate(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin02-initiate' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getInvoiceGenerationFin02Inprogress(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin02-inprogress' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getInvoiceGenerationFin02Approved(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin02-approved' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }


  public getInvoiceGenerationFin02bInvIntitate(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02b/invoice-generation-fin02b-initiate' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getInvoiceGenerationFin02bInvInprogress(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02b/invoice-generation-fin02b-inprogress' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getInvoiceGenerationFin02bInvApproved(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02b/invoice-generation-fin02b-approved' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }






  public getBacklogFin06Intitate(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-01/invoice-generation-fin06-initiate-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getBacklogFin06Inprogress(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-01/invoice-generation-fin06-inprogress-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getBacklogFin06Approved(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-01/invoice-generation-fin06-approved-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getBacklogFin01Intitate(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-01/invoice-generation-fin01-initiate-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getBacklogFin01Inprogress(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-01/invoice-generation-fin01-inprogress-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getBacklogFin01Approved(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-01/invoice-generation-fin01-approved-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getBacklogFin01InvIntitate(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-01/invoice-generation-fin01-inv-initiate-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getBacklogFin01InvInprogress(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-01/invoice-generation-fin01-inv-inprogress-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getBacklogFin01InvApproved(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-01/invoice-generation-fin01-inv-approved-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }



  public getBacklogFin07IntitateOlder(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-02a/invoice-generation-fin07-initiate-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getBacklogFin07InprogressOlder(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-02a/invoice-generation-fin07-inprogress-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getBacklogFin07ApprovedOlder(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-02a/invoice-generation-fin07-approved-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getBacklogFin03aIntitateOlder(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-02a/invoice-generation-fin03a-initiate-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getBacklogFin03aInprogressOlder(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-02a/invoice-generation-fin03a-inprogress-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getBacklogFin03aApprovedOlder(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-02a/invoice-generation-fin03a-approved-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getBacklogFin02aInvIntitateOlder(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-02a/invoice-generation-fin02a-inv-initiate-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getBacklogFin02aInvInprogressOlder(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-02a/invoice-generation-fin02a-inv-inprogress-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getBacklogFin02aInvApprovedOlder(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-histroy-fin-02a/invoice-generation-fin02a-inv-approved-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getBacklogFin08bIntitate(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin08b-initiate-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getBacklogFin08bInprogress(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin08b-inprogress-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getBacklogFin08bApproved(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin08b-approved-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }


  public getBacklogFin08cIntitate(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin08c-initiate-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getBacklogFin08cInprogress(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin08c-inprogress-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getBacklogFin08cApproved(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin08c-approved-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }


  public getBacklogFin08Intitate(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin08-initiate-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getBacklogFin08Inprogress(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin08-inprogress-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getBacklogFin08Approved(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin08-approved-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }


  public getBacklogFin03Intitate(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin03-initiate-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getBacklogFin03Inprogress(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin03-inprogress-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getBacklogFin03Approved(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin03-approved-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getBacklogFin02Intitate(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin02-initiate-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getBacklogFin02Inprogress(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin02-inprogress-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getBacklogFin02Approved(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02/invoice-generation-fin02-approved-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }


  public getBacklogFin02bInvIntitate(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02b/invoice-generation-fin02b-initiate-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }

  public getBacklogFin02bInvInprogress(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02b/invoice-generation-fin02b-inprogress-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }
  public getBacklogFin02bInvApproved(month, year, stateId, districtId) {
    return this.http.get<Array<InvoiceGeneration>>(this.serviceApiUrl + 'cims-history-fin-02b/invoice-generation-fin02b-approved-older' + '/' + month + '/' + year + '/' + stateId + '/' + districtId);
  }


}
