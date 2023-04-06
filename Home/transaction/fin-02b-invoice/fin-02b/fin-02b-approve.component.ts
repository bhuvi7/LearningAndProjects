import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { Fin02bInvoiceService } from '../fin-02b-invoice-service';
import * as dateFormat from 'dateformat';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { toWords } from 'number-to-words';
import { Fin02b } from '../model/fin02b';
import { CimsHistoryFin02b } from '../model/cimshistoryfin02b';
import pdfMake from "pdfmake/build/pdfmake.min";
import pdfFonts from "pdfmake/build/vfs_fonts";
pdfMake.vfs = pdfFonts.pdfMake.vfs;
import html2canvas from 'html2canvas';
import { formatNumber } from '@angular/common';
import { PagesService } from '../../../pages.service';
@Component({
    selector: 'fin-02b-approve',
    templateUrl: 'fin-02b-approve.component.html',
    styleUrls: ['fin-02b-approve.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})

export class Fin02bApproveComponent implements OnInit {

    public enableSaveButton: Boolean = window.history.state.approvalPage ? true : false;

    public fin02b: Fin02b;
    public cimsHistoryFin02b: CimsHistoryFin02b;
    public monthYear;
    public docRef = "";
    public date;
    public invoiceDate;
    public lastDate;
    public sstPercentage = 0;
    public invoiceTotalValueInWords: String;
    public invoiceTotalWholeValueInWords: String;
    public invoiceTotalDecimalValueInWords: String;
    public showSaveSubmitButton;
    public isApproved = false;
    public showButtons = false;
    public modalBodyContent;
    public loading: Boolean = true;
    public logo = ''
    public userId: Number;
    public totalinvoiceinwords: string;
    public invoiceTotalValueInWord: string;
    public storingPpd: any;
    public storingPkd: any;
    public swap: string = '';
    public district: any;
    public districtId: number;
    public clinicTypeId: number;
    public clinicAddress: string = '';
    public month: any;
    public clinicTypeCode: string;
    public districtName: string;
    public disableApprove: boolean;
    public show: any;
    public enabler: boolean
    public months: any;
    public year: any;

    constructor(private router: Router, private route: ActivatedRoute, private fin02bService: Fin02bInvoiceService,
        config: NgbModalConfig, private modalService: NgbModal, private pagesService: PagesService) {
        config.backdrop = 'static';
        config.keyboard = false;
    }

    ngOnInit() {
        this.disableApprove = false
        this.userId = this.pagesService.getUserId();
        this.fin02b = new Fin02b()
        this.cimsHistoryFin02b = new CimsHistoryFin02b()
        if (this.route.params['_value']['_id'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.fin02bService.fetchDataForFin02bApprove(par['_id'])).subscribe(x => {
                this.fin02bService.findInvoiceSstPercentage(4).subscribe(y => {
                    this.sstPercentage = y;
                })
                this.fin02b = x;
                this.months = x.month
                this.year = x.year
                if (this.fin02b.clinicTypeCode == 'PPD') {
                    this.storingPpd = x
                    this.swap = "2"


                }
                else if (this.fin02b.clinicTypeCode == 'PKD') {
                    this.storingPkd = x
                    this.swap = "1"


                }

                if (this.fin02b.stateName == "SARAWAK") {
                    if (this.fin02b.clinicTypeCode == "PKD") {
                        this.fin02b.clinicTypeCode = "PKB"
                    } else if (this.fin02b.clinicTypeCode == "PPD") {
                        this.fin02b.clinicTypeCode = "PPB"
                    }
                }
                // this.clinicTypeCode = this.fin02b.clinicTypeCode;
                this.clinicTypeId = this.fin02b.clinicTypeId
                this.districtId = this.fin02b.districtId
                // this.districtName = this.fin02b.districtName;

                this.cimsHistoryFin02b = x.cimsHistoryFin02b[0]
                this.monthYear = x.month + '/' + x.year;
                this.date = dateFormat(x.date, 'dd-mm-yyyy')
                this.invoiceDate = this.date
                this.docRef = this.fin02b.status == 'CREATED' || this.fin02b.status == 'SAVED' ? "" : this.fin02b.code;
                let transform = this.docRef
                let storing = transform.split("-")



                if (storing[4].length < 2) {
                    this.show = '0' + storing[4]
                }
                else {
                    this.show = storing[4]
                }
                this.isApproved = this.fin02b.status == 'APPROVED BY MOH' ? true : false;
                this.showButtons = this.fin02b.status == 'APPROVED BY MOH' ? false : true;
                this.showSaveSubmitButton = this.fin02b.status == 'IN INTERNAL APPROVAL' || this.fin02b.status == 'FOR APPROVAL TO MOH' ? false : true;
                if (this.fin02b.totalInvoiceValue % 1 == 0) {
                    this.invoiceTotalValueInWords = toWords(this.fin02b.totalInvoiceValue).toUpperCase();
                } else {
                    let totalAmountInString = this.fin02b.totalInvoiceValue.toString()
                    let splitValue = totalAmountInString.split('.')
                    this.invoiceTotalWholeValueInWords = toWords(splitValue[0]).toUpperCase();
                    this.invoiceTotalDecimalValueInWords = toWords(splitValue[1]).toUpperCase();
                    this.invoiceTotalValueInWords = this.invoiceTotalWholeValueInWords + ' AND CENTS ' + this.invoiceTotalDecimalValueInWords
                }

                this.fin02bService.fetchDistrictById(this.districtId).subscribe(x => {

                    this.district = x
                    if (this.swap == "1") {
                        this.clinicAddress = this.district.officePkdAddress;
                    } else if (this.swap == "2") {
                        this.clinicAddress = this.district.officePpdAddress;
                    }


                })
                this.loading = false;
            })
        }
    }

    openModal(content, modalContent) {
        this.invoiceDate = ''
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
        delete this.fin02b.cimsHistoryFin02b;
        this.fin02b.status = "SAVED";
        this.fin02bService.updateFin02bStatus(this.fin02b).subscribe(x => {
            Swal.fire('', 'FIN 02b Invoice saved successfully!!!', 'success');
        })
    }

    submitStatus() {
        delete this.fin02b.cimsHistoryFin02b;
        this.fin02b.status = 'IN INTERNAL APPROVAL'
        this.fin02bService.updateFin02bStatus(this.fin02b).subscribe(x => {
            Swal.fire('', 'FIN 02b Invoice submitted successfully!!!', 'success');
            history.back()

        })
    }

    updateStatus() {
        this.disableApprove = true
        this.enabler = true
        if (this.fin02b.status == 'IN INTERNAL APPROVAL') { this.fin02b.status = 'FOR APPROVAL TO MOH', this.fin02b.approval1UserId = this.userId }
        else if (this.fin02b.status == 'FOR APPROVAL TO MOH') { this.fin02b.status = 'APPROVED BY MOH', this.fin02b.approval2UserId = this.userId }
        delete this.fin02b.cimsHistoryFin02b;
        this.fin02bService.updateFin02bStatus(this.fin02b).subscribe(x => {
            this.enabler = false
            this.disableApprove = false
            Swal.fire('', 'FIN 02B Invoice approved successfully!!!', 'success');
            history.back()
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

        if (this.fin02b.status == 'FOR APPROVAL TO MOH') {
            if (this.invoiceDate != '') {
                this.invoiceDate = dateFormat(this.invoiceDate, 'dd-mm-yyyy')
                this.lastDate = this.invoiceDate
            }
        }

        let logo = this.logo

        let mySentence = this.invoiceTotalValueInWords;
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

        if (this.fin02b.status == 'FOR APPROVAL TO MOH') {
            if (this.invoiceDate != '') {
                this.lastDate = this.invoiceDate
            }
        }
        else if (this.invoiceDate == '') {
            this.lastDate = ""
        }
        else {
            var d = new Date(this.year, this.months, 0);
            let data = new Date(d);
            this.lastDate = data.getDate() + '/' + (data.getMonth() + 1) + '/' + data.getFullYear()

        }

        let spa = this.invoiceTotalValueInWord.split("-");

        for (let i = 0; i < spa.length; i++) {
            spa[i] = spa[i][0].toUpperCase() + spa[i].substr(1);
        }
        this.invoiceTotalValueInWord = spa.join("-");


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
                                text: 'Unit L3A-6, Level 3A, Wisma Kemajuan, 2 Jalan 19/18, 46300 Petaling Jaya, TEL: +603 7629 6777 FAX: +603 7629 6779 ', alignment: 'center', width: '100%',
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
                            // { text: '**  This is a system generated document from Qubics, no signature is required.', alignment: 'center', width: '100%' },
                            { text: 'Page ' + currentPage.toString() + ' of ' + pageCount, alignment: 'right', margin: [0, 0, 30, 0], width: '20%' },
                            { text: 'Page ' + currentPage.toString() + ' of ' + pageCount, alignment: 'right', margin: [0, 0, 30, 0], width: '80%' }
                        ]
                    },
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
                                text: 'INVOICE FOR MAINTENANCE CHARGES',
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
                            width: '55%',
                            stack: [
                                { text: 'INVOICE TO:', bold: true },
                                {
                                    table: {
                                        body: [
                                            [{
                                                table: {
                                                    widths: ['auto', 'auto', 'auto', 'auto'],
                                                    body: [

                                                        [{ text: 'PKD/PPD', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.fin02b.clinicTypeCode, }, { text: this.fin02b.districtName, margin: [-85, 0, 0, 0] }],

                                                        [{ text: 'ADDRESS', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.clinicAddress, colSpan: 2 }, {}]

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
                                { text: 'FORM FIN 02-B', bold: true, alignment: 'right', fontSize: 12 },
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
                        widths: ['*', '*', '*', '*', '*'],
                        body: [
                            [{ text: 'PKD / PPD NAME', bold: true, alignment: 'center' }, { text: 'REFERENCE', bold: true, alignment: 'center' }, { text: 'GROSS CHARGES (RM)', bold: true, alignment: 'center' },
                            { text: 'PENALTY (RM)', bold: true, alignment: 'center' }, { text: 'NET CHARGES PAYABLE (RM)', bold: true, alignment: 'center' }],
                            [{ text: this.cimsHistoryFin02b.clinicTypeCode + '-' + this.cimsHistoryFin02b.districtName, alignment: 'center' }, { text: 'BASE FEE ' + this.show, alignment: 'center' },
                            { text: formatNumber(this.cimsHistoryFin02b.totalEbeValue, 'en', '1.2-2'), alignment: 'right' }, { text: '-', alignment: 'center' },
                            { text: formatNumber(this.cimsHistoryFin02b.totalEbeValue, 'en', '1.2-2'), alignment: 'right' }],
                            // ['\n', '\n', '\n', '\n', '\n'],
                            // ['\n', '\n', '\n', '\n', '\n'],
                            // ['\n', '\n', '\n', '\n', '\n'],
                            [{ text: 'TOTAL INVOICE AMOUNT (Excluding SST)', colSpan: 2, bold: true }, '', { text: formatNumber(this.fin02b.invoiceBaseValue, 'en', '1.2-2'), bold: true, alignment: 'right' },
                            { text: '-', alignment: 'center' }, { text: formatNumber(this.fin02b.invoiceBaseValue, 'en', '1.2-2'), alignment: 'right', bold: true }],
                            [{ text: 'SST Amount (' + this.sstPercentage + '%)', colSpan: 4, bold: true, border: [false, false, false, false], }, '', '', '', { text: formatNumber(this.fin02b.sst, 'en', '1.2-2'), bold: true, alignment: 'right' }],
                            [{ text: 'TOTAL INVOICE AMOUNT (Inclusive SST)', colSpan: 4, bold: true, border: [false, false, false, false], }, '', '', '', { text: formatNumber(this.fin02b.totalInvoiceValue, 'en', '1.2-2'), bold: true, alignment: 'right' }],
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
                    columns: [
                        {
                            width: '100%',
                            table: {
                                widths: ['*', '*'],
                                body: [
                                    [{ text: 'REMITTANCE DETAILS', bold: true }, ''],
                                    [{ text: 'Payee:', bold: true, italics: true }, { text: 'QUANTUM MEDICAL SOLUTIONS SDN BHD', bold: true }],
                                    [{ text: 'Amount Due:', bold: true, italics: true }, { text: formatNumber(this.fin02b.totalInvoiceValue, 'en', '1.2-2'), bold: true }],
                                    [{ text: 'Bank Details:', bold: true, italics: true }, { text: 'RHB Bank Berhad', bold: true }],
                                    [{ text: 'Bank Account No:', bold: true, italics: true }, { text: '21219800038867', bold: true }],
                                ]
                            },
                            layout: 'lightHorizontalLines'
                        },
                        {}
                    ]
                },

                { text: 'This invoice is system generated and does not require a signature', bold: true, fontSize: 9, width: '100%' },

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