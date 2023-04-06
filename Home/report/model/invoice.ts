import { InvoicePaymentHistory } from './invoice-payment-history';

export class Invoice {
    id: string;
    name: string;
    invoiceNo: string;
    invoiceTypeId: number;
    invoiceTypeName: string;
    stateId: number;
    stateName: string;
    stateCode: string;
    districtId: number;
    districtName: string;
    districtCode: string;
    clinicTypeName: string;
    clinicTypeCode: string;
    month: string;
    year: string;
    invoiceDate: string;
    invoiceDateDisplay: string;
    retentionAmount: number;
    outstandingAmount: number;
    paymentStatus: string;
    quater: string;
    totalInvoiceValue: number;
    paymentReceived: any;
    paymentRefNo: any;
    paymentDate: any;
    paymentDateDisplay: any;
    age: number;

    invoiceBaseValue: number;
    sst: number;
    totalInvoiceValueWoRetention: number;
    debitNoteEntry:any;
    invoicePaymentHistory: Array<InvoicePaymentHistory>



}