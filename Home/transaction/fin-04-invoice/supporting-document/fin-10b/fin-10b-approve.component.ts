import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import * as dateFormat from 'dateformat';
import { Fin04InvoiceService } from '../../fin-04-invoice-service';
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
    selector: 'fin-10b-approve',
    templateUrl: './fin-10b-approve.component.html',
    styleUrls: ['./fin-10b-approve.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})

export class Fin10bApproveComponent implements OnInit {

    public fin10b: any;
    public stateName;
    public districtName;
    public clinicName;
    public clinicCode;
    public assetName;
    public beNumber;
    public monthYear;
    public docRef;
    public date;
    public invoiceDate;
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
    public showButtons = false;
    public isApproved = false;
    public modalBodyContent;
    public loading: Boolean = true;
    public logo = '';

    constructor(private router: Router, private route: ActivatedRoute, private fin04service: Fin04InvoiceService,
        config: NgbModalConfig, private modalService: NgbModal) {
        config.backdrop = 'static';
        config.keyboard = false;
    }

    ngOnInit() {
        this.displaySelectedEquip = false
        if (this.route.params['_value']['_id'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.fin04service.fetchForFin10bApprove(par['_id'])).subscribe(x => {
                this.fin10b = x;
                this.equipmentDatas = x.fin10bConstructionWorks;
                this.stateName = x.stateName;
                this.districtName = x.districtName;
                this.clinicName = x.clinic.clinicName;
                this.clinicCode = x.clinic.clinicCode;
                this.assetName = x.assetName;
                this.beNumber = x.beNumber;
                this.monthYear = x.month + '/' + x.year;
                this.equipmentTotalValue = x.totalAmount;
                this.date = dateFormat(x.date, 'dd-mm-yyyy');
                this.invoiceDate = this.date;
                this.equipmentCount = this.equipmentDatas.length;
                this.docRef = this.fin10b.status == 'CREATED' || this.fin10b.status == 'SAVED' ? "" : this.fin10b.code;
                this.showButtons = this.fin10b.status == 'APPROVED BY MOH' ? false : true;
                this.isApproved = this.fin10b.status == 'APPROVED BY MOH' ? true : false;
                this.showSaveSubmitButton = this.fin10b.status == 'IN INTERNAL APPROVAL' || this.fin10b.status == 'FOR APPROVAL TO MOH' ? false : true;
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
                for (let i = 0; i < this.equipmentDatas.length; i++) {
                    this.equipmentDatas[i]["serialNumber"] = i + 1;
                }
            });
        }
    }

    openModal(content, modalContent) {
        this.invoiceDate = "";
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
        delete this.fin10b.fin10bConstructionWorks;
        this.fin10b.status = "SAVED";
        this.fin04service.updateFin10b(this.fin10b).subscribe(x => {
            Swal.fire('', 'Fin 10b saved successfully!!!', 'success');
        })
    }

    submitStatus() {
        delete this.fin10b.fin10bConstructionWorks;
        this.fin10b.status = 'IN INTERNAL APPROVAL'
        this.fin04service.updateFin10b(this.fin10b).subscribe(x => {
            Swal.fire('', 'Fin 10b submitted successfully!!!', 'success');
            history.back()
        })
    }

    updateStatus() {
        if (this.fin10b.status == 'IN INTERNAL APPROVAL') { this.fin10b.status = 'FOR APPROVAL TO MOH' }
        else if (this.fin10b.status == 'FOR APPROVAL TO MOH') { this.fin10b.status = 'APPROVED BY MOH' }
        delete this.fin10b.fin10bConstructionWorks;
        this.fin04service.updateFin10b(this.fin10b).subscribe(x => {
            Swal.fire('', 'Fin 10b approved successfully!!!', 'success');
            history.back()
        })

    }

    navToList() {
        history.back()
        //this.router.navigate(['/transaction/fin-04-invoice/fin-10b-list'])
    }

    public downloadPdf() {
        var data = document.getElementById("pdfImage");
        html2canvas(data, { scale: 1.5, scrollY: -window.scrollY }).then(canvas => {
            this.logo = canvas.toDataURL('image/png')
            this.generatePDF()
        });

    }

    generatePDF() {
        let clinicName = this.clinicName;
        let capClinicName = clinicName.toUpperCase();

        let assestName = this.assetName;
        let capAssestName = assestName.toUpperCase();

        let pdfdate = this.invoiceDate
        if (this.fin10b.status == 'FOR APPROVAL TO MOH') {
            if (this.invoiceDate != '') {
                this.invoiceDate = dateFormat(this.invoiceDate, 'dd-mm-yyyy')
            }
        }

        let logo = this.logo
        let doc = this.docRef.split("-").slice(0, -1).join("-")

        if (this.fin10b.status == 'FOR APPROVAL TO MOH') {
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

        let lastDate = this.lastDate;

        var docDefinition = {
            pageMargins: [30, 85, 30, 100],
            header: function () {
                let header = [
                    {
                        image: logo,
                        fit: [125, 125],
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
                                    { text: 'PREPARED BY :', bold: true,fontSize: 10 },
                                    { text: 'SYADZWANI FAUZI', bold: true,fontSize: 10 },
                                    { text: 'PROJECT EXECUTIVE', bold: true,fontSize: 10 },
                                    { text: 'DATE:  ' + pdfdate, bold: true,fontSize: 10 }
                                ], margin: [30, 0, 0, 0]
                            },
                            {
                                stack: [
                                    { text: 'CHECKED BY :', bold: true,fontSize: 10 },
                                    { text: ' ZAIZUL BAHARY ZAKARIA', bold: true,fontSize: 10 },
                                    { text: 'PROJECT CONSULTANT', bold: true,fontSize: 10 },
                                    { text: 'DATE:  ' + pdfdate, bold: true,fontSize: 10 }

                                    // { text: 'Technical Service Department', bold: true }
                                ], margin: [30, 0, 0, 0]
                            },
                            {
                                stack: [
                                    { text: 'VERIFIED BY,', bold: true,fontSize: 10 },
                                    { text: ' JURUTERA NEGERI', bold: true,fontSize: 10 },
                                    { text: 'NAME & DESIGNATION : ', bold: true,fontSize: 10 },
                                    { text: 'DATE  : ', bold: true,fontSize: 10 }
                                    // { text: 'NAME                : ' + (this.fin06.approval2UserName==null?'':this.fin06.approval2UserName), bold: true },
                                    // { text: 'DESIGNATION : '+  (this.fin06.approval2UserDesignation==null?'':this.fin06.approval2UserDesignation), bold: true },
                                    // { text: 'DATE                 : '+  (this.fin06.approval2Date==null?'':dateFormat(this.fin06.approval2Date, 'dd-mm-yyyy')), bold: true }
                                ], margin: [0, 0, 30, 0]
                            }
                        ]
                    },
                    {
                        columns: [
                            {
                                text: '**  This is a system generated document from Qubics, no signature is required.', alignment: 'right', width: '70%'
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
                                text: 'SUMMARY OF CONSTRUCTION WORKS',
                                bold: true
                            },],
                        ]
                    },
                    alignment: 'center',
                },
                {
                    text: '\n'
                },
                {
                    columns: [
                        {
                            width: '65%',
                            stack: [
                                //{ text: 'INVOICE TO:', bold: true },
                                {
                                    table: {
                                        body: [
                                            [{
                                                table: {
                                                    widths: ['auto', 'auto', 'auto'],
                                                    body: [
                                                        [{ text: 'STATE/DISTRICT', bold: true,fontSize: 10 }, { text: ':', bold: true, alignment: 'right',fontSize: 10 }, { text: this.stateName + ' / ' + this.districtName,fontSize: 10 }],
                                                        // [{ text: 'DISTRICT', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.districtName }],
                                                        [{ text: 'CLINIC NAME', bold: true,fontSize: 10 }, { text: ':', bold: true, alignment: 'right',fontSize: 10 }, { text: capClinicName,fontSize: 10 }],
                                                        [{ text: 'CLINIC CODE', bold: true,fontSize: 10 }, { text: ':', bold: true, alignment: 'right',fontSize: 10 }, { text: this.clinicCode,fontSize: 10 }],
                                                        [{ text: 'ASSET NAME', bold: true,fontSize: 10 }, { text: ':', bold: true, alignment: 'right',fontSize: 10 }, { text: capAssestName,fontSize: 10 }],
                                                        [{ text: 'BE NUMBER', bold: true,fontSize: 10 }, { text: ':', bold: true, alignment: 'right',fontSize: 10 }, { text: this.beNumber,fontSize: 10 }],
                                                        [{ text: 'MONTH/YEAR', bold: true,fontSize: 10 }, { text: ':', bold: true, alignment: 'right',fontSize: 10 }, { text: this.monthYear,fontSize: 10 }],
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
                                { text: 'FORM FIN 10-B', bold: true, alignment: 'right' },
                                {
                                    table: {
                                        widths: ['*', 'auto'],
                                        body: [
                                            [{ text: 'DOC REF:', bold: true,fontSize: 10 }, { text: this.docRef, alignment: 'right', bold: true,fontSize: 10 }],
                                            [{ text: 'DATE:', bold: true,fontSize: 10 }, { text: pdfdate, alignment: 'right', bold: true,fontSize: 10 }]
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
                        widths: ['*', 'auto', 'auto', 'auto', 'auto', 'auto', 'auto', 'auto', 'auto', 'auto'],
                        body: [
                            // [{ text: 'Sl No', colSpan: 1, bold: true, alignment: 'center', border: [1, 1, 1, 0],fontSize: 10 }, { text: 'DETAILS OF CONSTRUCTION WORKS COSTS INCURRED', colSpan: 8, bold: true, alignment: 'center',fontSize: 10 }, '', '', '', '', '', '', '',
                            // { text: 'Agreed Total (RM)', colSpan: 1, bold: true, alignment: 'center', border: [1, 1, 1, 0],fontSize: 10 }],
                            [ { text: 'DETAILS OF CONSTRUCTION WORKS COSTS INCURRED', colSpan: 10, bold: true, alignment: 'center',fontSize: 10 }, '', '', '', '', '', '', '','',''],

                            [{ text: 'Sl No', colSpan: 1, bold: true, alignment: 'center', border: [1, 0, 1, 1],fontSize: 10 }, { text: 'Type Of Expense', colSpan: 1, bold: true, alignment: 'center', margin: [0, 0, 10, 0],fontSize: 10 }, { text: 'Name Of Supplier', colSpan: 1, bold: true, alignment: 'center', margin: [0, 0, 10, 0],fontSize: 10 },
                            { text: 'CW Final BQ Cost (RM)', colSpan: 1, bold: true, alignment: 'center', margin: [0, 0, 10, 0],fontSize: 10 }, { text: 'Tender Ref No', colSpan: 1, bold: true, alignment: 'center', margin: [0, 0, 10, 0],fontSize: 10 },
                            { text: 'Letter of Award/Contract Ref', colSpan: 1, bold: true, alignment: 'center', margin: [0, 0, 10, 0],fontSize: 10 }, { text: 'Certificate of Practical Completion Ref', colSpan: 1, bold: true, alignment: 'center', margin: [0, 0, 10, 0],fontSize: 10 },
                            { text: 'Cert of T&C S/N No.', colSpan: 1, bold: true, alignment: 'center', margin: [0, 0, 10, 0],fontSize: 10 }, { text: 'Certificate of Acceptance Ref', colSpan: 1, bold: true, alignment: 'center', margin: [0, 0, 10, 0],fontSize: 10 }, { text: 'Agreed Total (RM)', colSpan: 1, bold: true, alignment: 'center', border: [1, 0, 1, 1],fontSize: 10 }],
                            ...this.equipmentDatas.map(data => {
                                return [{ text: data.serialNumber, alignment: 'center',fontSize: 10 }, { text: "Preliminaries,Design & Build,Civil & Structure,Electrical, Mechanical, Plumbing, misc associated works aircond", alignment: 'center',fontSize: 10 }, { text: data.nameOfSupplier, alignment: 'center',fontSize: 10 },
                                { text: formatNumber(data.cwFinalBqCost, 'en', '1.2-2'), alignment: 'right',fontSize: 10 }, { text: data.tenderRefNo, alignment: 'center',fontSize: 10 }, { text: data.letterOfAwardOrContractRefNo, alignment: 'center',fontSize: 10 },
                                { text: data.certOfPracticalCompletionRefNo, alignment: 'center',fontSize: 10 }, { text: data.certOfTcNo, alignment: 'center',fontSize: 10 }, { text: data.certOfAcceptanceRef, alignment: 'center',fontSize: 10 },
                                { text: formatNumber(data.agreedTotal, 'en', '1.2-2'), alignment: 'right',fontSize: 10 }]
                            }),
                            [{ text: 'TOTAL (RM) ', colSpan: 9, alignment: 'center', bold: true,fontSize: 10 }, '', '', '', '', '', '', '', '', { text: formatNumber(this.equipmentTotalValue, 'en', '1.2-2'), bold: true, alignment: 'right',fontSize: 10 }],

                        ]
                    }
                },
                // {
                //     text: '(Ringgit Malaysia: ' + this.equipmentTotalValueInWords + ' ONLY)', bold: true, italics: true
                // },
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