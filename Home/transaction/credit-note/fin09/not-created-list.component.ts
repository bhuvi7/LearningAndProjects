import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';
//import { environment } from '../../../../../../../environments/environment';
// import { toWords } from 'number-to-words';
//import { dateformat } from 'dateformat';
//import { Fin01InvoiceService } from "../../fin-01-invoice-service";
import { element } from 'protractor';

var dateFormat = require('dateformat');

@Component({
    selector: 'fin09-credit-note-not-created-list',
    templateUrl: './not-created-list.component.html',

})

export class Fin09NotCreatedListComponent implements OnInit {

    dtOptions: DataTables.Settings = {
        pagingType: 'full_numbers',
        pageLength: 10
    };
    //dtTrigger: Subject<any> = new Subject();

    public propsData = window.history.state.data ? window.history.state.data[0].clinicCode : 0;
    public listDatas: any;
    public listDatas1: any;
    public buttonEnableBoolean: Boolean;
    public selectedValue: Boolean;
    public createData: any;

    constructor(private router: Router,
        // private fin06Service: Fin01InvoiceService, 
        private http: HttpClient) { }

    ngOnInit() {
        this.listDatas = []
        this.listDatas1 = []
        // this.fin06Service.fetchAll().subscribe(data => {
        let x: any = [
            {
                "id": 8,
                "fin09RefNo": "FIN09-001",
                "beGeneralName": "Acrylic Curing Units",
                "beNumber": "16-353N",
                "serialNo": null,
                "modelNo": "MANFREDI A614.80CRYDING 12",
                "purchaseCost": "614.80",
                "certificateTcRef": null,
                "totalPurchaseCost": null,
                "totalNo": null,
                "installedDate": "2020-01-01T05:30:00",
                "purchasedDate": "2017-05-31T05:30:00",
                "acceptedDate": "2017-06-01T05:30:00",
                "stateName": "JOHOR",
                "districtName": "KLUANG",
                "clinicName": "Klinik Pergigian Di Klinik Kesihatan Simpang Renggam",
                "clinicCode": "JHR452",
                "clinicType": "KESIHATAN",
                "year": "2020",
                "month": "01",
                "curentStatus": null,
                "isModified": null,
                "createdBy": null,
                "createdDate": "2020-06-17 17:14:55",
                "modifiedBy": null,
                "modifiedDate": "2020-06-17 17:14:55",
                "isEnabled": "Y",
                "downloadedDate": "2020-06-17 17:14:55",
                "tSdFin06Id": null,
                "tSdFin01Id": null,
                "tInvFin01Id": null,
                "selected": false
            },
            {
                "id": 14,
                "fin09RefNo": "FIN09-002",
                "beGeneralName": "Acrylic Curing Units",
                "beNumber": "16-353N1",
                "serialNo": null,
                "modelNo": "MANFREDI ACRYDING 12",
                "purchaseCost": "678.40",
                "certificateTcRef": null,
                "totalPurchaseCost": null,
                "totalNo": null,
                "installedDate": "2020-02-01T05:30:00",
                "purchasedDate": "2017-05-31T05:30:00",
                "acceptedDate": "2017-06-01T05:30:00",
                "stateName": "JOHOR",
                "districtName": "KLUANG",
                "clinicName": "Klinik Pergigian Di Klinik Kesihatan Simpang Renggam",
                "clinicCode": "JHR452",
                "clinicType": "KESIHATAN",
                "year": "2020",
                "month": "02",
                "curentStatus": null,
                "isModified": null,
                "createdBy": null,
                "createdDate": "2020-06-17 17:14:55",
                "modifiedBy": null,
                "modifiedDate": "2020-06-17 17:14:55",
                "isEnabled": "Y",
                "downloadedDate": "2020-06-17 17:14:55",
                "tSdFin06Id": null,
                "tSdFin01Id": null,
                "tInvFin01Id": null,
                "selected": false
            },
            {
                "id": 9,
                "fin09RefNo": "FIN09-003",
                "beGeneralName": "Acrylic Curing Units",
                "beNumber": "16-353N",
                "serialNo": null,
                "modelNo": "MANFREDI ACRYDING 12",
                "purchaseCost": "954.00",
                "certificateTcRef": null,
                "totalPurchaseCost": null,
                "totalNo": null,
                "installedDate": "2020-03-01T05:30:00",
                "purchasedDate": "2017-05-31T05:30:00",
                "acceptedDate": "2017-06-01T05:30:00",
                "stateName": "JOHOR",
                "districtName": "KLUANG",
                "clinicName": "Klinik Pergigian Di Klinik Kesihatan Jalan Mengkibol",
                "clinicCode": "JHR455",
                "clinicType": "KESIHATAN",
                "year": "2020",
                "month": "03",
                "curentStatus": null,
                "isModified": null,
                "createdBy": null,
                "createdDate": "2020-06-17 17:20:06",
                "modifiedBy": null,
                "modifiedDate": "2020-06-17 17:20:06",
                "isEnabled": "Y",
                "downloadedDate": "2020-06-17 17:20:06",
                "tSdFin06Id": null,
                "tSdFin01Id": null,
                "tInvFin01Id": null,
                "selected": false
            }
        ]
        x.forEach(element => {
            if (this.propsData !== element.clinicCode) {
                element.monthYear = element.month + '/' + element.year
                element.selected = false
                element.installedDate = dateFormat(element.installedDate, 'dd-mm-yyyy')
                element.purchasedDate = dateFormat(element.purchasedDate, 'dd-mm-yyyy')
                element.acceptedDate = dateFormat(element.acceptedDate, 'dd-mm-yyyy')
                this.listDatas.push(element)
            }
        });
        this.listDatas1 = this.listDatas.slice(0, 2);
        // this.dtTrigger.next()
        // })
        // this.fetchAll()
        this.buttonEnableBoolean = true
    }

    // private async fetchAll() {
    //     const promise: any = await this.http.get(environment.serviceApiUrl + 'fin-01-inv-master/all').toPromise();
    //     this.listDatas = []
    //     promise.forEach(element => {
    //         if (this.propsData !== element.id) {
    //             element.selected = false
    //             this.listDatas.push(element)
    //         }
    //     });
    // }

    buttonEnable(id) {
        this.createData = []
        this.listDatas.forEach(element => {
            if (element.id === id) {
                element.selected = true
                this.createData.push(element)
            } else {
                element.selected = false
            }
        });
        this.buttonEnableBoolean = false
    }

    // buttonEnableValue() {
    //     return this.buttonEnableBoolean
    // }

    customRadio(id) {
        return "customRadio" + id
    }

    radioSelect() {
        let data = this.createData
        this.createData = []
        this.listDatas.forEach(element => {
            if (element.id === data[0].id) {
                this.createData.push(element)
            }
        })
        this.router.navigateByUrl('transaction/credit-note/fin09/credit-note-create', { state: { data: this.createData } });
    }
}