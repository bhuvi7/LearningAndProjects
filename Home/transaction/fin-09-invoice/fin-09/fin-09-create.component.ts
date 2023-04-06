import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { Fin09InvoiceService } from '../fin-09-invoice-service';
import Swal from 'sweetalert2';
import { Fin09ABCDE } from '../model/fin09-abcde';
import { IOption } from '../model/IOption';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import * as dateFormat from 'dateformat';

@Component({
    selector: 'fin-09-create',
    templateUrl: './fin-09-create.component.html',
    styleUrls: ['./fin-09-create.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})


export class Fin09CreateComponent implements OnInit {

    public states: Array<any> = [];
    public districts: Array<any> = [];
    public clinicTypes: Array<any> = [];
    public clinics: Array<any> = [];
    public state: number = 0;
    public district: number = 0;
    public clinicType: number = 0;
    public clinic: number = 0;
    public fin09sd: Fin09ABCDE;
    public fin09: Array<Fin09ABCDE>;
    public enableFin09: Boolean = false;
    public disableCreate: Boolean = false;
    public sOption: IOption;
    public simpleOption: Array<IOption>;
    public selectedOptionClinic = '0';
    public clinicOption: Array<any>;
    public modalBodyContent;

    public curDate: any
    public selectedDate: string
    public quater: string;
    public year: any;
    public storing: any;
    public checking: any;
    public month: any;
    public approvalMonth: any;
    public approvalYear: any;
    public invoiceDatas: any;
    public disableCreateButton: boolean = false

    // public approvedQuaters = 
    // [{
    //     Q: 'Q1'},
    //     {Q: 'Q2'},
    //     {Q: 'Q3'},
    //     {Q: 'Q4'}]
    // public approvedYears = [{
    //     Y2019: '2019',
    //     Y2020: '2020',
    //     Y2021: '2021',
    //     Y2022: '2022'
    // }]

    constructor(private fin09Service: Fin09InvoiceService, private router: Router, config: NgbModalConfig, private modalService: NgbModal) {
        config.backdrop = 'static';
        config.keyboard = false;
    }

    ngOnInit() {
        this.fin09sd = new Fin09ABCDE();
        this.clinicOption = [
            { value: '0', label: 'Select' }
        ]
        this.simpleOption = this.fin09Service.getCharacters(this.clinicOption);
        this.sOption = new IOption();
        this.fin09Service.fetchStateDetails().subscribe(x => {
            this.states = x;
        })
        this.fin09Service.fetchClinicTypeDetails().subscribe(x => {
            this.clinicTypes = x;
        })
        this.fin09Service.fetchDataForFin09Approved('APPROVED BY MOH').subscribe(x => {
            this.invoiceDatas = x;


        })

        this.fin09sd.lateDeliveryPenalty = null
        this.fin09sd.repairTimePenalty = null
        this.fin09sd.responseTimePenalty = null
        this.fin09sd.scheduledMaintenancePenalty = null
        this.fin09sd.uptimePenalty = null

        //mine
        this.fin09Service.fetchAllUnadjustedPenalty(null).subscribe(x => {
            this.checking = x
        })

    }

    fetchClinics(districtId, clinicTypeId) {
        this.fin09Service.fetchClinicsDetails(districtId, clinicTypeId).subscribe(x => {
            this.clinics = x;
            this.clinics.forEach(clinic => {
                this.sOption.value = clinic.id.toString();
                this.sOption.label = clinic.clinicName;
                this.clinicOption.push(this.sOption);
                this.sOption = new IOption();
            })
            this.simpleOption = this.fin09Service.getCharacters(this.clinicOption);
        })
    }

    handleForm(event) {
        // this.disableCreateButton=false
        switch (event.target.id) {
            case "state":
                this.state = event.target.value;
                this.district = 0;
                this.clinicType = 0;
                this.clinic = 0;
                this.enableFin09 = false;
                this.fin09 = [];

                this.districts = this.states.find(state => state.id == event.target.value).districts
                this.clinics = []
                break;
            case "district":
                this.district = event.target.value;
                this.clinicType = 0;
                this.clinic = 0;
                this.enableFin09 = false;
                this.fin09 = [];
                this.clinics = []
                break;
            case "clinicType":
                this.clinicType = event.target.value;

                this.clinic = 0;
                this.enableFin09 = false;
                this.fin09 = [];
                this.fetchClinics(this.district, this.clinicType)
                break;
            case "clinic":
                this.clinic = event.target.value;
                break;

            case "Quater":
                this.quater = event.target.value;
                break;

            case "Year":
                this.year = event.target.value;
                break;

            case "Month":
                this.month = event.target.value;
                break;

            default:
                break;
        }
        //mine

        this.checking.forEach(x => {

            if (x.stateId == this.state && x.districtId == this.district && x.clinicTypeId == this.clinicType && x.approvalQuater == this.quater && x.approvalYear == this.year) {

                Swal.fire('', 'Penalty Already Exist For This State', 'error')
                this.state = 0
                this.district = null
                this.clinicType = null
                this.quater = null

                this.year = null
                this.month = null


            }

        })
    }

    openModal(content, modalContent) {
        this.modalBodyContent = modalContent
        this.modalService.open(content);
    }

    closeModal() {
        if (this.modalBodyContent == 'Create') { this.createFin09(); }
    }

    //current Date fetcher
    curDatePicker() {

        let date_ob = new Date();
        let date = ("0" + date_ob.getDate()).slice(-2);
        let month = ("0" + (date_ob.getMonth() + 1)).slice(-2);

        let year = date_ob.getFullYear();

        this.curDate = year + "-" + month + "-" + date
    }

    //
    datePicker() {

        let date = new Date();
        let day = date.getDate();
        let month = date.getMonth() + 1;
        let year = date.getFullYear();
        let monthStr;
        let dateStr;

        if (month < 10) { monthStr = "0" + month }
        else { monthStr = month }
        (day < 10 ? dateStr = "0" + day : dateStr = day);
        this.selectedDate = year + "-" + monthStr + "-" + dateStr;
        this.approvalMonth = monthStr
        this.approvalYear = year

    }




    addInFin09() {

        // 
        if (this.state == 0 || this.district == 0 || this.clinicType == 0 || this.fin09sd.approvalYear == undefined || this.fin09sd.approvalMonth == undefined) {
            Swal.fire('', 'Enter All The Fields', 'error')
        }
        let splitter = this.fin09sd.approvalDate.split("-");

        this.fin09sd.stateId = this.state
        this.fin09sd.districtId = this.district
        this.fin09sd.clinicTypeId = this.clinicType
        if (this.fin09sd.lateDeliveryPenalty == null) { this.fin09sd.lateDeliveryPenalty = 0 }
        if (this.fin09sd.repairTimePenalty == null) { this.fin09sd.repairTimePenalty = 0 }
        if (this.fin09sd.responseTimePenalty == null) { this.fin09sd.responseTimePenalty = 0 }
        if (this.fin09sd.uptimePenalty == null) { this.fin09sd.uptimePenalty = 0 }
        if (this.fin09sd.scheduledMaintenancePenalty == null) { this.fin09sd.scheduledMaintenancePenalty = 0 }
        let checking = this.fin09sd.lateDeliveryPenalty + this.fin09sd.repairTimePenalty +
            this.fin09sd.responseTimePenalty + this.fin09sd.uptimePenalty + this.fin09sd.scheduledMaintenancePenalty;


        if (this.state == 0 || this.district == 0 || this.clinicType == 0 || this.fin09sd.approvalYear == undefined || this.fin09sd.approvalMonth == undefined) {
            Swal.fire('', 'Enter All The Fields', 'error')
        }
        else if ((splitter[0] <= this.fin09sd.approvalYear && parseInt(splitter[1]) < parseInt(this.fin09sd.approvalMonth)) || (splitter[0] < this.fin09sd.approvalYear)) {
            Swal.fire('', 'Month & Year should be lesser than penalty approval date', 'error')
        }
        else {


            if (this.fin09.length > 0) {

                //  this.disableCreateButton=true
                Swal.fire('', 'Cannot Add More Than One FIN09', 'error')

            }
            else {
                this.fin09sd.totalPenalty = this.fin09sd.lateDeliveryPenalty + this.fin09sd.repairTimePenalty
                    + this.fin09sd.responseTimePenalty + this.fin09sd.uptimePenalty + this.fin09sd.scheduledMaintenancePenalty;
                this.fin09.push(this.fin09sd);
                this.enableFin09 = true;
                this.fin09sd = new Fin09ABCDE();
                this.state = 0
                this.district = 0
                this.clinicType = 0
                this.selectedOptionClinic = '0'
            }


        }


    }

    // removeFin09SD(index) {
    //     this.fin09.splice(index, 1)
    //     if (this.fin09.length == 0) { this.enableFin09 = false }
    // }

    createFin09() {

        this.fin09Service.createFin09(this.fin09).subscribe(x => {


            if (x.id == null) {
                Swal.fire('', 'FIN09 Already Created For This Month & Year', 'error')
            } else {

                Swal.fire('', 'FIN 09 created and Code Reference No - ' + x.code, 'success');

                history.back();
            }
        })
    }

    navToList() {
        history.back();
    }

}