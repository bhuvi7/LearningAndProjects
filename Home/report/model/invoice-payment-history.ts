export class InvoicePaymentHistory {
    id: number;
    invoiceNo: string;
    paymentMode: string;
    paymentReceived: number;
    paymentRefNo: string;
    paymentDate: Date;
    paymentDateDisplay: Date;
    transactionRefNo: string;
    totalInvoiceBaseAmt;
    totalSstAmt;
    totalInvoiceAmt;
    totalRetentionAmt;
    totalOutstandingAmt;
    sst;
    retentionAmount;
    outstandingAmount;
    totalInvoiceValue;
    invoiceBaseValue;
    districtName;
    stateName;
    updatedName: string;
    uniqueKey: any;
    fin09InvoiceNo: any;
    createdDateFormat:any;
    createdDate: Date;


}