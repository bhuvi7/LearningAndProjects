
import { Component, OnInit, ViewChild } from "@angular/core";
import { EMPTY, empty, Subject } from 'rxjs';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { PAIService } from '../payment-against-invoice/pai-service';
import { DataTableDirective } from 'angular-datatables';

import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { FLOAT } from 'html2canvas/dist/types/css/property-descriptors/float';




@Component({
    selector: "pami",
    templateUrl: "./pami.component.html",
})
export class PAMIComponent implements OnInit {

    public groups: any;
    public searchText: string;
    public listDatas: any;
    public filterDatas: Array<any>;
    public stateFilter: string = "";
    public districtFilter: string = "";
    public stateFilterDatas: Array<any>;
    public districtFilterDatas: Array<any>;
    public loading: Boolean = false;

    public sum: FLOAT;
    public rupee = "RS";
    public payList: any = [];
    public payrefnoList: any = [];
    public finGroups: any = [];
    public clicked: boolean;
    public curDate: any
    public selectedDate: string
    public value
    public limit
    public dateEnabler: boolean = true
    public clinicTypeFilter: string = "";


    // finGroupsList is the final payment array...
    public finGroupsList: any = []

    constructor(private router: Router, public paiService: PAIService) { }
    dtOptions: DataTables.Settings = {
        pagingType: 'full_numbers',
        pageLength: 10
    };
    dtTrigger: Subject<any> = new Subject();
    @ViewChild(DataTableDirective, { static: false })
    dtElement: DataTableDirective;

    ngOnInit() {
        this.clicked = true
        this.selectedDate = ""
        this.sum = 0
        this.filterDatas = [];
        this.stateFilterDatas = [];

        this.districtFilterDatas = []

        this.paiService.fetchStateDetails().subscribe(x => {
            x.forEach((x => {
                if (x.id != 0) { this.stateFilterDatas.push(x) }
            }))

        })
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
        if (this.selectedDate != '') {
            //this.filterFunction();
        }
        else {
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

        }
    }

    onTick(id: number, e) {
        if (e.target.checked) {
            this.filterDatas.forEach
                (invoice => {
                    if (invoice.id == id) {
                        //For enabling the text field...

                        invoice.disableCollectionAmount = false
                        invoice.disableCollectionRefNo = false
                        invoice.enableListData = e.target.checked

                        //Assiging value to text field when tick is executed...
                        // invoice.amountReceived = invoice.outstandingAmount
                    }
                })
            let search = this.filterDatas.find(x => x.id === id)
            this.finGroups.push(search)

        }
        else {
            this.filterDatas.forEach(invoice => {
                if (invoice.id == id) {

                    invoice.disableCollectionAmount = true
                    invoice.disableCollectionRefNo = true
                    invoice.enableListData = e.target.checked

                    // invoice.amountReceived = "";
                }
            })
            let search = this.finGroups.find(x => x.id === id)
            let index = this.finGroups.indexOf(search, 0)
            this.finGroups.splice(index, 1)

        }

        //Handling disabled Pay Button
        if (this.finGroups.length > 0) {
            this.clicked = false
            this.dateEnabler = false
        }
        else {
            this.clicked = true
            this.dateEnabler = true
        }

        this.handleTotalAmountPaid();
    }

    handleTotalAmountPaid() {
        let amountList = [];
        this.filterDatas.forEach(invoice => {
            if (invoice.enableListData) {
                amountList.push(invoice.amountReceived);
            }
        })
        this.sum = amountList.reduce((a, b) => a + b, 0);
    }

    //this fuction called on keystroke...
    amountFunc(e: any, id: number) {
        this.filterDatas.forEach(invoice => {
            if (invoice.id == id) {
                this.value = e.target.value === "" ? "" : e.target.value.replace(/[^(0-9.s)]/gi, '');
                if (parseFloat(this.value) > parseFloat(invoice.outstandingAmount)) {

                    Swal.fire('', 'Collection Receiving Amount can not be more than Outstanding Amount', 'error');
                }
                invoice.amountReceived = (this.value === "") ? "" : parseFloat(this.value) > parseFloat(invoice.outstandingAmount) ? "" : parseFloat(this.value)

            }
        })
        this.handleTotalAmountPaid();
    }

    //this function called on onChange...
    amountFunc1(e, id: number) {
        this.filterDatas.forEach(invoice => {
            if (invoice.id == id) {
                if (invoice.amountReceived === 0 || invoice.amountReceived === "") {
                    Swal.fire('', 'Collection Receiving Amount can not be Zero or Empty', 'error');
                    invoice.amountReceived = "";
                }
            }
        })
        this.handleTotalAmountPaid();
    }


    amountRefFunc(e: any, id: number) {
        this.filterDatas.forEach(invoice => {
            if (invoice.id == id) {
                invoice.amountRefNo = e.target.value
            }
        })
    }

    finalPush1() {

        Swal.fire({
            title: 'Are you sure?',
            text: 'Once you completed the transaction, you cannot be able to revert this action!',
            type: 'warning',
            showCloseButton: true,
            showCancelButton: true
        }).then((pay) => {
            if (pay.dismiss) {
                Swal.fire('', 'Transaction Cancelled', 'error');
            }
            //this is accepted part...
            else {
                this.finGroups.forEach(data => {
                    let invoicePaymentData = {
                        invoiceNo: data.invoiceNo,
                        paymentReceived: data.amountReceived,
                        paymentRefNo: data.amountRefNo,
                        paymentDate: this.selectedDate,
                        paymentMode: "Bank Receipting",
                        updatedBy: JSON.parse(localStorage.getItem('currentUser')).id
                    }
                    //finGroupsList is final payment array that has to be sent to database...
                    this.finGroupsList.push(invoicePaymentData);
                })
                this.paiService.multipleInvoicePayment(this.finGroupsList).subscribe((x) => {
                    Swal.fire('', 'Payment Successful. Transaction Reference No - ' + x[0].transactionRefNo, 'success');

                })

                this.clicked = true
                this.ngOnInit()
            }
        });
    }

    finalPush() {

        this.finGroups.forEach(data => {
            if (data.amountReceived === "") {
                Swal.fire('', 'Collection Receiving Amount can not be Empty', 'error');
                this.finGroupsList = [];
                return;
            }
            else if (this.selectedDate == "") {
                Swal.fire('', 'Enter A Collection Receiving date', 'error')
            }

            else {
                this.finalPush1()
            }
        })
    }

    handleForm(event) {
        switch (event.target.id) {
            case "state":
                this.stateFilter = event.target.value;
                this.districtFilter = "";
                this.districtFilterDatas = this.stateFilterDatas.find(state => state.stateName == event.target.value).districts
                break;

            case "district":
                this.districtFilter = event.target.value;
                break;

            case "clinicType":
                this.clinicTypeFilter = event.target.value;

                break;
            default:
                break;
        }
    }

    filterFunction() {
        this.finGroupsList = [];
        this.finGroups = [];
        this.sum = 0;


        this.loading = true;

        this.paiService.fetchAllInvoiceFilter1(this.stateFilter, this.districtFilter, this.clinicTypeFilter).subscribe((x) => {
            this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
                dtInstance.destroy();
                this.dtTrigger.next();
                this.filterDatas = x;



                if (this.filterDatas && this.filterDatas.length > 0) {
                    this.filterDatas.forEach(invoice => {
                        invoice.enableListData = false;
                        invoice.disableCollectionAmount = true;
                        invoice.disableCollectionRefNo = true;
                        invoice.amountReceived = "";
                        invoice.amountRefNo = "";
                    })

                }

            });
            this.loading = false;

        })
    }

    resetFilter() {
        this.stateFilter = ""
        this.districtFilter = ""
        this.clinicTypeFilter = ""
        this.districtFilterDatas = []
        this.filterDatas = []
        this.dateEnabler = true
    }

    update(id) {
        this.router.navigateByUrl('transaction/pai/pai-updatedetails/' + id);
    }


    ngAfterViewInit(): void {
        this.dtTrigger.next();
    }

    ngOnDestroy(): void {
        this.dtTrigger.unsubscribe();
    }


}