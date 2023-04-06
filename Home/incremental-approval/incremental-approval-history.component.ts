import { Component, OnInit,OnDestroy,ViewChild } from '@angular/core';
import { Router } from "@angular/router";
import { DataTableDirective } from 'angular-datatables';
import { Observable } from 'rxjs/Rx';
import { Subject } from 'rxjs';
import 'sweetalert2/src/sweetalert2.scss';
import Swal from 'sweetalert2';
import { IncrementalService } from "./incremental.service"


@Component({
  selector: 'incremental-approval-history',
  templateUrl: './incremental-approval-history.component.html'
})
export class IncrementalApprovalHistoryComponent implements OnInit {
  dtOptions: DataTables.Settings = {
    pagingType: 'full_numbers',
    pageLength: 10
  };
  dtTrigger: Subject<any> = new Subject();
  @ViewChild(DataTableDirective, {static: false})
  dtElement: DataTableDirective;
  public loading :boolean=false;
    approvalReason: any;
    rejectionReason: any;
  public  flag: string;
  stateFilterDatas: any[];
  constructor( private router: Router, private incrementalService: IncrementalService) { }

  public invoiceDatas: any;
  public buttonEnable: Boolean;
  public createData: any;
  public invoiceType: string;
  public cardTitle: string;
  
  public filterDatas: any;
  public isFileApproved: Boolean;
  
  public comment: string;

  ngOnInit() {
      this.filterDatas = [];
    //   this.fin01Service.fetchDataForFin02aCreateList().subscribe(x => {
        //   this.invoiceDatas = x;
        //   this.invoiceDatas.forEach(element => {
        //       this.filterDatas.push(element);
        //   });
        this.filterDatas = [{month:"7",year:"2020",
        stateName:"JOHOR", districtName:"BATU",fileName:"FILE-1",invoiceType:"FIN02", 
        fileLink:"https://file-examples-com.github.io/uploads/2017/02/file_example_XLS_50.xls",  
        fileLink3:"https://www.contextures.com/SampleData.zip", comment:"Arithmetic-Error-rejected",
        fileLink2:"https://www.esa.int/var/esa/storage/images/esa_multimedia/images/2017/07/vesuvius_on_fire/17064023-1-eng-GB/Vesuvius_on_fire_focuson.gif"
         },
        {month:"8",year:"2020",
        stateName:"PULAU", districtName:"SPS",fileName:"FILE-2",invoiceType:"FIN02", comment:"Error-Corrupted-File",
        fileLink:"https://file-examples-com.github.io/uploads/2017/02/file_example_XLS_100.xls" }]
          this.loading = false;
    //   })
      this.incrementalService.fetchStateDetails().subscribe(x => {
          this.stateFilterDatas = x;    
          this.dtTrigger.next();
      })
      this.comment = this.incrementalService.getcomment();
      
      // this.filterDatas.push(this.comment);
      
      this.flag = this.incrementalService.getflag() 
      
      this.buttonEnable = true;
      if(this.flag==="approved")
      {
        this.isFileApproved = true;
      }
      else if(this.flag==="rejected")
      {
        this.isFileApproved = false;
      }
    //   document.getElementById("data.comment").onchange = this.enableButton();
  }
//   document.getElementById("data.comment").onchange = function() {myFunction()};
//  function myFunction()
//   {

//   }
//   enableButton(comment)
//   { 
//       if( comment.lenght > 3 )
//       {
//           this.buttonEnable = true;
//       }

}