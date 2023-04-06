import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
//import { environment } from '../../../../../../../environments/environment';
//import { Fin01InvoiceService } from '../../fin-01-invoice-service';
// import { toWords } from 'number-to-words';

@Component({
    selector: 'credit-note-create',
    templateUrl: './credit-note-create.component.html',
    styleUrls: ['./credit-note-create.component.scss']
})

export class Fin09CreateComponent implements OnInit, OnDestroy {

    public enableSaveButton: Boolean = window.history.state.approvalPage ? true : false;

    public state = "MELEKA";
    public districtName = "ALOR GAJA";
    public clinicName = "KLINIK KEKSKJAKDJNCJ DJKJS DJNKSJ";
    public clinicCode = "MLK002";
    public monthYear;
    public docRef = "FIN06" + this.clinicCode + "-009";
    public date = "30/09/2019";
    public equipmentDatas: any;
    public equipmentCount = 0;
    public equipmentTotalValue = 0.00;
    public equipmentTotalValueInWords: String;

    constructor(private router: Router, private http: HttpClient,
        //private fin06service: Fin01InvoiceService
    ) { }

    ngOnInit() {
        if (window.history.state.data) {
            localStorage.setItem('pageData', JSON.stringify(window.history.state))
        }
        let data = JSON.parse(localStorage.getItem('pageData'));
        this.equipmentDatas = [
            { stateName: "MELEKA", districtName: "ALOR GAJA", clinicName: "KLINIK KEKSKJAKDJNCJ DJKJS DJNKSJ", clinicCode: "MLK002", month: "04", year: "2020", beGeneralName: "LIGHTS, INFRARED", beNumber: "MLIPLIF001", serialNo: "", modelNo: "Solmed Duo", purchaseCost: 6800.00, certificateTcRef: "MLIPLIF001" },
            { stateName: "MELEKA", districtName: "ALOR GAJA", clinicName: "KLINIK KEKSKJAKDJNCJ DJKJS DJNKSJ", clinicCode: "MLK002", month: "04", year: "2020", beGeneralName: "LIGHTS, INFRARED", beNumber: "MLIPLIF001", serialNo: "", modelNo: "Solmed Duo", purchaseCost: 6800.00, certificateTcRef: "MLIPLIF001" },
        ];
        //this.equipmentDatas = []
        //this.fin06service.fetchByClinicCode(data.data[0].clinicCode).subscribe(x => {
        this.equipmentDatas = data.data
        this.equipmentDatas.map(data => {
            data.purchaseCost = parseFloat(data.purchaseCost)
        })
        this.equipmentCount = this.equipmentDatas.length;
        this.equipmentDatas.map(data => {
            this.equipmentTotalValue += parseFloat(data.purchaseCost)
        })
        this.monthYear = this.equipmentDatas[0].month + "/" + this.equipmentDatas[0].year
        //})
        //this.processData();
        //this.invoiceService.sendGetRequest().subscribe(data => {
        // this.equipmentDatas = [
        //     { stateName: "MELEKA", districtName: "ALOR GAJA", clinicName: "KLINIK KEKSKJAKDJNCJ DJKJS DJNKSJ", clinicCode: "MLK002", month: "04", year: "2020", beGeneralName: "LIGHTS, INFRARED", beNumber: "MLIPLIF001", serialNo: "", modelNo: "Solmed Duo", purchaseCost: 6800.00, certificateTcRef: "MLIPLIF001" },
        //     { stateName: "MELEKA", districtName: "ALOR GAJA", clinicName: "KLINIK KEKSKJAKDJNCJ DJKJS DJNKSJ", clinicCode: "MLK002", month: "04", year: "2020", beGeneralName: "LIGHTS, INFRARED", beNumber: "MLIPLIF001", serialNo: "", modelNo: "Solmed Duo", purchaseCost: 6800.00, certificateTcRef: "MLIPLIF001" },
        // ];
        // this.equipmentCount = this.equipmentDatas.length;
        // this.equipmentDatas.map(data => {
        //     this.equipmentTotalValue += parseFloat(data.purchaseCost)
        // })
        // this.monthYear = this.equipmentDatas[0].month + "/" + this.equipmentDatas[0].year
        // this.equipmentDatas = data
        // this.equipmentTotalValueInWords = "( Ringgit Malaysia : " + toWords(this.equipmentTotalValue) + " Only )"
        //})
    }

    ngOnDestroy() {
        localStorage.removeItem('pageData')
    }

    // async processData() {
    //     let data = JSON.parse(localStorage.getItem('pageData'));
    //     const promise: any = await this.http.get(environment.serviceApiUrl + "fin-01-inv-master/findByClinicCode/" + data.data.clinicCode).toPromise()
    //     this.equipmentDatas = promise
    //     this.equipmentDatas.map(data => {
    //         data.purchaseCost = parseFloat(data.purchaseCost)
    //     })
    //     this.equipmentCount = this.equipmentDatas.length;
    //     this.equipmentDatas.map(data => {
    //         this.equipmentTotalValue += parseFloat(data.purchaseCost)
    //     })
    //     this.monthYear = this.equipmentDatas[0].month + "/" + this.equipmentDatas[0].year
    // }

    createCreditNote() {
        // this.equipmentDatas.forEach(element => {
        //     element.fin_06_status = 'Created'
        //     this.http.post(environment.serviceApiUrl+'/fin-06-supp-doc/add',element)
        // });
        this.router.navigateByUrl('/transaction/credit-note/fin09/created-list',
            // { state: JSON.parse(localStorage.getItem('pageData')) }
        )
    }

    saveStatusToIA() {
        this.router.navigateByUrl('/transaction/fin-01-invoice/fin-06-list', { state: JSON.parse(localStorage.getItem('pageData')) })

    }

    navToList() {
        this.router.navigate(['/transaction/credit-note/fin09/not-created-list'])
    }
}