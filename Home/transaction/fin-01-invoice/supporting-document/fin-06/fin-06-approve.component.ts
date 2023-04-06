import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import * as dateFormat from 'dateformat';
import { Fin01InvoiceService } from '../../fin-01-invoice-service';
import { Fin06 } from '../../model/fin06';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { toWords } from 'number-to-words';
import pdfMake from "pdfmake/build/pdfmake.min";
import pdfFonts from "pdfmake/build/vfs_fonts";
pdfMake.vfs = pdfFonts.pdfMake.vfs;
import html2canvas from 'html2canvas';
import { formatNumber } from '@angular/common';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { PagesService } from '../../../../pages.service';

@Component({
    selector: 'fin-06-approve',
    templateUrl: './fin-06-approve.component.html',
    styleUrls: ['./fin-06-approve.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})

export class Fin06ApproveComponent implements OnInit {

    public fin06: Fin06;
    public stateName;
    public districtName;
    public clinicName;
    public clinicCode;
    public monthYear;
    public docRef;
    public date;
    public invoiceDate;
    public dateLastdate;
    public lastDate;
    public equipmentDatas: any;
    public equipmentCount = 0;
    public equipmentTotalValue = 0.00;
    public equipmentTotalValueInWords: String;
    public equipmentTotalWholeValueInWords: String;
    public equipmentTotalDecimalValueInWords: String;
    public displaySelectedEquip: Boolean;
    public selectedEquipments: any
    public noOfEquip;
    public showSaveSubmitButton;
    public isApproved = false;
    public showButtons = false;
    public modalBodyContent;
    public loading: Boolean = true;
    public logo = ''
    public userId: Number;
    public totalinvoiceinwords: string;
    public invoiceTotalValueInWord: string;
    public month: any;
    public year: any;

    constructor(private router: Router, private route: ActivatedRoute, private fin01service: Fin01InvoiceService,
        config: NgbModalConfig, private modalService: NgbModal, private pagesService: PagesService) {
        config.backdrop = 'static';
        config.keyboard = false;
    }

    ngOnInit() {
        this.userId = this.pagesService.getUserId();
        this.displaySelectedEquip = false
        if (this.route.params['_value']['_id'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.fin01service.fetchDataForFin06Approve(par['_id'])).subscribe(x => {
                this.fin06 = x;
                this.equipmentDatas = x.cimsHistoryFin01;
                this.equipmentDatas.forEach(element => {
                    this.equipmentTotalValue += element.purchaseAmount
                    element.checked = false
                    element.installedDateDisplay = dateFormat(element.installedDate, 'dd-mm-yyyy')
                    element.purchasedDateDisplay = dateFormat(element.purchasedDate, 'dd-mm-yyyy')
                    element.acceptedDateDisplay = dateFormat(element.acceptedDate, 'dd-mm-yyyy')
                });
                this.selectedEquipments = []
                this.stateName = x.stateName;
                this.month = x.month
                this.year = x.year
                this.districtName = x.districtName;
                this.clinicName = x.clinic.clinicName;
                this.clinicCode = x.clinic.clinicCode;
                this.monthYear = x.month + '/' + x.year;
                this.date = dateFormat(x.date, 'dd-mm-yyyy');
                this.invoiceDate = this.date;
                this.dateLastdate = this.date;
                this.equipmentCount = this.equipmentDatas.length;
                this.docRef = this.fin06.status == 'CREATED' || this.fin06.status == 'SAVED' ? "" : this.fin06.code;
                this.showButtons = this.fin06.status == 'APPROVED BY MOH' ? false : true;
                this.isApproved = this.fin06.status == 'APPROVED BY MOH' ? true : false;
                this.showSaveSubmitButton = this.fin06.status == 'IN INTERNAL APPROVAL' || this.fin06.status == 'FOR APPROVAL TO MOH' ? false : true;
                if (this.equipmentTotalValue % 1 == 0) {
                    this.equipmentTotalValueInWords = toWords(this.equipmentTotalValue).toUpperCase();
                } else {
                    let totalAmountInString = this.equipmentTotalValue.toString()
                    let splitValue = totalAmountInString.split('.')
                    this.equipmentTotalWholeValueInWords = toWords(splitValue[0]).toUpperCase();
                    this.equipmentTotalDecimalValueInWords = toWords(splitValue[1]).toUpperCase();
                    this.equipmentTotalValueInWords = this.equipmentTotalWholeValueInWords + ' AND CENTS ' + this.equipmentTotalDecimalValueInWords
                }
                this.loading = false;
            });
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
        delete this.fin06.cimsHistoryFin01;
        this.fin06.status = "SAVED";
        this.fin01service.updateFin06Status(this.fin06).subscribe(x => {
            Swal.fire('', 'Fin 06 saved successfully!!!', 'success');
        })
    }

    submitStatus() {
        delete this.fin06.cimsHistoryFin01;
        this.fin06.status = 'IN INTERNAL APPROVAL'
        this.fin01service.updateFin06Status(this.fin06).subscribe(x => {
            Swal.fire('', 'Fin 06 submitted successfully!!!', 'success');
            history.back()
            // this.router.navigateByUrl('/transaction/fin-01-invoice/fin-06-list')
        })
    }

    updateStatus() {
        if (this.fin06.status == 'IN INTERNAL APPROVAL') { this.fin06.status = 'FOR APPROVAL TO MOH', this.fin06.approval1UserId = this.userId }
        else if (this.fin06.status == 'FOR APPROVAL TO MOH') { this.fin06.status = 'APPROVED BY MOH', this.fin06.approval2UserId = this.userId }
        delete this.fin06.cimsHistoryFin01;
        this.fin01service.updateFin06Status(this.fin06).subscribe(x => {
            Swal.fire('', 'Fin 06 approved successfully!!!', 'success');
            history.back()
            // this.router.navigateByUrl('/transaction/fin-01-invoice/fin-06-list')
        })

    }

    navToList() {
        history.back()
        // this.router.navigate(['/transaction/fin-01-invoice/fin-06-list'])
    }

    public downloadPdf() {
        var data = document.getElementById("pdfImage");
        html2canvas(data, { scale: 1.5, scrollY: -window.scrollY }).then(canvas => {
            this.logo = canvas.toDataURL('image/png')
            this.generatePDF()
        });

    }
    //capitalize only the first letter of the string.
    capitalizeFirstLetter(string) {
        return string.charAt(0).toUpperCase() + string.slice(1);
    }
    //capitalize all words of a string.
    capitalizeWords(string) {
        return string.replace(/(?: ^|\s)\S/g, function (a) { return a.toUpperCase(); });
    };

    generatePDF() {

        if (this.fin06.status == 'FOR APPROVAL TO MOH') {
            if (this.invoiceDate != '') {
                this.invoiceDate = dateFormat(this.invoiceDate, 'dd-mm-yyyy')
                this.lastDate = this.invoiceDate
            }
        }

        let logo = this.logo
        let approval2UserName = this.fin06.approval2UserName == null ? '' : this.fin06.approval2UserName;
        let approval2UserDesignation = this.fin06.approval2UserDesignation == null ? '' : this.fin06.approval2UserDesignation;
        let approval2Date = this.fin06.approval2Date == null ? '' : dateFormat(this.fin06.approval2Date, 'dd-mm-yyyy');

        let mySentence = this.equipmentTotalValueInWords;
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

        let doc = this.docRef.split("-").slice(0, -1).join("-")

        if (this.fin06.status == 'FOR APPROVAL TO MOH') {
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
        let tempLastDate = this.dateLastdate;

        var docDefinition = {
            pageMargins: [30, 135, 30, 100],
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
                                stack: [
                                    { text: 'Prepared By,', bold: true },
                                    { text: 'HJ MOHD FAIZAL BIN HJ ABDUL MAJID', bold: true },
                                    { text: 'SVP - Corporate Finance', bold: true },
                                    { text: 'DATE:' + tempLastDate, bold: true }
                                ], margin: [30, 0, 0, 0]
                            },
                            {
                                stack: [
                                    { text: 'Checked By,', bold: true },
                                    { text: 'PRAMODAN CHINGANTAVIDA', bold: true },
                                    { text: 'General Manager', bold: true },
                                    { text: 'Technical Service Department', bold: true },
                                    { text: 'DATE:' + tempLastDate, bold: true }
                                ], margin: [30, 0, 0, 0]
                            },
                            {
                                stack: [

                                    { text: 'Verified By,', bold: true },
                                    { text: 'PKD/PPD CLINICS', bold: true },
                                    { text: 'NAME               : ', bold: true },
                                    { text: 'DESIGNATION : ', bold: true },
                                    // { text: 'NAME               : ' + approval2UserName, bold: true },
                                    // { text: 'DESIGNATION : ' + approval2UserDesignation, bold: true },
                                    { text: 'DATE                 : ', bold: true }
                                ], margin: [0, 0, 30, 0]
                            }
                        ],
                    },
                    {
                        columns: [
                            {
                                text: 'This is a system generated document, no signature is required.', fontSize: 9, alignment: 'right', width: '70%'
                            },
                            {},
                            { text: 'Page ' + currentPage.toString() + ' of ' + pageCount, alignment: 'right', margin: [0, 0, 30, 0], width: '20%' }
                        ]
                    }
                ]
                return footerText;
            },
            content: [
                {
                    table: {
                        body: [
                            [{
                                text: 'FOR ' + this.monthYear, bold: true,
                            },],
                        ]
                    },
                    margin: [355, 0, 0, 10],
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
                                //{ text: 'INVOICE TO:', bold: true },
                                {
                                    table: {
                                        body: [
                                            [{
                                                table: {
                                                    widths: ['auto', 'auto', '*'],
                                                    body: [
                                                        [{ text: 'STATE', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.stateName }],
                                                        [{ text: 'DISTRICT', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.districtName }],
                                                        [{ text: 'CLINIC NAME', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.clinicName }],
                                                        [{ text: 'CLINIC CODE', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.clinicCode }],
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
                                { text: 'FORM FIN 06', bold: true, alignment: 'right', },
                                {
                                    table: {
                                        widths: ['*', 'auto'],
                                        body: [
                                            [{ text: 'DOC REF:', bold: true }, { text: doc, alignment: 'right', bold: true }],
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
                        widths: ['*', 'auto', 'auto', 'auto', 'auto', 'auto'],
                        body: [
                            [{ text: 'PURCHASED BIOMEDICAL EQUIPMENT', colSpan: 6, bold: true, alignment: 'center' }, '', '', '', '', ''],
                            [{ text: 'ASSET NAME', bold: true, alignment: 'center' }, { text: 'BE NUMBER', bold: true, alignment: 'center' }, { text: 'SERIAL NO', bold: true, alignment: 'center' },
                            { text: 'MODEL NO', bold: true, alignment: 'center' }, { text: 'VALUE (RM)', bold: true, alignment: 'center' }, { text: 'CERT OF T&C REF', bold: true, alignment: 'center' }],
                            ...this.equipmentDatas.map(data => {
                                return [{ text: data.assetName }, { text: data.beNumber, alignment: 'center' }, { text: data.serialNumber, alignment: 'center' },
                                { text: data.modelNumber, alignment: 'center' }, { text: formatNumber(data.purchaseAmount, 'en', '1.2-2'), alignment: 'right' }, { text: data.tcCertificate, alignment: 'center' }]
                            }),
                            // ['\n', '\n', '\n', '\n', '\n', '\n'],
                            // ['\n', '\n', '\n', '\n', '\n', '\n'],
                            // ['\n', '\n', '\n', '\n', '\n', '\n'],
                            [{ text: 'TOTAL', bold: true, alignment: 'center' }, { text: this.equipmentCount, bold: true, alignment: 'center' }, '', '', { text: formatNumber(this.equipmentTotalValue, 'en', '1.2-2'), bold: true, alignment: 'right' }, '']
                        ]
                    }
                },
                {
                    text: '\n'
                },
                {
                    text: '(Ringgit Malaysia: ' + this.invoiceTotalValueInWord + ' Only)', bold: true, italics: true
                },
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

        pdfMake.createPdf(docDefinition).download('Supporting Document ' + this.docRef + '.pdf');
    }
}