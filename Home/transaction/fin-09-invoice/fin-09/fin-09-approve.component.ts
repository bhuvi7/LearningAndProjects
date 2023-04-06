import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { Fin09InvoiceService } from '../fin-09-invoice-service';
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

@Component({
    selector: 'fin-09-approve',
    templateUrl: './fin-09-approve.component.html',
    styleUrls: ['./fin-09-approve.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})

export class Fin09ApproveComponent implements OnInit {

    public invoice: any;
    public clinicTypeCode: any;
    public districtName;
    public stateName;
    public monthYear;
    public docRef = "";
    public date;
    public invoiceDate;
    public lastDate;
    public clinicDatas: any;
    public invoiceTotalValue = 0.0;
    public sstAmount = 0.0;
    public totalInvoiceAmount = 0.0;
    public invoiceTotalValueInWords: String;
    public invoiceTotalWholeValueInWords: String;
    public invoiceTotalDecimalValueInWords: String;
    public showSaveSubmitButton;
    public showButtons = false;
    public isApproved = false;
    public modalBodyContent;
    public loading: Boolean = true;
    public logo = ''
    public totalinvoiceinwords: string;
    public invoiceTotalValueInWord: string;

    constructor(private router: Router, private route: ActivatedRoute, private fin09Service: Fin09InvoiceService,
        config: NgbModalConfig, private modalService: NgbModal) {
        config.backdrop = 'static';
        config.keyboard = false;
    }

    ngOnInit() {
        if (this.route.params['_value']['_id'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.fin09Service.fetchDataForFin09Approve(par['_id'])).subscribe(x => {
                this.invoice = x;
                this.clinicDatas = x.fin09Clinic;
                this.clinicDatas.forEach(element => {
                    element.dateDisplay = dateFormat(new Date(), 'dd-mm-yyyy')
                });
                this.clinicTypeCode = x.clinicTypeCode;
                this.districtName = x.districtName;
                this.stateName = x.stateName;
                this.monthYear = x.month + '/' + x.year;
                this.date = dateFormat(x.date, 'dd-mm-yyyy')
                this.invoiceDate = this.date
                this.docRef = this.invoice.status == 'CREATED' || this.invoice.status == 'SAVED' ? "" : this.invoice.code;
                this.showButtons = this.invoice.status == 'APPROVED BY MOH' ? false : true;
                this.isApproved = this.invoice.status == 'APPROVED BY MOH' ? true : false;
                this.showSaveSubmitButton = this.invoice.status == 'IN INTERNAL APPROVAL' || this.invoice.status == 'FOR APPROVAL TO MOH' ? false : true;
                if (this.totalInvoiceAmount % 1 == 0) {
                    this.invoiceTotalValueInWords = toWords(this.totalInvoiceAmount).toUpperCase();
                } else {
                    let totalAmountInString = this.totalInvoiceAmount.toString()
                    let splitValue = totalAmountInString.split('.')
                    this.invoiceTotalWholeValueInWords = toWords(splitValue[0]).toUpperCase();
                    this.invoiceTotalDecimalValueInWords = toWords(splitValue[1]).toUpperCase();
                    this.invoiceTotalValueInWords = this.invoiceTotalWholeValueInWords + ' AND CENTS ' + this.invoiceTotalDecimalValueInWords
                }
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
        delete this.invoice.fin09Clinic;
        this.invoice.status = "SAVED";
        this.fin09Service.updateFin09Status(this.invoice).subscribe(x => {
            Swal.fire('', 'FIN 09 saved successfully!!!', 'success');
        })
    }

    submitStatus() {
        delete this.invoice.fin09Clinic;
        this.invoice.status = 'IN INTERNAL APPROVAL'
        this.fin09Service.updateFin09Status(this.invoice).subscribe(x => {
            Swal.fire('', 'FIN 09 submitted successfully!!!', 'success');
            history.back()
        })
    }

    updateStatus() {
        if (this.invoice.status == 'IN INTERNAL APPROVAL') { this.invoice.status = 'FOR APPROVAL TO MOH' }
        else if (this.invoice.status == 'FOR APPROVAL TO MOH') { this.invoice.status = 'APPROVED BY MOH' }
        delete this.invoice.fin09Clinic;
        this.fin09Service.updateFin09Status(this.invoice).subscribe(x => {
            Swal.fire('', 'FIN 09 approved successfully!!!', 'success');
            history.back()
        })

    }

    navToList() {
        history.back()
        //this.router.navigate(['/transaction/fin-04-invoice/fin-04-list'])
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

        let clinicDatas = []
        let index = 1

        this.clinicDatas.forEach(clinic => {
            clinicDatas.push([
                { text: index, alignment: 'center' },
                clinic.clinicName,
                { text: clinic.clinicCode, alignment: 'center' },
                { text: formatNumber(clinic.responseTimePenalty, 'en', '1.2-2'), alignment: 'right' },
                { text: formatNumber(clinic.repairTimePenalty, 'en', '1.2-2'), alignment: 'right' },
                { text: formatNumber(clinic.uptimePenalty, 'en', '1.2-2'), alignment: 'right' },
                { text: formatNumber(clinic.scheduledMaintenancePenalty, 'en', '1.2-2'), alignment: 'right' },
                { text: formatNumber(clinic.lateDeliveryPenalty, 'en', '1.2-2'), alignment: 'right' },
                { text: formatNumber(clinic.totalPenalty, 'en', '1.2-2'), alignment: 'right' }
            ])
            index++;
        });

        if (this.invoice.status == 'FOR APPROVAL TO MOH') {
            if (this.invoiceDate != '') {
                this.lastDate = this.invoiceDate
            }
        }
        else if (this.invoiceDate == '') {
            this.lastDate = ""
        }
        else {
            let date = this.monthYear.split('')
            var lastday = function (y, m) {
                return new Date(y, m + 1, 0).getDate();
            }
            this.lastDate = lastday(date[0], date[1]) + '/' + this.monthYear
        }

        var docDefinition = {
            //header: 'Invoice Report - Total Invoice Value: ' + this.totalInvoiceValue,
            footer: function (currentPage, pageCount) {
                let footerText = {
                    text: 'Page ' + currentPage.toString() + ' of ' + pageCount, alignment: 'right', margin: [0, 0, 40, 0]
                }
                return footerText;
            },
            // header: function (currentPage, pageCount, pageSize) {
            //     // you can apply any logic and return any valid pdfmake element

            //     return [
            //         { text: 'simple text', alignment: (currentPage % 2) ? 'left' : 'right' },
            //         { canvas: [{ type: 'rect', x: 170, y: 32, w: pageSize.width - 170, h: 40 }] }
            //     ]
            // },
            content: [
                {
                    image: this.logo,
                    fit: [275, 125],
                    alignment: 'center',
                    margin: [0, -30, 0, 10]
                },
                {
                    table: {
                        widths: ['*'],
                        //widths: ['auto', 'auto', 'auto', 'auto', 'auto'],
                        body: [
                            [{
                                text: 'SUMMARY OF PENALTY REPORTS',
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
                    margin: [355, 0, 0, 0],
                    alignment: 'center',
                },
                {
                    text: '\n'
                },
                {
                    columns: [
                        {
                            width: '40%',
                            stack: [
                                {
                                    table: {
                                        body: [
                                            [{
                                                table: {
                                                    widths: ['*', '*', '*'],
                                                    body: [
                                                        [{ text: 'STATE', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.stateName }],
                                                        [{ text: 'DISTRICT', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.districtName }],
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
                            width: '30%',
                            stack: [
                                { text: 'FORM FIN 09', bold: true, alignment: 'right' },
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
                        headerRows: 2,
                        widths: ['auto', 125, 'auto', 'auto', 'auto', 'auto', 'auto', 'auto', 'auto'],
                        body: [
                            [{ text: 'Sl No', rowSpan: 2, bold: true, alignment: 'center' }, { text: 'CLINIC NAME', rowSpan: 2, bold: true, alignment: 'center' }, { text: 'CLINIC CODE', rowSpan: 2, bold: true, alignment: 'center' },
                            { text: 'RESPONSE TIME PENALTY (RM)', bold: true, alignment: 'center' }, { text: 'REPAIR TIME PENALTY (RM)', bold: true, alignment: 'center' }, { text: 'UPTIME PENALTY (RM)', bold: true, alignment: 'center' },
                            { text: 'SCHEDULED MAINTENANCE PENALTY (RM)', bold: true, alignment: 'center' }, { text: 'LATE DELIVERY PENALTY (RM)', bold: true, alignment: 'center' }, { text: 'TOTAL PENALTY (RM)', rowSpan: 2, bold: true, alignment: 'center' }],
                            ['', '', '', { text: 'FIN 09-A', bold: true, alignment: 'center', margin: [0, 0, 0, 0] }, { text: 'FIN 09-B', bold: true, alignment: 'center', margin: [0, 0, 0, 0] }, { text: 'FIN 09-C', bold: true, alignment: 'center', margin: [0, 0, 0, 0] },
                                { text: 'FIN 09-D', bold: true, alignment: 'center', margin: [0, 0, 0, 0] }, { text: 'FIN 09-E', bold: true, alignment: 'center', margin: [0, 0, 0, 0] }, ''],
                            ...clinicDatas,
                            ['\n', '\n', '\n', '\n', '\n', '\n', '\n', '\n', '\n',],
                            [{ text: 'TOTAL', colSpan: 3, bold: true }, '', '', { text: formatNumber(this.invoice.totalResponseTimePenalty, 'en', '1.2-2'), bold: true, alignment: 'right' }, { text: formatNumber(this.invoice.totalRepairTimePenalty, 'en', '1.2-2'), bold: true, alignment: 'right' },
                            { text: formatNumber(this.invoice.totalUptimePenalty, 'en', '1.2-2'), bold: true, alignment: 'right' }, { text: formatNumber(this.invoice.totalScheduledMaintenancePenalty, 'en', '1.2-2'), bold: true, alignment: 'right' },
                            { text: formatNumber(this.invoice.totalLateDeliveryPenalty, 'en', '1.2-2'), bold: true, alignment: 'right' }, { text: formatNumber(this.invoice.totalPenalty, 'en', '1.2-2'), bold: true, alignment: 'right' }]
                        ]
                    }
                },
                {
                    text: '\n'
                },
                {
                    columns: [
                        {
                            stack: [
                                { text: 'PREPARED BY,', bold: true },
                                // { text: '................', bold: true },
                                { text: 'QMS FINANCE', bold: true },
                                // { text: 'DATE:', bold: true }
                            ],
                        },
                        {
                            stack: [
                                { text: 'REVIEWED BY,', bold: true },
                                { text: 'WAN ARMAN WAN GHOPA', bold: true },
                                { text: 'QMS CENTRAL ZONE MANAGER', bold: true },
                                // { text: 'DATE:', bold: true }
                            ],
                        },
                        {
                            stack: [
                                { text: 'VERIFIED BY,', bold: true },
                                { text: 'DR. ZUZANA BINTI SELAMAT', bold: true },
                                // { text: 'PKD/PPD CLINICS', bold: true },
                                // { text: 'NAME & DESIGNATION :', bold: true },
                                // { text: 'DATE:', bold: true }
                            ],
                        }
                    ]
                },
                {
                    text: '**  This is a system generated document from Qubics, no signature is required.'
                }
            ],
            pageSize: 'A4',
            pageOrientation: 'landscape',
            defaultStyle: {
                font: pdfMake.vfs.Roboto,
                fontSize: 10,
                lineHeight: 1.3
            },
            preserveSpace: {
                preserveLeadingSpaces: true
            }
        };

        pdfMake.createPdf(docDefinition).download('Invoice ' + this.docRef + '.pdf');
    }
}