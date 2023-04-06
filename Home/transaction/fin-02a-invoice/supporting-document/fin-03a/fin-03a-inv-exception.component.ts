import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Fin01InvoiceService } from '../../../fin-01-invoice/fin-01-invoice-service';
import { element } from 'protractor';
// import { toWords } from 'number-to-words';

@Component({
    selector: 'fin-03a-inv-exception',
    templateUrl: './fin-03a-inv-exception.component.html'
})

export class Fin03aInvExceptionComponent implements OnInit {

    dtOptions: DataTables.Settings = {
        pagingType: 'full_numbers',
        pageLength: 10
    };
    //dtTrigger: Subject<any> = new Subject();

    public propsData = window.history.state.data ? window.history.state.data[0].id : 0;
    public listDatas: any;
    public listDatas1: any;
    public buttonEnableBoolean: Boolean;
    public selectedValue: Boolean;
    public createData: any;
    public invoiceType: string;
    public cardTitle: string;
    constructor(private router: Router) { }

    ngOnInit() {
        this.createData = []
        this.listDatas = []
        this.listDatas1 = []

        this.listDatas = [
            {
                "id": 8,
                "beGeneralName": "Acrylic Curing Units",
                "beNumber": "16-353N",
                "serialNo": null,
                "modelNo": "MANFREDI ACRYDING 12",
                "purchaseCost": "9700",
                "certificateTcRef": null,
                "totalPurchaseCost": null,
                "totalNo": null,
                "installedDate": "2017-06-01T05:30:00",
                "purchasedDate": "2017-05-31T05:30:00",
                "acceptedDate": "2017-06-01T05:30:00",
                "stateName": "JOHOR",
                "districtName": "KLUANG",
                "clinicName": "Klinik Pergigian Di Klinik Kesihatan Simpang Renggam",
                "clinicCode": "JHR452",
                "clinicType": "KESIHATAN",
                "year": "2020",
                "month": "03",
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
                "id": 11,
                "beGeneralName": "Air Polisher",
                "beNumber": "JHNAIP001",
                "serialNo": null,
                "modelNo": "AIR FLOW MASTER",
                "purchaseCost": "29000",
                "certificateTcRef": null,
                "totalPurchaseCost": null,
                "totalNo": null,
                "installedDate": "2017-11-30T05:30:00",
                "purchasedDate": "2017-12-01T05:30:00",
                "acceptedDate": "2017-12-01T05:30:00",
                "stateName": "JOHOR",
                "districtName": "JOHOR BAHRU",
                "clinicName": "Klinik Pergigian Di Klinik Kesihatan Kempas",
                "clinicCode": "JHR367",
                "clinicType": "KESIHATAN",
                "year": "2020",
                "month": "03",
                "curentStatus": null,
                "isModified": null,
                "createdBy": null,
                "createdDate": "2020-06-17 17:23:32",
                "modifiedBy": null,
                "modifiedDate": "2020-06-17 17:23:32",
                "isEnabled": "Y",
                "downloadedDate": "2020-06-17 17:23:32",
                "tSdFin06Id": null,
                "tSdFin01Id": null,
                "tInvFin01Id": null,
                "selected": false
            },
            {
                "id": 12,
                "beGeneralName": "Air Polisher",
                "beNumber": "JHNAIP001",
                "serialNo": null,
                "modelNo": "AIR FLOW MASTER",
                "purchaseCost": "29000",
                "certificateTcRef": null,
                "totalPurchaseCost": null,
                "totalNo": null,
                "installedDate": "2017-11-30T05:30:00",
                "purchasedDate": "2017-12-01T05:30:00",
                "acceptedDate": "2017-12-01T05:30:00",
                "stateName": "JOHOR",
                "districtName": "BATU PAHAT",
                "clinicName": "Klinik Pergigian Di Klinik Kesihatan Rengit",
                "clinicCode": "JHR428",
                "clinicType": "KESIHATAN",
                "year": "2020",
                "month": "03",
                "curentStatus": null,
                "isModified": null,
                "createdBy": null,
                "createdDate": "2020-06-17 17:24:30",
                "modifiedBy": null,
                "modifiedDate": "2020-06-17 17:24:30",
                "isEnabled": "Y",
                "downloadedDate": "2020-06-17 17:24:30",
                "tSdFin06Id": null,
                "tSdFin01Id": null,
                "tInvFin01Id": null,
                "selected": false
            },
            {
                "id": 13,
                "beGeneralName": "Hematology, Erythrocyte Aggregation",
                "beNumber": "JHNANA002",
                "serialNo": null,
                "modelNo": "LENA NE",
                "purchaseCost": "12800",
                "certificateTcRef": null,
                "totalPurchaseCost": null,
                "totalNo": null,
                "installedDate": "2017-06-01T05:30:00",
                "purchasedDate": "2017-05-30T05:30:00",
                "acceptedDate": "2017-06-01T05:30:00",
                "stateName": "MELAKA",
                "districtName": "ALOR GAJAH",
                "clinicName": "Klinik Kesihatan Hutan Percha",
                "clinicCode": "MLK004",
                "clinicType": "KESIHATAN",
                "year": "2020",
                "month": "03",
                "curentStatus": null,
                "isModified": null,
                "createdBy": null,
                "createdDate": "2020-06-17 17:27:20",
                "modifiedBy": null,
                "modifiedDate": "2020-06-17 17:27:20",
                "isEnabled": "Y",
                "downloadedDate": "2020-06-17 17:27:20",
                "tSdFin06Id": null,
                "tSdFin01Id": null,
                "tInvFin01Id": null,
                "selected": false
            }
        ]

        //this.dtTrigger.next()
        // })
        // this.fetchAll()
        this.buttonEnableBoolean = true
    }

    customRadio(id) {
        return "customRadio" + id
    }

    buttonEnable(id) {
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
    radioSelect() {

        this.router.navigateByUrl('transaction/fin-02a-invoice/fin-03a-create', { state: { data: this.createData } });
    }

}