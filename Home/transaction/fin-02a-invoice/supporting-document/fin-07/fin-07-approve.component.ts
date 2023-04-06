import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import * as dateFormat from 'dateformat';
import { Fin02aInvoiceService } from '../../../fin-02a-invoice/fin-02a-invoice-service';
import { Fin07 } from '../../model/fin07';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { toWords } from 'number-to-words';
import pdfMake from "pdfmake/build/pdfmake.min";
import pdfFonts from "pdfmake/build/vfs_fonts";
pdfMake.vfs = pdfFonts.pdfMake.vfs;
import html2canvas from 'html2canvas';
import { formatNumber } from '@angular/common';
import { PagesService } from '../../../../pages.service';

@Component({
    selector: 'fin-07-approve',
    templateUrl: './fin-07-approve.component.html',
    styleUrls: ['./fin-07-approve.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})

export class Fin07ApproveComponent implements OnInit {

    public fin07: Fin07;
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
    public batchNo;
    public instalmentNo;
    public equipmentValue;
    public rentalCharge;
    public clinicAddress = "";
    public equipmentDatas: any;
    public equipmentCount = 0;
    public equipmentsTotalRentalCharge = 0.00;
    public equipmentsTotalRentalChargeInWords: String;
    public equipmentTotalWholeValueInWords: String;
    public equipmentTotalDecimalValueInWords: String;
    public displaySelectedEquip: Boolean;
    public selectedEquipments: any
    public noOfEquip = 0;
    public showSaveSubmitButton;
    public isApproved = false;
    public showButtons = false;
    public modalBodyContent;
    public loading: Boolean = true;
    public logo = ''
    public userId: Number;
    public totalinvoiceinwords: string;
    public invoiceTotalValueInWord: string;
    public disableApprove: boolean;
    public enabler: boolean
    public month: any;
    public year: any;

    constructor(private router: Router, private route: ActivatedRoute, private fin02aservice: Fin02aInvoiceService,
        config: NgbModalConfig, private modalService: NgbModal, private pagesService: PagesService) {
        config.backdrop = 'static';
        config.keyboard = false;
    }

    ngOnInit() {
        this.disableApprove = false
        this.userId = this.pagesService.getUserId();
        this.displaySelectedEquip = false
        if (this.route.params['_value']['_id'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.fin02aservice.fetchDataForFin07Approve(par['_id'])).subscribe(x => {
                this.fin07 = x;

                this.equipmentDatas = x.cimsHistoryFin02a;
                this.equipmentDatas.sort((a, b) => (a.beNumber > b.beNumber) ? 1 : -1)
                this.month = x.month
                this.year = x.year
                this.stateName = x.stateName;
                this.districtName = x.districtName;
                this.clinicName = x.clinic.clinicName;
                this.clinicCode = x.clinic.clinicCode;
                this.clinicAddress = x.clinic.address;
                this.monthYear = x.month + '/' + x.year;
                this.date = dateFormat(x.date, 'dd-mm-yyyy');
                this.invoiceDate = this.date;
                this.dateLastdate = this.lastDate;
                this.equipmentCount = x.totalEquipmentCount;
                this.equipmentsTotalRentalCharge = x.totalRentalCharge
                this.docRef = this.fin07.status == 'CREATED' || this.fin07.status == 'SAVED' ? "" : this.fin07.code;
                this.showButtons = this.fin07.status == 'APPROVED BY MOH' ? false : true;
                this.isApproved = this.fin07.status == 'APPROVED BY MOH' ? true : false;
                this.showSaveSubmitButton = this.fin07.status == 'IN INTERNAL APPROVAL' || this.fin07.status == 'FOR APPROVAL TO MOH' ? false : true;
                if (this.equipmentsTotalRentalCharge % 1 == 0) {
                    this.equipmentsTotalRentalChargeInWords = toWords(this.equipmentsTotalRentalCharge).toUpperCase();
                } else {
                    let totalAmountInString = this.equipmentsTotalRentalCharge.toString()
                    let splitValue = totalAmountInString.split('.')
                    this.equipmentTotalWholeValueInWords = toWords(splitValue[0]).toUpperCase();
                    this.equipmentTotalDecimalValueInWords = toWords(splitValue[1]).toUpperCase();
                    this.equipmentsTotalRentalChargeInWords = this.equipmentTotalWholeValueInWords + ' AND CENTS ' + this.equipmentTotalDecimalValueInWords
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
        delete this.fin07.cimsHistoryFin02a;
        this.fin07.status = "SAVED";
        this.fin02aservice.updateFin07Status(this.fin07).subscribe(x => {
            Swal.fire('', 'Fin 07 saved successfully!!!', 'success');
        })
    }

    submitStatus() {
        delete this.fin07.cimsHistoryFin02a;
        this.fin07.status = 'IN INTERNAL APPROVAL'
        this.fin02aservice.updateFin07Status(this.fin07).subscribe(x => {
            Swal.fire('', 'Fin 07 submitted successfully!!!', 'success');
            history.back()
            // this.router.navigateByUrl('/transaction/fin-01-invoice/fin-06-list')
        })
    }

    updateStatus() {
        this.disableApprove = true
        this.enabler = true
        if (this.fin07.status == 'IN INTERNAL APPROVAL') { this.fin07.status = 'FOR APPROVAL TO MOH', this.fin07.approval1UserId = this.userId }
        else if (this.fin07.status == 'FOR APPROVAL TO MOH') { this.fin07.status = 'APPROVED BY MOH', this.fin07.approval2UserId = this.userId }
        delete this.fin07.cimsHistoryFin02a;
        this.fin02aservice.updateFin07Status(this.fin07).subscribe(x => {
            this.enabler = false
            Swal.fire('', 'Fin 07 approved successfully!!!', 'success');
            history.back()
            this.disableApprove = false
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

    generatePDF() {

        if (this.fin07.status == 'FOR APPROVAL TO MOH') {
            if (this.invoiceDate != '') {
                this.invoiceDate = dateFormat(this.invoiceDate, 'dd-mm-yyyy')
            }
        }

        let logo = this.logo
        let approval2UserName = this.fin07.approval2UserName == null ? '' : this.fin07.approval2UserName;
        let approval2UserDesignation = this.fin07.approval2UserDesignation == null ? '' : this.fin07.approval2UserDesignation;
        let approval2Date = this.fin07.approval2Date == null ? '' : dateFormat(this.fin07.approval2Date, 'dd-mm-yyyy');

        let mySentence = this.equipmentsTotalRentalChargeInWords;
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

        if (this.fin07.status == 'FOR APPROVAL TO MOH') {
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

        let tempLastDate = this.lastDate;

        var docDefinition = {
            pageMargins: [30, 135, 30, 100],
            header: function (currentPage) {
                // if (currentPage == 1) {
                let header = [



                    {
                        image: logo,
                        fit: [275, 125],
                        alignment: 'center',
                        margin: [0, 0, 0, 10]
                    },



                ]
                return header;
                // }
                // else {
                //     let header = [



                //         {
                //             image: logo,
                //             fit: [275, 125],
                //             alignment: 'center',
                //             margin: [0, 0, 0, 10]
                //         },
                //         {
                //             columns: [
                //                 {},
                //                 {},
                //                 {
                //                     text: 'DOC REF : ' + doc, bold: true,
                //                     // text: doc, alignment: 'right', bold: true 
                //                 }
                //             ]

                //         }


                //     ]

                //     return header;
                // }

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
                                    // { text: 'DATE:', bold: true }
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
                                    { text: 'DATE                 : ', bold: true },
                                    //{ text: 'DATE                 : ' + approval2Date, bold: true }
                                ], margin: [0, 0, 30, 0]
                            }
                        ],
                    },
                    {
                        columns: [
                            {
                                text: 'This invoice is system generated and does not required a signature.', fontSize: 9, alignment: 'right', width: '70%'
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
                        widths: ['*'],
                        //widths: ['auto', 'auto', 'auto', 'auto', 'auto'],
                        body: [
                            [{
                                text: 'SUMMARY OF RENTAL CHARGES FOR NEW BIOMEDICAL EQUIPMENT INSTALLED',
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
                    margin: [235, 0, 0, 0],
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
                                                        [{ text: 'CLINIC NAME', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.clinicName }],
                                                        [{ text: 'CLINIC ADDRESS', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.clinicAddress }],
                                                        [{ text: 'CLINIC CODE', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.clinicCode }],
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
                                { text: 'FORM FIN 07', bold: true, alignment: 'right' },
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

                // {
                //     width: '30%',
                //     stack: [

                //         {
                //             table: {
                //                 widths: ['auto', 'auto'],
                //                 body: [
                //                     [{ text: 'DOC REF:', bold: true }, { text: doc, alignment: 'right', bold: true }]


                //                 ],
                //             }
                //         }
                //     ]
                // },
                {
                    table: {
                        headerRows: 2,
                        widths: ['*', 'auto', 'auto', 'auto', 'auto', 'auto', 'auto'],
                        body: [
                            [{ text: 'ASSET DETAILS', colSpan: 2, bold: true, alignment: 'center' }, '', { text: 'NEW BIOMEDICAL EQUIPMENT INSTALLED TO DATE', colSpan: 5, bold: true, alignment: 'center' }, '', '', '', ''],
                            [{ text: 'ASSET NAME', bold: true, alignment: 'center' }, { text: 'BE NUMBER', bold: true, alignment: 'center' }, { text: 'BATCH NO', bold: true, alignment: 'center' },
                            { text: 'INSTALLMENT NO', bold: true, alignment: 'center' }, { text: 'VALUE OF EQUIPMENT (RM)', bold: true, alignment: 'center' }, { text: 'RENTAL CHARGES (RM)', bold: true, alignment: 'center' },
                            { text: 'CERT OF T&C REF', bold: true, alignment: 'center' }],
                            ...this.equipmentDatas.map(data => {
                                return [{ text: data.assetName }, { text: data.beNumber, alignment: 'center' }, { text: data.batchNumber, alignment: 'center' },
                                { text: data.installmentNumber, alignment: 'center' }, { text: formatNumber(data.valueOfEquipment, 'en', '1.2-2'), alignment: 'right' }, { text: formatNumber(data.rentalCharges, 'en', '1.2-2'), alignment: 'right' },
                                { text: data.tcCertificate, alignment: 'center' }]
                            }),
                            [{ text: 'TOTAL', bold: true, alignment: 'center' }, { text: this.equipmentCount, bold: true, alignment: 'center' }, '', '', '', { text: formatNumber(this.equipmentsTotalRentalCharge, 'en', '1.2-2'), bold: true, alignment: 'right' }, '']
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
            pageOrientation: 'portrait',
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