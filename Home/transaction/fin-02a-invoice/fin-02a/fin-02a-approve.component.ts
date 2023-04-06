import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { Fin02aInvoiceService } from '../fin-02a-invoice-service';
import * as dateFormat from 'dateformat';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { toWords } from 'number-to-words';
import pdfMake from "pdfmake/build/pdfmake.min";
import pdfFonts from "pdfmake/build/vfs_fonts";
pdfMake.vfs = pdfFonts.pdfMake.vfs;
import html2canvas from 'html2canvas';
import { formatNumber } from '@angular/common';
import { PagesService } from '../../../pages.service';

@Component({
    selector: 'fin-02a-approve',
    templateUrl: './fin-02a-approve.component.html',
    styleUrls: ['./fin-02a-approve.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})

export class Fin02aApproveComponent implements OnInit {

    public enableSaveButton: Boolean = window.history.state.approvalPage ? true : false;

    public invoice: any;
    public clinicTypeCode: any;
    public districtName;
    public monthYear;
    public docRef = "";
    public date;
    public invoiceDate;
    public lastDate;
    public clinicDatas: any;
    public totalRentalCharge = 0.0;
    public sstPercentage = 0;
    public sstAmount = 0.0;
    public totalInvoiceAmount = 0.0;
    public totalInvoiceAmountInWords: String;
    public invoiceTotalInvoiceAmountWholeValueInWords: String;
    public invoiceTotalInvoiceAmountDecimalValueInWords: String;
    public showSaveSubmitButton;
    public isApproved = false;
    public showButtons = false;
    public districtId: number;
    public clinicTypeId: number;
    public district;
    public districtAddress: string;
    public modalBodyContent;
    public loading: Boolean = true;
    public logo = '';
    public userId: Number;
    public totalinvoiceinwords: string;
    public invoiceTotalValueInWord: string;
    public disableApprove: boolean;
    public enabler: boolean;
    public month: any;
    public year: any;

    constructor(private router: Router, private route: ActivatedRoute, private fin02aService: Fin02aInvoiceService,
        config: NgbModalConfig, private modalService: NgbModal, private pagesService: PagesService) {
        config.backdrop = 'static';
        config.keyboard = false;
    }

    ngOnInit() {
        this.disableApprove = false
        this.userId = this.pagesService.getUserId();
        if (this.route.params['_value']['_id'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.fin02aService.fetchDataForFin02aApprove(par['_id'])).subscribe(x => {
                this.fin02aService.findInvoiceSstPercentage(2).subscribe(y => {
                    this.sstPercentage = y;
                })
                this.invoice = x;
                this.clinicDatas = x.fin03a;
                this.clinicDatas.forEach(element => {
                    element.dateDisplay = dateFormat(new Date(), 'dd-mm-yyyy')
                });
                this.clinicDatas.map(data => {
                    this.totalRentalCharge += data.totalRentalCharge
                })
                if (x.stateName == 'SARAWAK') {
                    if (x.clinicTypeCode == 'PKD') {
                        this.clinicTypeCode = 'PKB'
                    } else if (x.clinicTypeCode == 'PPD') {
                        this.clinicTypeCode = 'PPB'
                    }
                } else {
                    this.clinicTypeCode = x.clinicTypeCode;
                }
                this.districtName = x.districtName;
                this.districtId = x.districtId;
                this.clinicTypeId = x.clinicTypeId;
                this.monthYear = x.month + '/' + x.year;
                this.date = dateFormat(x.date, 'dd-mm-yyyy')
                this.invoiceDate = this.date
                this.sstAmount = x.sst;
                this.month = x.month
                this.year = x.year
                this.totalInvoiceAmount = x.totalInvoiceValue;
                this.docRef = this.invoice.status == 'CREATED' || this.invoice.status == 'SAVED' ? "" : this.invoice.invoiceNo;
                this.isApproved = this.invoice.status == 'APPROVED BY MOH' ? true : false;
                this.showButtons = this.invoice.status == 'APPROVED BY MOH' ? false : true;
                this.showSaveSubmitButton = this.invoice.status == 'IN INTERNAL APPROVAL' || this.invoice.status == 'FOR APPROVAL TO MOH' ? false : true;
                if (this.totalInvoiceAmount % 1 == 0) {
                    this.totalInvoiceAmountInWords = toWords(this.totalInvoiceAmount).toUpperCase();
                } else {
                    let totalAmountInString = this.totalInvoiceAmount.toString()
                    let splitValue = totalAmountInString.split('.')
                    this.invoiceTotalInvoiceAmountWholeValueInWords = toWords(splitValue[0]).toUpperCase();
                    this.invoiceTotalInvoiceAmountDecimalValueInWords = toWords(splitValue[1]).toUpperCase();
                    this.totalInvoiceAmountInWords = this.invoiceTotalInvoiceAmountWholeValueInWords + ' AND CENTS ' + this.invoiceTotalInvoiceAmountDecimalValueInWords
                }
                this.fin02aService.fetchDistrictById(this.districtId).subscribe(x => {
                    this.district = x
                    if (this.clinicTypeId == 1) {
                        this.districtAddress = this.district.officePkdAddress;
                    } else if (this.clinicTypeId == 2) {
                        this.districtAddress = this.district.officePpdAddress;
                    }


                })
            })
            this.loading = false;
        }

    }

    openModal(content, modalContent) {
        this.invoiceDate = '';
        this.modalBodyContent = modalContent
        this.modalService.open(content);
    }

    closeModal() {
        if (this.modalBodyContent == 'Save') { this.saveStatus(); }
        if (this.modalBodyContent == 'Submit') { this.submitStatus(); }
        if (this.modalBodyContent == 'Approve') { this.updateStatus(); }
        if (this.modalBodyContent == 'invoiceDateChange') { this.downloadPdf() }
    }

    saveStatus() {
        delete this.invoice.fin03a;
        this.invoice.status = "SAVED";
        this.fin02aService.updateFin02aStatus(this.invoice).subscribe(x => {
            Swal.fire('', 'Fin 02A Invoice saved successfully!!!', 'success');
        })
    }

    submitStatus() {
        delete this.invoice.fin03a;
        this.invoice.status = 'IN INTERNAL APPROVAL'
        this.fin02aService.updateFin02aStatus(this.invoice).subscribe(x => {
            Swal.fire('', 'Fin 02A Invoice submitted successfully!!!', 'success');
            history.back()
        })
    }

    updateStatus() {
        this.disableApprove = true
        this.enabler = true
        if (this.invoice.status == 'IN INTERNAL APPROVAL') { this.invoice.status = 'FOR APPROVAL TO MOH', this.invoice.approval1UserId = this.userId }
        else if (this.invoice.status == 'FOR APPROVAL TO MOH') { this.invoice.status = 'APPROVED BY MOH', this.invoice.approval2UserId = this.userId }
        delete this.invoice.fin03a;
        this.fin02aService.updateFin02aStatus(this.invoice).subscribe(x => {
            this.enabler = false
            Swal.fire('', 'FIN 02A Invoice approved successfully!!!', 'success');
            history.back()
            this.disableApprove = false
        })

    }

    navToList() {
        history.back()
    }

    public downloadPdf() {
        var data = document.getElementById("pdfImage");
        html2canvas(data, { scale: 1.5, scrollY: -window.scrollY }).then(canvas => {
            this.logo = canvas.toDataURL('image/png')
            this.generatePDF()
        });

    }

    generatePDF() {

        if (this.invoice.status == 'FOR APPROVAL TO MOH') {
            if (this.invoiceDate != '') {
                this.invoiceDate = dateFormat(this.invoiceDate, 'dd-mm-yyyy')
                this.lastDate = this.invoiceDate
            }
        }

        let logo = this.logo
        let mySentence = this.totalInvoiceAmountInWords;
        let words = mySentence.split(" ");

        for (let i = 0; i < words.length; i++) {
            words[i] = words[i].toLowerCase();
        }

        this.totalinvoiceinwords = words.join(" ");

        let mySentenc = this.totalinvoiceinwords;
        let word = mySentenc.split(" ");

        for (let i = 0; i < word.length; i++) {
            word[i] = word[i][0].toUpperCase() + word[i].substr(1);
        }

        this.invoiceTotalValueInWord = word.join(" ");

        let spa = this.invoiceTotalValueInWord.split("-");

        for (let i = 0; i < spa.length; i++) {
            spa[i] = spa[i][0].toUpperCase() + spa[i].substr(1);
        }
        this.invoiceTotalValueInWord = spa.join("-");

        if (this.invoice.status == 'FOR APPROVAL TO MOH') {
            if (this.invoiceDate != '') {
                this.lastDate = this.invoiceDate
            }
        }
        else if (this.invoiceDate == '') {
            this.lastDate = ""
        }
        else {
            var d = new Date(this.year, this.month, 0);
            let data = new Date(d);
            this.lastDate = data.getDate() + '/' + (data.getMonth() + 1) + '/' + data.getFullYear()

        }
        var docDefinition = {
            pageMargins: [30, 135, 30, 60],
            header: function () {
                let header = [
                    {
                        image: logo,
                        fit: [275, 125],
                        alignment: 'center',
                        margin: [0, 0, 0, 10]
                    }
                ]
                return header;
            },
            footer: function (currentPage, pageCount) {
                let footerText = [
                    {
                        columns: [
                            {
                                text: 'QUANTUM MEDICAL SOLUTIONS SDN BHD (868557-V) ', alignment: 'center', width: '100%',
                            }
                        ]
                    },
                    {
                        columns: [
                            {
                                text: 'Unit L3A-6, Level 3A, Wisma Xemajuan, 2 Jalan 19/18, 46300 Petaling Jaya, TEL: +603 7629 6777 FAX: +603 7629 6779 ', alignment: 'center', width: '100%',
                            }
                        ]
                    },
                    {
                        columns: [
                            {
                                text: 'EMAIL: finance_AR@qms.com.my', alignment: 'center', width: '100%'
                            },
                        ]
                    },
                    {
                        columns: [

                            // {
                            //     text: '**  This invoice is system generated and does not required a signature', alignment: 'right', width: '70%'
                            // },
                            // {
                            //     text: '**  This is a system generated document from Qubics, no signature is required.', alignment: 'right', width: '70%'
                            // },
                            {},
                            { text: 'Page ' + currentPage.toString() + ' of ' + pageCount, alignment: 'right', margin: [0, 0, 30, 0], width: '20%' }
                        ]
                    }
                ]
                return footerText;
            },
            content: [
                {
                    text: 'SST ID : W10-1904-32000110',
                    alignment: 'center', fontSize: 12, bold: true

                },
                {
                    table: {
                        widths: ['*'],
                        //widths: ['auto', 'auto', 'auto', 'auto', 'auto'],
                        body: [
                            [{
                                text: 'INVOICE FOR RENTAL CHARGES',
                                bold: true
                            },],
                        ]
                    },
                    alignment: 'center',
                },
                {
                    table: {
                        body: [
                            [{
                                text: 'FOR ' + this.monthYear, bold: true,
                            },],
                        ]
                    },
                    margin: [235, 0, 0, 10],
                    alignment: 'center',
                },
                {
                    text: 'TAX INVOICES', bold: true, fontSize: 15,
                    alignment: 'center',
                },
                {
                    text: '\n'
                },
                {
                    columns: [
                        {
                            width: '50%',
                            stack: [
                                { text: 'INVOICE TO:', bold: true },
                                {
                                    table: {
                                        body: [
                                            [{
                                                table: {
                                                    widths: ['*', 'auto', '*', '*'],
                                                    body: [
                                                        [{ text: 'PKD/PPD', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.clinicTypeCode }, { text: this.districtName, margin: [-35, 0, 0, 0] }],
                                                        [{ text: 'ADDRESS', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.districtAddress, colSpan: 2 }, {}],
                                                        // [{ text: 'MONTH/YEAR', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.monthYear }],
                                                    ],
                                                    alignment: 'center',
                                                }, layout: {
                                                    defaultBorder: false,

                                                },
                                            }]
                                        ]
                                    }
                                }
                            ],
                        },
                        {},
                        {
                            width: '40%',
                            stack: [
                                { text: 'FORM FIN 02-A', bold: true, alignment: 'right', fontSize: 12 },
                                {
                                    table: {
                                        widths: ['*', 'auto'],
                                        body: [
                                            [{ text: 'INVOICE REF:', bold: true }, { text: this.docRef, alignment: 'right', bold: true }],
                                            [{ text: 'DATE:', bold: true }, { text: this.lastDate, alignment: 'right', bold: true }]
                                        ],
                                    }
                                }
                            ]
                        }
                    ]
                },
                {
                    text: '\n'
                },
                {
                    table: {
                        headerRows: 1,
                        widths: ['*', 'auto', 'auto', 'auto'],
                        body: [
                            [{ text: 'DISTRICT NAME', bold: true, alignment: 'center' }, { text: 'FORM FIN 03A REF NO', bold: true, alignment: 'center' }, { text: 'GROSS CHARGES (RM)', bold: true, alignment: 'center' },
                            { text: 'NET CHARGES PAYABLE (RM)', bold: true, alignment: 'center' }],
                            ...this.clinicDatas.map(data => {
                                return [{ text: data.districtName }, { text: data.code.substring(0, 18), alignment: 'center' },
                                { text: formatNumber(data.totalRentalCharge, 'en', '1.2-2'), alignment: 'right' }, { text: formatNumber(data.totalRentalCharge, 'en', '1.2-2'), alignment: 'right' }]
                            }),
                            // ['\n', '\n', '\n', '\n'],
                            // ['\n', '\n', '\n', '\n'],
                            [{ text: 'TOTAL INVOICE AMOUNT (Excluding SST)', colSpan: 2, bold: true }, '', { text: formatNumber(this.totalRentalCharge, 'en', '1.2-2'), bold: true, alignment: 'right' }, { text: formatNumber(this.totalRentalCharge, 'en', '1.2-2'), bold: true, alignment: 'right' }],
                            [{ text: 'SST Amount (' + this.sstPercentage + '%)', colSpan: 2, bold: true, border: [false, false, false, false], }, '', { text: '', border: [false, false, false, false], }, { text: formatNumber(this.sstAmount, 'en', '1.2-2'), bold: true, alignment: 'right' }],
                            [{ text: 'TOTAL INVOICE AMOUNT (Inclusive SST)', colSpan: 2, bold: true, border: [false, false, false, false], }, '', { text: '', border: [false, false, false, false], }, { text: formatNumber(this.totalInvoiceAmount, 'en', '1.2-2'), bold: true, alignment: 'right' }],
                        ]
                    }
                },
                {
                    text: '\n'
                },
                {
                    text: '(Ringgit Malaysia: ' + this.invoiceTotalValueInWord + ' Only)', bold: true, italics: true
                },
                {
                    text: '\n'
                },
                {
                    text: '\n'
                },
                {
                    text: '\n'
                },
                {
                    text: '\n'
                },
                {
                    columns: [
                        {
                            width: '100%',
                            table: {
                                widths: ['*', '*'],
                                body: [
                                    [{ text: 'REMITTANCE DETAILS', bold: true }, ''],
                                    [{ text: 'PAYEE:', bold: true, italics: true }, { text: 'QUANTUM MEDICAL SOLUTIONS SDN BHD', bold: true }],
                                    [{ text: 'Amount Due:', bold: true, italics: true }, { text: formatNumber(this.totalInvoiceAmount, 'en', '1.2-2'), bold: true }],
                                    [{ text: 'Bank Details:', bold: true, italics: true }, { text: 'RHB Bank Berhad', bold: true }],
                                    [{ text: 'Bank Account No:', bold: true, italics: true }, { text: '21219800038867', bold: true }],
                                ]
                            },
                            layout: 'lightHorizontalLines'
                        },
                        {}
                    ]
                },

                { text: '**  This invoice is system generated and does not require a signature', bold: true, width: '100%' },
            ],
            pageSize: 'A4',
            pageOrientation: 'portrait',
            defaultStyle: {
                font: pdfMake.vfs.Roboto,
                fontSize: 10,
                lineHeight: 1
            },
            preserveSpace: {
                preserveLeadingSpaces: true
            }
        };

        pdfMake.createPdf(docDefinition).download('Invoice ' + this.docRef + '.pdf');
    }
}