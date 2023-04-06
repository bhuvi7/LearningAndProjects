export class InvoiceType {
    public id: number;
    public invoiceTypeName: string;
    public invoiceTypeCode: string;
    public retentionAvailable: string = 'N';
    public retentionPercentage: number = 0;
    public sstIncluded: string = 'N';
    public sstPercentage: number = 0;
    public penaltyAdjustmentApplicable: string = 'N';
    public isActive: string = 'N';
}