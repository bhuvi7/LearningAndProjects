import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import * as dateFormat from 'dateformat';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { Fin02aInvoiceService } from "src/app/pages/home/transaction/fin-02a-invoice/fin-02a-invoice-service";
import { Fin07 } from '../../model/fin07';
import 'rxjs/add/operator/switchMap';
import { toWords } from 'number-to-words';
import pdfMake from "pdfmake/build/pdfmake.min";
import pdfFonts from "pdfmake/build/vfs_fonts";
pdfMake.vfs = pdfFonts.pdfMake.vfs;
import html2canvas from 'html2canvas';
import { formatNumber } from '@angular/common';
import { PagesService } from '../../../../pages.service';

@Component({
    selector: 'fin-03a-approve',
    templateUrl: './fin-03a-approve.component.html',
    styleUrls: ['./fin-03a-approve.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})

export class Fin03aApproveComponent implements OnInit {

    public enableSaveButton: Boolean = window.history.state.approvalPage ? true : false;

    public fin03a: any;
    public clinicTypeCode: any;
    public districtName;
    public monthYear;
    public docRef = "";
    public date;
    public invoiceDate;
    public lastDate;
    public clinicDatas: any;
    public noOfNewBEUnits;
    public districtId: number;
    public clinicTypeId: number;
    public district;
    public districtAddress: string;
    public rentalCharge;
    public equipmentTotalCount = 0;
    public totalRentalCharge = 0;
    public totalRentalChargeInWords: String;
    public equipmentTotalRentalChargeWholeValueInWords: String;
    public equipmentTotalRentalChargeDecimalValueInWords: String;
    public showSaveSubmitButton;
    public isApproved = false;
    public showButtons = false;
    public modalBodyContent;
    public loading: Boolean = true;
    public fin07: Fin07;
    public logo = ''
    public userId: Number;
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
        if (this.route.params['_value'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.fin02aService.fetchDataForFin03aApprove(par['_id'])).subscribe(x => {
                this.fin03a = x;
                this.clinicDatas = x.fin07;

                this.clinicDatas.sort((a, b) => (a.code > b.code) ? 1 : -1)
                this.clinicDatas.forEach(element => {
                    element.dateDisplay = dateFormat(new Date(), 'dd-mm-yyyy')
                });
                if (x.stateName == 'SARAWAK') {
                    if (x.clinicTypeCode == 'PKD') {
                        this.clinicTypeCode = 'PKB'
                    } else if (x.clinicTypeCode == 'PPD') {
                        this.clinicTypeCode = 'PPB'
                    }
                } else {
                    this.clinicTypeCode = x.clinicTypeCode;
                }
                this.equipmentTotalCount = x.totalEquipmentCount
                this.totalRentalCharge = x.totalRentalCharge
                this.districtName = x.districtName;
                this.districtId = x.districtId;
                this.month = x.month
                this.year = x.year
                this.clinicTypeId = x.clinicTypeId;
                this.monthYear = x.month + '/' + x.year;
                this.date = dateFormat(x.date, 'dd-mm-yyyy')
                this.invoiceDate = this.date
                this.docRef = this.fin03a.status == 'CREATED' || this.fin03a.status == 'SAVED' ? "" : this.fin03a.code;
                this.isApproved = this.fin03a.status == 'APPROVED BY MOH' ? true : false;
                this.showButtons = this.fin03a.status == 'APPROVED BY MOH' ? false : true;
                this.showSaveSubmitButton = this.fin03a.status == 'IN INTERNAL APPROVAL' || this.fin03a.status == 'FOR APPROVAL TO MOH' ? false : true;
                if (this.totalRentalCharge % 1 == 0) {
                    this.totalRentalChargeInWords = toWords(this.totalRentalCharge).toUpperCase();
                } else {
                    let totalAmountInString = this.totalRentalCharge.toString()
                    let splitValue = totalAmountInString.split('.')
                    this.equipmentTotalRentalChargeWholeValueInWords = toWords(splitValue[0]).toUpperCase();
                    this.equipmentTotalRentalChargeDecimalValueInWords = toWords(splitValue[1]).toUpperCase();
                    this.totalRentalChargeInWords = this.equipmentTotalRentalChargeWholeValueInWords + ' AND CENTS ' + this.equipmentTotalRentalChargeDecimalValueInWords
                }
                // 
                this.fin02aService.fetchDistrictById(this.districtId).subscribe(x => {
                    this.district = x
                    if (this.clinicTypeId == 1) {

                        this.districtAddress = this.district.officePkdAddress;
                    } else if (this.clinicTypeId == 2) {
                        this.districtAddress = this.district.officePpdAddress;
                    }
                    // 	

                })
            })
        }

        this.loading = false;
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
        delete this.fin03a.fin07;
        this.fin03a.status = "SAVED";
        this.fin02aService.updateFin03aStatus(this.fin03a).subscribe(x => {
            Swal.fire('', 'Fin 03A saved successfully!!!', 'success')
        })
    }

    submitStatus() {
        delete this.fin03a.fin07;
        this.fin03a.status = 'IN INTERNAL APPROVAL'
        this.fin02aService.updateFin03aStatus(this.fin03a).subscribe(x => {
            Swal.fire('', 'Fin 03A submitted successfully!!!', 'success')
            history.back()
            // this.router.navigateByUrl('/transaction/fin-01-invoice/fin-01-list')
        })
    }

    updateStatus() {
        this.disableApprove = true
        this.enabler = true
        if (this.fin03a.status == 'IN INTERNAL APPROVAL') { this.fin03a.status = 'FOR APPROVAL TO MOH', this.fin03a.approval1UserId = this.userId }
        else if (this.fin03a.status == 'FOR APPROVAL TO MOH') { this.fin03a.status = 'APPROVED BY MOH', this.fin03a.approval2UserId = this.userId }
        delete this.fin03a.fin07;
        this.fin02aService.updateFin03aStatus(this.fin03a).subscribe(x => {
            this.enabler = false
            Swal.fire('', 'Fin 03A approved successfully!!!', 'success');
            history.back();
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

        if (this.fin03a.status == 'FOR APPROVAL TO MOH') {
            if (this.invoiceDate != '') {
                this.invoiceDate = dateFormat(this.invoiceDate, 'dd-mm-yyyy')
            }
        }

        let logo = this.logo
        let approval2UserName = this.fin03a.approval2UserName == null ? '' : this.fin03a.approval2UserName;
        let approval2UserDesignation = this.fin03a.approval2UserDesignation == null ? '' : this.fin03a.approval2UserDesignation;
        let approval2Date = this.fin03a.approval2Date == null ? '' : dateFormat(this.fin03a.approval2Date, 'dd-mm-yyyy');

        let doc = this.docRef.split("-").slice(0, -1).join("-")

        if (this.fin03a.status == 'FOR APPROVAL TO MOH') {
            if (this.invoiceDate != '') {
                this.lastDate = this.invoiceDate
            }
        }
        else if (this.invoiceDate == '') {
            this.lastDate = ""
        }
        else {
            // let monthYear = "0" + this.monthYear;
            // let date = monthYear.split('')
            // var lastday = function (y, m) {
            //     return new Date(y, m, 0).getDate();
            // }
            // this.lastDate = lastday(date[0], date[1]) + '/' + this.monthYear

            var d = new Date(this.year, this.month, 0);
            let data = new Date(d);
            this.lastDate = data.getDate() + '/' + (data.getMonth() + 1) + '/' + data.getFullYear()

        }

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
                    }
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
                                    { text: 'Verified By,', bold: true },
                                    { text: 'PKD/PPD', bold: true },
                                    { text: 'NAME               : ', bold: true },
                                    { text: 'DESIGNATION : ', bold: true },
                                    // { text: 'NAME               : ' + approval2UserName, bold: true },
                                    // { text: 'DESIGNATION : ' + approval2UserDesignation, bold: true },
                                    { text: 'DATE                 : ', bold: true }
                                    //{ text: 'DATE                 : ' + approval2Date, bold: true }
                                ], margin: [30, 0, 0, 0]
                            }, {}, {}
                        ],
                    },
                    {
                        columns: [
                            { text: 'This invoice is system generated and does not required a signature.', fontSize: 9, alignment: 'right', bold: true, width: '70%' },
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
                    table: {
                        widths: ['*'],
                        //widths: ['auto', 'auto', 'auto', 'auto', 'auto'],
                        body: [
                            [{
                                text: 'SUMMARY OF RENTAL CHARGES',
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
                            width: '60%',
                            stack: [
                                //{ text: 'INVOICE TO:', bold: true },
                                {
                                    table: {
                                        body: [
                                            [{
                                                table: {

                                                    widths: ['*', '*', '*', '*', '*'],
                                                    body: [
                                                        [{ text: 'PKD/PPD', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.clinicTypeCode }, { text: this.districtName, margin: [-25, 0, 0, 0] }, {}],
                                                        [{ text: 'ADDRESS', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.districtAddress, colSpan: 3 }, {}, {}],
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
                                { text: 'FORM FIN 03-A', bold: true, alignment: 'right' },
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
                        widths: ['*', 'auto', 'auto', 'auto'],
                        body: [
                            [{ text: 'CLINIC DETAILS', colSpan: 2, bold: true, alignment: 'center' }, '', { text: 'NEW BIOMEDICAL EQUIPMENT', colSpan: 2, bold: true, alignment: 'center' }, ''],
                            [{ text: 'CLINIC NAME', bold: true, alignment: 'center' }, { text: 'CLINIC CODE', bold: true, alignment: 'center' }, { text: 'No. of New BE (Units)', bold: true, alignment: 'center' },
                            { text: 'RENTAL CHARGES (RM)', bold: true, alignment: 'center' }],
                            ...this.clinicDatas.map(data => {
                                return [{ text: data.clinic.clinicName }, { text: data.clinic.clinicCode, alignment: 'center' }, { text: data.totalEquipmentCount, alignment: 'center' },
                                { text: formatNumber(data.totalRentalCharge, 'en', '1.2-2'), alignment: 'right' }]
                            }),
                            [{ text: 'TOTAL AMOUNT PAYABLE', bold: true }, '', { text: this.equipmentTotalCount, bold: true, alignment: 'center' }, { text: formatNumber(this.totalRentalCharge, 'en', '1.2-2'), bold: true, alignment: 'right' }]
                        ]
                    }
                },
                // {
                //     text: '(Ringgit Malaysia: ' + this.equipmentTotalValueInWords + ' ONLY)', bold: true, italics: true
                // },
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