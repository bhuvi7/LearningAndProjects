import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { Fin04InvoiceService } from '../fin-04-invoice-service';
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
    selector: 'fin-04-approve',
    templateUrl: './fin-04-approve.component.html',
    styleUrls: ['./fin-04-approve.component.scss'],
    providers: [NgbModalConfig, NgbModal]
})

export class Fin04ApproveComponent implements OnInit {

    public invoice: any;
    public clinicTypeCode: any;
    public districtName;
    public monthYear;
    public docRef = "";
    public date;
    public invoiceDate;
    public lastDate;
    public clinicDatas: any;
    public invoiceTotalValue = 0.0;
    public sstPercentage = 0;
    public sstAmount = 0.0;
    public totalInvoiceAmount = 0.0;
    public invoiceTotalValueInWords: String;
    public invoiceTotalInvoiceAmountWholeValueInWords: String;
    public invoiceTotalInvoiceAmountDecimalValueInWords: String;
    public showSaveSubmitButton;
    public showButtons = false;
    public isApproved = false;
    public modalBodyContent;
    public loading: Boolean = true;
    public logo = '';
    public totalinvoiceinwords:string;
    public invoiceTotalValueInWord: string;
    public districtAddress: string;
    public districtId: number;
    public district;
    public clinicAddress: string='';
    public clinicTypeId: number;
    public storingPpd:any;
    public storingPkd:any;
    public swap:string='';
    public invoiceFin10bDate: any;
    public fin10bdate: any;

    constructor(private router: Router, private route: ActivatedRoute, private fin04Service: Fin04InvoiceService,
        config: NgbModalConfig, private modalService: NgbModal) {
        config.backdrop = 'static';
        config.keyboard = false;
    }

    ngOnInit() {
        if (this.route.params['_value']['_id'] != "undefined") {
            this.route.params.switchMap((par: Params) => this.fin04Service.fetchDataForFin04Approve(par['_id'])).subscribe(x => {
                this.fin04Service.findInvoiceSstPercentage(5).subscribe(y => {
                    this.sstPercentage = y;
                })
                this.invoice = x;
                //mine
                // x.foreach(x=>{
                    
                // })
              if(  x.clinicTypeCode=='PPD'){
                this.storingPpd =x

                this.swap='1'
              }
              else if(  x.clinicTypeCode=='PKD'){
                this.storingPkd=x
                this.swap='2'
            }
                this.clinicDatas = x.fin10b;
                // console.log(JSON.stringify(this.clinicDatas));
                // console.log(JSON.stringify(this.clinicDatas));
                this.clinicDatas.forEach(element => {
                    element.dateDisplay = dateFormat(new Date(), 'dd-mm-yyyy')
                });
                this.clinicTypeCode = x.clinicTypeCode;
                this.clinicTypeId = x.clinicTypeId
                this.districtName = x.districtName;
                this.districtId = x.districtId
                this.monthYear = x.month + '/' + x.year;
                this.date = dateFormat(x.date, 'dd-mm-yyyy')
                this.invoiceDate = this.date
                this.invoiceTotalValue = x.invoiceBaseValue
                this.sstAmount = x.sst;
                this.totalInvoiceAmount = x.totalInvoiceValue;
                this.docRef = this.invoice.status == 'CREATED' || this.invoice.status == 'SAVED' ? "" : this.invoice.invoiceNo;
                
                this.showButtons = this.invoice.status == 'APPROVED BY MOH' ? false : true;
                this.isApproved = this.invoice.status == 'APPROVED BY MOH' ? true : false;
                this.showSaveSubmitButton = this.invoice.status == 'IN INTERNAL APPROVAL' || this.invoice.status == 'FOR APPROVAL TO MOH' ? false : true;
                if (this.totalInvoiceAmount % 1 == 0) {
                    this.invoiceTotalValueInWords = toWords(this.totalInvoiceAmount).toUpperCase();
                } else {
                    let totalAmountInString = this.totalInvoiceAmount.toString()
                    let splitValue = totalAmountInString.split('.')
                    this.invoiceTotalInvoiceAmountWholeValueInWords = toWords(splitValue[0]).toUpperCase();
                    this.invoiceTotalInvoiceAmountDecimalValueInWords = toWords(splitValue[1]).toUpperCase();
                    this.invoiceTotalValueInWords = this.invoiceTotalInvoiceAmountWholeValueInWords + ' AND CENTS ' + this.invoiceTotalInvoiceAmountDecimalValueInWords
                } 

                this.fin04Service.fetchDateFrom10b(x.id).subscribe((x)=> {
                    this.invoiceFin10bDate= x;
                    this.fin10bdate = this.invoiceFin10bDate[0].date
                    this.fin10bdate = dateFormat(this.fin10bdate, 'dd-mm-yyyy')
                     console.log('this.invoice.2: ', this.fin10bdate);
                     console.log('this.invoice.1: ', this.invoiceFin10bDate);
                    // console.log('this.invoice.3: ', fin10bdate);
                
                })

                this.fin04Service.fetchClinicsDetails(this.districtId,this.clinicTypeId).subscribe(x => {
                    this.district = x
                    	
                    if(this.swap=='1'){

                        this.district.forEach(x=>{
                          if(  x.districtName==this.storingPpd.districtName){

                            this.clinicAddress= x.address 
                           
                          }
                        })	
                    }
                    else if (this.swap=='2'){
                        this.district.forEach(x=>{
                           if( x.districtName==this.storingPkd.districtName){
                            this.clinicAddress= x.address 
                           
                           }
                        })
                    }
                   
              
                })
            })
            this.loading = false;
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
        delete this.invoice.fin10b;
        this.invoice.status = "SAVED";
        this.fin04Service.updateFin04Status(this.invoice).subscribe(x => {
            Swal.fire('', 'Fin 04 saved successfully!!!', 'success');
        })
    }

    submitStatus() {
        delete this.invoice.fin10b;
        this.invoice.status = 'IN INTERNAL APPROVAL'
        this.fin04Service.updateFin04Status(this.invoice).subscribe(x => {
            Swal.fire('', 'Fin 04 submitted successfully!!!', 'success');
            history.back()
        })
    }

    updateStatus() {
        if (this.invoice.status == 'IN INTERNAL APPROVAL') { this.invoice.status = 'FOR APPROVAL TO MOH' }
        else if (this.invoice.status == 'FOR APPROVAL TO MOH') { this.invoice.status = 'APPROVED BY MOH' }
        delete this.invoice.fin10b;
        this.fin04Service.updateFin04Status(this.invoice).subscribe(x => {
            Swal.fire('', 'FIN 04 approved successfully!!!', 'success');
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
        let clinicAddress = this.clinicAddress;
        let capClinicAddress = clinicAddress.toUpperCase();

        let pdfdate = this.invoiceDate
        let pdf10bdate = this.fin10bdate
        //   let pdfdate = this.fin10bdate 
       //  console.log("this.fin10bdate",this.fin10bdate) 
        //   console.log("pdfdate",pdfdate)
        
        if (this.invoice.status == 'FOR APPROVAL TO MOH') {
            if (this.invoiceDate != '') {
                this.invoiceDate = dateFormat(this.invoiceDate, 'dd-mm-yyyy')
            }
            // else if(this.invoiceDate == '') {
            //     this.invoiceDate = ""
            // }
        }

        let logo = this.logo

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

        if(this.invoice.status == 'FOR APPROVAL TO MOH'){
            if (this.invoice.status == 'FOR APPROVAL TO MOH') {
            this.lastDate=this.invoiceDate}
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
                                    { text: 'VERIFIED BY,', bold: true,fontSize: 9 },
                                    { text: 'JURUTERA NEGERI', bold: true,fontSize: 9 },
                                    { text: 'KEMENTERIAN KESIHATAN MALAYSIA', bold: true,fontSize: 9 },
                                    { text: 'NAME & DESIGNATION :', bold: true,fontSize: 9 },
                                    { text: 'DATE :', bold: true,fontSize: 9 }
                                ], margin: [400, 0, 0, 0]
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
                    text:'SST ID : W10-1904-32000110', bold: true,fontSize: 9,
                    alignment: 'center',
                },
                {
                    table: {
                        widths: ['*'],
                        //widths: ['auto', 'auto', 'auto', 'auto', 'auto'],
                        body: [
                            [{
                                text: 'INVOICE FOR CONSTRUCTION WORKS',fontSize: 9,
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
                                text: 'FOR '+this.monthYear, bold: true,fontSize: 9,
                            },],
                        ]
                    },
                    margin:[240,0,0,10],
                    alignment: 'center',
                },
                {
                    text:'TAX INVOICES',bold: true ,fontSize:12,
                    alignment: 'center',
                },
                {
                    text: '\n'
                },
                {
                    columns: [
                        {
                            width: '55%',
                            stack: [
                                { text: 'INVOICE TO:', bold: true },
                                {
                                    table: {
                                        body: [
                                            [{
                                                table: {
                                                    widths: ['auto', 'auto', 'auto'],
                                                    body: [
                                                        [{ text: 'PKD/PPD', bold: true,fontSize: 9}, { text: ':', bold: true, alignment: 'right',fontSize: 9 }, { text: this.clinicTypeCode +' - '+ this.districtName, bold: true,fontSize: 9 }],
                                                        // [{ text: 'DISTRICT', bold: true }, { text: ':', bold: true, alignment: 'right' }, { text: this.districtName }],
                                                        [{ text: 'ADDRESS', bold: true,fontSize: 9 }, { text: ':', bold: true, alignment: 'right',fontSize: 9 }, { text: capClinicAddress,fontSize: 9 }],
                                                        [{ text: 'MONTH/YEAR', bold: true,fontSize: 9 }, { text: ':', bold: true, alignment: 'right',fontSize: 9 }, { text: this.monthYear, bold: true, alignment: 'left',fontSize: 9}]
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
                            width: '40%',
                            stack: [
                                { text: 'FORM FIN 04', bold: true,alignment:'right',fontSize: 9 },
                                {
                                    table: {
                                        widths: ['*', 'auto'],
                                        body: [
                                            [{ text: 'INVOICE REF:', bold: true,fontSize: 9 }, { text: this.docRef, alignment: 'right', bold: true,fontSize: 9 }],
                                            [{ text: 'DATE:', bold: true,fontSize: 9 }, { text: pdf10bdate, alignment: 'right', bold: true,fontSize: 9 }]
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
                            [{ text: 'CLINIC DETAILS', colSpan: 2, bold: true, alignment: 'center',fontSize: 9 }, '', '', { text: '', colSpan: 2,fontSize: 9 }, "", { text: "",fontSize: 9 }],
                            [{ text: 'CLINIC NAME', bold: true, alignment: 'center',fontSize: 9 }, { text: 'CLINIC CODE', bold: true, alignment: 'center',fontSize: 9 }, { text: 'ASSET TYPE', bold: true, alignment: 'center',fontSize: 9 },
                            { text: 'CONSTRUCTION WORKS COST PER FIN-10B (RM)', bold: true, alignment: 'center',fontSize: 9 }, { text: 'FORM FIN-10B REF', bold: true, alignment: 'center',fontSize: 9 },
                            { text: 'CERT OF ACCEPTANCE REF', bold: true, alignment: 'center',fontSize: 9 }],
                            ...this.clinicDatas.map(data => {
                                return [{ text: data.clinic.clinicName, alignment: 'left',fontSize: 9 }, { text: data.clinic.clinicCode, alignment: 'center',fontSize: 9 },
                                { text: data.assetName, alignment: 'center',fontSize: 9 }, { text: formatNumber(data.totalAmount, 'en', '1.2-2'), alignment: 'right',fontSize: 9 },
                                { text: data.code, alignment: 'center',fontSize: 9 }, { text: data.certOfAcceptanceRef, alignment: 'center',fontSize: 9 }]
                            }),
                            ['\n', '\n', '\n', '\n', '\n', '\n'],
                            ['\n', '\n', '\n', '\n', '\n', '\n'],
                            [{ text: 'TOTAL INVOICE AMOUNT (Excluding SST)', colSpan: 2, bold: true,fontSize: 9 }, '', { text: '' }, { text: formatNumber(this.invoiceTotalValue, 'en', '1.2-2'), bold: true, alignment: 'right',fontSize: 9 }, { text: '' }, { text: '' }],
                            [{ text: 'SST Amount (' + this.sstPercentage + '%)', colSpan: 2, bold: true,fontSize: 9 }, '', { text: '' }, { text: formatNumber(this.sstAmount, 'en', '1.2-2'), bold: true, alignment: 'right',fontSize: 9 }, { text: '' }, { text: '' }],
                            [{ text: 'TOTAL INVOICE AMOUNT (Inclusive SST)', colSpan: 2, bold: true,fontSize: 9 }, '', { text: '' }, { text: formatNumber(this.totalInvoiceAmount, 'en', '1.2-2'), bold: true, alignment: 'right',fontSize: 9 }, { text: '' }, { text: '' }],

                        ]
                    }
                },
                {
                    text: '(Ringgit Malaysia: ' + this.invoiceTotalValueInWord + ' ONLY)', bold: true, italics: true,fontSize: 9
                },
                {
                    text: '\n'
                },
                {
                    columns: [
                        {
                            width: '70%',
                            table: {
                                widths: ['*', '*'],
                                body: [
                                    [{ text: 'REMITTANCE DETAILS', bold: true,fontSize: 9 }, ''],
                                    [{ text: 'Payee :', bold: true, italics: true,fontSize: 9 }, { text: 'QUANTUM MEDICAL SOLUTIONS SDN BHD', bold: true,fontSize: 9 }],
                                    [{ text: 'Amount Due:', bold: true, italics: true,fontSize: 9 }, { text: 'RM ' + formatNumber(this.totalInvoiceAmount, 'en', '1.2-2'), bold: true,fontSize: 9 }],
                                    [{ text: 'Bank Details:', bold: true, italics: true,fontSize: 9 }, { text: 'RHB Bank Berhad', bold: true,fontSize: 9 }],
                                    [{ text: 'Bank Account No:', bold: true, italics: true,fontSize: 9 }, { text: '21219800038867', bold: true,fontSize: 9 }],
                                ]
                            },
                            layout: 'lightHorizontalLines'
                        },
                        // {
                        //     width: '30%',
                        //         stack: [
                        //             { text: 'VERIFIED BY,', bold: true,fontSize: 9 },
                        //             { text: 'JURUTERA NEGERI', bold: true,fontSize: 9 },
                        //             { text: 'KEMENTERIAN KESIHATAN MALAYSIA', bold: true,fontSize: 9 },
                        //             { text: 'NAME & DESIGNATION :', bold: true,fontSize: 9 },
                        //             { text: 'DATE :', bold: true,fontSize: 9 }
                        //         ], margin: [0, 0, 30, 0]
                        // }
                    ]
                }
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

        pdfMake.createPdf(docDefinition).download('Invoice ' + this.docRef + '.pdf');
    }
}

function id(id: any) {
    throw new Error('Function not implemented.');
}
