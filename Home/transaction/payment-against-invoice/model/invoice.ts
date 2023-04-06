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
    clinicTypeId: number;
    month: any;
    year: string;
    status: string;
    invoiceDate: string;
    invoiceDateDisplay: string;
    retentionAmount: number;
    outstandingAmount: number;
    paymentStatus: string;
    quater: string;
    invoiceBaseValue: number;
    totalInvoiceValueWoRetention: number;
    totalInvoiceValue: number;
    sst: number;
    netAfterSst: number;
    debitNoteEntry: number;


    paymentDate: string;
    paymentReceived: number;
    paymentRefNo: string;

    createdBy: string;
    updatedBy: string;

    fin06: Array<any>;
    fin10b: Array<any>;
    fin03a: Array<any>;
    invoicePaymentHistory: Array<InvoicePaymentHistory>;

}