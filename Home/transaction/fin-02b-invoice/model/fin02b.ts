import { CimsHistoryFin02b } from './cimshistoryfin02b';

export class Fin02b {

    public code: string;
    public stateName: string;
    public districtName: string;
    public clinicTypeCode: string;
    public month: string;
    public year: string;
    public date: Date;
    public status: string;
    public invoiceBaseValue: number;
    public sst: number;
    public totalInvoiceValue: number;
    public cimsHistoryFin02b: Array<CimsHistoryFin02b>;
    public approval1UserId: Number;
    public approval2UserId: Number;
    public submittedUserName: String;
    public approval1UserName: String;
    public approval2UserName: String;
    public submittedDate: Date;
    public approval1Date: Date;
    public approval2Date: Date;
    public submittedUserDesignation: String;
    public approval1UserDesignation: String;
    public approval2UserDesignation: String;
    public districtId: number;
    public clinicTypeId: number;
    public totalEbeValue: number;


}