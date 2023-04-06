import { Component, OnInit } from "@angular/core";
import { Router, ActivatedRoute, Params } from "@angular/router";
import { Subject } from 'rxjs';
import { Fin04InvoiceService } from '../../fin-04-invoice-service';

@Component({
    selector: "fin-10b-create-list",
    templateUrl: "./fin-10b-create-list.component.html"
})
export class Fin10bCreateListComponent implements OnInit {

    dtOptions: DataTables.Settings = {
        pagingType: 'full_numbers',
        pageLength: 10
    };
    dtTrigger: Subject<any> = new Subject();

    public listDatas: any;
    public buttonEnableBoolean: Boolean;
    public selectedValue: Boolean;
    public clinicId: any;
    public invoiceType: string;
    public cardTitle: string;
    public filterDatas: any;
    public stateFilter: string = "";
    public districtFilter: string = "";
    public stateFilterDatas: Array<any>;
    public districtFilterDatas: Array<any>;
    public loading: Boolean = true;

    constructor(private router: Router, private route: ActivatedRoute, private fin04Service: Fin04InvoiceService) { }

    ngOnInit() {
        this.filterDatas = []
        this.fin04Service.fetchFor10bCreateList().subscribe(x => {
            this.listDatas = x;
            this.listDatas.forEach(element => {
                this.filterDatas.push(element);
            });
            this.loading = false;
            this.dtTrigger.next();
        })
        this.fin04Service.fetchStateDetails().subscribe(x => {
            this.stateFilterDatas = x;
        })
        this.buttonEnableBoolean = true
    }

    customRadio(id) {
        return "customRadio" + id
    }

    buttonEnable(clinicId) {
        this.clinicId = clinicId
        this.buttonEnableBoolean = false
    }

    handleForm(event) {
        switch (event.target.id) {
            case "state":
                this.stateFilter = event.target.value;
                this.districtFilter = "";
                this.districtFilterDatas = this.stateFilterDatas.find(state => state.stateName == event.target.value).districts
                this.filterFunction();
                break;
            case "district":
                this.districtFilter = event.target.value;
                this.filterFunction();
                break;
            default:
                break;
        }
    }

    filterFunction() {
        this.filterDatas = []
        if (this.stateFilter !== "" && this.districtFilter == "") {
            this.listDatas.forEach(element => {
                if (element.stateName == this.stateFilter) {
                    this.filterDatas.push(element)
                }
            });
        } else if (this.stateFilter !== "" && this.districtFilter !== "") {
            this.listDatas.forEach(element => {
                if (element.stateName == this.stateFilter && element.districtName == this.districtFilter) {
                    this.filterDatas.push(element)
                }
            })
        }
    }

    resetFilter() {
        this.stateFilter = ""
        this.districtFilter = ""
        this.districtFilterDatas = []
        this.filterDatas = []
        this.listDatas.forEach(element => {
            this.filterDatas.push(element)
        })
    }

    radioSelect() {

        this.router.navigateByUrl('transaction/fin-04-invoice/fin-10b-create/' + this.clinicId);
    }

}