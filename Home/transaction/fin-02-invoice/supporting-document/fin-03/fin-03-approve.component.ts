import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { Fin02InvoiceService } from '../../fin-02-service';
import * as dateFormat from 'dateformat';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { toWords } from 'number-to-words';
import { Fin03Clinic } from '../../model/fin03-clinic';
import html2canvas from 'html2canvas';
pdfMake.vfs = pdfFonts.pdfMake.vfs;
import pdfMake from "pdfmake/build/pdfmake.min";
import pdfFonts from "pdfmake/build/vfs_fonts";
pdfMake.vfs = pdfFonts.pdfMake.vfs;
import { formatNumber } from '@angular/common';
import { table } from 'console';
import { PagesService } from '../../../../pages.service';

@Component({
    selector: 'fin-03-approve',
    templateUrl: 'fin-03-approve.component.html',
    styleUrls: ['fin-03-approve.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})

export class Fin03ApproveComponent implements OnInit {

    public enableSaveButton: Boolean = window.history.state.approvalPage ? true : false;

    public invoice: any;
    public clinicTypeCode: any;
    public districtName;
    public clinicAddress;
    public monthYear;
    public docRef = "";
    public date;
    public invoiceDate;
    public lastDate;
    public showSaveSubmitButton;
    public isApproved = false;
    public showButtons = false;
    public modalBodyContent;
    public loading: Boolean = true;
    public districtId: number;
    public districtAddress: string;
    public clinicTypeId: number;
    public district;


    public fin08Datas: Array<any>;
    public fin09Datas: Array<any>;
    public clinicDatas: Array<Fin03Clinic>;
    public fin03Clinic: Fin03Clinic;
    public netMaintenanceCharges: number = 0.0;
    public netMaintenanceChargesInWords;
    public sumOfNewBEEquipmentMaintananceCharges: number = 0.0;
    public sumOfPurchasedEquipmentMaintenanceCharges: number = 0.0;
    public equipmentTotalWholeValueInWords: String;
    public equipmentTotalDecimalValueInWords: String;
    public sumOfTotalMaintenanceCharges: number = 0.0;
    public sumOfPenalty: any;
    public logo = '';
    public userId: Number;
    public disableApprove: boolean;
    public enabler: boolean
    public month: any;
    public year: any;

    constructor(private router: Router, private route: ActivatedRoute, private fin02Service: Fin02InvoiceService,
        config: NgbModalConfig, private modalService: NgbModal, private pagesService: PagesService) {
        config.backdrop = 'static';
        config.keyboard = false;
    }



    ngOnInit() {
        this.disableApprove = false
        this.userId = this.pagesService.getUserId();
        this.fin03Clinic = new Fin03Clinic();
        this.clinicDatas = []
        if (this.route.params['_value']['_id'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.fin02Service.fetchDataForFin03Approve(par['_id'])).subscribe(x => {

                //
                if (x.stateName == 'SARAWAK') {
                    if (x.clinicTypeCode == 'PKD') {
                        this.clinicTypeCode = 'PKB'
                    } else if (x.clinicTypeCode == 'PPD') {
                        this.clinicTypeCode = 'PPB'
                    }
                } else {
                    this.clinicTypeCode = x.clinicTypeCode;
                }
                this.invoice = x;
                this.month = x.month
                this.year = x.year
                this.districtName = x.districtName;
                this.districtId = x.districtId;
                this.clinicTypeId = x.clinicTypeId;

                this.monthYear = x.month + '/' + x.year;
                this.date = dateFormat(x.date, 'dd-mm-yyyy')
                this.invoiceDate = this.date
                this.docRef = this.invoice.status == 'CREATED' || this.invoice.status == 'SAVED' ? "" : this.invoice.code;



                this.isApproved = this.invoice.status == 'APPROVED BY MOH' ? true : false;
                this.showButtons = this.invoice.status == 'APPROVED BY MOH' ? false : true;
                this.showSaveSubmitButton = this.invoice.status == 'IN INTERNAL APPROVAL' || this.invoice.status == 'FOR APPROVAL TO MOH' ? false : true;
                //this.equipmentTotalValueInWords = toWords(this.totalInvoiceAmount).toUpperCase();
                this.netMaintenanceCharges = x.netMaintenanceCharges;
                this.fin08Datas = x.fin08;
                if (x.fin09[0] == null) {
                    this.fin09Datas = []
                } else {
                    this.fin09Datas = x.fin09[0].fin09Clinic;
                }
                this.fin08Datas.forEach(clinic => {
                    //

                    let fin09ForFin08 = this.fin09Datas.find(fin09Clinic => fin09Clinic.clinicId == clinic.clinicId)
                    //
                    if (clinic.fin08b[0]) {
                        this.fin03Clinic.fin08bEquipmentCount = clinic.fin08b[0].totalEquipmentCount
                        this.fin03Clinic.fin08bMaintenanceCharge = clinic.fin08b[0].totalMaintenanceCharges
                    }
                    if (clinic.fin08c[0]) {
                        this.fin03Clinic.fin08cEquipmentCount = clinic.fin08c[0].totalEquipmentCount
                        this.fin03Clinic.fin08cMaintenanceCharge = clinic.fin08c[0].totalMaintenanceCharges
                    }
                    this.fin03Clinic.fin08MaintenanceCharge = clinic.totalMaintenanceCharges
                    this.fin03Clinic.clinicName = clinic.clinic.clinicName
                    this.fin03Clinic.clinicCode = clinic.clinicCode
                    if (fin09ForFin08) {
                        this.fin03Clinic.fin09PenaltyCharge = fin09ForFin08.totalPenalty
                    } else {
                        this.fin03Clinic.fin09PenaltyCharge = 0
                    }
                    this.fin03Clinic.netMaintenanceCharge = this.fin03Clinic.fin08MaintenanceCharge - this.fin03Clinic.fin09PenaltyCharge
                    this.clinicDatas.push(this.fin03Clinic)
                    this.fin03Clinic = new Fin03Clinic();
                })
                this.fin08Datas.forEach(clinic => {
                    if (clinic.fin08b[0]) {
                        this.sumOfNewBEEquipmentMaintananceCharges += clinic.fin08b[0].totalMaintenanceCharges
                    }
                    if (clinic.fin08c[0]) {
                        this.sumOfPurchasedEquipmentMaintenanceCharges += clinic.fin08c[0].totalMaintenanceCharges
                    }
                    this.sumOfTotalMaintenanceCharges += clinic.totalMaintenanceCharges
                })
                this.fin09Datas.forEach(fin09Clinic => {
                    //  
                    this.sumOfPenalty += fin09Clinic.totalPenalty
                });
                if (this.netMaintenanceCharges % 1 == 0) {
                    this.netMaintenanceChargesInWords = toWords(this.netMaintenanceCharges).toUpperCase();
                } else {
                    let totalAmountInString = this.netMaintenanceCharges.toString()
                    let splitValue = totalAmountInString.split('.')
                    this.equipmentTotalWholeValueInWords = toWords(splitValue[0]).toUpperCase();
                    this.equipmentTotalDecimalValueInWords = toWords(splitValue[1]).toUpperCase();
                    this.netMaintenanceChargesInWords = this.equipmentTotalWholeValueInWords + ' AND CENTS ' + this.equipmentTotalDecimalValueInWords
                }
                this.fin02Service.fetchDistrictById(this.districtId).subscribe(x => {

                    this.district = x
                    if (this.clinicTypeId == 1) {
                        this.districtAddress = this.district.officePkdAddress;
                    } else if (this.clinicTypeId == 2) {
                        this.districtAddress = this.district.officePpdAddress;
                    }


                })
                this.loading = false;

            })
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
        delete this.invoice.fin06;
        this.invoice.status = "SAVED";
        // this.fin01Service.updateFIN01Status(this.invoice).subscribe(x => {
        //     Swal.fire('', 'Fin 03 Invoice saved successfully!!!', 'success');
        // })
    }

    submitStatus() {
        delete this.invoice.fin06;
        this.invoice.status = 'IN INTERNAL APPROVAL'
        // this.fin01Service.updateFIN01Status(this.invoice).subscribe(x => {
        //     Swal.fire('', 'Fin 03 Invoice submitted successfully!!!', 'success');
        //     history.back()
        //     // this.router.navigateByUrl('/transaction/fin-01-invoice/FIN-01-list')
        // })
    }

    updateStatus() {
        this.disableApprove = true
        this.enabler = true

        if (this.invoice.status == 'IN INTERNAL APPROVAL') { this.invoice.status = 'FOR APPROVAL TO MOH', this.invoice.approval1UserId = this.userId }
        else if (this.invoice.status == 'FOR APPROVAL TO MOH') { this.invoice.status = 'APPROVED BY MOH', this.invoice.approval2UserId = this.userId }
        delete this.invoice.fin08;
        delete this.invoice.fin09;
        this.fin02Service.updateFin03Status(this.invoice).subscribe(x => {
            this.enabler = false
            Swal.fire('', 'Fin 03 approved successfully!!!', 'success');
            history.back()
            this.disableApprove = false
            // this.router.navigateByUrl('/transaction/fin-01-invoice/FIN-01-list')
        })

    }

    navToList() {
        history.back()
        // this.router.navigate(['/transaction/fin-01-invoice/FIN-01-list'])
    }

    public downloadPdf() {
        var data = document.getElementById("pdfImage");
        html2canvas(data, { scale: 1.5, scrollY: -window.scrollY }).then(canvas => {
            this.logo = canvas.toDataURL('image/png')
            this.generatePDF()
        });

    }



    generatePDF() {
        // 

        if (this.sumOfPenalty == undefined) {
            this.sumOfPenalty = 0
        }
        if (this.invoice.status == 'FOR APPROVAL TO MOH') {
            if (this.invoiceDate != '') {
                this.invoiceDate = dateFormat(this.invoiceDate, 'dd-mm-yyyy')
                this.lastDate = this.invoiceDate
            }
        }

        let logo = this.logo

        let doc = this.docRef.split("-").slice(0, -1).join("-")
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
            pageMargins: [30, 180, 30, 30],
            header: function (currentPage) {

                if (currentPage == 1) {
                    let header = [
                        {
                            image: logo,
                            fit: [275, 125],
                            alignment: 'center',
                            margin: [0, 0, 0, 10]
                        }
                    ]
                    return header;
                }
                else {
                    let header = [
                        {
                            image: logo,
                            fit: [275, 125],
                            alignment: 'center',
                            margin: [0, 0, 0, 10]
                        },
                        {
                            columns: [
                                {},
                                {},
                                {},
                                {
                                    text: 'DOC REF : ' + doc, bold: true,
                                    // text: doc, alignment: 'right', bold: true 
                                }
                            ]

                        }
                    ]
                    return header;
                }
            },
            footer: function (currentPage, pageCount) {
                let footerText = [
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
                                text: 'SUMMARY OF MAINTENANCE CHARGES',
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
                                //{ text: 'INVOICE TO:', bold: true },
                                {
                                    table: {
                                        body: [
                                            [{
                                                table: {
                                                    widths: ['*', '*', '*', '*', '*'],
                                                    body: [
                                                        [{ text: 'PKD/PPD', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.clinicTypeCode }, { text: this.districtName, margin: [-35, 0, 0, 0] }, {}],
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
                                { text: 'FORM FIN 03', bold: true, alignment: 'right' },
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
                        headerRows: 5,
                        widths: ['*', 'auto', 'auto', 'auto', 'auto', 'auto', 'auto', 'auto', 'auto', 'auto'],
                        body: [
                            [{ text: 'CLINIC NAME', bold: true, rowSpan: 5, alignment: 'center', margin: [50, 50, 50, 0] }, { text: 'CLINIC CODE', rowSpan: 5, bold: true, alignment: 'center', margin: [0, 50, 0, 0] },
                            { text: ' MAINTENANCE CHARGES', bold: true, colSpan: 7, alignment: 'center' }, '', '', '', '', '', '', { text: 'NET CHARGES PAYABLE', bold: true, rowSpan: 3, alignment: 'center', margin: [0, 25, 0, 0] }],
                            ['', '', { text: ' FORM FIN 08', bold: true, colSpan: 5, alignment: 'center' }, '', '', '', '', { text: ' FORM FIN 09', bold: true, alignment: 'center' },
                                { text: ' ', bold: true, alignment: 'center' }, ''],
                            ['', '', { text: ' NEW BE (NBE)', bold: true, colSpan: 2, alignment: 'center' }, '', { text: ' PURCHASED BE (PBE)', bold: true, colSpan: 2, alignment: 'center' }, '',
                                { text: ' TOTAL MAINTENANCE CHARGES', bold: true, colSpan: 1, alignment: 'center' }, { text: ' PENALTY', bold: true, colSpan: 1, alignment: 'center' },
                                { text: ' NET MAINTENANCE CHARGES', bold: true, colSpan: 1, alignment: 'center' }, ''],
                            ['', '', { text: ' No.of.Units', bold: true, alignment: 'center' }, { text: ' RM', bold: true, alignment: 'center' }, { text: ' No.of.Units', bold: true, alignment: 'center' }, { text: ' RM', bold: true, alignment: 'center' },
                                { text: ' RM', bold: true, alignment: 'center' }, { text: ' RM', bold: true, alignment: 'center' },
                                { text: ' RM', bold: true, alignment: 'center' }, { text: ' RM', bold: true, alignment: 'center' }],
                            ['', '', { text: ' ', bold: true, alignment: 'center' }, { text: ' (B)', bold: true, alignment: 'center' }, { text: ' ', bold: true, alignment: 'center' }, { text: ' (C)', bold: true, alignment: 'center' },
                                { text: ' (D)=(B)+(C)', bold: true, alignment: 'center' }, { text: ' (E) ', bold: true, alignment: 'center' },
                                { text: ' (F)=(D)-(E)', bold: true, alignment: 'center' }, { text: '(G)=(F)', bold: true, alignment: 'center' }],

                            ...this.clinicDatas.map(data => {

                                if (data.fin09PenaltyCharge == 0) {
                                    data.fin09PenaltyCharges = "Not Finalised"
                                    return [{ text: data.clinicName, alignment: 'left' }, { text: data.clinicCode }, { text: data.fin08bEquipmentCount, alignment: 'center' },
                                    { text: formatNumber(data.fin08bMaintenanceCharge, 'en', '1.2-2'), alignment: 'right' }, { text: data.fin08cEquipmentCount, alignment: 'center' },
                                    { text: formatNumber(data.fin08cMaintenanceCharge, 'en', '1.2-2'), alignment: 'right' }, { text: formatNumber(data.fin08MaintenanceCharge, 'en', '1.2-2'), alignment: 'right' },
                                    { text: data.fin09PenaltyCharges, alignment: 'center' }, { text: formatNumber(data.netMaintenanceCharge, 'en', '1.2-2'), alignment: 'right' },
                                    { text: formatNumber(data.netMaintenanceCharge, 'en', '1.2-2'), alignment: 'right' }]
                                }
                                else {
                                    return [{ text: data.clinicName, alignment: 'left' }, { text: data.clinicCode }, { text: data.fin08bEquipmentCount, alignment: 'center' },
                                    { text: formatNumber(data.fin08bMaintenanceCharge, 'en', '1.2-2'), alignment: 'right' }, { text: data.fin08cEquipmentCount, alignment: 'center' },
                                    { text: formatNumber(data.fin08cMaintenanceCharge, 'en', '1.2-2'), alignment: 'right' }, { text: formatNumber(data.fin08MaintenanceCharge, 'en', '1.2-2'), alignment: 'right' },
                                    { text: formatNumber(data.fin09PenaltyCharge, 'en', '1.2-2'), alignment: 'right' }, { text: formatNumber(data.netMaintenanceCharge, 'en', '1.2-2'), alignment: 'right' },
                                    { text: formatNumber(data.netMaintenanceCharge, 'en', '1.2-2'), alignment: 'right' }]
                                }

                            }),
                            // ['\n', '\n', '\n', '\n', '\n', '\n', '\n', '\n', '\n', '\n'],
                            // ['\n', '\n', '\n', '\n', '\n', '\n', '\n', '\n', '\n', '\n'],
                            // ['\n', '\n', '\n', '\n', '\n', '\n', '\n', '\n', '\n', '\n'],

                            [{ text: 'TOTAL AMOUNT', bold: true, alignment: 'left', colSpan: 2 }, '', { text: '', bold: true }, { text: formatNumber(this.sumOfNewBEEquipmentMaintananceCharges, 'en', '1.2-2'), bold: true, alignment: 'right' },
                            { text: '', bold: true }, { text: formatNumber(this.sumOfPurchasedEquipmentMaintenanceCharges, 'en', '1.2-2'), bold: true, alignment: 'right' },
                            { text: formatNumber(this.sumOfTotalMaintenanceCharges, 'en', '1.2-2'), bold: true, alignment: 'right' }, { text: formatNumber(this.sumOfPenalty, 'en', '1.2-2'), bold: true, alignment: 'right' },
                            { text: formatNumber(this.netMaintenanceCharges, 'en', '1.2-2'), bold: true, alignment: 'right' }, { text: formatNumber(this.netMaintenanceCharges, 'en', '1.2-2'), bold: true, alignment: 'right' }]


                        ]
                    }
                },
                {
                    text: '\n'
                },
                {
                    columns: [
                        {
                            width: '50%',
                            stack: [
                                {
                                    table: {
                                        body: [
                                            [{
                                                table: {
                                                    widths: ['*', 'auto'],
                                                    body: [
                                                        [{ text: 'Summary :', bold: true, alignment: 'left', }, { text: 'RM', bold: true, alignment: 'right' },],
                                                        [{ text: 'TOTAL MAINTENANCE CHARGES', bold: true, alignment: 'left', }, { text: formatNumber(this.sumOfTotalMaintenanceCharges, 'en', '1.2-2'), bold: true, alignment: 'right' },],
                                                        [{ text: 'GROSS CHARGES', bold: true, alignment: 'left', }, { text: formatNumber(this.netMaintenanceCharges, 'en', '1.2-2'), bold: true, alignment: 'right' }],
                                                        [{ text: 'PENALTY', bold: true, alignment: 'left', }, { text: formatNumber(this.sumOfPenalty, 'en', '1.2-2'), bold: true, alignment: 'right' },],
                                                        [{ text: 'NET CHARGES PAYABLE', bold: true, alignment: 'left', }, { text: formatNumber(this.netMaintenanceCharges, 'en', '1.2-2'), bold: true, alignment: 'right' }]
                                                    ],
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
                                { text: 'Verified By,', bold: true },
                                { text: '\n', bold: true },
                                { text: 'PKD/PPD', bold: true },
                                { text: 'NAME                : ', bold: true },
                                { text: 'DESIGNATION  : ', bold: true },
                                { text: 'DATE                  : ', bold: true }
                                // { text: 'NAME                : ' + (this.invoice.approval2UserName == null ? '' : this.invoice.approval2UserName), bold: true },
                                // { text: 'DESIGNATION : ' + (this.invoice.approval2UserDesignation == null ? '' : this.invoice.approval2UserDesignation), bold: true },
                                // { text: 'DATE                 : ' + (this.invoice.approval2Date == null ? '' : dateFormat(this.invoice.approval2Date, 'dd-mm-yyyy')), bold: true }
                            ],
                        }
                    ]
                },
            ],
            pageSize: 'A4',
            pageOrientation: 'landscape',
            defaultStyle: {
                font: pdfMake.vfs.Roboto,
                fontSize: 10,
                lineHeight: 1
            },
            preserveSpace: {
                preserveLeadingSpaces: true
            }
        };

        pdfMake.createPdf(docDefinition).download('Supporting Document ' + this.docRef + '.pdf');
    }
}