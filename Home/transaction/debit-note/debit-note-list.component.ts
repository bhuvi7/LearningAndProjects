import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { PAIService } from './../payment-against-invoice/pai-service';
import * as dateFormat from 'dateformat';
import { Invoice } from './../payment-against-invoice/model/invoice';
import { InvoicePaymentHistory } from './../payment-against-invoice/model/invoice-payment-history';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';
//import { environment } from '../../../../../../../environments/environment';
// import { toWords } from 'number-to-words';
//import { dateformat } from 'dateformat';
//import { Fin01InvoiceService } from "../../fin-01-invoice-service";
import { element } from 'protractor';

var dateFormat = require('dateformat');

@Component({
    selector: 'debit-note-create-list',
    templateUrl: './debit-note-list.component.html',

})

export class DebitNoteListComponent implements OnInit {
    public invoice: Invoice;
  public invoicePayment: InvoicePaymentHistory;
  public loading: Boolean = true;
  public today = dateFormat(new Date(), 'yyyy-mm-dd');
  public disableSubmit: Boolean = false;
  public id: any
  public paiData:any;
  constructor(public router: Router, private route: ActivatedRoute, private paiService: PAIService) { }

  ngOnInit() {
    this.invoice = new Invoice();
    this.invoicePayment = new InvoicePaymentHistory();
    if (this.route.params['_value']['_id'] != "undefined") {
      this.route.params.switchMap((par: Params) => this.paiService.fetchForPAIUpdate(par['_id'])).subscribe(x => {
        this.invoice = x;
        
        if (this.invoice.invoiceDate == null) { this.invoice.invoiceDateDisplay = null }
        else {
          this.invoice.invoiceDateDisplay = dateFormat(this.invoice.invoiceDate, 'dd-mm-yyyy')
        }
        this.loading = false;
      })
    }
  
  }
 

  

  updateAsDraft() {
    delete this.invoice.fin06
    delete this.invoice.fin10b
    delete this.invoice.fin03a
    this.invoice.paymentStatus = 'PAYMENT-DRAFT'
    this.paiService.updateInvoice(this.invoice).subscribe(x => {
      this.router.navigateByUrl('transaction/debit-note/debit-note');
    })
  }



  save() {
   

    if (this.invoicePayment.paymentRefNo == undefined || this.invoicePayment.paymentReceived == 0 || this.invoicePayment.paymentRefNo == "") {
      Swal.fire('', 'Please fill all fields', 'error');
    }
    // else if(this.invoice.outstandingAmount===this.invoice.totalInvoiceValue ) {
      
    //   Swal.fire('', 'Collection Receiving Amount can not be adjusted', 'error');
     
    // }
    // else if (this.invoice.outstandingAmount+this.invoicePayment.paymentReceived > this.invoice.totalInvoiceValue ) {
    //   Swal.fire('', 'Collection Receiving Amount can not be more than Total Invoice Value', 'error');
    //   
    // }
   
    else {
      this.invoicePayment.invoiceNo = this.invoice.invoiceNo
      this.invoicePayment.paymentMode = "Debit Note"
      this.paiService.debitPayment(this.invoicePayment).subscribe(x => {
     
        Swal.fire('', 'Payment Received Successfully. Transaction Reference No - ' + x.transactionRefNo, 'success');
        history.back(); 
      })
      
      
      
      
    }



  }

  action() {
    this.router.navigateByUrl('transaction/debit-note/debit-note-create');
  }

}

//     dtOptions: DataTables.Settings = {
//         pagingType: 'full_numbers',
//         pageLength: 10
//     };
//     //dtTrigger: Subject<any> = new Subject();

//     public propsData = window.history.state.data ? window.history.state.data[0].clinicCode : 0;
//     public listDatas: any;
//     public listDatas1: any;
//     public buttonEnableBoolean: Boolean;
//     public selectedValue: Boolean;
//     public createData: any;

//     constructor(private router: Router,
//         // private fin06Service: Fin01InvoiceService, 
//         private http: HttpClient) { }

//     ngOnInit() {
//         this.listDatas = []
//         this.listDatas1 = []
//         // this.fin06Service.fetchAll().subscribe(data => {
//         let x: any = [
//             {
//                 "id": 8,
//                 "cnRefNo": "CN-001",
//                 "beGeneralName": "Acrylic Curing Units",
//                 "beNumber": "16-353N",
//                 "serialNo": null,
//                 "modelNo": "MANFREDI A614.80CRYDING 12",
//                 "purchaseCost": "614.80",
//                 "certificateTcRef": null,
//                 "totalPurchaseCost": null,
//                 "totalNo": null,
//                 "installedDate": "2020-01-01T05:30:00",
//                 "purchasedDate": "2017-05-31T05:30:00",
//                 "acceptedDate": "2017-06-01T05:30:00",
//                 "stateName": "JOHOR",
//                 "districtName": "KLUANG",
//                 "clinicName": "Klinik Pergigian Di Klinik Kesihatan Simpang Renggam",
//                 "clinicCode": "JHR452",
//                 "clinicType": "KESIHATAN",
//                 "year": "2020",
//                 "month": "01",
//                 "curentStatus": null,
//                 "isModified": null,
//                 "createdBy": null,
//                 "createdDate": "2020-06-17 17:14:55",
//                 "modifiedBy": null,
//                 "modifiedDate": "2020-06-17 17:14:55",
//                 "isEnabled": "Y",
//                 "downloadedDate": "2020-06-17 17:14:55",
//                 "tSdFin06Id": null,
//                 "tSdFin01Id": null,
//                 "tInvFin01Id": null,
//                 "selected": false
//             },
//             {
//                 "id": 14,
//                 "cnRefNo": "CN-002",
//                 "beGeneralName": "Acrylic Curing Units",
//                 "beNumber": "16-353N1",
//                 "serialNo": null,
//                 "modelNo": "MANFREDI ACRYDING 12",
//                 "purchaseCost": "678.40",
//                 "certificateTcRef": null,
//                 "totalPurchaseCost": null,
//                 "totalNo": null,
//                 "installedDate": "2020-02-01T05:30:00",
//                 "purchasedDate": "2017-05-31T05:30:00",
//                 "acceptedDate": "2017-06-01T05:30:00",
//                 "stateName": "JOHOR",
//                 "districtName": "KLUANG",
//                 "clinicName": "Klinik Pergigian Di Klinik Kesihatan Simpang Renggam",
//                 "clinicCode": "JHR452",
//                 "clinicType": "KESIHATAN",
//                 "year": "2020",
//                 "month": "02",
//                 "curentStatus": null,
//                 "isModified": null,
//                 "createdBy": null,
//                 "createdDate": "2020-06-17 17:14:55",
//                 "modifiedBy": null,
//                 "modifiedDate": "2020-06-17 17:14:55",
//                 "isEnabled": "Y",
//                 "downloadedDate": "2020-06-17 17:14:55",
//                 "tSdFin06Id": null,
//                 "tSdFin01Id": null,
//                 "tInvFin01Id": null,
//                 "selected": false
//             },
//             {
//                 "id": 9,
//                 "cnRefNo": "CN-003",
//                 "beGeneralName": "Acrylic Curing Units",
//                 "beNumber": "16-353N",
//                 "serialNo": null,
//                 "modelNo": "MANFREDI ACRYDING 12",
//                 "purchaseCost": "954.00",
//                 "certificateTcRef": null,
//                 "totalPurchaseCost": null,
//                 "totalNo": null,
//                 "installedDate": "2020-03-01T05:30:00",
//                 "purchasedDate": "2017-05-31T05:30:00",
//                 "acceptedDate": "2017-06-01T05:30:00",
//                 "stateName": "JOHOR",
//                 "districtName": "KLUANG",
//                 "clinicName": "Klinik Pergigian Di Klinik Kesihatan Jalan Mengkibol",
//                 "clinicCode": "JHR455",
//                 "clinicType": "KESIHATAN",
//                 "year": "2020",
//                 "month": "03",
//                 "curentStatus": null,
//                 "isModified": null,
//                 "createdBy": null,
//                 "createdDate": "2020-06-17 17:20:06",
//                 "modifiedBy": null,
//                 "modifiedDate": "2020-06-17 17:20:06",
//                 "isEnabled": "Y",
//                 "downloadedDate": "2020-06-17 17:20:06",
//                 "tSdFin06Id": null,
//                 "tSdFin01Id": null,
//                 "tInvFin01Id": null,
//                 "selected": false
//             }
//         ]
//         x.forEach(element => {
//             if (this.propsData !== element.clinicCode) {
//                 element.monthYear = element.month + '/' + element.year
//                 element.selected = false
//                 element.installedDate = dateFormat(element.installedDate, 'dd-mm-yyyy')
//                 element.purchasedDate = dateFormat(element.purchasedDate, 'dd-mm-yyyy')
//                 element.acceptedDate = dateFormat(element.acceptedDate, 'dd-mm-yyyy')
//                 this.listDatas.push(element)
//             }
//         });
//         this.listDatas1 = this.listDatas.slice(0, 2);
//         // this.dtTrigger.next()
//         // })
//         // this.fetchAll()
//         this.buttonEnableBoolean = true
//     }

//     // private async fetchAll() {
//     //     const promise: any = await this.http.get(environment.serviceApiUrl + 'fin-01-inv-master/all').toPromise();
//     //     this.listDatas = []
//     //     promise.forEach(element => {
//     //         if (this.propsData !== element.id) {
//     //             element.selected = false
//     //             this.listDatas.push(element)
//     //         }
//     //     });
//     // }

//     buttonEnable(id) {
//         this.createData = []
//         this.listDatas.forEach(element => {
//             if (element.id === id) {
//                 element.selected = true
//                 this.createData.push(element)
//             } else {
//                 element.selected = false
//             }
//         });
//         this.buttonEnableBoolean = false
//     }

//     // buttonEnableValue() {
//     //     return this.buttonEnableBoolean
//     // }

//     customRadio(id) {
//         return "customRadio" + id
//     }

//     radioSelect() {
//         let data = this.createData
//         this.createData = []
//         this.listDatas.forEach(element => {
//             if (element.id === data[0].id) {
//                 this.createData.push(element)
//             }
//         })
//         this.router.navigateByUrl('transaction/debit-note/debit-note-create', { state: { data: this.createData } });
//     }
// }