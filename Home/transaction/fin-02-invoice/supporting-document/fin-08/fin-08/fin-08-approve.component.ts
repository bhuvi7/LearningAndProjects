import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { Fin08InvoiceService } from '../fin-08-invoice-service';
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
import { Fin08 } from '../../../model/fin08';
import { PagesService } from '../../../../../pages.service';

@Component({
    selector: 'fin-08-approve',
    templateUrl: './fin-08-approve.component.html',
    styleUrls: ['./fin-08-approve.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})

export class Fin08ApproveComponent implements OnInit {

    public fin08: Fin08;
    public clinicTypeCode: any;
    public districtName;
    public clinicName;
    public clinicAddress;
    public clinicCode;
    public stateName;
    public monthYear;
    public docRef;
    public date;
    public invoiceDate;
    public lastDate;
    public equipmentDatas: any;
    public totalRentalCharge = 0.0;
    public sstAmount = 0.0;
    public totalInvoiceAmount = 0.0;
    public newEquipmentMaintenanceCharge = 0.0;
    public purchasedEquipmentMaintenanceCharge = 0.0;
    public maintenanceCharges;
    public totalMaintenanceCharges = 0.0;
    public totalMonthlyMaintenanceCharge = 0.0;
    public totalMaintenanceChargesInWhole;
    public totalMaintenanceChargesInWords: String;
    public equipmentTotalWholeValueInWords: String;
    public equipmentTotalDecimalValueInWords: String;
    public showSaveSubmitButton;
    public isApproved = false;
    public showButtons = false;
    public modalBodyContent;
    public loading: Boolean = true;
    public logo = '';
    public userId: Number;
    public disableApprove: boolean;
    public enabler: boolean
    public month: any;
    public year: any;

    constructor(private router: Router, private route: ActivatedRoute, private fin08Service: Fin08InvoiceService,
        config: NgbModalConfig, private modalService: NgbModal, private pagesService: PagesService) {
        config.backdrop = 'static';
        config.keyboard = false;
    }

    ngOnInit() {
        this.disableApprove = false
        this.userId = this.pagesService.getUserId();
        if (this.route.params['_value']['_id'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.fin08Service.fetchDataForFin08Approve(par['_id'])).subscribe(x => {
                this.month = x.month
                this.year = x.year
                this.fin08 = x;
                this.fin08.fin08b = x.fin08b;
                this.fin08.fin08c = x.fin08c;
                this.stateName = x.stateName;
                this.districtName = x.districtName;
                this.clinicName = x.clinic.clinicName;
                this.clinicCode = x.clinic.clinicCode;
                this.clinicAddress = x.clinic.address;
                if (x.fin08b[0]) {
                    this.newEquipmentMaintenanceCharge = x.fin08b[0].totalMaintenanceCharges;
                }
                if (x.fin08c[0]) {
                    this.purchasedEquipmentMaintenanceCharge = x.fin08c[0].totalMaintenanceCharges;
                }
                this.totalMaintenanceCharges = x.totalMaintenanceCharges;
                this.monthYear = x.month + '/' + x.year;
                this.date = dateFormat(x.date, 'dd-mm-yyyy');
                this.invoiceDate = this.date
                // this.totalMaintenanceCharges = x.totalMaintenanceCharges;
                this.docRef = this.fin08.status == 'CREATED' || this.fin08.status == 'SAVED' ? "" : this.fin08.code;
                this.showButtons = this.fin08.status == 'APPROVED BY MOH' ? false : true;
                this.isApproved = this.fin08.status == 'APPROVED BY MOH' ? true : false;
                this.showSaveSubmitButton = this.fin08.status == 'IN INTERNAL APPROVAL' || this.fin08.status == 'FOR APPROVAL TO MOH' ? false : true;
                if (this.totalMaintenanceCharges % 1 == 0) {
                    this.totalMaintenanceChargesInWords = toWords(this.totalMaintenanceCharges).toUpperCase();
                } else {
                    let totalAmountInString = this.totalMaintenanceCharges.toString()
                    let splitValue = totalAmountInString.split('.')
                    this.equipmentTotalWholeValueInWords = toWords(splitValue[0]).toUpperCase();
                    this.equipmentTotalDecimalValueInWords = toWords(splitValue[1]).toUpperCase();
                    this.totalMaintenanceChargesInWords = this.equipmentTotalWholeValueInWords + ' AND CENTS ' + this.equipmentTotalDecimalValueInWords
                }
                // //    this.totalMaintenanceChargesInWords = toWords(this.totalMaintenanceCharges).toUpperCase();
                // this.totalMaintenanceChargesInWhole = Math.round(this.totalMaintenanceCharges);

                // //    
                // this.totalInvoiceAmountInWords = toWords(this.totalMaintenanceChargesInWhole).toUpperCase();
                this.loading = false;

            });
        }
    }

    openModal(content, modalContent) {
        this.invoiceDate = ""
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
        delete this.fin08.cimsHistoryFin08;
        delete this.fin08.fin08b;
        delete this.fin08.fin08c;

        this.fin08.status = "SAVED";
        this.fin08Service.updateFin08Status(this.fin08).subscribe(x => {
            Swal.fire('', 'Fin 08 saved successfully!!!', 'success');
        })
    }

    submitStatus() {
        delete this.fin08.cimsHistoryFin08;
        delete this.fin08.fin08b;
        delete this.fin08.fin08c;
        this.fin08.status = 'IN INTERNAL APPROVAL'
        this.fin08Service.updateFin08Status(this.fin08).subscribe(x => {
            Swal.fire('', 'Fin 08 submitted successfully!!!', 'success');
            history.back()
            // this.router.navigateByUrl('/transaction/fin-01-invoice/FIN-01-list')
        })
    }

    updateStatus() {
        this.disableApprove = true
        this.enabler = true
        if (this.fin08.status == 'IN INTERNAL APPROVAL') { this.fin08.status = 'FOR APPROVAL TO MOH', this.fin08.approval1UserId = this.userId }
        else if (this.fin08.status == 'FOR APPROVAL TO MOH') { this.fin08.status = 'APPROVED BY MOH', this.fin08.approval2UserId = this.userId }
        delete this.fin08.cimsHistoryFin08;
        delete this.fin08.fin08b;
        delete this.fin08.fin08c;

        this.fin08Service.updateFin08Status(this.fin08).subscribe(x => {
            this.enabler = false
            Swal.fire('', 'Fin 08 approved successfully!!!', 'success');
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

        if (this.fin08.status == 'FOR APPROVAL TO MOH') {
            if (this.invoiceDate != '') {
                this.invoiceDate = dateFormat(this.invoiceDate, 'dd-mm-yyyy')
                this.lastDate = this.invoiceDate
            }
        }

        let logo = this.logo
        let approval2UserName = this.fin08.approval2UserName == null ? '' : this.fin08.approval2UserName;
        let approval2UserDesignation = this.fin08.approval2UserDesignation == null ? '' : this.fin08.approval2UserDesignation;
        let approval2Date = this.fin08.approval2Date == null ? '' : dateFormat(this.fin08.approval2Date, 'dd-mm-yyyy');

        let doc = this.docRef.split("-").slice(0, -1).join("-")

        if (this.fin08.status == 'FOR APPROVAL TO MOH') {
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
                                    //{ text: 'DATE                 : ' + approval2Date, bold: true }
                                ], margin: [0, 0, 30, 0]
                            }
                        ],
                    },
                    {
                        columns: [
                            {
                                // text: '**  This is a system generated document from Qubics, no signature is required.', alignment: 'right', width: '70%'
                                text: 'This supporting document is system generated and does not required a signature.', fontSize: 9, alignment: 'right', width: '70%'
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
                                text: 'SUMMARY OF MAINTENANCE CHARGES FOR NEW AND PURCHASED BIOMEDICAL EQUIPMENT',
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
                                                        [{ text: 'STATE', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.stateName }],
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
                                { text: 'FORM FIN 08', bold: true, alignment: 'right', fontSize: 12 },
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
                        headerRows: 1,
                        widths: ['*', 'auto', 'auto'],
                        body: [
                            [{ text: 'MAINTENANCE CHARGES - NEW BIOMEDICAL EQUIPMENT (FIN 08-B)', bold: true, alignment: 'center' }, { text: 'MAINTENANCE CHARGES - PURCHASED BIOMEDICAL EQUIPMENT (FIN 08-C)', bold: true, alignment: 'center' },
                            { text: 'TOTAL MAINTENANCE CHARGES (RM) (B+C)', bold: true, alignment: 'center' }],

                            [{ text: formatNumber(this.newEquipmentMaintenanceCharge, 'en', '1.2-2'), alignment: 'right' },
                            { text: formatNumber(this.purchasedEquipmentMaintenanceCharge, 'en', '1.2-2'), alignment: 'right' }, { text: formatNumber(this.totalMaintenanceCharges, 'en', '1.2-2'), alignment: 'right' }],
                            // ['\n', '\n', '\n'],
                            // ['\n', '\n', '\n'],
                            // ['\n', '\n', '\n'],
                            [{ text: 'TOTAL MAINTENANCE CHARGES FOR THE MONTH', bold: true, alignment: 'left', colSpan: 2 }, '', { text: formatNumber(this.totalMaintenanceCharges, 'en', '1.2-2'), bold: true, alignment: 'right' }]
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