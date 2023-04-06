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
    circleCode:string;
    month: string;
    monthName: string;
    year: string;
    invoiceDate: string;
    invoiceDateDisplay: string;
    retentionAmount: number;
    netRetentionAmount:number;
    outstandingAmount: number;
    paymentStatus: string;
    quater: string;
    invoiceBaseValue: number;
    sst: number;
    totalInvoiceValue: number;
    totalInvoiceValueWoRetention: number;

    paymentDate: string;
    paymentDateDisplay: string;
    paymentReceived: number;
    paymentRefNo: string;

    desc: string;

    invoicePaymentHistory: Array<InvoicePaymentHistory>



}