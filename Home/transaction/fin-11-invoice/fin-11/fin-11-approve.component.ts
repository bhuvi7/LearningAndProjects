import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { Fin11InvoiceService } from '../fin-11-invoice-service';
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
    selector: 'fin-11-approve',
    templateUrl: 'fin-11-approve.component.html',
    styleUrls: ['fin-11-approve.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})

export class Fin11ApproveComponent implements OnInit {

    public enableSaveButton: Boolean = window.history.state.approvalPage ? true : false;

    public fin11: any;
    public clinicTypeCode: any;
    public districtName;
    public districtId;
    public districtAddress = "N/A";
    public clinicName;
    public clinicAddress;
    public monthYear;
    public grossCharges;
    public docRef = "";
    public date;
    public invoiceDate;
    public lastDate;
    public typeOfExpenses;
    public reference;
    public concessionElements;
    public clinicDataDummy;
    public clinicDatas;
    public sstAmount = 0.0;
    public totalInvoiceAmount = 0.0;
    public invoiceTotalValueInWords: String;
    public invoiceTotalWholeValueInWords: String;
    public invoiceTotalDecimalValueInWords: String;
    public showSaveSubmitButton;
    public isApproved = false;
    public showButtons = false;
    public modalBodyContent;
    public loading: Boolean = true;
    public logo = ''
    public totalinvoiceinwords:string;
    public invoiceTotalValueInWord: string;

    constructor(private router: Router, private route: ActivatedRoute, private fin11Service: Fin11InvoiceService,
        config: NgbModalConfig, private modalService: NgbModal) {
        config.backdrop = 'static';
        config.keyboard = false;
    }

    ngOnInit() {
        if (this.route.params['_value']['_id'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.fin11Service.fetchDataForFin11Approve(par['_id'])).subscribe(x => {
                this.fin11 = x;
                //
                //
                this.districtId = x.districtId
                this.clinicDataDummy = x.fin11ConcessionElements;
                this.clinicDatas = [{}]

                for (var i = 0; i < this.clinicDataDummy.length; i++) {
                    if (this.clinicDataDummy[i].districtId === this.districtId) {
                        this.clinicDatas[0] = this.clinicDataDummy[i];
                    }
                }

                //
                // this.clinicDatas.forEach(element => {
                //     element.dateDisplay = dateFormat(new Date(), 'dd-mm-yyyy')
                // });
                // this.clinicDatas.map(data => {
                //     this.invoiceTotalValue += data.totalAmount

                // })
                this.clinicTypeCode = x.clinicTypeCode;
                this.districtName = x.districtName;
                this.concessionElements = this.clinicDatas[0].concessionElements;
                //
                this.monthYear = x.month + '/' + x.year;
                this.date = dateFormat(x.invoiceDate, 'dd-mm-yyyy')
                this.invoiceDate = this.date
                this.totalInvoiceAmount = x.totalInvoiceValue;
                this.docRef = this.fin11.status == 'CREATED' || this.fin11.status == 'SAVED' ? "" : this.fin11.invoiceNo;
                this.isApproved = this.fin11.status == 'APPROVED BY MOH' ? true : false;
                this.showButtons = this.fin11.status == 'APPROVED BY MOH' ? false : true;
                this.showSaveSubmitButton = this.fin11.status == 'IN INTERNAL APPROVAL' || this.fin11.status == 'FOR APPROVAL TO MOH' ? false : true;
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
        delete this.fin11.fin11ConcessionElements;
        this.fin11.status = "SAVED";
        this.fin11Service.updateFin11Status(this.fin11).subscribe(x => {
            Swal.fire('', 'FIN 11 Invoice saved successfully!!!', 'success');
        })
    }

    submitStatus() {
        delete this.fin11.fin11ConcessionElements;
        this.fin11.status = 'IN INTERNAL APPROVAL'
        this.fin11Service.updateFin11Status(this.fin11).subscribe(x => {
            Swal.fire('', 'FIN 11 Invoice submitted successfully!!!', 'success');
            history.back()

        })
    }

    updateStatus() {
        if (this.fin11.status == 'IN INTERNAL APPROVAL') { this.fin11.status = 'FOR APPROVAL TO MOH' }
        else if (this.fin11.status == 'FOR APPROVAL TO MOH') { this.fin11.status = 'APPROVED BY MOH' }
        delete this.fin11.fin11ConcessionElements;
        this.fin11Service.updateFin11Status(this.fin11).subscribe(x => {
            Swal.fire('', 'FIN 11 Invoice approved successfully!!!', 'success');
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

        if (this.fin11.status == 'FOR APPROVAL TO MOH') { 
            if (this.invoiceDate != '') {
            this.invoiceDate = dateFormat(this.invoiceDate, 'dd-mm-yyyy') 
        this.lastDate=this.invoiceDate} }

            let mySentence = this.invoiceTotalValueInWords;
            let words = mySentence.split(" ");
   
           for (let i = 0; i < words.length; i++) {
              words[i] = words[i].toLowerCase();
           }
   
           this.totalinvoiceinwords=words.join(" ");
   
           let mySentenc = this.totalinvoiceinwords;
            let word = mySentenc.split(" ");
   
           for (let i = 0; i < word.length; i++) {
              word[i] = word[i][0].toUpperCase() + word[i].substr(1);
           }
   
           this.invoiceTotalValueInWord=word.join(" ");

           let spa = this.invoiceTotalValueInWord.split("-");
        
        for (let i = 0; i < spa.length; i++) {
            spa[i] = spa[i][0].toUpperCase() + spa[i].substr(1);
         }
        this.invoiceTotalValueInWord=spa.join("-");

        if(this.fin11.status == 'FOR APPROVAL TO MOH'){
            if (this.invoiceDate != '') {
            this.lastDate=this.invoiceDate
        }
        }
        else if(this.invoiceDate == '')
        {
            this.lastDate=""
        }
        else{
            let date = this.monthYear.split('')
        var lastday = function(y,m){
            return  new Date(y, m +1, 0).getDate();
            }
            this.lastDate = lastday(date[0],date[1])+'/'+this.monthYear
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
                    text:'SST ID : W10-1904-32000110',
                    alignment: 'center',fontSize:12
                },
                {
                    table: {
                        widths: ['*'],
                        //widths: ['auto', 'auto', 'auto', 'auto', 'auto'],
                        body: [
                            [{
                                text: 'INVOICE FOR REIMBURSEMENT OF CONCESSION ELEMENTS',
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
                            width: '40%',
                            stack: [
                                { text: 'INVOICE TO:', bold: true },
                                {
                                    table: {
                                        body: [
                                            [{
                                                table: {
                                                    widths: ['*', '*', '*'],
                                                    body: [
                                                        [{ text: 'NAME', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.districtName }],
                                                        [{ text: 'MONTH/YEAR', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.monthYear }],
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
                                { text: 'FORM FIN 11', bold: true,alignment:'right' },
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
                        widths: ['*', 'auto', 'auto'],
                        body: [
                            [{ text: 'TYPE OF EXPENSES', bold: true, alignment: 'center' }, { text: 'REFERENCE', bold: true, alignment: 'center' }, { text: 'CONCESSION ELEMENTS (RM)', bold: true, alignment: 'center' }],
                            ...this.clinicDatas.map(data => {
                                return [data.typeOfExpenses, { text: data.reference, alignment: 'center' }, { text: formatNumber(data.concessionElements, 'en', '1.2-2'), alignment: 'right' }]
                            }),
                            ['\n', '\n', '\n'],
                            [{ text: 'TOTAL (RM)', colSpan: 2, bold: true }, {}, { text: formatNumber(this.totalInvoiceAmount, 'en', '1.2-2'), bold: true, alignment: 'right' }]
                        ]
                    }
                },
                {
                    text: '\n'
                },
                {
                    text: '(Ringgit Malaysia: ' + this.invoiceTotalValueInWord + ' ONLY)', bold: true, italics: true
                },
                {
                    text: '\n'
                },
                {
                    columns: [
                        {},
                        {},
                        {
                            stack: [
                                { text: 'Verifed By,', bold: true },
                                { text: 'BHG PERKHIDMATAN KEJURUTERAAN', bold: true },
                                // { text: 'NAME & DESIGNATION :', bold: true },
                                // { text: 'DATE:', bold: true }
                            ],
                        }
                    ]
                },
                {
                    columns: [
                        {
                            width: '70%',
                            table: {
                                widths: ['*', '*'],
                                body: [
                                    [{ text: 'REMITTANCE DETAILS', bold: true }, ''],
                                    [{ text: '', bold: true, italics: true }, { text: 'QUANTUM MEDICAL SOLUTIONS SDN BHD', bold: true }],
                                    [{ text: 'Amount Due:', bold: true, italics: true }, { text: formatNumber(this.totalInvoiceAmount, 'en', '1.2-2'), bold: true }],
                                    [{ text: 'Bank Details:', bold: true, italics: true }, { text: 'RHB Bank Berhad', bold: true }],
                                    [{ text: 'Bank Account No:', bold: true, italics: true }, { text: '21219800038867', bold: true }],
                                ]
                            },
                            layout: 'lightHorizontalLines'
                        },
                        {}
                    ]
                },
                {
                    text: '**  This is a system generated document from Qubics, no signature is required.', alignment: 'center'
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