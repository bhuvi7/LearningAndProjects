import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';
//import { environment } from '../../../../../../../environments/environment';
// import { toWords } from 'number-to-words';
//import { dateformat } from 'dateformat';
//import { Fin01InvoiceService } from "../../fin-01-invoice-service";
import { element } from 'protractor';
import { DataTableDirective } from 'angular-datatables';
import { Fin09InvoiceService } from '../../transaction/fin-09-invoice/fin-09-invoice-service';
import Swal from 'sweetalert2';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
// import { Fin09ABCDE } from '../../fin-09-invoice/model/fin09-abcde';
import { PAIService } from '../../transaction/payment-against-invoice/pai-service';
import { Fin09ABCDE } from '../../transaction/fin-09-invoice/model/fin09-abcde';
import { FLOAT } from 'html2canvas/dist/types/css/property-descriptors/float';

var dateFormat = require('dateformat');

@Component({
    selector: 'special-credit-note-created-list',
    templateUrl: './created-list-approval.component.html',

})

export class CreatedListApprovalComponent implements OnInit {

    public searchText: string;
    public listDatas: any;
    public filterDatas: Array<any>;
    public stateFilter: string;
    public districtFilter: string;
    public invoiceTypeName: any;
    public clinicTypeCode: any;
    public stateFilterDatas: Array<any>;
    public districtFilterDatas: Array<any>;
    public loading: Boolean = false;
    public sumAmt: any = [];
    public sum: FLOAT;
    public sums: any;
    public paymentRefNo;
    public rupee = "RS";
    public payList: any = [];
    public payrefnoList: any = [];
    public finGroups: any = [];
    public modalBodyContent;
    public disableCreate: Boolean = false;
    public totalRetention: number = 1000;
    // finGroupsList is the final payment array...
    public finGroupsList: any = []
    clicked = false;
    public fin09sd: Fin09ABCDE;
    public approvalQuater: any;
    public approvalYear: any;
    public month: any;
    public checking: any;
    public storings: Array<any>;



    constructor(private router: Router, config: NgbModalConfig, private route: ActivatedRoute, private modalService: NgbModal, public paiService: PAIService, public fin09service: Fin09InvoiceService) { }

    dtOptions: DataTables.Settings = {
        pagingType: 'full_numbers',
        pageLength: 10
    };
    dtTrigger: Subject<any> = new Subject();
    @ViewChild(DataTableDirective, { static: false })
    dtElement: DataTableDirective;
    ngOnInit() {
        if (this.route.params['_value']['_id'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.fin09service.fetchDataForFin09Approve(par['_id'])).subscribe(x => {
                console.log(x);


                this.fin09sd = x;
                this.stateFilter = this.fin09sd.stateName;
                this.districtFilter = this.fin09sd.districtName
                this.sums = this.fin09sd.totalPenalty
                this.paymentRefNo = this.fin09sd.code;
                this.month = this.fin09sd.month
                this.clinicTypeCode = this.fin09sd.clinicTypeId
                this.approvalQuater = this.fin09sd.approvalQuater
                this.approvalYear = parseInt(this.fin09sd.approvalYear)

            })
        }
        //this.filterFunction();
        setTimeout(() => {

            this.filterFunction()
            
        }, 900)

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
                    invoice.amountReceived = "";
                }
            })
            let search = this.finGroups.find(x => x.id === id)
            let index = this.finGroups.indexOf(search, 0)
            this.finGroups.splice(index, 1)
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
        let amt = this.fin09sd.totalPenalty
        this.sums = amt - this.sum

    }

    //this fuction called on keystroke...
    amountFunc(e: any, id: number) {
        this.filterDatas.forEach(invoice => {
            console.log("Data-----" + invoice);
            if (invoice.id == id) {
                let value = e.target.value === "" ? "" : e.target.value.replace(/[^(0-9.s)]/gi, '');
                if (parseFloat(value) > this.fin09sd.totalPenalty || parseFloat(value) > invoice.outstandingAmount) {
                    Swal.fire('', 'Collection Receiving Amount can not be more than Total Penalty or Outstanding Amount', 'error');
                }
                invoice.amountReceived = (value === "") ? "" : (parseFloat(value) > this.fin09sd.totalPenalty || parseFloat(value) > invoice.outstandingAmount) ? "" : parseFloat(value);


1
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


    update() {
        

        let finGroupsList = [];
        this.finGroups.forEach(data => {
            if (data.amountReceived === "") {
                Swal.fire('', 'Collection Receiving Amount can not be Empty', 'error');
                finGroupsList = [];
                return;
            } else {
                if (this.sums < 0) {
                    Swal.fire('', 'Penalty Amount cannot be Less than zero', 'error');
                }
                else {
                    let invoicePaymentData = {
                        invoiceNo: data.invoiceNo,
                        paymentReceived: data.amountReceived,
                        fin09InvoiceNo: this.paymentRefNo,
                        paymentRefNo: "",
                        paymentMode: "Credit Note",
                        updatedBy: JSON.parse(localStorage.getItem('currentUser')).id
                    }
                    finGroupsList.push(invoicePaymentData);

                    Swal.fire({
                        title: 'Are you sure?',
                        text: 'To Proceed Penalty Adjustment for Invoice',
                        type: 'warning',
                        showCloseButton: true,
                        showCancelButton: true
                    }).then((willDelete) => {
                        if (willDelete.dismiss) {
                            Swal.fire('', 'Penalty Adjustment Not Successful', 'error');
                            history.back();
                        } else {

                            this.paiService.penaltyInvoicePaymentSpecial(finGroupsList).subscribe(x => {
                                Swal.fire('', 'Penalty Adjustment Successfully. Transaction Reference No - ' + x[0].transactionRefNo, 'success');
                                //   this.router.navigateByUrl('transaction/credit-note/fin09/created-list')
                                history.back();
                            })
                        }
                    });
                }



            }
        });
    }


    filterFunction() {
        this.finGroupsList = [];
        this.finGroups = [];
        this.clicked = false
        this.loading = true;
        this.filterDatas = [];
        // this.month = ''
        this.storings = []


        // if (this.approvalQuater == 'Q4') {
        //     this.month = 11;

        // }
        // else if (this.approvalQuater == 'Q3') {
        //     this.month = 8;

        // }
        // else if (this.approvalQuater == 'Q2') {
        //     this.month = 5;

        // }
        // else if (this.approvalQuater == 'Q1') {
        //     this.month = 2;

        // }

        console.log(this.stateFilter);
        console.log(this.districtFilter);
        console.log(this.clinicTypeCode);


        this.paiService.fetchAllInvoiceFilter1(this.stateFilter, this.districtFilter, this.clinicTypeCode).subscribe((x) => {


            this.dtTrigger.next();

            let date_1 = new Date(this.approvalYear, this.month - 1);


            x.forEach((element, i) => {
                element.intMonthAndYear = new Date(parseInt(element.year), parseInt(element.month) - 1)
                this.storings.push(element)
                // if (x.length - 1 == i) {

                //     this.storings.forEach((element, i) => {
                //         if ((element.invoiceTypeId == '3' || element.invoiceTypeId == '4') && (element.intMonthAndYear <= date_1)) {
                //             this.filterDatas.push(element)
                //             if (this.storings.length - 1 == i) {

                //             }
                //         }
                //     });
                // }


                if (x.length - 1 == i) {

                    this.storings.forEach((element, i) => {
                        if ((element.invoiceTypeId == '3' || element.invoiceTypeId == '4')) {
                            this.filterDatas.push(element)
                        }
                    });
                }
            });




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
        this.loading = false


    }
}