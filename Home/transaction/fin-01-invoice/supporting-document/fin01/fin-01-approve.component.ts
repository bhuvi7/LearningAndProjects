import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Fin01InvoiceService } from '../../fin-01-invoice-service';
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
import { PagesService } from '../../../../pages.service';
@Component({
    selector: 'fin-01-approve',
    templateUrl: './fin-01-approve.component.html',
    styleUrls: ['./fin-01-approve.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})

export class Fin01ApproveComponent implements OnInit {

    public enableSaveButton: Boolean = window.history.state.approvalPage ? true : false;

    public fin01: any;
    public clinicTypeCode: any;
    public districtName;
    public monthYear;
    public docRef = "";
    public splitedDocRef = "";
    public date;
    public invoiceDate;
    public lastDate;
    public clinicDatas: any;
    public equipmentTotalCount = 0;
    public equipmentTotalValue = 0;
    public equipmentTotalValueInWords: String;
    public equipmentTotalWholeValueInWords: String;
    public equipmentTotalDecimalValueInWords: String;
    public showSaveSubmitButton;
    public isApproved = false;
    public showButtons = false;
    public modalBodyContent;
    public loading: Boolean = true;
    public logo = ''
    public userId: Number;
    public month: any;
    public year: any;

    constructor(private router: Router, private route: ActivatedRoute, private fin01Service: Fin01InvoiceService,
        config: NgbModalConfig, private modalService: NgbModal, private pagesService: PagesService) {
        config.backdrop = 'static';
        config.keyboard = false;
    }

    ngOnInit() {
        this.userId = this.pagesService.getUserId();
        if (this.route.params['_value'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.fin01Service.fetchDataForFin01Approve(par['_id'])).subscribe(x => {
                // 
                // 
                this.fin01 = x;
                //
                this.populateDistrictAddress(this.fin01.districtId, this.fin01.clinicTypeId);
                this.clinicDatas = x.fin06;
                //
                this.clinicDatas.sort((a, b) => (a.code > b.code) ? 1 : -1)


                this.clinicDatas.forEach(element => {
                    element.dateDisplay = dateFormat(new Date(), 'dd-mm-yyyy')
                });
                this.clinicDatas.map(data => {
                    this.equipmentTotalCount += data.totalEquipmentCount
                    this.equipmentTotalValue += data.totalAmount
                })
                this.month = x.month
                this.year = x.year
                this.clinicTypeCode = x.clinicTypeCode;
                this.districtName = x.districtName;
                this.monthYear = x.month + '/' + x.year;
                this.date = dateFormat(x.date, 'dd-mm-yyyy')
                this.invoiceDate = this.date
                this.docRef = this.fin01.status == 'CREATED' || this.fin01.status == 'SAVED' ? "" : this.fin01.code;
                this.isApproved = this.fin01.status == 'APPROVED BY MOH' ? true : false;
                this.showButtons = this.fin01.status == 'APPROVED BY MOH' ? false : true;
                this.showSaveSubmitButton = this.fin01.status == 'IN INTERNAL APPROVAL' || this.fin01.status == 'FOR APPROVAL TO MOH' ? false : true;
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
            })
        }

    }

    public district: any;
    public districtAddress = "";
    populateDistrictAddress(districtId, clinicTypeId) {
        this.fin01Service.fetchDistrictById(districtId).subscribe(x => {
            this.district = x;
            if (clinicTypeId == '1') {
                this.districtAddress = this.district.officePkdAddress;
            } else if (clinicTypeId == '2') {
                this.districtAddress = this.district.officePpdAddress;
            }
        })
        // 
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
        delete this.fin01.fin06;
        this.fin01.status = "SAVED";
        this.fin01Service.updateFin01Status(this.fin01).subscribe(x => {
            Swal.fire('', 'Fin 01 saved successfully!!!', 'success')
        })
    }

    submitStatus() {
        delete this.fin01.fin06;
        this.fin01.status = 'IN INTERNAL APPROVAL'
        this.fin01Service.updateFin01Status(this.fin01).subscribe(x => {
            Swal.fire('', 'Fin 01 submitted successfully!!!', 'success')
            history.back()
            // this.router.navigateByUrl('/transaction/fin-01-invoice/fin-01-list')
        })
    }

    updateStatus() {
        if (this.fin01.status == 'IN INTERNAL APPROVAL') { this.fin01.status = 'FOR APPROVAL TO MOH', this.fin01.approval1UserId = this.userId }
        else if (this.fin01.status == 'FOR APPROVAL TO MOH') { this.fin01.status = 'APPROVED BY MOH', this.fin01.approval2UserId = this.userId }
        delete this.fin01.fin06;
        this.fin01Service.updateFin01Status(this.fin01).subscribe(x => {
            Swal.fire('', 'Fin 01 approved successfully!!!', 'success');
            history.back();

            // this.router.navigateByUrl('/transaction/fin-01-invoice/fin-01-list')
        })

    }

    navToList() {
        history.back()
        // this.router.navigate(['/transaction/fin-01-invoice/fin-01-list'])
    }

    public downloadPdf() {
        var data = document.getElementById("pdfImage");
        html2canvas(data, { scale: 1.5, scrollY: -window.scrollY }).then(canvas => {
            this.logo = canvas.toDataURL('image/png')
            this.generatePDF()
        });

    }

    generatePDF() {

        if (this.fin01.status == 'FOR APPROVAL TO MOH') {
            if (this.invoiceDate != '') {
                this.invoiceDate = dateFormat(this.invoiceDate, 'dd-mm-yyyy')
                this.lastDate = this.invoiceDate
            }
        }

        let logo = this.logo
        let approval2UserName = this.fin01.approval2UserName == null ? '' : this.fin01.approval2UserName;
        let approval2UserDesignation = this.fin01.approval2UserDesignation == null ? '' : this.fin01.approval2UserDesignation;
        let approval2Date = this.fin01.approval2Date == null ? '' : dateFormat(this.fin01.approval2Date, 'dd-mm-yyyy');

        let doc = this.docRef.split("-").slice(0, -1).join("-")

        let date = this.monthYear.split('')
        if (this.fin01.status == 'FOR APPROVAL TO MOH') {
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
                                    { text: 'Verified By,', bold: true },
                                    { text: 'PKD/PPD', bold: true },
                                    { text: 'NAME               : ', bold: true },
                                    { text: 'DESIGNATION : ', bold: true },
                                    // { text: 'NAME               : ' + approval2UserName, bold: true },
                                    // { text: 'DESIGNATION : ' + approval2UserDesignation, bold: true },
                                    { text: 'DATE                 : ', bold: true }
                                    // { text: 'DATE                 : ' + approval2Date, bold: true }
                                ], margin: [30, 0, 0, 0]
                            },
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
                        widths: ['*'],
                        //widths: ['auto', 'auto', 'auto', 'auto', 'auto'],
                        body: [
                            [{
                                text: 'SUMMARY OF PURCHASE CHARGES',
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
                                                    widths: ['*', 'auto', 'auto'],
                                                    body: [
                                                        [{ text: 'PKD/PPD', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.clinicTypeCode }],
                                                        [{ text: 'DISTRICT', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.districtName }],
                                                        [{ text: 'ADDRESS', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.districtAddress, }]
                                                        // [{ text: 'MONTH/YEAR', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.monthYear, colSpan: 3 }],
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
                                { text: 'FORM FIN 01', bold: true, alignment: 'right', },
                                {
                                    table: {
                                        widths: ['*', 'auto'],
                                        body: [
                                            [{ text: 'DOC REF:', bold: true }, {}],
                                            //  [{ text: 'DOC REF:', bold: true }, { text: doc, alignment: 'right', bold: true }],
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
                            [{ text: 'CLINIC DETAILS', colSpan: 2, bold: true, alignment: 'center' }, '', { text: 'PURCHASE BIOMEDICAL EQUIPMENT', colSpan: 2, bold: true, alignment: 'center' }, ''],
                            [{ text: 'CLINIC NAME', bold: true, alignment: 'center' }, { text: 'CLINIC CODE', bold: true, alignment: 'center' }, { text: 'No. of New BE (Units)', bold: true, alignment: 'center' },
                            { text: 'RENTAL CHARGES (RM)', bold: true, alignment: 'center' }],
                            ...this.clinicDatas.map(data => {
                                return [{ text: data.clinic.clinicName }, { text: data.clinic.clinicCode, alignment: 'center' }, { text: data.totalEquipmentCount, alignment: 'center' },
                                { text: formatNumber(data.totalAmount, 'en', '1.2-2'), alignment: 'right' }]
                            }),
                            // ['\n', '\n', '\n', '\n'],
                            // ['\n', '\n', '\n', '\n'],
                            // ['\n', '\n', '\n', '\n'],
                            [{ text: 'TOTAL AMOUNT PAYABLE', bold: true }, '', { text: this.equipmentTotalCount, bold: true, alignment: 'center' }, { text: formatNumber(this.equipmentTotalValue, 'en', '1.2-2'), bold: true, alignment: 'right' }]
                        ]
                    }
                },
                // {
                //     text: '(Ringgit Malaysia: ' + this.equipmentTotalValueInWords + ' ONLY)', bold: true, italics: true
                // },
                // {
                //     text: '\n'
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