import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { PagesService} from "../../../pages/home/pages.service";
import { UserManagementService } from "../../../pages/home/user-management/user-management.service";
@Component({
    selector: 'dashboard-progress',
    templateUrl: './dashboard-progress.component.html',

    encapsulation: ViewEncapsulation.None
})
export class DashboardProgress implements OnInit {
    public selectedDate: any;
    public invoiceGenerationFlag:boolean=false;    
    public revenueAnalysisFlag:boolean=false;
    public accountReceivablesFlag:boolean=false;
    public retentionFlag:boolean=false;
    public unadjustedPenalitiesFlag:boolean=false;
    public backlogInvoicesFlag:boolean=false;
    public eisFlag:boolean=false;
    constructor(private userManagementService: UserManagementService,private pagesService:PagesService) { }

    ngOnInit() {

        this.userManagementService.getAllUserGroupMenuMappingByGroupId(this.pagesService.getMenuMappingPriorityGroupId()).subscribe((datas) => {
          datas.forEach((data=>{
                   
            if(data.menuName == 'Revenue Analysis'){
                this.revenueAnalysisFlag = true;
           
            }      
            if(data.menuName == 'Account Receivables' )        
            {
                this.accountReceivablesFlag = true;
            }
            if( data.menuName == 'Retention'){
                this.retentionFlag = true;
            }
            if(data.menuName == 'Unadjusted Penalities'){
                this.unadjustedPenalitiesFlag = true;
            }
            if( data.menuName == 'Backlog Invoices'){
                this.backlogInvoicesFlag = true;
            }
            if (data.menuName == 'EIS'){
                this.eisFlag = true;
            }
            if( data.menuName == 'Invoice Generation'){
                this.invoiceGenerationFlag = true;
            }
        }))
      
        })

    }

}