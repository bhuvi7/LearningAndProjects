import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import * as dateFormat from 'dateformat';
import { Fin08InvoiceService } from '../../fin-08-invoice-service';
import { Fin08b } from '../../../../model/fin08b';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { toWords } from 'number-to-words';
import pdfMake from "pdfmake/build/pdfmake.min";
import pdfFonts from "pdfmake/build/vfs_fonts";
pdfMake.vfs = pdfFonts.pdfMake.vfs;
import html2canvas from 'html2canvas';
import { formatNumber } from '@angular/common';
import { PagesService } from '../../../../../../pages.service';

@Component({
    selector: 'fin-08b-approve',
    templateUrl: './fin-08b-approve.component.html',
    styleUrls: ['./fin-08b-approve.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})

export class Fin08bApproveComponent implements OnInit {

    public fin08b: Fin08b;
    public stateName;
    public districtName;
    public clinicName;
    public clinicCode;
    public monthYear;
    public docRef;
    public date;
    public invoiceDate;
    public lastDate;
    public batchNo;
    public instalmentNo;
    public equipmentValue;
    public maintenanceCharge = 0;
    public clinicAddress = "";
    public equipmentDatas: any;
    public equipmentCount = 0;
    public maintenanceCharges;
    public sum;
    public totalMaintenanceCharges: any;
    public totalMaintenanceChargesInWhole = 0;
    public totalMaintenanceChargesInWords: String;
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
    public logo = '';
    public index;
    public userId: Number;
    public disableApprove: boolean;
    public enabler: boolean;
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
        this.displaySelectedEquip = false
        if (this.route.params['_value']['_id'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.fin08Service.fetchDataForFin08bApprove(par['_id'])).subscribe(x => {
                this.fin08b = x;
                this.equipmentDatas = x.cimsHistoryFin02;


                this.equipmentDatas.sort((a, b) => (a.beNumber > b.beNumber) ? 1 : -1)
                this.stateName = x.stateName;
                this.districtName = x.districtName;
                this.clinicName = x.clinic.clinicName;
                this.clinicCode = x.clinic.clinicCode;
                this.clinicAddress = x.clinic.address;
                this.month = x.month
                this.year = x.year

                this.monthYear = x.month + '/' + x.year;
                this.date = dateFormat(x.date, 'dd-mm-yyyy');
                this.invoiceDate = this.date
                this.equipmentCount = this.equipmentDatas.length;
                this.totalMaintenanceCharges = x.totalMaintenanceCharges;
                this.docRef = this.fin08b.status == 'CREATED' || this.fin08b.status == 'SAVED' ? "" : this.fin08b.code;
                this.showButtons = this.fin08b.status == 'APPROVED BY MOH' ? false : true;
                this.isApproved = this.fin08b.status == 'APPROVED BY MOH' ? true : false;
                this.showSaveSubmitButton = this.fin08b.status == 'IN INTERNAL APPROVAL' || this.fin08b.status == 'FOR APPROVAL TO MOH' ? false : true;
                //    this.totalMaintenanceChargesInWords = toWords(this.totalMaintenanceCharges).toUpperCase();
                if (this.totalMaintenanceCharges % 1 == 0) {
                    this.totalMaintenanceChargesInWords = toWords(this.totalMaintenanceCharges).toUpperCase();
                } else {
                    let totalAmountInString = this.totalMaintenanceCharges.toString()
                    let splitValue = totalAmountInString.split('.')
                    this.equipmentTotalWholeValueInWords = toWords(splitValue[0]).toUpperCase();
                    this.equipmentTotalDecimalValueInWords = toWords(splitValue[1]).toUpperCase();
                    this.totalMaintenanceChargesInWords = this.equipmentTotalWholeValueInWords + ' AND CENTS ' + this.equipmentTotalDecimalValueInWords
                }
                // this.totalMaintenanceChargesInWhole = Math.round(this.totalMaintenanceCharges);

                // //    
                // this.totalMaintenanceChargesInWords = toWords(this.totalMaintenanceChargesInWhole).toUpperCase();
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
        delete this.fin08b.cimsHistoryFin02;
        this.fin08b.status = "SAVED";
        this.fin08Service.updateFin08bStatus(this.fin08b).subscribe(x => {
            Swal.fire('', 'Fin 08b saved successfully!!!', 'success');
        })
    }

    submitStatus() {
        delete this.fin08b.cimsHistoryFin02;
        this.fin08b.status = 'IN INTERNAL APPROVAL'
        this.fin08Service.updateFin08bStatus(this.fin08b).subscribe(x => {
            Swal.fire('', 'Fin 08b submitted successfully!!!', 'success');
            history.back()
        })
    }

    updateStatus() {
        this.disableApprove = true
        this.enabler = true
        if (this.fin08b.status == 'IN INTERNAL APPROVAL') { this.fin08b.status = 'FOR APPROVAL TO MOH', this.fin08b.approval1UserId = this.userId }
        else if (this.fin08b.status == 'FOR APPROVAL TO MOH') { this.fin08b.status = 'APPROVED BY MOH', this.fin08b.approval2UserId = this.userId }
        delete this.fin08b.cimsHistoryFin02;
        this.fin08Service.updateFin08bStatus(this.fin08b).subscribe(x => {
            this.enabler = false
            this.disableApprove = false
            Swal.fire('', 'Fin 08b approved successfully!!!', 'success');
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

        if (this.fin08b.status == 'FOR APPROVAL TO MOH') {
            if (this.invoiceDate != '') {
                this.invoiceDate = dateFormat(this.invoiceDate, 'dd-mm-yyyy')
                this.lastDate = this.invoiceDate
            }
        }

        let logo = this.logo
        let approval2UserName = this.fin08b.approval2UserName == null ? '' : this.fin08b.approval2UserName;
        let approval2UserDesignation = this.fin08b.approval2UserDesignation == null ? '' : this.fin08b.approval2UserDesignation;
        let approval2Date = this.fin08b.approval2Date == null ? '' : dateFormat(this.fin08b.approval2Date, 'dd-mm-yyyy');

        let doc = this.docRef.split("-").slice(0, -1).join("-")
        if (this.fin08b.status == 'FOR APPROVAL TO MOH') {
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
            // var month = 0; // January
            var d = new Date(this.year, this.month, 0);

            // this.lastDate = lastday(date[0], date[1]) + '/' + this.monthYear

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
                                text: 'SUMMARY OF MAINTENANCE CHARGES FOR NEW BIOMEDICAL EQUIPMENT',
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
                                { text: 'FORM FIN 08-B', bold: true, alignment: 'right' },
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
                        widths: [50, '*', 'auto', 'auto', 'auto'],
                        body: [
                            [{ text: '' }, { text: 'MAINTENANCE CHARGES - NEW BIOMEDICAL EQUIPMENT', bold: true, colSpan: 4, alignment: 'center' }, '', '', '',],
                            [{ text: 'S.No', bold: true, alignment: 'center' }, { text: 'ASSET NAME', bold: true, alignment: 'center' }, { text: 'BE NUMBER', bold: true, alignment: 'center' },
                            { text: 'VALUE OF EQUIPMENT(RM)', bold: true, alignment: 'center' }, { text: 'MONTHLY MAINTENANCE CHARGES(RM)', bold: true, alignment: 'center' }],
                            ...this.equipmentDatas.map(data => {
                                return [{ text: data.serialNumber }, { text: data.assetName, alignment: 'left' }, { text: data.beNumber, alignment: 'center' },
                                { text: formatNumber(data.valueOfEquipment, 'en', '1.2-2'), alignment: 'right' }, { text: formatNumber(data.maintenanceCharges, 'en', '1.2-2'), alignment: 'right' }]
                            }),
                            // ['\n', '\n', '\n', '\n', '\n',],
                            // ['\n', '\n', '\n', '\n', '\n',],
                            // ['\n', '\n', '\n', '\n', '\n',],
                            [{ text: 'TOTAL MAINTENANCE CHARGES FOR THE MONTH', bold: true, alignment: 'left', colSpan: 4 }, '', '', '', { text: formatNumber(this.totalMaintenanceCharges, 'en', '1.2-2'), bold: true, alignment: 'right' }]
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