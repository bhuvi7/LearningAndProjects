import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subject } from 'rxjs';
import { DataTableDirective } from 'angular-datatables';
import { Fin04InvoiceService } from '../../fin-04-invoice-service';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
// import { toWords } from 'number-to-words';

@Component({
    selector: 'fin-10b-approved-list',
    templateUrl: './fin-10b-approved-list.component.html',
    providers: [NgbModalConfig, NgbModal]
})
export class Fin10bApprovedListComponent implements OnInit {

    dtOptions: DataTables.Settings = {
        pagingType: 'full_numbers',
        pageLength: 10
    };
    dtTrigger: Subject<any> = new Subject();
    @ViewChild(DataTableDirective, { static: false })
    dtElement: DataTableDirective;

    public propsData = window.history.state.data ? window.history.state.data[0].clinicCode : 0
    public pageType = this.route.params['_value']['_type'];
    public listDatas: any;
    public buttonEnableBoolean: Boolean;
    public selectedValue: Boolean;
    public createData: any;
    public modalBodyContent;
    public approveData;
    public filterDatas: any;
    public stateFilter: string = "";
    public districtFilter: string = "";
    public stateFilterDatas: Array<any>;
    public districtFilterDatas: Array<any>;
    public loading: Boolean = false;
    public invoiceDatas: any;
    public invoiceFilterDatas: any;
    public userId: Number;



    constructor(private router: Router, private route: ActivatedRoute, private fin04Service: Fin04InvoiceService,
        config: NgbModalConfig, private modalService: NgbModal) {
        config.backdrop = 'static';
        config.keyboard = false;
    }

    ngOnInit() {

    }
    ngAfterViewInit(): void {
        this.dtTrigger.next();
    }

    ngOnDestroy(): void {
        this.dtTrigger.unsubscribe();
    }


    selectedIds(selectedIds) {
        this.loading = true
        this.fin04Service.fetchDataForFin10bApprovedList('APPROVED BY MOH', selectedIds).subscribe(fin => {

            if (fin.length == 0) {
                Swal.fire('', 'All the clinics under this state/district is Approved,check Approved process', 'info');
                this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
                    dtInstance.destroy();
                    this.dtTrigger.next();
                    this.filterDatas = [];
                });
                this.loading = false
            } else {
                fin.forEach((element, i) => {

                    if (element.stateName == 'SARAWAK') {
                        if (element.clinicTypeId == 1) {
                            element.clinicTypeCode = 'PKB'
                        }
                        if (element.clinicTypeId == 2) {
                            element.clinicTypeCode = 'PPB'
                        }
                    }
                    if (fin.length - 1 == i) {
                        this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
                            dtInstance.destroy();
                            this.dtTrigger.next();
                            this.filterDatas = fin;
                        })
                        this.loading = false
                    }
                });
            }
            // this.loading = false

        })
        // } 
        // }
    }

    navigateToApproval(id) {
        this.router.navigateByUrl('transaction/fin-04-invoice/fin-10b-approve/' + id);
    }
}